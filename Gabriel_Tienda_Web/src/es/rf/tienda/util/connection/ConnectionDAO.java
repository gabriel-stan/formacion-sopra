package es.rf.tienda.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.rf.tienda.exception.DAOException;

public class ConnectionDAO {
	
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
	private static String USER = "alumno";
//	private static String USER = "hr";
	
	/**
	 * Contraseña de conexión
	 */
	private static String PASSWORD = "curso201605";
//	private static String PASSWORD = "hr";
	
	
	/**
	 * devuelve la conexion a la base de datos
	 * @return Objeto de conexion con la BD
	 * @throws DAOException
	 */
	public static Connection getConnection() throws DAOException {

		try {
			if(conn == null || conn.isClosed()){

				try {
					Class.forName(JDBC_DRIVER);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					System.out.println("Conexión fallida");
					throw new DAOException("Error al cargar el Driver JDBC");
				}
				
				try {
					conn = DriverManager.getConnection(URL, USER, PASSWORD);
					//conectar();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Conexión fallida");
					throw new DAOException("No se ha podido conectar con la BD");
				}
				
				if(conn != null){
					System.out.println("Conexión realizada correctamente");
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
	
	/**
	 * Cierra la conexion con la base de datos
	 * @throws DAOException
	 */
	public static void closeConnection() throws DAOException {
		try {
			if (conn!=null && 
					!conn.isClosed()){
				conn.close();				
			}
		} catch (Exception e){
			System.out.println("No se ha podido cerrar la conexión a la BD");
			throw new DAOException("Error al cerrar conexi�n a BD");			
		} finally {
			conn = null;
		}
	}
	
	/**
	 * Hace commit sobre la base de datos
	 * @throws DAOException
	 */
	public static void commit() throws DAOException {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new DAOException("Error al realizar commit");
		}
	}

	/**
	 * Realiza rollback de los cambios en la base de datos
	 * @throws DAOException
	 */
	public static void rollback() throws DAOException 
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

	/**
	 * Cierra el Statement
	 * @param ps
	 * @throws DAOException
	 */
	public static void closeStatement(PreparedStatement ps) throws DAOException {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new DAOException("No ha sido posible realizar operación close sobre elemento Statement");
		}
	}

	/**
	 * Cierra el statement
	 * @param ps
	 * @throws DAOException
	 */
	public static void closeStatement(Statement ps) throws DAOException {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new DAOException("No ha sido posible realizar operación close sobre elemento Statement");
		}
	}

	/**
	 * Cierra el ResultSet
	 * @param rs
	 * @throws DAOException
	 */
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
	
	/**
	 * Ejecuta una query de tipo INSERT, UPDATE o DELETE
	 * @param sql
	 * @return
	 * @throws DAOException
	 */
	public static int execute(String sql) throws DAOException {
		conn = getConnection();
		
		Statement stm = null;
		try {
			
			stm = conn.createStatement();
			
			return stm.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al preparar la query");
		} finally {
			closeStatement(stm);
		}
		
	}
	
	/**
	 * Ejecuta una query de tipo SELECT
	 * @param sql
	 * @return
	 * @throws DAOException
	 */
	public static ResultSet executeQuery(String sql) throws DAOException{
		conn = getConnection();
		
		Statement stm = null;
		try {
			
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			return stm.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al preparar la query");
		} finally {
			// closeStatement(stm); // no cerramos el statement porque automaticamente cierra el resultset
		}
	}
	
	/**
	 * Obtiene la próxima clave primaria de una tabla
	 * @param table Tabla 
	 * @param pk Campo de clave primaria
	 * @return
	 * @throws DAOException
	 */
	public static int getNextKey(String table, String pk) throws DAOException{
		conn = getConnection();
		
		String sql = "SELECT MAX(" + pk + ") AS num FROM " + table;
		
		Statement stm = null;
		try {
			stm = conn.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);
			if(rs != null && !rs.isClosed() && rs.next()){
				return rs.getInt("num") + 1;
			} else {
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al preparar la query");
		} finally {
			closeStatement(stm);
		}
		
	}

}
