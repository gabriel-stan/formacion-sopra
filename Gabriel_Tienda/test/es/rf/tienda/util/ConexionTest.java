package es.rf.tienda.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.util.connection.ConnectionDAO;
import es.rf.tienda.util.dao.CategoriaDAOUtil;

public class ConexionTest {
	
	private static Connection conn;

	private static final String CAT_ID = "999";
	private static final String CAT_NOMBRE = "@TEST Inciesos Pestosos";
	private static final String CAT_DESC = "Estos inciesos son muy pestosos";

	private static final String INSERT_1 = "INSERT INTO " + CategoriaDAOUtil.TABLE + " (" + CategoriaDAOUtil.COL_ID + "," + CategoriaDAOUtil.COL_NOMBRE + "," + CategoriaDAOUtil.COL_DESCRIPCION + ") VALUES ('999', '@TEST Inciesos Pestosos', 'Estos inciesos son muy pestosos')";
	private static final String SELECT_1 = "SELECT * FROM " + CategoriaDAOUtil.TABLE;
	private static final String UPDATE_1 = "UPDATE " + CategoriaDAOUtil.TABLE + " SET (" + CategoriaDAOUtil.COL_NOMBRE + "='@TEST Inciesos Olorosos'," + CategoriaDAOUtil.COL_DESCRIPCION + "='Estos inciesos son muy olorosos') WHERE " + CategoriaDAOUtil.COL_ID + "='999'";	
	private static final String DELETE_1 = "DELETE FROM " + CategoriaDAOUtil.TABLE + " WHERE " + CategoriaDAOUtil.COL_ID + " = '999'";
	private static final String DELETE_ALL = "DELETE FROM " + CategoriaDAOUtil.TABLE + " WHERE " + CategoriaDAOUtil.COL_NOMBRE + " LIKE '@_'";
	
	@BeforeClass
	public static void init() throws DAOException, SQLException {
		conn = ConnectionDAO.getConnection();
		conn.setAutoCommit(false);	// forzar autocommit false
		
		// limpiar posibles pruebas fallidas
		Statement stm;
		try {
			stm = conn.createStatement();
			int filas = stm.executeUpdate(DELETE_ALL);	
		} catch (SQLException e) {
			System.out.println("Error al limpiar");
			e.printStackTrace();
		}	
	}
	
	@AfterClass
	public static void end() throws DAOException, SQLException {
		Statement stm = conn.createStatement();
		int filas = stm.executeUpdate(DELETE_ALL);
		ConnectionDAO.closeConnection();
	}

	@Test
	public void testInsertar() throws SQLException, DAOException {
		
		Statement stm = conn.createStatement();
		int filas = stm.executeUpdate(INSERT_1);
		
		System.out.println("Resultado test insert: " + filas);
		ConnectionDAO.commit();
		
		ResultSet rs = stm.executeQuery(SELECT_1);
		rs.next();
		assertEquals(CAT_ID, rs.getString(CategoriaDAOUtil.COL_ID));
		assertEquals(CAT_NOMBRE, rs.getString(CategoriaDAOUtil.COL_NOMBRE));
		assertEquals(CAT_DESC, rs.getString(CategoriaDAOUtil.COL_DESCRIPCION));
		
	}
	
	/*
	@Test
	public void testGetConnection() throws DAOException {
//		conn = Conexion.getConnection();
	}
	
	@Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testCommit() {
		fail("Not yet implemented");
	}

	@Test
	public void testRollback() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseStatement() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseResulSet() {
		fail("Not yet implemented");
	}
	*/
	
}
