package br.up.mbean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.catalina.core.ApplicationPart;

import br.up.dao.LivroDao;
import br.up.entidades.Livro;

@ManagedBean(name = "mBeanLivro")
@SessionScoped
public class MBeanLivro {

	private ArrayList<Livro> livros = new ArrayList<Livro>();
	private ArrayList<Livro> lista1 = new ArrayList<Livro>();
	private ArrayList<Livro> lista2 = new ArrayList<Livro>();
	private ArrayList<Livro> lista3 = new ArrayList<Livro>();

	private Integer id;
	private String titulo;
	private String autores;
	private String isbn;
	private Integer ano;
	private Double preco;
	private String descricao;
	private String caminhoImagem;

	private ApplicationPart imagem;

	public void salvar() {

		String caminhoImagem = "g:\\Java Web\\imagens\\" + imagem.getSubmittedFileName();

		try {
			byte[] bytesImagem = new byte[(int) imagem.getSize()];
			imagem.getInputStream().read(bytesImagem);
			File f = new File(caminhoImagem);
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bytesImagem);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Livro livro = new Livro();
		livro.setId(this.id);
		livro.setTitulo(titulo);
		livro.setIsbn(isbn);
		livro.setAutores(autores);
		livro.setAno(ano);
		livro.setPreco(preco);
		livro.setDescricao(descricao);
		livro.setCaminhoImagem(caminhoImagem);

		if (this.id == null) {
			new LivroDao().inserir(livro);
		} else {
			new LivroDao().alterar(livro);
		}
		livros = new LivroDao().listar();
	}

	/**
	 * Captura o conjunto com 9 dos últimos itens adicionados para enviar
	 * para a home.
	 */
	public void listar_1() {
		
		for (int i = 0; i < 3; i++) {
			if (livros.size() > i) {
				lista1.add(livros.get(i));
			}
		}
		for (int i = 3; i < 6; i++) {
			if (livros.size() > i) {
				lista2.add(livros.get(i));
			}
		}
		for (int i = 6; i < 9; i++) {
			if (livros.size() > i) {
				lista3.add(livros.get(i));
			}
		}
	}

	
	@PostConstruct
	public void listar() {
		livros = new LivroDao().listar();
		Collections.reverse(livros);
	}

	public void alterar(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.autores = livro.getAutores();
		this.isbn = livro.getIsbn();
		this.ano = livro.getAno();
		this.preco = livro.getPreco();
		this.descricao = livro.getDescricao();

	}
	
	public String carregarDetalhesLivro(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.isbn = livro.getIsbn();
		this.autores = livro.getAutores();
		this.ano = livro.getAno();
		this.preco = livro.getPreco();
		this.descricao = livro.getDescricao();

		return "livro.jsf";
	}

	public void excluir(Livro livro) {
		new LivroDao().excluir(livro.getId());
		livros = new LivroDao().listar();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Livro> getLivros() {
		return livros;
	}

	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ApplicationPart getImagem() {
		return imagem;
	}

	public void setImagem(ApplicationPart imagem) {
		this.imagem = imagem;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}
	
	public ArrayList<Livro> getLista1() {
		return lista1;
	}

	public void setLista1(ArrayList<Livro> lista1) {
		this.lista1 = lista1;
	}

	public ArrayList<Livro> getLista2() {
		return lista2;
	}

	public void setLista2(ArrayList<Livro> lista2) {
		this.lista2 = lista2;
	}

	public ArrayList<Livro> getLista3() {
		return lista3;
	}

	public void setLista3(ArrayList<Livro> lista3) {
		this.lista3 = lista3;
	}
	
}
