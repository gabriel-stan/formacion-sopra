package es.rf.tienda.objetos.daos;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dominio.CategoriaBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.connection.ConnectionDAO;
import es.rf.tienda.util.dao.CategoriaDAOUtil;

public class CategoriaDAOTest {

	Categoria cat1;
	Categoria cat2;
	Categoria cat3;
	Categoria cat4;
	
	static CategoriaDAO catDAO;

	public final String NOMBRE1 = "@TEST Inciensos Asociados";
	public final String DESCRIPTION1 = "Estos inciensos son los mas olorosos del mundo";
	
	public final String NOMBRE2 = "@TEST Inciensos Pestosos light";
	public final String DESCRIPTION2 = "Estos inciensos son algo pestosos";
	
	public final String NOMBRE3 = "@TEST Inciensos Pestosos extra";
	public final String DESCRIPTION3 = "Estos inciensos son bastante pestosos";
	
	public final String NOMBRE4 = "@TEST Inciensos Pestosos pro";
	public final String DESCRIPTION4 = "Estos inciensos son los mas pestosos del mundo";
	
	@BeforeClass
	public static void setUp() throws DAOException{		
		catDAO = new CategoriaDAO();		
		ConnectionDAO.execute("DELETE FROM " + CategoriaDAOUtil.TABLE + " WHERE cat_nombre LIKE '@TEST%'");
		
	}
	
	@AfterClass
	public static void limpiar() throws DAOException{
		ConnectionDAO.execute("DELETE FROM " + CategoriaDAOUtil.TABLE + " WHERE cat_nombre LIKE '@TEST%'");
		ConnectionDAO.commit();
	}
	
	@Before
	public void init() {
		cat1 = new Categoria();
		cat2 = new Categoria();
		cat3 = new Categoria();
		cat4 = new Categoria();
	}
	
	@After
	public void after() throws DAOException {
		ConnectionDAO.execute("DELETE FROM " + CategoriaDAOUtil.TABLE + " WHERE cat_nombre LIKE '@TEST%'");
		ConnectionDAO.commit();		
	}

	@Test
	public void testGet() throws DAOException, DomainException {
		// inserto una categoria
		cat1.setCat_nombre(NOMBRE1);
		cat1.setCat_descripcion(DESCRIPTION1);
		
		catDAO.insert(cat1);
		ConnectionDAO.commit();
		
		//compruebo el get
		CategoriaBase c = new CategoriaBase();
		c.setCat_nombre(NOMBRE1);
		c.setCat_descripcion(DESCRIPTION1);
		
		c = catDAO.getObject(cat1.getId_categoria());
		assertEquals(NOMBRE1, c.getCat_nombre());
		assertEquals(DESCRIPTION1, c.getCat_descripcion());
		
	}

	@Test
	public void testGetAll() throws DAOException, DomainException {
		// inserto una categoria
		cat1.setCat_nombre(NOMBRE1);
		cat1.setCat_descripcion(DESCRIPTION1);
		
		cat2.setCat_nombre(NOMBRE1);
		cat2.setCat_descripcion(DESCRIPTION1);
		
		cat3.setCat_nombre(NOMBRE2);
		cat3.setCat_descripcion(DESCRIPTION2);

		catDAO.insert(cat1);
		catDAO.insert(cat2);
		catDAO.insert(cat3);
		ConnectionDAO.commit();
		
		List<CategoriaBase> categorias = catDAO.getAll();
		assertTrue(categorias.size()>=3);
		
		for (Iterator<CategoriaBase> iterator = categorias.iterator(); iterator.hasNext();) {
			CategoriaBase categoria = (CategoriaBase) iterator.next();
			if(!categoria.getCat_nombre().startsWith("@TEST")){
				iterator.remove();
			}
		}
		
		assertEquals(categorias.size(), 3);
		
		CategoriaBase aux = null;

		aux = categorias.get(0);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE1, aux.getCat_nombre());
		assertEquals(DESCRIPTION1, aux.getCat_descripcion());
		
