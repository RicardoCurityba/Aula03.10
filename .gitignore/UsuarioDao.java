package br.up.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.up.entidades.Usuario;

public class UsuarioDao {

	public void inserir(Usuario usuario) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}

	public Usuario buscar(Integer id) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}

	public Usuario buscar(String email, String senha) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("select u from Usuario u " + "where u.email = :email and u.senha = :senha");
		q.setParameter("email", email);
		q.setParameter("senha", senha);

		try {
			return (Usuario) q.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from Usuario");

		return new ArrayList<Usuario>(q.getResultList());
	}

	public void alterar(Usuario usuario) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
	}

	public void remover(Integer id) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Usuario Usuario = em.find(Usuario.class, id);
		em.remove(Usuario);
		em.getTransaction().commit();
	}

}
