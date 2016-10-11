package es.rf.tienda.daos;

import static org.junit.Assert.*;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.rf.tienda.exception.DAOException;

public class testConexion {
	private static Connection conn;
	
	/*@Test
	public void inicio() throws DAOException, SQLException{
		conn = ConnectionDAO.getConnection();
		Lectura l = new Lectura();
		l.leer();
	}	*/
/*	@Before
	public void inicio() throws DAOException{
		conn = Conexion.getConnection();
	}

	@Test
	public void testCommit() throws  DAOException, SQLException {
		Statement stm = conn.createStatement();
		stm.executeUpdate("Update EMP set JOB = JOB");
		RFDataConnection.commit();
	}

	@Test
	public void testRollback() throws SQLException, DAOException {
		Statement stm = conn.createStatement();
		stm.executeUpdate("Update EMP set sal = sal + 10000 ");
		RFDataConnection.rollback();
	}

	@Test
	public void testCloseStatement() throws SQLException, DAOException {
		Statement stm = conn.createStatement();
		RFDataConnection.closeStatement(stm);
		assertTrue("Cerrar statment",stm.isClosed());
	}

	@Test
	public void testCloseResulSet() throws SQLException, DAOException {
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM EMP");
		RFDataConnection.closeResulSet(rs);
		
	}
*/
}
