package es.rf.tienda.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import es.rf.tienda.servlets.utils.ServletUtils;

public class AdminFilter implements Filter {
	
	private static Logger log = Logger.getLogger(AdminFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;		
//		HttpServletResponse resp = (HttpServletResponse) response;		

		if(!ServletUtils.isAdmin(req)){
			request.setAttribute(ServletUtils.PAGE_MESSAGE_ERROR, "No tienes permisos. Se notificará al administrador del sistema.");
			
//			String contextPath = req.getContextPath();
			String url = req.getRequestURL().toString();
//			String baseURL = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
			
			log.warn("El usuario que intenta acceder no es admin: " + url);
			System.out.println("Redirigiendo a: /home");
//			resp.sendRedirect(baseURL + ServletUtils.HOME);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
			
//			return;
		}		
		
		chain.doFilter(request, response);		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
