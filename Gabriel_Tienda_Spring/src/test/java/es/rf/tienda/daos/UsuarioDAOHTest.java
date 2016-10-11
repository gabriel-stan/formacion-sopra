package es.rf.tienda.daos;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.dominio.UsuarioBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.connection.ConnectionDAO;
import es.rf.tienda.util.dao.UsuarioDAOUtil;

public class UsuarioDAOHTest {

	Usuario u1 = new Usuario();
	Usuario u2 = new Usuario();
	Usuario u3 = new Usuario();
	
	static UsuarioDAOH userDAO;

	public final String NOMBRE1 = "TESTusuario1";
	public final String EMAIL1 = "usuario1@tiendaweb.com";
	public final String PASS1 = "Usuario1$";
	public final int TIPO1 = 0;
	public final String DNI1 = "13.456.789-A";
	public final Calendar FECHA_ALTA_1 = Calendar.getInstance();
	public final Calendar FECHA_CONFIRMACION_1 = Calendar.getInstance();

	public final String NOMBRE2 = "TESTusuario2";
	public final String EMAIL2 = "usuario2@tiendaweb.com";
	public final String PASS2 = "Usuario2$";
	public final int TIPO2 = 1;
	public final String DNI2 = "13.456.789-B";
	public final Calendar FECHA_ALTA_2 = Calendar.getInstance();
	public final Calendar FECHA_CONFIRMACION_2 = Calendar.getInstance();

	@BeforeClass
	public static void setUp() {		
		userDAO = new UsuarioDAOH();		
		
		try {
			cleanUp();	
		} catch (Exception e) {
//			e.printStackTrace();			
		}
		
	}
	
	@AfterClass
	public static void cleanUp() throws DAOException{
		ConnectionDAO.execute("DELETE FROM " + UsuarioDAOUtil.TABLE + " WHERE " + UsuarioDAOUtil.COL_NOMBRE + " LIKE 'TEST%'");
		ConnectionDAO.commit();
	}
	
	@Before
	public void before() {
		u1 = new Usuario();
		u2 = new Usuario();
		u3 = new Usuario();
		
		try {
			cleanUp();
		} catch (DAOException e) {
			// e.printStackTrace();
		}	
	}
	
	@After
	public void after() {
		try {
			cleanUp();
		} catch (DAOException e) {
			// e.printStackTrace();
		}
	}
	
	@Test
	public void testGetObject() throws DomainException, DAOException {
		insertarUnUsuario();
		
		UsuarioBase aux = userDAO.getObject(u1.getId_usuario());
		
		assertEquals(aux, u1.getBaseObject());
		
	}

	@Test
	public void testGetAll() throws DAOException, DomainException {
		insertarDosUsuarios();
		
		assertTrue(userDAO.getAll().size() >= 2);
	}

	@Test
	public void testFilter() throws DomainException, DAOException {
		insertarDosUsuarios();
		
		u3.setUser_nombre(NOMBRE1);
		
		assertEquals(1, userDAO.filter(u3.getBaseObject()).size());
		
	}

	@Test
	public void testInsert() throws DomainException, DAOException {
		u1.setUser_nombre(NOMBRE1);
		u1.setUser_email(EMAIL1);
		u1.setUser_pass(PASS1);
		u1.setUser_tipo(TIPO1);
		u1.setUser_dni(DNI1);
		u1.setUser_fecAlta(FECHA_ALTA_1);
		u1.setUser_fecConfirmacion(FECHA_CONFIRMACION_1);
		
		assertEquals(1, userDAO.insert(u1));
	}

	@Test
	public void testUpdate() throws DomainException, DAOException {
		insertarUnUsuario();
		
		u1.setUser_dni(DNI2);
		
		assertEquals(1, userDAO.update(u1));
		
		UsuarioBase aux = userDAO.getObject(u1.getId_usuario());
		
		assertEquals(u1, aux);
	}

	@Test
	public void testDelete() throws DAOException, DomainException {
		insertarUnUsuario();
		
		assertEquals(1, userDAO.delete(u1));
		
	}
	
	private void insertarUnUsuario() throws DomainException, DAOException{
		u1.setUser_nombre(NOMBRE1);
		u1.setUser_email(EMAIL1);
		u1.setUser_pass(PASS1);
		u1.setUser_tipo(TIPO1);
		u1.setUser_dni(DNI1);
		u1.setUser_fecAlta(FECHA_ALTA_1);
		u1.setUser_fecConfirmacion(FECHA_CONFIRMACION_1);
		
		userDAO.insert(u1);
	}
	
	private void insertarDosUsuarios() throws DomainException, DAOException{
		u1.setUser_nombre(NOMBRE1);
		u1.setUser_email(EMAIL1);
		u1.setUser_pass(PASS1);
		u1.setUser_tipo(TIPO1);
		u1.setUser_dni(DNI1);
		u1.setUser_fecAlta(FECHA_ALTA_1);
		u1.setUser_fecConfirmacion(FECHA_CONFIRMACION_1);
		
		userDAO.insert(u1);
		
		u2.setUser_nombre(NOMBRE2);
		u2.setUser_email(EMAIL2);
		u2.setUser_pass(PASS2);
		u2.setUser_tipo(TIPO2);
		u2.setUser_dni(DNI2);
		u2.setUser_fecAlta(FECHA_ALTA_2);
		u2.setUser_fecConfirmacion(FECHA_CONFIRMACION_2);
		
		userDAO.insert(u2);		
	}

}
