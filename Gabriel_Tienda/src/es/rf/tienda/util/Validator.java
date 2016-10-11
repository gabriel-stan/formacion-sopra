package es.rf.tienda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.rf.tienda.util.string.StringUtils;


/********************************************************************************************
 * NOMBRE: Validator.java
 * 
 * DESCRIPCION: 
 * 			Clase auxiliar para validar los datos que sean 
 * 			introducidos en la aplicaci·n.
 * 
 *  @version	23/09/2016 
 *  @author 	Miguel Garcia
 *  @author 	Gabriel Stan
 *  
 * ******************************************************************************************/
public class Validator {
	
	private static final String ALFANUMERIC_PATTERN = "^[0-9a-zA-Z]+$";
	
	private static final String PASSWORD_PATTERN = 
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	/**
	 * PatrÛn para validar el email, evitando puntos finales antes de la @ y que solo contenga
	 * car·cteres alfanum·ricos		 
	 */
	private final static String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Permite verificar que un DNI cumple con el patr·n XX.XXX.XXX-L
	 */
	private final static String DNI_PATTERN = "\\d{2}\\.\\d{3}\\.\\d{3}-[a-zA-Z]";

	/**
	 * Permite verificar que una fecha cumple con el patrÛn dd/mm/aaaa
	 */
	private final static String FECHA_PATTERN = "\\d{2}\\/\\d{2}\\/\\d{4}";
		
	/**
	 * Permite validar un tel·fono, el cual debe contener de 10 a 20 d·gitos
	 * y donde los espacios est·n permitidos
	 */
	private final static String PHONE_PATTERN =  "[\\d ]{10,20}";
		
	/**
	 * Permite validar un id de producto con mayusuclas y numeros
	 */
	private final static String ID_PRODUCTO_PATTERN = "((?=.*\\d)(?=.*[A-Z]){5,5})";;
	
	/**
	 * Orden de las letras con las cuales se comprobar· la validez del DNI
	 */
	private final static String LETRA_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	/**
	 * Longitud que debe tener todo DNI pasado a la aplicaci·n.
	 */
	private final static int LONGITUD_DNI = 12;
	
	/**
	 * Precio minimo de un producto
	 */
	private final static double PRECIO_PRODUCTO_MIN = 0.00;
	
	/**
	 * Precio maximo de un producto
	 */
	private final static double PRECIO_PRODUCTO_MAX = 9999.99;
	
	
	public static final int USER_NOMBRE_LONG_MIN = 5;
	public static final int USER_NOMBRE_LONG_MAX = 50;
	public static final int USER_EMAIL_LONG_MIN = 6;
	public static final int USER_EMAIL_LONG_MAX = 100;
	public static final int USER_PASS_LONG_MIN = 6;
	public static final int USER_PASS_LONG_MAX = 20;
	public static final int USER_DNI_LONG = 12;
	
	

	/* ***************************************************************************************
	 * NOMBRE: isAlfanumeric                                                                 *
	 * 
	 * DESCRIPCI·N: *//**
	 * 		Permite verificar que el texto pasado solo contiene caracters alfanum·ricos
	 * 
	 * @param texto String a verificar que solo tenga car·cteres alfanum·ricos
	 * 
	 * @return  true, si cumple solo contiene caracters alfanum·ricos. <br> 
	 * 			false en caso contrario
	 * FECHA: Enero 2016
	 * 
	 * AUTOR: Miguel Garcia - Barcelona
	 * 
	 * **************************************************************************************/
	public static boolean isAlfanumeric(String texto){
		return texto.matches(ALFANUMERIC_PATTERN);
	}
	
	/**
	 * Comprueba si un String es null o vacio	
	 * @param prueba
	 * @return
	 */
	public static boolean isVacio( String prueba ){
		return (prueba==null || prueba.isEmpty()) ? true : false;				
	}
	
