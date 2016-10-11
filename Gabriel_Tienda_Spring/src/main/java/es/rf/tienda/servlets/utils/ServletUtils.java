package es.rf.tienda.servlets.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.rf.tienda.dominio.UsuarioBase;

public class ServletUtils {
	
	/*
	 * Ruta de los jsp
	 */
	public static final String WWW = "WEB-INF/views/";
	
	/*
	 * Paginas jsp de la web
	 */
	public static final String HOME = "home";
	
	public static final String INDEX_PAGE = "index";
	public static final String LOGIN_PAGE = "login";
	public static final String CATEGORIAS_PAGE = "categorias";
	public static final String ADD_CATEGORIA_PAGE = "add-categoria";
	public static final String USUARIOS_PAGE = "usuarios";
	public static final String ADD_USUARIO_PAGE = "add-usuario";

	/*
	 * Constantes
	 */
	public static final String USUARIOS = "usuarios";
	public static final String USUARIO = "usuario";
	public static final String CATEGORIAS = "categorias";
	public static final String CATEGORIA = "categoria";

	public static final String ACTION = "action";
	public static final String ACTION_ADD = "add";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_DELETE = "delete";
	
	public static final String PAGE_MESSAGE_SUCCESS = "page_message_success";
	public static final String PAGE_MESSAGE_ERROR = "page_message_error";
	
	
	/**
	 * Dispatcher generico
	 * @param request
	 * @param response
	 * @param path
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher(ServletUtils.WWW + path);
		if (dispatcher != null) dispatcher.forward(request, response);
	}
	
	/**
	 * Devuelve el objeto usuario de la sesion o null
	 * @param request
	 * @return
	 */
	public static UsuarioBase getUsuario(HttpServletRequest request){
		return (UsuarioBase) request.getSession().getAttribute(USUARIO);
	}
	
	/**
	 * Mete el usuario en sesion
	 * @param request
	 * @param u
	 */
	public static void setUsuario(HttpServletRequest request, UsuarioBase u){
		request.getSession().setAttribute(USUARIO, u);
	}
	
	/**
	 * Devuelve true si hay un usuario conectado
	 * @param request
	 * @return
	 */
	public static boolean isLoggedIn(HttpServletRequest request) {
		return getUsuario(request) != null;
	}
	
	/**
	 * Comprueba que el usuario conectado es admin
	 * @param request
	 * @return
	 */
	public static boolean isAdmin(HttpServletRequest request){
		return isLoggedIn(request) && getUsuario(request).getUser_tipo() == 9;
	}
	

}
