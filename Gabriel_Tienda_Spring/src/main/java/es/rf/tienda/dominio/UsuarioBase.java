package es.rf.tienda.dominio;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre		UsuarioBase
 * Descripcion	Lista de usuarios base para los DAO
 * @author 		Estibaliz Torralbo
 * @author		Gabriel Stan
 * @version		26/09/2016
 *
 */

@Entity
public class UsuarioBase {

	/**
	 * ID del usuario
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id_usuario;
	
	/**
	 * Nombre del usuario
	 */
	private String user_nombre;
	
	/**
	 * Email del usuario
	 */
	@Column(unique=true)
	private String user_email;

	/**
	 * Contrasena del usuario
	 */
	private String user_pass;

	/**
	 * Tipo de usuario
	 * (Visitante: 0, Usuario: 1; Cliente: 2; Empleado: 3; Terceros: 4; Administrador: 9)
	 */
	private int user_tipo;

	/**
	 * DNI del usuario
	 */
	private String user_dni;
	
	/**
	 * Fecha de alta del usuario
	 */
	private Calendar user_fecAlta;

	/**
	 * Fecha de confirmacion del usuario
	 */
	private Calendar user_fecConfirmacion;
	
	
	/**
	 * Constructor vacio
	 */
	public UsuarioBase() {}
	
	/**
	 * Comprueba que los datos del usuario pasan las validaciones requeridas
	 * @return
	 */
	public boolean isValid(){	
		// comprobamos los campos obligatorios 
		return id_usuario>0 && !Validator.isVacio(user_nombre) && !Validator.isVacio(user_email) 
				&& !Validator.isVacio(user_pass) && ((user_tipo>=0 && user_tipo<=4) || user_tipo == 9);
	}
	
	/**
	 * Getter para identificador de usuario
	 * @return Integer con el id del usuario
	 */
	public int getId_usuario() {
		return id_usuario;
	}
	
	/**
	 * Setter para identificador de usuario
	 * @throws DomainException 
	 * 
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	/**
	 * Getter para nombre de usuario
	 * @return Integer con el id del usuario
	 */
	public String getUser_nombre() {
		return user_nombre;
	}
	
	/**
	 * Setter para nombre de usuario
	 * @throws DomainException 
	 * 
	 */	
	public void setUser_nombre(String user_nombre) {		
		this.user_nombre = user_nombre;
	}
	
	/**
	 * Getter para email de usuario
	 * @return Integer con el id del usuario
	 */
	public String getUser_email() {
		return user_email;
	}
	
	/**
	 * Setter para email de usuario
	 * @throws DomainException 
	 * 
	 */	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	/**
	 * Getter para contraseña de usuario
	 * @return Integer con el id del usuario
	 */
	public String getUser_pass() {
		return user_pass;
	}
	
	/**
	 * Setter para contraseña de usuario
	 * @throws DomainException 
	 * 
	 */
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	
	/**
	 * Getter para tipo de usuario
	 * @return Integer con el id del usuario
	 */
	public int getUser_tipo() {
		return user_tipo;
	}
	
	/**
	 * Setter para tipo de usuario
	 * @throws DomainException 
	 * 
	 */
	public void setUser_tipo(int user_tipo) {
		this.user_tipo = user_tipo;
	}
	
	/**
	 * Getter para dni de usuario
	 * @return Integer con el id del usuario
	 */
	public String getUser_dni() {
		return user_dni;
	}
	
	/**
	 * Setter para dni de usuario
	 * @throws DomainException 
	 * 
	 */	
	public void setUser_dni(String user_dni) {
		this.user_dni = user_dni;
	}
	
	/**
	 * Getter para fecha de alta de usuario
	 * @return Integer con el id del usuario
	 */
	public Calendar getUser_fecAlta() {
		return user_fecAlta;
	}

	/**
	 * Setter para fecha de alta de usuario
	 * @throws DomainException 
	 * 
	 */	
	public void setUser_fecAlta(Calendar user_fecAlta) {
		this.user_fecAlta = user_fecAlta;
	}
	
	/**
	 * Getter para fecha de confirmacion de usuario
	 * @return Integer con el id del usuario
	 */
	public Calendar getUser_fecConfirmacion() {
		return user_fecConfirmacion;
	}
	
	/**
	 * Setter para confirmacion de alta de usuario
	 * @throws DomainException 
	 * 
	 */	
	public void setUser_fecConfirmacion(Calendar user_fecConfirmacion) {
		this.user_fecConfirmacion = user_fecConfirmacion;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_usuario;
		result = prime * result + ((user_dni == null) ? 0 : user_dni.hashCode());
		result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result + ((user_fecAlta == null) ? 0 : user_fecAlta.hashCode());
		result = prime * result + ((user_fecConfirmacion == null) ? 0 : user_fecConfirmacion.hashCode());
		result = prime * result + ((user_nombre == null) ? 0 : user_nombre.hashCode());
		result = prime * result + ((user_pass == null) ? 0 : user_pass.hashCode());
		result = prime * result + user_tipo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioBase other = (UsuarioBase) obj;
		if (id_usuario != other.id_usuario)
			return false;
		if (user_dni == null) {
			if (other.user_dni != null)
				return false;
		} else if (!user_dni.equals(other.user_dni))
			return false;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (user_fecAlta == null) {
			if (other.user_fecAlta != null)
				return false;
		} else if (user_fecAlta.get(Calendar.YEAR)!=other.user_fecAlta.get(Calendar.YEAR) || user_fecAlta.get(Calendar.DAY_OF_YEAR)!=other.user_fecAlta.get(Calendar.DAY_OF_YEAR)) 
			return false;
		if (user_fecConfirmacion == null) {
			if (other.user_fecConfirmacion != null)
				return false;
		} else if (user_fecConfirmacion.get(Calendar.YEAR)!=other.user_fecConfirmacion.get(Calendar.YEAR) || user_fecConfirmacion.get(Calendar.DAY_OF_YEAR)!=other.user_fecConfirmacion.get(Calendar.DAY_OF_YEAR))
			return false;
		if (user_nombre == null) {
			if (other.user_nombre != null)
				return false;
		} else if (!user_nombre.equals(other.user_nombre))
			return false;
		if (user_pass == null) {
			if (other.user_pass != null)
				return false;
		} else if (!user_pass.equals(other.user_pass))
			return false;
		if (user_tipo != other.user_tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", user_nombre=" + user_nombre + ", user_email="
				+ user_email + ", user_pass="+ user_pass + ", user_tipo="+ user_tipo + ", user_dni="+ user_dni
				+ ", user_fecAlta="+ user_fecAlta + ", user_fecConfirmacion="+ user_fecConfirmacion+"]";
	}
	
}
