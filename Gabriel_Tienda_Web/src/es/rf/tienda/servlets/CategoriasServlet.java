package es.rf.tienda.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dominio.CategoriaBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.CategoriaDAOH;
import es.rf.tienda.servlets.utils.ServletUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Servlet implementation class CategoriasServlet
 */
@WebServlet("/categorias")
public class CategoriasServlet extends HttpServlet implements Observer {
	private static final long serialVersionUID = 1L;
	
	private static CategoriaDAOH catDAO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		catDAO = new CategoriaDAOH();
		catDAO.addObserver(this);
		
		update(null, null);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = ServletUtils.CATEGORIAS_PAGE;
		
		if(request.getParameter(ServletUtils.ACTION) != null){ 
			
			if(!ServletUtils.isAdmin(request)){
				ServletUtils.forward(request, response, ServletUtils.INDEX_PAGE);
				return;
			}
			
			if(request.getParameter(ServletUtils.ACTION).equals(ServletUtils.ACTION_ADD)) {
				// forward = ServletUtils.ADD_CATEGORIA_PAGE;
			} else if(request.getParameter(ServletUtils.ACTION).equals(ServletUtils.ACTION_EDIT)) {
				
				int id = 0;

				try{
					id = Integer.parseInt(request.getParameter("id"));
					CategoriaBase cat = catDAO.getObject(id);					
					request.setAttribute(ServletUtils.CATEGORIA, cat);
				}catch (Exception e) {
					request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "La categoria seleccionada no existe");
				}				
				
			}
			
			forward = ServletUtils.ADD_CATEGORIA_PAGE;
		} 
		
		ServletUtils.forward(request, response, forward);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletUtils.isAdmin(request)){
			ServletUtils.forward(request, response, ServletUtils.INDEX_PAGE);
			return;
		}
		
		int id = 0;
		String name = request.getParameter("name");
		String desc = request.getParameter("description");

		Categoria cat = new Categoria();

		try{
			id = Integer.parseInt(request.getParameter("id"));
		}catch (Exception e) {
			// bueno, no pasa na
		}
		
		
		if(id == 0){
			// insert
			try {
				cat.setCat_nombre(name);
				cat.setCat_descripcion(desc);
				
				catDAO.insert(cat);
				
				request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "La categoria se ha insertado correctamente");
				
				ServletUtils.forward(request, response, ServletUtils.CATEGORIAS_PAGE);
				return;
			} catch (DomainException e) {
				// error de parametros
				request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Compruebe los campos de la categoria: " + e.getLocalizedMessage());	
				e.printStackTrace();
			} catch (DAOException e) {
				// error de insercion
				request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al insertar la categoria: " + e.getLocalizedMessage());	
				e.printStackTrace();
			}
			
			ServletUtils.forward(request, response, ServletUtils.ADD_CATEGORIA_PAGE);
			return;
			
		} else {
			
			// update or delete
			String action = request.getParameter(ServletUtils.ACTION);
			
			if(action != null && action.equals(ServletUtils.ACTION_DELETE)){
				// delete
				try {
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
				
				ServletUtils.forward(request, response, ServletUtils.CATEGORIAS_PAGE);
				return;
			} else {
				// update
				try {
					cat.setId_categoria(id);
					cat.setCat_nombre(name);
					cat.setCat_descripcion(desc);
					
					catDAO.update(cat);
					
					request.setAttribute(ServletUtils.PAGE_MESSAGE_SUCCESS, "La categoria se ha actualizado correctamente");
					
					ServletUtils.forward(request, response, ServletUtils.CATEGORIAS_PAGE);
					return;
					
				} catch (DomainException e) {
					request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Compruebe los campos de la categoria: " + e.getLocalizedMessage());	
					e.printStackTrace();
				} catch (DAOException e) {
					request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "Error al actualizar la categoria: " + e.getLocalizedMessage());	
					e.printStackTrace();
				}
			}
			
			ServletUtils.forward(request, response, ServletUtils.ADD_CATEGORIA_PAGE);
			return;
			
		}
		
	}

	/**
	 * Update de Observer para actualizar las categorias
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		System.out.println("Actualizando Categorias");
		
		ServletContext context = getServletContext();
		List<CategoriaBase> listaCategorias = new ArrayList<CategoriaBase>();
		
		try {
			listaCategorias = catDAO.getAll();
		} catch (DAOException e) {
			System.err.println("Error al actualizar las categorias en memoria");
			e.printStackTrace();
		}
		
		context.setAttribute(ServletUtils.CATEGORIAS, listaCategorias);
		
	}

}
