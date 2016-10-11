package es.rf.tienda.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.dominio.UsuarioBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.daos.UsuarioDAOH;
import es.rf.tienda.servlets.utils.ServletUtils;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioDAOH userDAO;
	
	/**
	 * Responde a login y register para mostrar la pagina de inicio de sesion/registro
	 * @param request
	 * @param response
	 * @param loginBean
	 * @return
	 */
	@RequestMapping(value = {"/login", "/register"}, method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request, HttpServletResponse response, UsuarioBase loginBean) {
		ModelAndView model = null;
		
		if(ServletUtils.isLoggedIn(request)){
			model = new ModelAndView(ServletUtils.INDEX_PAGE);			
		} else {
			model = new ModelAndView(ServletUtils.LOGIN_PAGE);
			model.addObject("loginBean", loginBean);
		}	
		
		return model;
	}
	
	/**
	 * Realiza el login de un usuario
	 * @param request
	 * @param response
	 * @param loginBean
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean") UsuarioBase loginBean) {
		ModelAndView model = null;
		
		try {
			List<UsuarioBase> lista = userDAO.filter(loginBean);
			
			if(lista.size() == 1){
				request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "El usuario se ha conectado correctamente");				
				ServletUtils.setUsuario(request, lista.get(0));
				
				model = new ModelAndView(ServletUtils.INDEX_PAGE);
				return model;
			} else {
				request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "El usuario no existe o la contraseña es incorrecta.");
			}
			
		} catch (DAOException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error interno. Por favor contacte con el administrador del sistema.");
			e.printStackTrace();
		}
		
		model = new ModelAndView(ServletUtils.LOGIN_PAGE);
		return model;
	}
	
	/**
	 * Realiza el logout de un usuario
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		ServletUtils.setUsuario(request, null);
		
		return ServletUtils.INDEX_PAGE;
	}
	
	/**
	 * Realiza el registro de un usuario
	 * @param request
	 * @param response
	 * @param loginBean
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean") UsuarioBase loginBean) {
		ModelAndView model = null;
		
		Usuario nuevoUsuario = new Usuario();
		try {
			nuevoUsuario.setUser_nombre(loginBean.getUser_nombre());
			nuevoUsuario.setUser_email(loginBean.getUser_email());
			nuevoUsuario.setUser_dni(loginBean.getUser_dni());
			nuevoUsuario.setUser_pass(loginBean.getUser_pass());
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
			System.err.println(e.getLocalizedMessage());
		}

		
		model = new ModelAndView(ServletUtils.LOGIN_PAGE);
		model.addObject("registerBean", loginBean);
		return model;
	}
	
}
