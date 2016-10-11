package es.rf.tienda.dominio;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre		Direccion
 * Descripcion	Lista de direcciones
 * @author 		Estibaliz Torralbo
 * @version		15 de sep. de 2016
 *
 */
public class Direccion {

	private String dir_nombre;			
	private String dir_direccion;		
	private String dir_poblacion;
	private String dir_cPostal;
	private String dir_provincia;
	private String dir_pais;
	private String dir_correoE;
	
	/**
	 * Getter para nombre de la categoria
	 * @return String con el nombre de la categoria
	 */	
	public String getDir_nombre() {
		return dir_nombre;
	}
	
	/**
	 * Setter para nombre de la categoria
	 * 
	 */
	public void setDir_nombre(String dir_nombre) throws DomainException {
		// se comprueba que el nombre es alfanumerico
		if(!Validator.isAlfanumeric(dir_nombre))
			throw new DomainException("dir_nombre. No es alfanumérico");
		this.dir_nombre = dir_nombre;
	}
	
	/**
	 * Getter para direccion de la categoria
	 * @return String con la direccion de la categoria
	 */	
	public String getDir_direccion() {
		return dir_direccion;
	}
	
	/**
	 * Setter para direccion de la categoria
	 * 
	 */
	public void setDir_direccion(String dir_direccion) throws DomainException {
		// se comprueba que la direccion es alfanumerica
		if(!Validator.isAlfanumeric(dir_direccion))
			throw new DomainException("dir_direccion. No es alfanumérico");
		this.dir_direccion = dir_direccion;
	}
	
	/**
	 * Getter para poblacion de la categoria
	 * @return String con la poblacion de la categoria
	 */	
	public String getDir_poblacion() {
		return dir_poblacion;
	}
	
	/**
	 * Setter para poblacion de la categoria
	 * 
	 */
	public void setDir_poblacion(String dir_poblacion) throws DomainException {		
		// se comprueba que la poblacion es alfanumerica
		if(!Validator.isAlfanumeric(dir_poblacion))
			throw new DomainException("dir_poblacion. No es alfanumérico");
		this.dir_poblacion = dir_poblacion;
	}
	
	/**
	 * Getter para codigo postal de la categoria
	 * @return String con el codigo postal de la categoria
	 */	
	public String getDir_cPostal() {
		return dir_cPostal;
	}
	
	/**
	 * Setter para codigo postal de la categoria
	 * 
	 */
	public void setDir_cPostal(String dir_cPostal) throws DomainException {
		// se comprueba que el codigo postal es alfanumerica
		if(!Validator.isAlfanumeric(dir_cPostal))
			throw new DomainException("dir_cPostal. No es alfanumérico");
		this.dir_cPostal = dir_cPostal;
	}
	
	/**
	 * Getter para provincia de la categoria
	 * @return String con el provincia de la categoria
	 */	
	public String getDir_provincia() {
		return dir_provincia;
	}
	
	/**
	 * Setter para provincia de la categoria
	 * 
	 */
	public void setDir_provincia(String dir_provincia) throws DomainException {		
		// se comprueba que la provincia es alfanumerica
		if(!Validator.isAlfanumeric(dir_provincia))
			throw new DomainException("dir_provincia. No es alfanumérico");
		this.dir_provincia = dir_provincia;
	}
	
	/**
	 * Getter para pais de la categoria
	 * @return String con el pais de la categoria
	 */	
	public String getDir_pais() {
		return dir_pais;
	}
	
	/**
	 * Setter para pais de la categoria
	 * 
	 */
	public void setDir_pais(String dir_pais) throws DomainException {
		// se comprueba que el pais es alfanumerico
		if(!Validator.isAlfanumeric(dir_pais))
			throw new DomainException("dir_pais. No es alfanumérico");
		this.dir_pais = dir_pais;
	}
	
	/**
	 * Getter para email de la categoria
	 * @return String con el email de la categoria
	 */	
	public String getDir_correoE() {
		return dir_correoE;
	}
	
	/**
	 * Setter para email de la categoria
	 * 
	 */
	public void setDir_correoE(String dir_correoE) throws DomainException {
		// se comprueba que el email es alfanumerico 
		if(!Validator.isAlfanumeric(dir_correoE))
			throw new DomainException("dir_correoE. No es alfanumérico");
		if(!Validator.isEmailValido(dir_correoE))
			throw new DomainException("dir_correoE. No es válido.");
		this.dir_correoE = dir_correoE;
	}
		
	@Override
	public String toString() {
		return "Dirección [dir_nombre=" + dir_nombre + ", dir_direccion=" + dir_direccion + ", dir_poblacion="
				+ dir_poblacion + ", dir_cPostal="+ dir_cPostal + ", dir_provincia="+ dir_provincia + ", dir_pais="+ dir_pais
				+ ", dir_correoE="+ dir_correoE +"]";
	}
}
