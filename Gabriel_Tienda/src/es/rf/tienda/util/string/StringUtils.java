package es.rf.tienda.util.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.Validator;

public class StringUtils {
	
	/**
	 * Crea una lista de campos separados por coma, rodeados por parentesis y comillas simples segun indicado
	 * @param lista
	 * @return
	 */
	public static String crearLista(List<String> lista, boolean parentesis, boolean comillas){
		
		String salida = " ";
		
		if(lista!=null && !lista.isEmpty()){
			
			if(parentesis)
				salida = "(";
			
			salida += addSalida(null, lista.get(0), null, null, comillas);
			
			for (int i = 1; i < lista.size(); i++) {
				salida += addSalida(null, lista.get(i), null, ",", comillas);
			}
			
			if(parentesis)
				salida += ")";
		}
		
		return salida;
	}
	
	/**
	 * Crea una lista de tipo clave=valor separados por el separador indicado
	 * @param claves
	 * @param valores
	 * @param separador puede ser " , "   o   " AND "   o   " OR "   etc
	 * @return
	 * @throws DAOException
	 */
	public static String crearListaClaveValor(List<String> claves, List<String> valores, String equalsOrLike, String separador) throws DAOException{

		String salida = " ";
		
		if(claves!=null && !claves.isEmpty() && valores!=null && !valores.isEmpty()){
			if(claves.size() == valores.size()){
				
				salida += addSalida(claves.get(0), valores.get(0), equalsOrLike, null, true);
				
				for (int i = 1; i < claves.size(); i++) {
					salida += addSalida(claves.get(i), valores.get(i), equalsOrLike, separador, true);
				}				
				
			} else {
				throw new DAOException("Las claves y valores no coinciden");
			}
		}
		
		return salida;
	}
	
	/**
	 * Construye un String con campo y valor, precedido del separador indicado
	 * @param nombreCampo
	 * @param valor
	 * @param separador
	 * @return
	 */
	public static String addSalida(String nombreCampo, int valor, String equalsOrLike, String separador){
		return addSalidaSimple(nombreCampo, String.valueOf(valor), equalsOrLike, separador);
	}
	
	public static String addSalida(String nombreCampo, long valor, String equalsOrLike, String separador){
		return addSalidaSimple(nombreCampo, String.valueOf(valor), equalsOrLike, separador);
	}
	
	public static String addSalida(String nombreCampo, double valor, String equalsOrLike, String separador){
		return addSalidaSimple(nombreCampo, String.valueOf(valor), equalsOrLike, separador);
	}
	
	public static String addSalida(String nombreCampo, String valor, String equalsOrLike, String separador, boolean comillas){
		if(Validator.isVacio(valor)){
			valor = "";
		}
		
		if(equalsOrLike != null && equalsOrLike.equals(SQLUtils.LIKE)){
			valor = "%" + valor + "%";
		}	
		
		if(comillas)
			valor = "'" + valor + "'";
		
		return addSalidaSimple(nombreCampo, valor, equalsOrLike, separador);
	}
	
	/**
	 * Devuelve un String para clausulas WHERE con la pareja clave-valor segun los valores, comparador y separador indicado
	 * @param nombreCampo
	 * @param valor
	 * @param equalsOrLike
	 * @param separador
	 * @return
	 */
	private static String addSalidaSimple(String nombreCampo, String valor, String equalsOrLike, String separador){
		String salida = "";
		
		if(separador != null){
			salida += " " + separador + " ";				
		} else {
			salida += " ";
		}
		
		if(!Validator.isVacio(nombreCampo)){
			if(equalsOrLike == null){
				equalsOrLike = "=";
			}
			
			salida += nombreCampo + " " + equalsOrLike + " ";
		}	
		
		salida += valor;
		
		return salida;
	}

	
	/**
	 * Construye un string para ponerlo en la lista de valores de una query
	 * @param s
	 * @return
	 */
	public static String parseStringToSQLValue(String s){
		if(!Validator.isVacio(s)){
			return "'" + s + "'";
		} else {
			return "null";
		}
	}
	
	/**
	 * Pasa un objeto Calendar a un String con formato dd/MM/yyyy
	 * @param cal
	 * @return
	 */
	public static String calendarToString(Calendar cal){		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(cal.getTime());
				
		return fecha;
	}
	
	/**
	 * Pasa un String con formato dd/MM/yyyy a un objeto Calendar
	 * @param fecha
	 * @return
	 * @throws DomainException
	 */
	public static Calendar stringToCalendar(String fecha) throws DomainException{
		
		if(!Validator.esFechaValida(fecha)){
			throw new DomainException(ErrorMessages.ERR_FECHA_FORMAT);
		}
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sdf.setLenient(false);
			cal.setTime(sdf.parse(fecha)); 	// comprobamos si es fecha valida
			return cal;
		} catch (ParseException e) {
			throw new DomainException(ErrorMessages.ERR_FECHA_FORMAT, e);
		}		
		
	}

}
