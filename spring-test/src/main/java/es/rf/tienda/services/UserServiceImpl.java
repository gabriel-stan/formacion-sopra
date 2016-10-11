package es.rf.tienda.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rf.tienda.dao.interfaces.UserDAO;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean isValidUser(String username, String password) throws DAOException, SQLException {
		return userDAO.isValidUser(username, password);
	}

}
