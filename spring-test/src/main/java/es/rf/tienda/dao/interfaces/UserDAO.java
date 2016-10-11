package es.rf.tienda.dao.interfaces;

import java.sql.SQLException;

import es.rf.tienda.exception.DAOException;

public interface UserDAO {

	public boolean isValidUser(String username, String password) throws DAOException, SQLException;

	
}
