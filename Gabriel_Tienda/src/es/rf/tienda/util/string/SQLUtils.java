package es.rf.tienda.util.string;

import java.util.ArrayList;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.util.Validator;

public class SQLUtils {
	
	public static String AND = " AND ";
	public static String OR = " OR ";
	public static String COMA = " , ";
	public static String ALL = " * ";
	public static String EQUALS = " = ";
	public static String LIKE = " LIKE ";

	private static String SELECT = "SELECT COL FROM TABLE ";
	private static String INSERT = "INSERT INTO TABLE COL VALUES VALORES";
	private static String UPDATE = "UPDATE TABLE SET VAL WHERE COND";
	private static String DELETE = "DELETE FROM TABLE WHERE CON";

	
	/**
	 * Crea una query SELECT a partir de las columnas, tabla y condiciones de filtrado especificados
	 * @param columnas
	 * @param tabla
	 * @param condClaves
	 * @param condValores
	 * @return
	 * @throws DAOException
	 */
	public static String creaSelect(String columna, String tabla, String condClave, String condValor, String equalsOrLike) throws DAOException{
		
		ArrayList<String> columnas = new ArrayList<String>();
		columnas.add(columna);
		
		ArrayList<String> condClaves = null;
		ArrayList<String> condValores = null;
		
		if(condClave != null && condValor != null){
			condClaves = new ArrayList<String>();
			condClaves.add(condClave);
			
			condValores = new ArrayList<String>();
			condValores.add(condValor);
		}
		
		return creaSelect(columnas, tabla, condClaves, condValores, equalsOrLike, null);
	}
	
	public static String creaSelect(ArrayList<String> columnas, String tabla, ArrayList<String> condClaves, ArrayList<String> condValores, String equalsOrLike, String separador) throws DAOException{
		String sql = SELECT;
		
		if(columnas == null || columnas.isEmpty()){
			sql = sql.replace("COL", "*");	
		} else {
			sql = sql.replace("COL", StringUtils.crearLista(columnas, false, false));
		}
		
		if(!Validator.isVacio(tabla)){
			sql = sql.replace("TABLE", tabla);
		} else {
			throw new DAOException("No se ha indicado un nombre de tabla");
		}
				
//		if(condClaves!=null && condValores!=null){
//			if(!condClaves.isEmpty() && !condValores.isEmpty() && 
//				condClaves.size() == condValores.size()){
//				
//				sql += " WHERE ";
//				sql += StringUtils.crearListaClaveValor(condClaves, condValores, equalsOrLike, separador);
//				
//			} else {
//				throw new DAOException("Las condiciones del WHERE no coinciden");
//			}
//		}
		
		sql += creaWhere(condClaves, condValores, equalsOrLike, separador);
		
		return sql;
	}
	
	/**
	 * Crea una query INSERT a partir de la tabla, columnas y valores especificados
	 * @param tabla
	 * @param columnas
	 * @param valores
	 * @return
	 * @throws DAOException
	 */
	public static String creaInsert(String tabla, ArrayList<String> columnas, ArrayList<String> valores) throws DAOException{
		String sql = INSERT;
		
		if(!Validator.isVacio(tabla)){
			sql = sql.replace("TABLE", tabla);
		} else {
			throw new DAOException("No se ha indicado un nombre de tabla");
		}
		
		if(columnas == null || columnas.isEmpty() || valores == null || valores.isEmpty()
				|| columnas.size() != valores.size()){
			throw new DAOException("No se han indicado valores a insertar o no coinciden");
		} else {
			sql = sql.replace("COL", StringUtils.crearLista(columnas, true, false));
			sql = sql.replace("VALORES", StringUtils.crearLista(valores, true, true));
		}
		
		return sql;
		
	}
	
	/**
	 * Crea una query UPDATE a partir de los valores especificados
	 * @param tabla
	 * @param setClaves
	 * @param setValores
	 * @param condClaves
	 * @param condValores
	 * @return
	 * @throws DAOException 
	 */
	public static String creaUpdate(String tabla, ArrayList<String> setClaves, ArrayList<String> setValores, String condClave, String condValor) throws DAOException{
		ArrayList<String> condClaves = new ArrayList<String>(); 
		ArrayList<String> condValores = new ArrayList<String>(); 
		
		condClaves.add(condClave);
		condValores.add(condValor);
		
		return creaUpdate(tabla, setClaves, setValores, condClaves, condValores);		
	}
	
	public static String creaUpdate(String tabla, ArrayList<String> setClaves, ArrayList<String> setValores, ArrayList<String> condClaves, ArrayList<String> condValores) throws DAOException{
		String sql = UPDATE;
		
		if(!Validator.isVacio(tabla)){
			sql = sql.replace("TABLE", tabla);
		} else {
			throw new DAOException("No se ha indicado un nombre de tabla");
		}
		
		if(setClaves == null || setClaves.isEmpty() || setValores == null || setValores.isEmpty()
			|| setClaves.size() != setValores.size()){
			throw new DAOException("No se han indicado valores a insertar o no coinciden");
		} else {
			sql = sql.replace("VAL", StringUtils.crearListaClaveValor(setClaves, setValores, null, COMA));
			sql = sql.replace("COND", StringUtils.crearListaClaveValor(condClaves, condValores, EQUALS, AND));
		}		
		
		return sql;
	}
	
	/**
	 * Crea una query DELETE a partir de los valores especificados
	 * @param tabla
	 * @param condClaves
	 * @param condValores
	 * @return
	 * @throws DAOException
	 */
	public static String creaDelete(String tabla, String condClave, String condValor) throws DAOException{
		ArrayList<String> condClaves = new ArrayList<String>();
		ArrayList<String> condValores = new ArrayList<String>();
		
		condClaves.add(condClave);
		condValores.add(condValor);
		
		return creaDelete(tabla, condClaves, condValores, EQUALS, null);
	}
	
	public static String creaDelete(String tabla, ArrayList<String> condClaves, ArrayList<String> condValores, String equalsOrLike, String andOr) throws DAOException{
		String sql = DELETE;
		
		if(!Validator.isVacio(tabla)){
			sql = sql.replace("TABLE", tabla);
		} else {
			throw new DAOException("No se ha indicado un nombre de tabla");
		}
		
		// si son null o vavios se borra todo se borra todo
		if(condClaves == null || condClaves.isEmpty() || condValores == null || condValores.isEmpty()){
			System.out.println("No se han indicado condiciones, se borraran todos los datos de la tabla");
		} else {
			sql = sql.replace("CON", StringUtils.crearListaClaveValor(condClaves, condValores, equalsOrLike, andOr));
		}
		
		return sql;
		
	}
	
	/**
	 * Crea un String con las clausulas WHERE a partir de los parametros dados
	 * @param condClaves
	 * @param condValores
	 * @param equalsOrLike
	 * @param andOr
	 * @return
	 * @throws DAOException
	 */
	public static String creaWhere(ArrayList<String> condClaves, ArrayList<String> condValores, String equalsOrLike, String andOr) throws DAOException{
		String sql = "";
		
		if(condClaves!=null && condValores!=null){
			if(!condClaves.isEmpty() && !condValores.isEmpty() && 
				condClaves.size() == condValores.size()){
				
				sql += " WHERE " + StringUtils.crearListaClaveValor(condClaves, condValores, equalsOrLike, andOr);
				
			} else {
				throw new DAOException("Las condiciones del WHERE no coinciden");
			}
		}
		
		return sql;
	}
	
}
