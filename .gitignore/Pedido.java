package br.up.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Date data;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido" , cascade = CascadeType.ALL)
	private List<Item> itens_livros;
	
	@OneToMany(mappedBy = "pedido" , cascade = CascadeType.ALL)
	private List<Item> itens_filmes;
	
	private Double valor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Item> getItens_livros() {
		return itens_livros;
	}
	public void setItens_livros(List<Item> itens_livros) {
		this.itens_livros = itens_livros;
	}
	public List<Item> getItens_filmes() {
		return itens_filmes;
	}
	public void setItens_filmes(List<Item> itens_filmes) {
		this.itens_filmes = itens_filmes;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
