package es.rf.tienda.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
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

import es.rf.tienda.daos.CategoriaDAOH;
import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dominio.CategoriaBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.servlets.utils.ServletUtils;

@Controller
@RequestMapping("/categorias")
public class CategoriasController implements Observer {
	
	@Autowired
	private CategoriaDAOH catDAO;

	@Autowired
	private ServletContext servletContext;
		
	@PostConstruct
	public void init() {		
		catDAO.addObserver(this);
		update(null, null);		
	}
	
	public CategoriaDAOH getCatDAO() {
		return catDAO;
	}

	public void setCatDAO(CategoriaDAOH catDAO) {
		this.catDAO = catDAO;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * Listado de categorias
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getCategorias() {
		return ServletUtils.CATEGORIAS_PAGE;
	}
	
	/**
	 * Muestra pagina de añadir categoria
	 * @param request
	 * @param response
	 * @param categoriaBean
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addCategoriaGet(HttpServletRequest request, HttpServletResponse response, CategoriaBase categoriaBean) {
	
//		String url = request.getRequestURL().toString();
//		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		
		ModelAndView model = null;		
		
		model = new ModelAndView(ServletUtils.ADD_CATEGORIA_PAGE);
		model.addObject("categoriaBean", categoriaBean);
				
		return model;
	}
	
	/**
	 * Añade una categoria
	 * @param request
	 * @param response
	 * @param categoriaBean
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addCategoriaPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("categoriaBean") CategoriaBase categoriaBean) {

		ModelAndView model = null;
		String redirect = ServletUtils.ADD_CATEGORIA_PAGE;
		
		Categoria cat = new Categoria();
		try {
			cat.setCat_nombre(categoriaBean.getCat_nombre());
			cat.setCat_descripcion(categoriaBean.getCat_descripcion());
			
			catDAO.insert(cat);
			
			request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "La categoria se ha insertado correctamente");
			
			redirect = ServletUtils.CATEGORIAS_PAGE;
			
		} catch (DomainException e) {
			// error de parametros
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Compruebe los campos de la categoria: " + e.getLocalizedMessage());	
			e.printStackTrace();
		} catch (DAOException e) {
			// error de insercion
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al insertar la categoria: " + e.getLocalizedMessage());	
			e.printStackTrace();
		}
		
		model = new ModelAndView(redirect);
		model.addObject("categoriaBean", categoriaBean);
		
		return model;
	}
	
	/**
	 * Muestra pagina de editar categoria
	 * @param request
	 * @param response
	 * @param categoriaBean
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView editCategoriaGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("categoriaBean") CategoriaBase categoriaBean, @PathVariable("id") int id) {
		
		ModelAndView model = null;
		String redirect = ServletUtils.ADD_CATEGORIA_PAGE;
		
		try{
			categoriaBean = catDAO.getObject(id);
		}catch (Exception e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "La categoria seleccionada no existe");
		}	
		
		model = new ModelAndView(redirect);
		model.addObject("categoriaBean", categoriaBean);
		
		return model;
	}
	
	/**
	 * Modifica una categoria
	 * @param request
	 * @param response
	 * @param categoriaBean
	 * @return
	 */
	@RequestMapping(value = "/edit/{id:[\\d]+}", method = RequestMethod.POST)
	public ModelAndView editCategoriaPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("categoriaBean") CategoriaBase categoriaBean) {
		
		ModelAndView model = null;
		String redirect = ServletUtils.ADD_CATEGORIA_PAGE;
		
		Categoria cat = new Categoria();
		
		try {
			cat.setId_categoria(categoriaBean.getId_categoria());
			cat.setCat_nombre(categoriaBean.getCat_nombre());
			cat.setCat_descripcion(categoriaBean.getCat_descripcion());
			
			catDAO.update(cat);
			
			request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "La categoria se ha actualizado correctamente");
			
			redirect = ServletUtils.CATEGORIAS_PAGE;
			
		} catch (DomainException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Compruebe los campos de la categoria: " + e.getLocalizedMessage());	
			e.printStackTrace();
		} catch (DAOException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al actualizar la categoria: " + e.getLocalizedMessage());	
			e.printStackTrace();
		}
		
		model = new ModelAndView(redirect);
		model.addObject("categoriaBean", categoriaBean);
		
		return model;
	}
	
	/**
	 * Borra una categoria
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteCategoriaGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") int id) {
		
		ModelAndView model = null;
		String redirect = ServletUtils.CATEGORIAS_PAGE;
		
		try{
			
			Categoria cat = new Categoria();
			cat.setId_categoria(id);
			catDAO.delete(cat);			
			
			request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "La categoria se ha borrado correctamente");
			
		} catch (DomainException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "La categoria no existe");	
			e.printStackTrace();
		} catch (DAOException e) {
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al borrar la categoria");	
			e.printStackTrace();
		}
		
		model = new ModelAndView(redirect);
		
		return model;
	}
	

	/**
	 * Implementa el metodo update del Observer
	 * Actualiza la lista de categorias
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		System.out.println("Actualizando Categorias");
		List<CategoriaBase> listaCategorias = new ArrayList<CategoriaBase>();
		
		try {
			listaCategorias = catDAO.getAll();
		} catch (DAOException e) {
			System.err.println("Error al actualizar las categorias en memoria");
			e.printStackTrace();
		}
		
		servletContext.setAttribute(ServletUtils.CATEGORIAS, listaCategorias);
		
	}
}
