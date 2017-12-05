package br.up.mbean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.catalina.core.ApplicationPart;

import br.up.dao.FilmeDao;
import br.up.entidades.Filme;

@ManagedBean(name="mBeanFilme")
public class MBeanFilme {
	
	private ArrayList<Filme> filmes = new ArrayList<Filme>();
	
	private Integer id;
	private String nome;
	private String genero;
	private String atores;
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
		
		Filme filme = new Filme();
		filme.setId(this.id);
		filme.setNome(nome);
		filme.setGenero(genero);
		filme.setAtores(atores);
		filme.setPreco(preco);
		filme.setAno(ano);
		filme.setDescricao(descricao);
		filme.setCaminhoImagem(caminhoImagem);
		
		if(this.id == null) {
			new FilmeDao().inserir(filme);
		}else {
			new FilmeDao().alterar(filme);
		}
		filmes = new FilmeDao().listar();
	}
	
	@PostConstruct
	public void listar() {
		filmes = new FilmeDao().listar();
		Collections.reverse(filmes);
	}
	
	public String carregarDetalhesFilme(Filme filme) {
		this.id = filme.getId();
		this.nome = filme.getNome();
		this.genero = filme.getGenero();
		this.atores = filme.getAtores();
		this.ano = filme.getAno();
		this.preco = filme.getPreco();
		this.descricao = filme.getDescricao();
		
		return "filme.jsf";
	}
	
	public void alterar(Filme filme) {
		this.id = filme.getId();
		this.nome = filme.getNome();
		this.genero = filme.getGenero();
		this.atores = filme.getAtores();
		this.preco = filme.getPreco();
		this.ano = filme.getAno();
		this.descricao = filme.getDescricao();
		
	}
	
	public void excluir(Filme filme) {
		new FilmeDao().excluir(filme.getId());
		filmes = new FilmeDao().listar();
	}
	
	public ArrayList<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(ArrayList<Filme> filmes) {
		this.filmes = filmes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAtores() {
		return atores;
	}
	public void setAtores(String atores) {
		this.atores = atores;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
}