	/* ***************************************************************************************
	 * NOMBRE: cumplePhoneNumber                                                                 *
	 * 
	 * DESCRIPCI·N: *//**
	 * 		El phone number debe tener un total de entre 10 y 20, contando d·gitos y espacios.
	 * 		M·nimo aceptable son 10 d·gitos.
	 * 
	 * @param phoneNumber String con el n·mero de telefono de entre 10 y 20 d·gitos. 
	 * 		Puede tener espacios, pero siempre con 10 d·gitos como m·nimo.
	 * 
	 * @return true, si cumple todas las condiciones
	 *
	 * FECHA: Enero 2016
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean cumplePhoneNumber(String phoneNumber){
		
		if(phoneNumber == null)
			return false;
		
		if(phoneNumber.length() < 10 || phoneNumber.length() > 20)
			return false;
		
		if(phoneNumber.replace(" ", "").replace("(", "").replace(")", "").length() > 10)
			return false;
		
		if(!phoneNumber.replace(" ", "").replace("(", "").replace(")", "").matches(PHONE_PATTERN))
			return false;
		
		return true;
		
	}

	/* ***************************************************************************************
	 * NOMBRE: isEmailValido                                                                 *
	 * 
	 * DESCRIPCI·N: *//**
	 * 			Comprueba si el email tiene un formato que pueda ser v·lido.
	 * 
	 * @param email String a comprobar
	 * 
	 * @return true, en caso que el formato sea v·lido
	 * FECHA: Enero 2016
	 * 
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean isEmailValido(String email){
		return email.matches(EMAIL_PATTERN);
	}

	/* ***************************************************************************************
	 * NOMBRE: cumpleDNI                                                                 *
	 * 
	 * DESCRIPCI·N: *//**
	 * 			Esta funciÛn verifica que el DNI cumple el siguiente formato: xx.xxx.xxx-L <br>
	 * 			El DNI ha de tener longitud 12
	 * 
	 * @param dni String con DNI a ser validado
	 * 
	 * @return true, si el DNI cumple el estandar nacional.
	 * FECHA: Enero 2016
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean cumpleDNI(String dni){
//		Pattern pattern = Pattern.compile(DNI_PATTERN);
//		Matcher matcher = pattern.matcher(dni);
//		return matcher.matches();
		return dni.matches(DNI_PATTERN);
	}
	

	/** ***************************************************************************************
	 * NOMBRE: cumpleRango                                                                 *
	 * 
	 * DESCRIPCI·N: *//**
	 * 		Comprueba que un N˙mero se necuentra entre 2 valores
	 * 
	 * @param valor (int)/(double) N˙mero a comprobar
	 * @param valorMinimo (int) N˙mero valor aceptable
	 * @param valorMaximo (int) M·N˙mero valor aceptable
	 * 
	 * @return true si valorMinimo > valor > valorMaximo
	 * FECHA: Enero 2016
	 * AUTOR: Miguel Garcia 
	 * 
	 * **************************************************************************************/
	public static boolean cumpleRango(
			int valor, 
			int valorMinimo,
			int valorMaximo){
		
		return (valor >= valorMinimo && valor <= valorMaximo) ? true : false;
		
	}
	public static boolean cumpleRango(
			double valor, 
			int valorMinimo,
			int valorMaximo){
		
		return ( Double.compare(valor, valorMinimo) >= 0 && Double.compare(valor, valorMaximo) <= 0) ? true : false;
		//return (valor >= valorMinimo && valor <= valorMaximo) ? true : false;		
		
	}


