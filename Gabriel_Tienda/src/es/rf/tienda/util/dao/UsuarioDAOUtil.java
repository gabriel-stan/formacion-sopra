package es.rf.tienda.util.dao;

import java.util.ArrayList;
import java.util.List;

import es.rf.tienda.dominio.UsuarioBase;


public class UsuarioDAOUtil {

	public static String TABLE = "UsuarioBase";
	public static String COL_ID = "id_usuario";
	public static String COL_NOMBRE = "user_nombre";
	public static String COL_EMAIL = "user_email";
	public static String COL_PASS = "user_pass";
	public static String COL_TIPO = "user_tipo";
	public static String COL_DNI = "user_dni";
	public static String COL_FECHA_ALTA = "user_fecAlta";
	public static String COL_FECHA_CONFIRM = "user_fecConfirmacion";
	
	/**
	 * Rellena los arrays con las claves y valores respectivas de la clase UsuarioBase
	 * @param u
	 * @param condClaves
	 * @param condValores
	 */
	public static void rellenaArrays(UsuarioBase u, List<String> condClaves, List<String> condValores) {
		if(condClaves == null || condValores == null){
			condClaves = new ArrayList<String>();
			condValores = new ArrayList<String>();			
		}
		
//		if(u.getId_usuario() > 0){
//			condClaves.add(COL_ID);
//			condValores.add(String.valueOf(u.getId_usuario()));
//		}
				
		if(u.getUser_nombre() != null){
			condClaves.add(COL_NOMBRE);
			condValores.add(u.getUser_nombre());
		}
		
		if(u.getUser_email() != null){
			condClaves.add(COL_EMAIL);
			condValores.add(u.getUser_email());
		}
		
		if(u.getUser_pass() != null){
			condClaves.add(COL_PASS);
			condValores.add(u.getUser_pass());
		}
		
		if(u.getUser_dni() != null){
			condClaves.add(COL_DNI);
			condValores.add(u.getUser_dni());
		}
		
		if(u.getUser_fecAlta() != null){
			// TODO
			condClaves.add(COL_FECHA_ALTA);
			condValores.add(u.getUser_fecAlta().toString());
		}
		
		if(u.getUser_fecConfirmacion() != null){
			// TODO
			condClaves.add(COL_FECHA_CONFIRM);
			condValores.add(u.getUser_fecConfirmacion().toString());
		}

		condClaves.add(COL_TIPO);
		condValores.add(String.valueOf(u.getUser_tipo()));
		
	}
	
	/**
	 * Rellena los arrays con el par clave-valor correspondientes al ID de la clase UsuarioBase
	 * @param id
	 * @param condClaves
	 * @param condValores
	 */
	public static void rellenaArrays(int id, List<String> condClaves, List<String> condValores){
		if(condClaves == null || condValores == null){
			condClaves = new ArrayList<String>();
			condValores = new ArrayList<String>();			
		}
		
		condClaves.add(COL_ID);
		condValores.add(String.valueOf(id));
	}
	
}
