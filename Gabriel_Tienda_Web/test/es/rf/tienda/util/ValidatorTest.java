package es.rf.tienda.util;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class ValidatorTest {

	static Calendar fechaActual;
	static Calendar fechaAnterior;
	static Calendar fechaPosterior;
	
	static {
		fechaActual = Calendar.getInstance();
		fechaAnterior = Calendar.getInstance();
		fechaAnterior.add(Calendar.DAY_OF_YEAR, -1);
		fechaPosterior = Calendar.getInstance();
		fechaPosterior.add(Calendar.DAY_OF_YEAR, +1);
	}
	
	@Test
	public void testIsAlfanumeric() {
		assertFalse(Validator.isAlfanumeric(Constantes.ALFA_NUMERIC_FAIL));
	}
	
	@Test
	public void testIsAlfanumeric2() {
		assertTrue(Validator.isAlfanumeric(Constantes.ALFA_NUMERIC_OK));
	}
	
	@Test
	public void testIsAlfanumeric3() {
		assertTrue(Validator.isAlfanumeric(Constantes.COD_ALFA));
	}
	
	@Test
	public void testIsAlfanumeric4() {
		assertTrue(Validator.isAlfanumeric(Constantes.COD_NUMERIC));
	}

	@Test
	public void testIsVacio() {
		assertTrue(Validator.isVacio(Constantes.VACIO));
	}

	@Test
	public void testIsVacio2() {
		assertTrue(Validator.isVacio(null));
	}

	@Test
	public void testIsVacio3() {
		assertFalse(Validator.isVacio(Constantes.ALFA_NUMERIC_OK));
	}

	@Test
	public void testCumplePhoneNumber() {
		assertTrue(Validator.cumplePhoneNumber(Constantes.PHONE_OK1));
		assertTrue(Validator.cumplePhoneNumber(Constantes.PHONE_OK2));
		assertTrue(Validator.cumplePhoneNumber(Constantes.PHONE_OK3));
		assertTrue(Validator.cumplePhoneNumber(Constantes.PHONE_OK4));
	}

	@Test
	public void testCumplePhoneNumber2() {
		assertFalse(Validator.cumplePhoneNumber(null));
		assertFalse(Validator.cumplePhoneNumber(Constantes.PHONE_FAIL1));
		assertFalse(Validator.cumplePhoneNumber(Constantes.PHONE_FAIL2));
		assertFalse(Validator.cumplePhoneNumber(Constantes.PHONE_FAIL3));
		assertFalse(Validator.cumplePhoneNumber(Constantes.PHONE_FAIL4));
	}

	@Test
	public void testIsEmailValido() {
		assertTrue(Validator.isEmailValido(Constantes.EMAIL_OK1));
		assertTrue(Validator.isEmailValido(Constantes.EMAIL_OK2));
		assertTrue(Validator.isEmailValido(Constantes.EMAIL_OK3));
		assertTrue(Validator.isEmailValido(Constantes.EMAIL_OK4));
		assertTrue(Validator.isEmailValido(Constantes.EMAIL_OK5));
	}

	@Test
	public void testIsEmailValido2() {
		assertFalse(Validator.isEmailValido(Constantes.EMAIL_FAIL1));
		assertFalse(Validator.isEmailValido(Constantes.EMAIL_FAIL2));
		assertFalse(Validator.isEmailValido(Constantes.EMAIL_FAIL3));
		assertFalse(Validator.isEmailValido(Constantes.EMAIL_FAIL4));
		assertFalse(Validator.isEmailValido(Constantes.EMAIL_FAIL5));
		assertFalse(Validator.isEmailValido(Constantes.EMAIL_FAIL6));
	}

	@Test
	public void testCumpleDNI() {
		assertTrue(Validator.cumpleDNI(Constantes.DNI_OK1));
		assertTrue(Validator.cumpleDNI(Constantes.DNI_OK2));
	}

	@Test
	public void testCumpleDNI2() {
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL1));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL2));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL3));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL4));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL5));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL6));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL7));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL8));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL9));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL10));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL11));
		assertFalse(Validator.cumpleDNI(Constantes.DNI_FAIL12));
	}

	@Test
	public void testCumpleRangoIntIntInt() {
		assertTrue(Validator.cumpleRango(5, 0, 6));
		assertTrue(Validator.cumpleRango(0, 0, 1));
		assertTrue(Validator.cumpleRango(0, -1, 6));
		assertTrue(Validator.cumpleRango(-5, -7, -5));
		assertTrue(Validator.cumpleRango(Integer.MAX_VALUE, 0, Integer.MAX_VALUE));
	}

	@Test
	public void testCumpleRangoIntIntInt2() {
		assertFalse(Validator.cumpleRango(2, 6, 9));
		assertFalse(Validator.cumpleRango(6, 9, 3));
		assertFalse(Validator.cumpleRango(12, 9, 3));
		assertFalse(Validator.cumpleRango(-5, -10, -6));
	}

	@Test
	public void testCumpleRangoDoubleIntInt() {
		assertTrue(Validator.cumpleRango(5.012, 0, 6));
		assertTrue(Validator.cumpleRango(0.01, 0, 1));
		assertTrue(Validator.cumpleRango(0.00, 0, 1));
		assertTrue(Validator.cumpleRango(0.999, 0, 1));
		assertTrue(Validator.cumpleRango(1.00000, 0, 1));
		assertTrue(Validator.cumpleRango(1.0, 0, 1));
		assertTrue(Validator.cumpleRango(0.5, -1, 6));
		assertTrue(Validator.cumpleRango(-5.1564, -7, -5));
	}

	@Test
	public void testCumpleRangoDoubleIntInt2() {
		assertFalse(Validator.cumpleRango(2.56, 6, 9));
		assertFalse(Validator.cumpleRango(5.9999, 6, 9));
		assertFalse(Validator.cumpleRango(9.00001, 6, 9));
		assertFalse(Validator.cumpleRango(6.56, 9, 3));
		assertFalse(Validator.cumpleRango(12.7, 9, 3));
		assertFalse(Validator.cumpleRango(-5.89, -10, -6));
	}

	@Test
	public void testCumpleLongitudMin() {
		assertTrue(Validator.cumpleLongitudMin(Constantes.LONGITUD_MIN_4_OK1, 4));
		assertTrue(Validator.cumpleLongitudMin(Constantes.LONGITUD_MIN_4_OK2, 4));
	}

	@Test
	public void testCumpleLongitudMin2() {
		assertFalse(Validator.cumpleLongitudMin(Constantes.LONGITUD_MIN_4_FAIL1, 4));
	}

	@Test
	public void testCumpleLongitudMax() {
		assertTrue(Validator.cumpleLongitudMax(Constantes.LONGITUD_MAX_8_OK1, 8));
		assertTrue(Validator.cumpleLongitudMax(Constantes.LONGITUD_MAX_8_OK2, 8));
	}

	@Test
	public void testCumpleLongitudMax2() {
		assertFalse(Validator.cumpleLongitudMax(Constantes.LONGITUD_MAX_8_FAIL1, 8));
	}

	@Test
	public void testCumpleLongitud() {
		assertTrue(Validator.cumpleLongitud(Constantes.LONGITUD_MIN_4_OK2, 4, 8));
	}

	@Test
	public void testCumpleLongitud2() {
		assertFalse(Validator.cumpleLongitud(Constantes.LONGITUD_MIN_4_FAIL1, 4, 8));
		assertFalse(Validator.cumpleLongitud(Constantes.LONGITUD_MAX_8_FAIL1, 4, 8));
	}

	@Test
	public void testValDateMin() {
		assertTrue(Validator.valDateMin(fechaActual, fechaAnterior));
	}

	@Test
	public void testValDateMin2() {
		assertFalse(Validator.valDateMin(fechaAnterior, fechaActual));
	}

	@Test
	public void testValDateMax() {
		assertTrue(Validator.valDateMax(fechaActual, fechaPosterior));		
	}

	@Test
	public void testValDateMax2() {
		assertFalse(Validator.valDateMax(fechaPosterior, fechaActual));		
	}

	@Test
	public void testEsFechaValida() {
		assertTrue(Validator.esFechaValida(Constantes.FECHA_OK));
	}

	@Test
	public void testEsFechaValida2() {
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL1));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL2));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL3));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL4));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL5));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL6));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL7));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL8));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL9));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL10));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL11));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL12));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL13));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL14));
		assertFalse(Validator.esFechaValida(Constantes.FECHA_FAIL15));
	}

	@Test
	public void testEsPasswordValida() {
		assertTrue(Validator.esPasswordValida(Constantes.PASS_USUARIO_OK1));
		assertTrue(Validator.esPasswordValida(Constantes.PASS_USUARIO_OK2));
		assertTrue(Validator.esPasswordValida(Constantes.PASS_USUARIO_OK3));
	}

	@Test
	public void testEsPasswordValida2() {
		assertFalse(Validator.esPasswordValida(Constantes.PASS_USUARIO_FAIL1));
		assertFalse(Validator.esPasswordValida(Constantes.PASS_USUARIO_FAIL2));
		assertFalse(Validator.esPasswordValida(Constantes.PASS_USUARIO_FAIL3));
		assertFalse(Validator.esPasswordValida(Constantes.PASS_USUARIO_FAIL4));
		assertFalse(Validator.esPasswordValida(Constantes.PASS_USUARIO_FAIL5));
		assertFalse(Validator.esPasswordValida(Constantes.PASS_USUARIO_FAIL6));
	}

}
