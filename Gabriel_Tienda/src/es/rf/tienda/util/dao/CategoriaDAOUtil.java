package es.rf.tienda.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.rf.tienda.dominio.CategoriaBase;
import es.rf.tienda.exception.DAOException;

public abstract class CategoriaDAOUtil {

	public static String TABLE = "CategoriaBase";
	public static String COL_ID = "id_categoria";
	public static String COL_NOMBRE = "cat_nombre";
	public static String COL_DESCRIPCION = "cat_descripcion";
	
	/**
	 * Construye un objeto de tipo Categoria a partir del resultado de la query
	 * @param rs
	 * @param sql
	 * @return
	 * @throws DAOException
	 */
	public static CategoriaBase construirCategoria(ResultSet rs, String sql) throws DAOException{		
		try {
			if(rs.next()){
				// si hay algun resultado
				if(rs.isLast()){
					// hay un solo resultado
					CategoriaBase res = new CategoriaBase();
					res.setId_categoria(rs.getInt("id_categoria"));
					res.setCat_nombre(rs.getString("cat_nombre"));
					res.setCat_descripcion(rs.getString("cat_descripcion"));
					
					return res;
					
				} else {
					// hay mas de un resultado
					throw new DAOException("Hay mas de un resultado para la query " + sql);				
				}
			} else {
				throw new DAOException("No hay ninun resultado para la query " + sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al procesar el resultado de la query " + sql);
		}
	}

	/**
	 * Construye una lista de objetos de tipo Categoria a parti de resultado de la query
	 * @param rs
	 * @param sql
	 * @return
	 * @throws DAOException 
	 */
	public static List<CategoriaBase> construirCategorias(ResultSet rs, String sql) throws DAOException {
		try {
			List<CategoriaBase> lista = new ArrayList<CategoriaBase>();
			while(rs.next()){
				CategoriaBase res = new CategoriaBase();
				res.setId_categoria(rs.getInt("id_categoria"));
				res.setCat_nombre(rs.getString("cat_nombre"));
				res.setCat_descripcion(rs.getString("cat_descripcion"));
				lista.add(res);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error al procesar el resultado de la query " + sql);
		}
	}
	
	public static void rellenaArraysWhere(CategoriaBase c, List<String> condClaves, List<String> condValores){
		if(condClaves == null || condValores == null){
			condClaves = new ArrayList<String>();
			condValores = new ArrayList<String>();
			
		}if(c.getId_categoria() > 0){
			condClaves.add(COL_ID);
			condValores.add(String.valueOf(c.getId_categoria()));
		}
				
		if(c.getCat_nombre() != null){
			condClaves.add(COL_NOMBRE);
			condValores.add(c.getCat_nombre());
		}
		
		if(c.getCat_descripcion() != null){
			condClaves.add(COL_DESCRIPCION);
			condValores.add(c.getCat_descripcion());
		}
	}
	
	public static void rellenaArraysWhere(int id, List<String> condClaves, List<String> condValores){
		if(condClaves == null || condValores == null){
			condClaves = new ArrayList<String>();
			condValores = new ArrayList<String>();			
		}
		
		condClaves.add(COL_ID);
		condValores.add(String.valueOf(id));
	}
	
	public static void rellenaArraysInsert(CategoriaBase c, List<String> columnas, List<String> valores){
		if(columnas == null || valores == null){
			columnas = new ArrayList<String>();
			valores = new ArrayList<String>();
		}
		
		if(c.getId_categoria() > 0){
			columnas.add(COL_ID);
			valores.add(String.valueOf(c.getId_categoria()));
		}
		
		if(c.getCat_nombre() != null){
			columnas.add(COL_NOMBRE);
			valores.add(c.getCat_nombre());
		}
		
		if(c.getCat_descripcion() != null){
			columnas.add(COL_DESCRIPCION);
			valores.add(c.getCat_descripcion());			
		}
	}

}
