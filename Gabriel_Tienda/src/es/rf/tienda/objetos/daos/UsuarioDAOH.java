package es.rf.tienda.objetos.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.dominio.UsuarioBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.interfaces.daos.InterfaceDAO;
import es.rf.tienda.util.connection.HibernateUtil;
import es.rf.tienda.util.dao.CategoriaDAOUtil;
import es.rf.tienda.util.dao.UsuarioDAOUtil;
import es.rf.tienda.util.string.SQLUtils;

public class UsuarioDAOH implements InterfaceDAO<UsuarioBase, Usuario> {
	
	private Session sesion;
	private Transaction tx;

	@Override
	public UsuarioBase getObject(int id) throws DAOException {
		
		String where = obtenWhere(id, SQLUtils.EQUALS);
		
		List<UsuarioBase> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery("from " + UsuarioDAOUtil.TABLE + " " + where).list();
		} finally {
			sesion.close();
		}

		if (lista.size() == 1) {
			return lista.get(0);
		} else
			throw new DAOException("Demasiados registros en Categorias");
	}

	@Override
	public List<UsuarioBase> getAll() throws DAOException {
		List<UsuarioBase> listaBase = new ArrayList<UsuarioBase>();
		
		try {
			iniciaOperacion();
			listaBase = sesion.createQuery("from " + UsuarioDAOUtil.TABLE + " ORDER BY " + UsuarioDAOUtil.COL_ID).list();
		} finally {
			sesion.close();
		}
		
		return listaBase;
	}

	@Override
	public List<UsuarioBase> filter(UsuarioBase obj) throws DAOException {
				
		String where = obtenWhere(obj, SQLUtils.EQUALS, SQLUtils.AND);
		
		List<UsuarioBase> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery("from " + UsuarioDAOUtil.TABLE + " " + where).list();
		} finally {
			sesion.close();
		}
		return lista;
	}

	@Override
	public int insert(Usuario obj) throws DAOException {
		UsuarioBase usuario = obj.getUsuarioBase();
		
		try {
			iniciaOperacion();
			sesion.save(usuario);
			tx.commit();
//			clase.setId_categoria((int) id);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			if (sesion!=null)
				sesion.close();
		}
		return 1;}

	@Override
	public int update(Usuario obj) throws DAOException {
		UsuarioBase usuario = obj.getUsuarioBase();
		try {
			iniciaOperacion();
			sesion.update(usuario);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		return 1;
	}

	@Override
	public int delete(Usuario obj) throws DAOException {
		UsuarioBase usuario = obj.getUsuarioBase();
		try {
			iniciaOperacion();
			sesion.delete(usuario);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		return 1;
	}

	private String obtenWhere(UsuarioBase u, String equalsOrLike, String andOr) throws DAOException {
		
		ArrayList<String> condClaves = new ArrayList<>();
		ArrayList<String> condValores = new ArrayList<>();
		
		UsuarioDAOUtil.rellenaArrays(u, condClaves, condValores);
				
		String where = SQLUtils.creaWhere(condClaves, condValores, equalsOrLike, andOr);

		return where;
	}

	private String obtenWhere(int id, String equalsOrLike) throws DAOException {
		
		ArrayList<String> condClaves = new ArrayList<>();
		ArrayList<String> condValores = new ArrayList<>();
		
		UsuarioDAOUtil.rellenaArrays(id, condClaves, condValores);
				
		String where = SQLUtils.creaWhere(condClaves, condValores, equalsOrLike, null);

		return where;
	}

	private void iniciaOperacion() throws HibernateException {
		sesion = HibernateUtil.getSession();
		tx = sesion.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}
	

}
