package es.rf.tienda.servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.UsuarioDAOH;
import es.rf.tienda.servlets.utils.ServletUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
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
		ServletUtils.forward(request, response, ServletUtils.LOGIN_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		String dni = request.getParameter("dni");
		String email = request.getParameter("email");
		
		Usuario nuevoUsuario = new Usuario();
		try {
			nuevoUsuario.setUser_nombre(nombre);
			nuevoUsuario.setUser_email(email);
			nuevoUsuario.setUser_dni(dni);
			nuevoUsuario.setUser_pass(password);
			nuevoUsuario.setUser_tipo(0);
			nuevoUsuario.setUser_fecAlta(Calendar.getInstance());
			nuevoUsuario.setUser_fecConfirmacion(Calendar.getInstance());
					
			try {
				userDAO.insert(nuevoUsuario);
				request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "El usuario se ha registrado correctamente");
				
			} catch (DAOException e) {
				request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error interno. Por favor contacte con el administrador del sistema.");
				e.printStackTrace();
			}
			
		} catch (DomainException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error en parametros, compruebe los datos introducidos (" + e.getLocalizedMessage() + ").");
//			e.printStackTrace();
			System.err.println(e.getLocalizedMessage());
		}
		
		ServletUtils.forward(request, response, ServletUtils.LOGIN_PAGE);
	}

}
