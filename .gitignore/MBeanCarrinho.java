package br.up.mbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.up.dao.FilmeDao;
import br.up.dao.LivroDao;
import br.up.dao.PedidoDao;
import br.up.entidades.Filme;
import br.up.entidades.Item;
import br.up.entidades.Livro;
import br.up.entidades.Pedido;
import br.up.entidades.Usuario;

@ManagedBean(name = "mBeanCarrinho")
@SessionScoped
public class MBeanCarrinho {
	
	private Pedido p = new Pedido();
	
	private ArrayList<Item> itens_livro = new ArrayList<Item>();
	private ArrayList<Item> itens_filme = new ArrayList<Item>();

	public String salvarPedido() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Usuario u = (Usuario) req.getSession().getAttribute("usuario");
		
		p.setData(new Date());
		p.setItens_livros(itens_livro);
		p.setItens_filmes(itens_filme);
		p.setUsuario(u);
		
		Double valPedido = 0d;
		
		for (Item i : itens_livro) {
			Double valor = i.getValor() * i.getQuantidade();
			i.setValor(valor);
			i.setPedido(p);
			valPedido += valor;
		}
		
		for (Item i : itens_filme) {
			Double valor = i.getValor() * i.getQuantidade();
			i.setValor(valor);
			i.setPedido(p);
			valPedido += valor;
		}
		
		p.setValor(valPedido);
		
		new PedidoDao().inserir(p);
		
		return "pedido.jsf";
	}
	
	public void adicionar(Integer idProd, String tipo) throws IOException {
		if (tipo.equals("livro")) {
			Livro livro = new LivroDao().buscar(idProd);

			Item item = new Item();
			item.setLivro(livro);
			item.setQuantidade(1);
			item.setValor(livro.getPreco());

			itens_livro.add(item);
		} else {
			Filme filme = new FilmeDao().buscar(idProd);
			
			Item item = new Item();
			item.setFilme(filme);
			item.setQuantidade(1);
			item.setValor(filme.getPreco());
			
			itens_filme.add(item);
		}

		FacesContext.getCurrentInstance().getExternalContext().redirect("carrinho_de_compras.jsf");	

	}
	
	public void alterar(Integer quant, Item item) {
		for (Item i : itens_filme) {
			if(i.getId() == item.getId()) {
				i.setQuantidade(quant);
				i.setValor(item.getValor()*quant);
			}
		}
		for (Item i : itens_livro) {
			if(i.getId() == item.getId()) {
				i.setQuantidade(quant);
				i.setValor(item.getValor()*quant);
			}
		}
	}
	
	public void excluir(Item item) {
		itens_filme.remove(item);
		itens_livro.remove(item);
	}

	public ArrayList<Item> getItens_livro() {
		return itens_livro;
	}

	public void setItens_livro(ArrayList<Item> itens_livro) {
		this.itens_livro = itens_livro;
	}	
	
	public ArrayList<Item> getItens_filme() {
		return itens_filme;
	}
	
	public void setItens_filme(ArrayList<Item> itens_filme) {
		this.itens_filme = itens_filme;
	}

	public Pedido getP() {
		return p;
	}

	public void setP(Pedido p) {
		this.p = p;
	}
	
}
