package es.rf.tienda.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.rf.tienda.exception.DAOException;

public class Conexion {
	
	/**
	 * Objeto conexion
	 */
	private static Connection conn;
	
	/**
	 * Driver JDBC
	 */
	private static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	/**
	 * URL de conexion
	 */
	private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		
	/**
	 * Nombre de la Base de Datos
	 */
	@SuppressWarnings("unused")
	private static String DATABASE = "";
	
	/**
	 * Usuario de conexión
	 */
//	private static String USER = "alumno";
	private static String USER = "hr";
	
	/**
	 * Contraseña de conexión
	 */
//	private static String PASSWORD = "curso201605";
	private static String PASSWORD = "hr";
	
	
	public static Connection getConnection() throws DAOException {

		try {
			if(conn == null || conn.isClosed()){

				try {
					Class.forName(JDBC_DRIVER);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					System.out.println("Conexion fallida");
					throw new DAOException("Error al cargar el Driver JDBC");
				}
				
				try {
					conn = DriverManager.getConnection(URL, USER, PASSWORD);
					//conectar();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Conexion fallida");
					throw new DAOException("No se ha podido conectar con la BD");
				}
				
				if(conn != null){
					System.out.println("Conexion realizada correctamente");
				} else {
					System.out.println("Conexion fallida");
					throw new DAOException("No se ha podido conectar con la BD");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al conectar con la BD");
		}
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("No se ha podido poner el autocommit a false");
		}
		
		return conn;		
	}
	
	public static void closeConnection() throws DAOException {
		try {
			if (conn!=null && 
					!conn.isClosed()){
				conn.close();				
			}
		} catch (Exception e){
			System.out.println("No se ha podido cerrar la conexi�n a la BD");
			throw new DAOException("Error al cerrar conexi�n a BD");			
		} finally {
			conn = null;
		}
	}
	
	public static void commit(Connection conn0) throws DAOException {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new DAOException("Error al realizar commit");
		}
	}


	public static void rollback(Connection conn0) throws DAOException 
	{
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al realizar rollback");
		}

	}

	public static void closeStatement(PreparedStatement ps) throws DAOException {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new DAOException("No ha sido posible realizar operaci�n close sobre elemento Statement");
		}
	}

	public static void closeResulSet(ResultSet rs) throws DAOException {
		try {
			if (rs!=null){
				rs.close();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new DAOException("No ha sido posible realizar operaci�n close sobre elemento ResultSet");
		}
	}

}
