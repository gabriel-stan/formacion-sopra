package es.rf.tienda.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DomainException;

public class CategoriaTest {
	
	Categoria prueba;

	final String NOMBRE = "Nombre de categoria";
	final String DESCRIPCION = "Descripcion de categoria";
	
	@Before
	public void init() {
		prueba = new Categoria();
	}

	@Test
	public void testIsValid() throws DomainException {
		prueba.setCat_nombre(NOMBRE);
		prueba.setCat_descripcion(DESCRIPCION);
		prueba.setId_categoria(10);
		assertTrue(prueba.isValid());
	}

	@Test
	public void testIsValid2() throws DomainException {
		prueba.setCat_descripcion(DESCRIPCION);
		prueba.setId_categoria(10);
		assertFalse(prueba.isValid());
	}

	@Test
	public void testIsValid3() throws DomainException {
		prueba.setCat_nombre(NOMBRE);
		prueba.setCat_descripcion(DESCRIPCION);
		assertFalse(prueba.isValid());
	}

	@Test
	public void testSetId_categoria() throws DomainException {
		prueba.setId_categoria(10);
		assertEquals("ID categoria", 10, prueba.getId_categoria());
	}

	@Test
	public void testSetCat_nombre() throws DomainException {
		prueba.setCat_nombre(NOMBRE);
		assertEquals("Nombre categoria", NOMBRE, prueba.getCat_nombre());
	}

	@Test
	public void testSetCat_descripcion() {
		prueba.setCat_descripcion(DESCRIPCION);
		assertEquals("Descripcion Categoria", DESCRIPCION, prueba.getCat_descripcion());
	}

}
