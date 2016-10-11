package es.rf.tienda.services.interfaces;

import java.sql.SQLException;

import es.rf.tienda.exception.DAOException;

public interface UserService {
	
	public boolean isValidUser(String username, String password) throws DAOException, SQLException;

}