		aux = categorias.get(1);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE1, aux.getCat_nombre());
		assertEquals(DESCRIPTION1, aux.getCat_descripcion());
		
		aux = categorias.get(2);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE2, aux.getCat_nombre());
		assertEquals(DESCRIPTION2, aux.getCat_descripcion());		
		
	}

	@Test
	public void testFilter() throws DAOException, DomainException {
		cat1.setCat_nombre(NOMBRE1);
		cat1.setCat_descripcion(DESCRIPTION1);
		
		cat2.setCat_nombre(NOMBRE1);
		cat2.setCat_descripcion(DESCRIPTION1);
		
		cat3.setCat_nombre(NOMBRE2);
		cat3.setCat_descripcion(DESCRIPTION2);

		catDAO.insert(cat1);
		catDAO.insert(cat2);
		catDAO.insert(cat3);
		ConnectionDAO.commit();
		
		// filtrar todos
		List<CategoriaBase> categorias = catDAO.filter(cat1.getCategoriaBase());
		assertEquals(categorias.size(), 2);
		
		CategoriaBase aux = null;

		aux = categorias.get(0);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE1, aux.getCat_nombre());
		assertEquals(DESCRIPTION1, aux.getCat_descripcion());
		
		aux = categorias.get(1);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE1, aux.getCat_nombre());
		assertEquals(DESCRIPTION1, aux.getCat_descripcion());
		
		
		// filtro por nombre
		cat4 = new Categoria();
		cat4.setCat_nombre(NOMBRE3);
		cat4.setCat_descripcion(DESCRIPTION3);
		
		catDAO.insert(cat4);

		cat4 = new Categoria();
		cat4.setCat_nombre(NOMBRE4);
		cat4.setCat_descripcion(DESCRIPTION4);
		
		catDAO.insert(cat4);
		ConnectionDAO.commit();
		

		cat4 = new Categoria();		
		cat4.setCat_nombre("Pestosos");
		
		categorias = catDAO.filter(cat4.getCategoriaBase());
		assertEquals(categorias.size(), 3);
		
		/*aux = null;

		aux = categorias.get(0);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE1, aux.getCat_nombre());
		assertEquals(DESCRIPTION1, aux.getCat_descripcion());
		
		aux = categorias.get(1);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE1, aux.getCat_nombre());
		assertEquals(DESCRIPTION1, aux.getCat_descripcion());
		
		aux = categorias.get(2);
		assertTrue(aux.isValid());
		assertEquals(NOMBRE1, aux.getCat_nombre());
		assertEquals(DESCRIPTION1, aux.getCat_descripcion());*/
		
		
	}

	@Test
	public void testInsert() throws DAOException, DomainException {
		cat1.setCat_nombre(NOMBRE1);
		cat1.setCat_descripcion(DESCRIPTION1);
		
		catDAO.insert(cat1);
		ConnectionDAO.commit();
	}

	@Test
	public void testUpdate() throws DAOException, DomainException {
		// inserto una categoria
		cat1.setCat_nombre(NOMBRE1);
		cat1.setCat_descripcion(DESCRIPTION1);
		
		catDAO.insert(cat1);
		ConnectionDAO.commit();
		
		// la modifico
		cat1.setCat_nombre(NOMBRE3);
		cat1.setCat_descripcion(DESCRIPTION4);
		
		catDAO.update(cat1);
		ConnectionDAO.commit();
		
		//compruebo el get
		CategoriaBase c =  catDAO.getObject(cat1.getId_categoria()); // genera excepcion de no encontrado
		
		assertEquals(cat1.getId_categoria(), c.getId_categoria());
		assertEquals(NOMBRE3, c.getCat_nombre());
		assertEquals(DESCRIPTION4, c.getCat_descripcion());
	}

	@Test(expected=DAOException.class)
	public void testDelete() throws DAOException, DomainException {
		// inserto una categoria
		cat1.setCat_nombre(NOMBRE1);
		cat1.setCat_descripcion(DESCRIPTION1);
		
		catDAO.insert(cat1);
		ConnectionDAO.commit();
		
		// la borro
		catDAO.delete(cat1);
		ConnectionDAO.commit();
		
		//compruebo el get
		catDAO.getObject(cat1.getId_categoria()); // genera excepcion de no encontrado
	}

}
