package es.rf.tienda.util;


/* *****************************************************
 * NOMBRE: ErrorMessages.java
 * 
 * DESCRIPCION:  
 * 			Clase con los String que contienen los mensajes de error 
 * 			especificados por las reglas de negocio.
 * 
 *  @version	Enero 2016
 *  
 *  @author 	Miguel Garcia
 *  @author 	Gabriel Stan
 *  
 *  *****************************************************/
public class ErrorMessages {
	
	/**
	 * Categoria
	 */
	public final static String ERR_CATEGORIA_ID = "El ID de la categoria debe ser mayor que 0";
	public final static String ERR_CATEGORIA_NOMBRE = "El nombre de la categoria no puede ser null ni vacio";
	
	/**
	 * Usuario
	 */
	public final static String ERR_USUARIO_ID = "El ID del usuario debe ser mayor que 0";
		
	/**
	 * Codigo de producto
	 */
	public final static String PROERR_001 = "Formato codigo erroneo";
	public final static String PROERR_002 = "Longitud de codigo erroneo";
	
	/**
	 * Campo con longitud erronea
	 */
	public final static String PROERR_003 = "La longitud de ? ha de estar entre ? y ?";
	
	/**
	 * Fecha
	 */
	public final static String ERR_FECHA_FORMAT = "El formato de la fecha proporcionada es incorrecto";

}
