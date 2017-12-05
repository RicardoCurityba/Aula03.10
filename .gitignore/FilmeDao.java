package br.up.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.up.entidades.Filme;

public class FilmeDao {
	
	static EntityManagerFactory emf;
	
	public void inserir(Filme filme) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(filme);
		em.getTransaction().commit();
	}
	
	public Filme buscar(Integer id) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Filme filme = em.find(Filme.class, id);
		return filme;
	}
	
	public void alterar(Filme filme) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(filme);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Filme> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from Filme");
		
		return new ArrayList<Filme>(q.getResultList());
	}
	
	public void excluir(Integer id) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Filme filme = em.find(Filme.class, id);
		em.remove(filme);
		em.getTransaction().commit();
	}
	
}