	/* ***************************************************************************************
	 * NOMBRE: cumpleLongitudMin                                                                 *
	 * 
	 * DESCRIPCI·N: *//**
	 * 		Comprobar que el texto pasado tiene una longitud de al menos x caracteres, siendo
	 * 		x el entero pasado como par·metro
	 * 
	 * @param texto String texto a comprobar
	 * @param longitudMinima int que indique longitud m·nima.
	 * 
	 * @return cierto, si la longitud del texto es mayor o igual que longitudMinima
	 * FECHA: Enero 2016
	 * AUTOR: Miguel Garcia
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitudMin(
			String texto, 
			int longitudMinima){
		
		if(texto == null){
			return false;
		}
		
		return (texto.length() >= longitudMinima);
		
	}


	/* ***************************************************************************************
	 * NOMBRE: cumpleLongitudMax                                                                 *
	 * 
	 * DESCRIPCI·N: *//**
	 * 		Comprobar que el texto pasado tiene una longitud de, como mucho, x caracteres, siendo
	 * 		x el entero pasado como par·metro
	 * 
	 * @param texto String con el texto a comprobar
	 * @param longitudMaxima int con la longitud m·xima del texto
	 * 
	 * @return true, si el texto es menor o igual que la longitud m·xima.
	 * FECHA: Enero 2016 
	 * AUTOR: Miguel Garcia 
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitudMax(
			String texto, 
			int longitudMaxima){
		
		if(texto == null){
			return false;
		}
		
		return (texto.length() <= longitudMaxima);
		
	}


	/****************************************************************************************
	 * NOMBRE: cumpleLongitud                                                                 *
	 * 
	 * DESCRIPCI”N: *//**
	 * 		Comprobar que la longitud de un texto se encuentra entre unos valores m·ximos y m·nimos 
	 * 
	 * @param texto String con el texto que a validar
	 * @param longitudMinima (int) MÌnima longitud del texto
	 * @param longitudMaxima (int) M·xima longitud v·lida para el texo
	 * 
	 * @return true, si la longitud del texto cumple: longitudMinima
	 *               <= longitudTexto <=longitudMaxima
	 * FECHA: Enero 2016 
	 * AUTOR: Miguel Garcia - Barcelona
	 * 
	 * **************************************************************************************/
	public static boolean cumpleLongitud(
			String texto, 
			int longitudMinima, 
			int longitudMaxima){

		return cumpleLongitudMin(texto, longitudMinima) && cumpleLongitudMax(texto, longitudMaxima);

	}
	/**
	 * Valida una fecha calendar con mÌnimo min
	 * @param fecha
	 * @param min
	 * @return
	 */
	
	public static boolean valDateMin(Calendar fecha, Calendar min){
		return fecha.after(min);
	}
	
	/**
	 * Valida una fecha calendar con m·ximo max
	 * @param fecha
	 * @param max
	 * @return
	 */
	public static boolean valDateMax(Calendar fecha, Calendar max){
		return fecha.before(max);
	}		
	
	/**
	 * isFechaActual
	 * Recibe una fecha Calendar y comprueba si es igual a la fecha actual
	 * @param fecha
	 * @return
	 */
	public static boolean isFechaActual(Calendar fecha){
		return fecha.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)  && fecha.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR);	
	}
	
	/**
	 * esFechaValida
	 * Recibe una string con formato fecha dd/mm/aaaa y comprueba el formato
	 * @param fecha
	 * @return
	 */
	public static boolean esFechaValida(String fecha){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sdf.setLenient(false);
			cal.setTime(sdf.parse(fecha)); // comprobamos si es fecha valida
			return fecha.matches(FECHA_PATTERN); // comprobamos formato requerido
		} catch (ParseException e) {
			//e.printStackTrace(); // ha fallado - return false
		}
		
		return false;
	}
	
	/**
	 * Comprueba que dos fechas sean iguales
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	public static boolean sonFechasIguales(Calendar fecha1, Calendar fecha2){
		return StringUtils.calendarToString(fecha1).equals(StringUtils.calendarToString(fecha2));
	}
	
	/**
	 * Nombre esPasswordValida
	 * Descripcion Comprueba que la cadena recibida cumpla con lasnormas de contraseÒa
	 * @param password string con la contraseÒa introducida
	 * @return true si cumple con las especificaciones
	 */
	public static boolean esPasswordValida(String password){
		return password.matches(PASSWORD_PATTERN);
	}

	/**
	 * Comprueba que el id del producto sea valido
	 * @param id_producto
	 * @return
	 */
	public static boolean isIdProductoValido(String id_producto) {
		return id_producto.matches(ID_PRODUCTO_PATTERN);
	}

	/**
	 * Comprueba que el precio de un producto este en el rango determinado
	 * @param pro_precio
	 * @return
	 */
	public static boolean isPrecioValido(double pro_precio) {
		return pro_precio >= PRECIO_PRODUCTO_MIN && pro_precio <= PRECIO_PRODUCTO_MAX;
	}
}
