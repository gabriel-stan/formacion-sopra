package es.rf.tienda.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.rf.tienda.daos.UsuarioDAOH;
import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.dominio.UsuarioBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.servlets.utils.ServletUtils;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsuarioDAOH userDAO;

	public UsuarioDAOH getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsuarioDAOH userDAO) {
		this.userDAO = userDAO;
	}
	
	/**
	 * Listado de usuarios
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getUsuarios(HttpServletRequest request) {
		
		updateUsers(request);
		return new ModelAndView(ServletUtils.USUARIOS_PAGE);
	}
	
	/**
	 * Muestra pagina de añadir usuario
	 * @param request
	 * @param response
	 * @param usuarioBean
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUsuarioGet(HttpServletRequest request, HttpServletResponse response, UsuarioBase usuarioBean) {

		ModelAndView model = null;		
		
		model = new ModelAndView(ServletUtils.ADD_USUARIO_PAGE);
		model.addObject("usuarioBean", usuarioBean);
				
		return model;
	}
	
	/**
	 * Añade un usuario
	 * @param request
	 * @param response
	 * @param usuarioBean
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUsuarioPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuarioBean") UsuarioBase usuarioBean) {
		
		ModelAndView model = null;
		String redirect = ServletUtils.ADD_USUARIO_PAGE;
		
		Usuario user = new Usuario();
		try {
			user.setUser_dni(usuarioBean.getUser_dni());
			user.setUser_email(usuarioBean.getUser_email());
			user.setUser_fecAlta(Calendar.getInstance());
			user.setUser_fecConfirmacion(Calendar.getInstance());
			user.setUser_nombre(usuarioBean.getUser_nombre());
			user.setUser_pass(usuarioBean.getUser_pass());
			user.setUser_tipo(usuarioBean.getUser_tipo());
						
			userDAO.insert(user);
			
			request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "El usuario se ha insertado correctamente");
			
			redirect = ServletUtils.USUARIOS_PAGE;
			
			updateUsers(request);
			
		} catch (DomainException e) {
			// error de parametros
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Compruebe los campos del usuario: " + e.getLocalizedMessage());	
			e.printStackTrace();
		} catch (DAOException e) {
			// error de insercion
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al insertar el usuario: " + e.getLocalizedMessage());	
			e.printStackTrace();
		}
		
		model = new ModelAndView(redirect);
		model.addObject("usuarioBean", usuarioBean);
		
		return model;
	}
	
	/**
	 * Muestra pagina de editar usuario
	 * @param request
	 * @param response
	 * @param usuarioBean
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView editUsuarioGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuarioBean") UsuarioBase usuarioBean, @PathVariable("id") int id) {
		
		ModelAndView model = null;
		String redirect = ServletUtils.ADD_USUARIO_PAGE;
		
		try{
			usuarioBean = userDAO.getObject(id);
		}catch (Exception e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "El usuario seleccionado no existe");
		}	
		
		model = new ModelAndView(redirect);
		model.addObject("usuarioBean", usuarioBean);
		
		return model;
	}
	
	/**
	 * Modifica un usuario
	 * @param request
	 * @param response
	 * @param usuarioBean
	 * @return
	 */
	@RequestMapping(value = "/edit/{id:[\\d]+}", method = RequestMethod.POST)
	public ModelAndView editUsuarioPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuarioBean") UsuarioBase usuarioBean) {
		
		ModelAndView model = null;
		String redirect = ServletUtils.ADD_USUARIO_PAGE;
		
		Usuario user = new Usuario();
		try {
			user.setUser_dni(usuarioBean.getUser_dni());
			user.setUser_email(usuarioBean.getUser_email());
			user.setUser_fecAlta(usuarioBean.getUser_fecAlta());
			user.setUser_fecConfirmacion(usuarioBean.getUser_fecConfirmacion());
			user.setUser_nombre(usuarioBean.getUser_nombre());
			user.setUser_pass(usuarioBean.getUser_pass());
			user.setUser_tipo(usuarioBean.getUser_tipo());
			
			user.getBaseObject().setId_usuario(usuarioBean.getId_usuario());
			
			userDAO.update(user);
			
			request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "El usuario se ha actualizado correctamente");
			
			redirect = ServletUtils.USUARIOS_PAGE;
			
			updateUsers(request);
			
		} catch (DomainException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Compruebe los campos del usuario: " + e.getLocalizedMessage());	
			e.printStackTrace();
		} catch (DAOException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al actualizar el usuario: " + e.getLocalizedMessage());	
			e.printStackTrace();
		}
		
		model = new ModelAndView(redirect);
		model.addObject("usuarioBean", usuarioBean);
		
		return model;
	}
	
	/**
	 * Borra un usuario
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteUsuarioGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int id) {

		ModelAndView model = null;
		String redirect = ServletUtils.USUARIOS_PAGE;
		
		try{
			Usuario user = new Usuario();
			user.setId_usuario(id);
			userDAO.delete(user);			
			
			request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "El usuario se ha borrado correctamente");

			updateUsers(request);
			
		} catch (DomainException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "El usuario no existe");	
			e.printStackTrace();
		} catch (DAOException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al borrar el usuario");	
			e.printStackTrace();
		}
		
		model = new ModelAndView(redirect);
		
		return model;
	}
	

	/**
	 * Actualiza la lista de usuarios de una sesion
	 */
	private void updateUsers(HttpServletRequest request) {
		
		if(ServletUtils.isAdmin(request)){

			System.out.println("Actualizando Usuarios");
			List<UsuarioBase> listaUsuarios = new ArrayList<UsuarioBase>();
		
			try {
				listaUsuarios = userDAO.getAll();
			} catch (DAOException e) {
				System.err.println("Error al actualizar los usuarios en memoria");
				e.printStackTrace();
			}
			
			request.getSession().setAttribute(ServletUtils.USUARIOS, listaUsuarios);
		}
	}

}
