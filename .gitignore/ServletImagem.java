package br.up.mbean;

import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.up.dao.FilmeDao;
import br.up.entidades.Filme;

@WebServlet("/ServletImagem")
public class ServletImagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletImagem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Filme filme = new FilmeDao().buscar(Integer.parseInt(id));
		
		File f = new File(filme.getCaminhoImagem());
		@SuppressWarnings("resource")
		FileImageInputStream fis = new FileImageInputStream(f);
		byte[] arrayImagem = new byte[(int) f.length()];
		fis.read(arrayImagem);
		
		response.getOutputStream().write(arrayImagem);
	}

}
