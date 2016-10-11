package es.rf.tienda.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.dominio.UsuarioBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.UsuarioDAOH;
import es.rf.tienda.servlets.utils.ServletUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static UsuarioDAOH userDAO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		userDAO = new UsuarioDAOH();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(ServletUtils.isLoggedIn(request)){
			ServletUtils.forward(request, response, ServletUtils.INDEX_PAGE);				
		} else {
			ServletUtils.forward(request, response, ServletUtils.LOGIN_PAGE);			
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UsuarioBase usuario = new UsuarioBase();
		usuario.setUser_email(email);
		usuario.setUser_pass(password);
		
		try {
			List<UsuarioBase> lista = userDAO.filter(usuario);
			
			if(lista.size() == 1){
				request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "El usuario se ha conectado correctamente");				
				ServletUtils.setUsuario(request, lista.get(0));
				ServletUtils.forward(request, response, ServletUtils.INDEX_PAGE);
				return;
			} else {
				request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "El usuario no existe o la contraseña es incorrecta.");
			}
			
		} catch (DAOException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error interno. Por favor contacte con el administrador del sistema.");
			e.printStackTrace();
		}
		
		ServletUtils.forward(request, response, ServletUtils.LOGIN_PAGE);

	}

}
