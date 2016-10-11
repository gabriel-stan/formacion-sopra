package es.rf.tienda.dominio;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.Constantes;
import es.rf.tienda.util.Validator;
import es.rf.tienda.util.string.StringUtils;

public class UsuarioTest {
	
	Usuario usuario;
	
	@Before
	public void init() {
		usuario = new Usuario();
	}

	@Test
	public void testIsValid() throws DomainException {
		usuario.setId_usuario(1);
		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_OK1);
		usuario.setUser_pass(Constantes.PASS_USUARIO_OK1);
		usuario.setUser_dni(Constantes.DNI_OK1);
		usuario.setUser_email(Constantes.EMAIL_OK1);
		usuario.setUser_tipo(0);
		usuario.setUser_fecAlta(Calendar.getInstance());
		usuario.setUser_fecConfirmacion(Calendar.getInstance());
		
		assertTrue(usuario.isValid());
	}

	@Test
	public void testSetId_usuario() throws DomainException {
		usuario.setId_usuario(10);
		assertEquals(10, usuario.getId_usuario());
	}

	@Test
	public void testSetUser_nombre() throws DomainException {
		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_OK1);
		assertEquals(Constantes.NOMBRE_USUARIO_OK1, usuario.getUser_nombre());

		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_OK2);
		assertEquals(Constantes.NOMBRE_USUARIO_OK2, usuario.getUser_nombre());

		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_OK3);
		assertEquals(Constantes.NOMBRE_USUARIO_OK3, usuario.getUser_nombre());

		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_OK4);
		assertEquals(Constantes.NOMBRE_USUARIO_OK4, usuario.getUser_nombre());

		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_OK5);
		assertEquals(Constantes.NOMBRE_USUARIO_OK5, usuario.getUser_nombre());
	}

	@Test (expected=DomainException.class)
	public void testSetUser_nombre2() throws DomainException {
		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_FAIL1);
	}

	@Test (expected=DomainException.class)
	public void testSetUser_nombre3() throws DomainException {
		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_FAIL2);
	}

	@Test (expected=DomainException.class)
	public void testSetUser_nombre4() throws DomainException {
		usuario.setUser_nombre(Constantes.NOMBRE_USUARIO_FAIL3);
	}
	
	@Test
	public void testSetUser_email() throws DomainException {
		usuario.setUser_email(Constantes.EMAIL_OK1);
		assertEquals(Constantes.EMAIL_OK1, usuario.getUser_email());

		usuario.setUser_email(Constantes.EMAIL_OK2);
		assertEquals(Constantes.EMAIL_OK2, usuario.getUser_email());

		usuario.setUser_email(Constantes.EMAIL_OK3);
		assertEquals(Constantes.EMAIL_OK3, usuario.getUser_email());

		usuario.setUser_email(Constantes.EMAIL_OK4);
		assertEquals(Constantes.EMAIL_OK4, usuario.getUser_email());

		usuario.setUser_email(Constantes.EMAIL_OK5);
		assertEquals(Constantes.EMAIL_OK5, usuario.getUser_email());		
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_email2() throws DomainException {
		usuario.setUser_email(Constantes.EMAIL_FAIL1);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_email3() throws DomainException {
		usuario.setUser_email(Constantes.EMAIL_FAIL2);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_email4() throws DomainException {
		usuario.setUser_email(Constantes.EMAIL_FAIL3);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_email5() throws DomainException {
		usuario.setUser_email(Constantes.EMAIL_FAIL4);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_email6() throws DomainException {
		usuario.setUser_email(Constantes.EMAIL_FAIL5);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_email7() throws DomainException {
		usuario.setUser_email(Constantes.EMAIL_FAIL6);
	}

	@Test
	public void testSetUser_pass() throws DomainException {
		usuario.setUser_pass(Constantes.PASS_USUARIO_OK1);
		assertEquals(Constantes.PASS_USUARIO_OK1, usuario.getUser_pass());
		
		usuario.setUser_pass(Constantes.PASS_USUARIO_OK2);
		assertEquals(Constantes.PASS_USUARIO_OK2, usuario.getUser_pass());
		
		usuario.setUser_pass(Constantes.PASS_USUARIO_OK3);
		assertEquals(Constantes.PASS_USUARIO_OK3, usuario.getUser_pass());
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_pass2() throws DomainException {
		usuario.setUser_pass(Constantes.PASS_USUARIO_FAIL1);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_pass3() throws DomainException {
		usuario.setUser_pass(Constantes.PASS_USUARIO_FAIL2);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_pass4() throws DomainException {
		usuario.setUser_pass(Constantes.PASS_USUARIO_FAIL3);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_pass5() throws DomainException {
		usuario.setUser_pass(Constantes.PASS_USUARIO_FAIL4);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_pass6() throws DomainException {
		usuario.setUser_pass(Constantes.PASS_USUARIO_FAIL5);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_pass7() throws DomainException {
		usuario.setUser_pass(Constantes.PASS_USUARIO_FAIL6);
	}

	@Test
	public void testSetUser_tipo() throws DomainException {
		usuario.setUser_tipo(0);
		assertEquals(0, usuario.getUser_tipo());

		usuario.setUser_tipo(1);
		assertEquals(1, usuario.getUser_tipo());

		usuario.setUser_tipo(2);
		assertEquals(2, usuario.getUser_tipo());

		usuario.setUser_tipo(3);
		assertEquals(3, usuario.getUser_tipo());

		usuario.setUser_tipo(4);
		assertEquals(4, usuario.getUser_tipo());

		usuario.setUser_tipo(9);
		assertEquals(9, usuario.getUser_tipo());
		
	}

	@Test (expected=DomainException.class)
	public void testSetUser_tipo3() throws DomainException {
		usuario.setUser_tipo(-1);
	}

	@Test (expected=DomainException.class)
	public void testSetUser_tipo4() throws DomainException {
		usuario.setUser_tipo(5);
	}

	@Test (expected=DomainException.class)
	public void testSetUser_tipo5() throws DomainException {
		usuario.setUser_tipo(6);
	}

	@Test (expected=DomainException.class)
	public void testSetUser_tipo6() throws DomainException {
		usuario.setUser_tipo(7);
	}

	@Test (expected=DomainException.class)
	public void testSetUser_tipo7() throws DomainException {
		usuario.setUser_tipo(8);
	}

	@Test (expected=DomainException.class)
	public void testSetUser_tipo8() throws DomainException {
		usuario.setUser_tipo(10);
	}

	@Test
	public void testSetUser_dni() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_OK1);
		assertEquals(Constantes.DNI_OK1, usuario.getUser_dni());

		usuario.setUser_dni(Constantes.DNI_OK2);
		assertEquals(Constantes.DNI_OK2, usuario.getUser_dni());
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni2() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL1);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni3() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL2);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni4() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL3);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni5() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL4);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni6() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL5);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni7() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL6);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni8() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL7);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni9() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL8);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni10() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL9);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni11() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL10);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni12() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL11);
	}
	
	@Test (expected=DomainException.class)
	public void testSetUser_dni13() throws DomainException {
		usuario.setUser_dni(Constantes.DNI_FAIL12);
	}

	@Test
	public void testSetUser_fecAlta() throws DAOException, DomainException {
		usuario.getBaseObject().setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_OK));
		assertTrue(Validator.sonFechasIguales(StringUtils.stringToCalendar(Constantes.FECHA_OK), usuario.getUser_fecAlta()));
		
		usuario.setUser_fecAlta(Calendar.getInstance());
		assertTrue(Validator.sonFechasIguales(Calendar.getInstance(), usuario.getUser_fecAlta()));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta1() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL1));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta2() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL2));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta3() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL3));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta4() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL4));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta5() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL5));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta6() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL6));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta7() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL7));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta8() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL8));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta9() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL9));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta10() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL10));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta11() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL11));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta12() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL12));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta13() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL13));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta14() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL14));
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecAlta15() throws DAOException, DomainException {
		usuario.setUser_fecAlta(StringUtils.stringToCalendar(Constantes.FECHA_FAIL15));
	}

	@Test
	public void testSetUser_fecConfirmacion() throws DomainException {
		usuario.getBaseObject().setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_OK));
		assertTrue(Validator.sonFechasIguales(StringUtils.stringToCalendar(Constantes.FECHA_OK), usuario.getUser_fecConfirmacion()));
		
		usuario.setUser_fecConfirmacion(Calendar.getInstance());
		assertTrue(Validator.sonFechasIguales(Calendar.getInstance(), usuario.getUser_fecConfirmacion()));
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL1));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL2));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL3));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL4));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL5));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL6));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL7));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL8));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL9));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL10));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL11));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL12));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL13));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL14));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
		
		try {
			usuario.setUser_fecConfirmacion(StringUtils.stringToCalendar(Constantes.FECHA_FAIL15));
			fail("Se esperaba una excepcion DomainException");
		} catch (DomainException e) {
			// pass
		}
	}

}
