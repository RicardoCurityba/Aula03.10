package br.up.mbean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.up.dao.UsuarioDao;
import br.up.entidades.Usuario;

@ManagedBean(name="mBeanUsuario")
public class MBeanUsuario {
	
	private Usuario usuario;
	
	private Integer id;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private String sexo;
	private String email;
	private String senha;
	
	public String login() {
		
		Usuario usuario = new UsuarioDao().buscar(email, senha);

		if (usuario == null) {
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login ou senha inválidos!",""));
			return "";
		}	
				
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().setAttribute("usuario", usuario);
		
		return ""+req.getSession().getAttribute("pagina");
	}
	
	public void sessao() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		setUsuario((Usuario) req.getSession().getAttribute("usuario"));
	}
	
	public void logout() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			req.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(this.id);
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setDataNascimento(dataNascimento);
		usuario.setSexo(sexo);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		if(this.id == null) {
			new UsuarioDao().inserir(usuario);
		}else {
			new UsuarioDao().alterar(usuario);
		}
		
	}
	
	public String alterar(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.dataNascimento = usuario.getDataNascimento();
		this.sexo = usuario.getSexo();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		
		return "cad_pessoa.jsf";
	}
	
	public void excluir(Usuario usuario) {
		new UsuarioDao().remover(usuario.getId());
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
