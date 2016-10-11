package es.rf.tienda.delegates;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.services.interfaces.UserService;

@Component
public class LoginDelegate {

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public boolean isValidUser(String username, String password) throws DAOException, SQLException {
		return userService.isValidUser(username, password);
	}
}
