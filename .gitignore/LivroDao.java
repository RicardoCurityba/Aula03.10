package br.up.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.up.entidades.Livro;

public class LivroDao {
	
	public void inserir(Livro livro) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
	}
	
	public Livro buscar(Integer id) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Livro livro = em.find(Livro.class, id);
		return livro;
	}
	
	public void alterar(Livro livro) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Livro> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from Livro");
		
		return new ArrayList<Livro>(q.getResultList());
	}
	
	public void excluir(Integer id	) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Livro livro = em.find(Livro.class, id);
		em.remove(livro);
		em.getTransaction().commit();
	}
	
}
