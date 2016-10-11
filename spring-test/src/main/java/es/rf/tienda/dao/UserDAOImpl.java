package es.rf.tienda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import es.rf.tienda.dao.interfaces.UserDAO;
import es.rf.tienda.exception.DAOException;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Resource
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean isValidUser(String username, String password) throws DAOException, SQLException {
		String query = "SELECT COUNT(1) FROM usuariobase WHERE user_email = ? AND user_pass = ?";
		PreparedStatement pst = dataSource.getConnection().prepareStatement(query);
		pst.setString(1, username);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			return rs.getInt(1) > 0;
		}
		return false;
	}

}
