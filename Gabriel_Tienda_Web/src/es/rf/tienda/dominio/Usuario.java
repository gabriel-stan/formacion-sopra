package es.rf.tienda.dominio;

import java.util.Calendar;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.Validator;

/**
 * Nombre:		Usuario
 * Descripcion: Extiende de UsuarioBase e implementa las validaciones de los campos en los setters
 * 
 * @version 	26/09/2016
 * @author 		Gabriel Stan
 *
 */
public class Usuario {
	
	private UsuarioBase usuario;
	
	
	public Usuario() {
		this.usuario = new UsuarioBase();
	}
	
	public Usuario(UsuarioBase usuario){
		this.usuario = usuario;
	}
	
	public UsuarioBase getUsuarioBase() {
		return usuario;
	}


	public void setId_usuario(int id_usuario) throws DomainException {
		if(id_usuario <= 0){
			throw new DomainException(ErrorMessages.ERR_USUARIO_ID);
		}
		
		usuario.setId_usuario(id_usuario);
	}

	public void setUser_dni(String user_dni) throws DomainException {
		
		// se comprueba que el dni es alfanumerico
//		if(!Validator.isAlfanumeric(user_dni))
//			throw new DomainException("user_dni. No es alfanumérico");
		// se comprueba que el dni tiene la longitud correcta
		if (!Validator.cumpleLongitud(user_dni, Validator.USER_DNI_LONG, Validator.USER_DNI_LONG))
			throw new DomainException("user_dni."+ErrorMessages.PROERR_002);
		// se comprueba que el dni tiene el formato correcto
		if (!Validator.cumpleDNI(user_dni))
			throw new DomainException("user_dni. "+ErrorMessages.PROERR_001);
		
		usuario.setUser_dni(user_dni);
	}
	
	public void setUser_email(String user_email) throws DomainException {
		
		// se comprueba que el email tiene la longitud correcta
		if(!Validator.cumpleLongitud(user_email, Validator.USER_EMAIL_LONG_MIN, Validator.USER_EMAIL_LONG_MAX))
			throw new DomainException("user_email."+ErrorMessages.PROERR_002);
		// se comprueba que el email es alfanumerico
//		if(!Validator.isAlfanumeric(user_email))
//			throw new DomainException("user_email. No es alfanumerico");
		// se comprueba que el email tiene el formato correcto
		if (!Validator.isEmailValido(user_email))
			throw new DomainException("user_email. "+ErrorMessages.PROERR_001);
		
		usuario.setUser_email(user_email);
	}
	
	public void setUser_fecAlta(Calendar user_fecAlta) throws DomainException {
		
		// comprobamos que la fecha no es nula 
		if (user_fecAlta == null)
			throw new DomainException("user_fecAlta. Es nulo");
		// comprobamos que la fecha es igual a la actual
		if (!Validator.isFechaActual(user_fecAlta))
			throw new DomainException("user_fecAlta. La fecha no coincide con el dia actual");
		
		usuario.setUser_fecAlta(user_fecAlta);
	}
	
	public void setUser_fecConfirmacion(Calendar user_fecConfirmacion) throws DomainException {

		// comprobamos que la fecha no es nula 
		if (user_fecConfirmacion == null)
			throw new DomainException("user_fecConfirmacion. Es nulo");
		// comprobamos que la fecha es igual a la actual
		if (!Validator.isFechaActual(user_fecConfirmacion))
			throw new DomainException("user_fecAlta. La fecha no coincide con el dia actual");
		
		usuario.setUser_fecConfirmacion(user_fecConfirmacion);
	}
	
	public void setUser_nombre(String user_nombre) throws DomainException {
		
		// se comprueba que el nombre es alfanumerico
		if(!Validator.isAlfanumeric(user_nombre))
			throw new DomainException("user_nombre. No es alfanumerico");
		if(!Validator.cumpleLongitud(user_nombre, Validator.USER_NOMBRE_LONG_MIN, Validator.USER_NOMBRE_LONG_MAX))
			throw new DomainException("user_nombre."+ErrorMessages.PROERR_002);
		
		usuario.setUser_nombre(user_nombre);
	}
	
	public void setUser_pass(String user_pass) throws DomainException {

		// se comprueba que el pass tiene la longitud correcta
		if (!Validator.cumpleLongitud(user_pass, Validator.USER_PASS_LONG_MIN, Validator.USER_PASS_LONG_MAX))
			throw new DomainException("user_pass."+ErrorMessages.PROERR_002);
		// se comprueba que el pass tiene el formato correcto
		if (!Validator.esPasswordValida(user_pass))
			throw new DomainException("user_pass. "+ErrorMessages.PROERR_001);
		
		usuario.setUser_pass(user_pass);
	}
	
	public void setUser_tipo(int user_tipo) throws DomainException {
		
		// comprueba que el tipo de usuario toma valor 0,1,2,3,4 o 9
		if(!Validator.cumpleRango(user_tipo, 0, 4) && user_tipo!=9)
			throw new DomainException("user_tipo. El tipo debe ser [0,1,2,3,4,9]");
		
		usuario.setUser_tipo(user_tipo);
	}
	
	/*
	 * Metodos de UsuarioBase
	 */
	public boolean isValid(){
		return usuario.isValid();
	}
	
	public int getId_usuario() {
		return usuario.getId_usuario();
	}
	
	public String getUser_nombre() {
		return usuario.getUser_nombre();
	}

	public String getUser_email() {
		return usuario.getUser_email();
	}

	public String getUser_pass() {
		return usuario.getUser_pass();
	}

	public int getUser_tipo() {
		return usuario.getUser_tipo();
	}

	public String getUser_dni() {
		return usuario.getUser_dni();
	}

	public Calendar getUser_fecAlta() {
		return usuario.getUser_fecAlta();
	}

	public Calendar getUser_fecConfirmacion() {
		return usuario.getUser_fecConfirmacion();
	}	
	
	@Override
	public int hashCode() {
		return usuario.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return usuario.equals(obj);
	}

	@Override
	public String toString() {
		return usuario.toString();
	}
}
