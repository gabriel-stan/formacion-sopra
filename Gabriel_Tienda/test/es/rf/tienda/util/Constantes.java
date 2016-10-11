package es.rf.tienda.util;

public class Constantes {
	
	// emails
	public final static String EMAIL_OK1 = "pepe@sopra.com";
	public final static String EMAIL_OK2 = "pepe@sopra.com.es";
	public final static String EMAIL_OK3 = "pepe123@sopra.com";
	public final static String EMAIL_OK4 = "pepe.garcia@sopra.com";
	public final static String EMAIL_OK5 = "pepe_garcia@sopra.com";
	
	public final static String EMAIL_FAIL1 = "pepe@sopracom";
	public final static String EMAIL_FAIL2 = "pepe @sopra.com";
	public final static String EMAIL_FAIL3 = "@sopra.com";
	public final static String EMAIL_FAIL4 = "pepe.sopra.com";
	public final static String EMAIL_FAIL5 = "pepe@.com";
	public final static String EMAIL_FAIL6 = "pepe";
	
	// nombre usuario
	public final static String NOMBRE_USUARIO_OK1 = "pepitoperez";
	public final static String NOMBRE_USUARIO_OK2 = "pepitoperez123";
	public final static String NOMBRE_USUARIO_OK3 = "123pepitoperez";
	public final static String NOMBRE_USUARIO_OK4 = "12345";
	public final static String NOMBRE_USUARIO_OK5 = "pepitoperez111112222z111112222z111112222z111112222";

	public final static String NOMBRE_USUARIO_FAIL1 = "1234";
	public final static String NOMBRE_USUARIO_FAIL2 = "pepitoperez111112222z111112222z111112222z1111122220";
	public final static String NOMBRE_USUARIO_FAIL3 = "pepitoperez$%#";
	
	// pass usuario
	public final static String PASS_USUARIO_OK1 = "Pepi1%";
	public final static String PASS_USUARIO_OK2 = "SiValido@4";
	public final static String PASS_USUARIO_OK3 = "pepitoPerezpepitoP5$";

	public final static String PASS_USUARIO_FAIL1 = "pepe";
	public final static String PASS_USUARIO_FAIL2 = "pe1@";
	public final static String PASS_USUARIO_FAIL3 = "pepitoperezpepitoperez";	
	public final static String PASS_USUARIO_FAIL4 = "pepitoperezpepiToper4#";	
	public final static String PASS_USUARIO_FAIL5 = "pepitoperez&";
	public final static String PASS_USUARIO_FAIL6 = "pepitoperez4";

	// alfanumericos
	public final static String ALFA_NUMERIC_OK = "123AB";
	public final static String ALFA_NUMERIC_FAIL = "123AB-%";
	public final static String COD_NUMERIC = "12345";
	public final static String COD_ALFA = "ABCDE";
	
	public final static String VACIO = "";

	// telefono
	public final static String PHONE_OK1 = "1234567890";
	public final static String PHONE_OK2 = "123 456 7890";
	public final static String PHONE_OK3 = "(123) 4567890";
	public final static String PHONE_OK4 = "123 456 78     90   ";
	
	public final static String PHONE_FAIL1 = "123456789";
	public final static String PHONE_FAIL2 = "123456789012345678901";
	public final static String PHONE_FAIL3 = "123 456 78 a";
	public final static String PHONE_FAIL4 = "123 456 78 %";

	// DNIs
	public final static String DNI_OK1 = "12.345.678-A";
	public final static String DNI_OK2 = "12.345.678-b";
	
	public final static String DNI_FAIL1 = "1.345.678-b";
	public final static String DNI_FAIL2 = "12345.678-b";
	public final static String DNI_FAIL3 = "as.345.678-b";
	public final static String DNI_FAIL4 = "1^.345.678-b";
	public final static String DNI_FAIL5 = "1..345.678-b";
	public final static String DNI_FAIL6 = "12.345.678--";
	public final static String DNI_FAIL7 = "12.345.678-9";
	public final static String DNI_FAIL8 = "12.345.678-";
	public final static String DNI_FAIL9 = "12.345.678-b2";
	public final static String DNI_FAIL10 = "12.345.678-bM";
	public final static String DNI_FAIL11 = "12345678B";
	public final static String DNI_FAIL12 = "12.345.678.b";
	
	// longitud
	public final static String LONGITUD_MIN_4_OK1 = "1234";
	public final static String LONGITUD_MIN_4_OK2 = "12345";
	public final static String LONGITUD_MIN_4_FAIL1 = "123";

	public final static String LONGITUD_MAX_8_OK1 = "1234567";
	public final static String LONGITUD_MAX_8_OK2 = "12345678";
	public final static String LONGITUD_MAX_8_FAIL1 = "123456789";
	
	//fechas
	public final static String FECHA_OK = "22/02/2016";
	public final static String FECHA_FAIL1 = "32/02/2016";
	public final static String FECHA_FAIL2 = "00/02/2016";
	public final static String FECHA_FAIL3 = "2/02/2016";
	public final static String FECHA_FAIL4 = "-2/02/2016";
	public final static String FECHA_FAIL5 = "22/2/2016";
	public final static String FECHA_FAIL6 = "22/2/201";
	public final static String FECHA_FAIL7 = "22-02-2016";
	public final static String FECHA_FAIL8 = "/02/2016";
	public final static String FECHA_FAIL9 = "22//2016";
	public final static String FECHA_FAIL10 = "22/2016";
	public final static String FECHA_FAIL11 = "22/02";
	public final static String FECHA_FAIL12 = "22/02/";
	public final static String FECHA_FAIL13 = "2a/02/2016";
	public final static String FECHA_FAIL14 = "22/0a/2016";
	public final static String FECHA_FAIL15 = "22/02/201g";

}
