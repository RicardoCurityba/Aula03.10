package br.up.mbean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.up.entidades.Usuario;

@WebFilter("/carrinho_de_compras.jsf")
public class LoginFilter implements Filter {

	public void destroy() {	
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse res = (HttpServletResponse)arg1;
		
		Usuario u = (Usuario) req.getSession().getAttribute("usuario");
		
		if (u == null) {
			req.getSession().setAttribute("pagina", "carrinho_de_compras.jsf");
			res.sendRedirect("login.jsf");
		}
		
		arg2.doFilter(arg0, arg1);

	}

	public void init(FilterConfig arg0) throws ServletException {	
	}
	
}