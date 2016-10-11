package es.rf.tienda.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.interfaces.InterfaceDAO;
import es.rf.tienda.interfaces.ValidatedObject;
import es.rf.tienda.util.connection.HibernateUtil;

public abstract class GenericDAO<T> extends Observable implements InterfaceDAO<T> {
	
	private Session sesion;
	private Transaction tx;

	@Override
	@SuppressWarnings("unchecked")
	public T getObject(int id) throws DAOException {		
		List<T> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery(getIDQuery(id)).list();
		} finally {
			sesion.close();
		}

		if (lista.size() == 1) {
			return lista.get(0);
		} else
			throw new DAOException("Demasiados registros");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() throws DAOException {
		List<T> listaBase = new ArrayList<T>();
		
		try {
			iniciaOperacion();
			listaBase = sesion.createQuery(getAllQuery()).list();
		} finally {
			sesion.close();
		}
		
		return listaBase;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> filter(T obj) throws DAOException {		
		List<T> lista = null;
		
		try {
			iniciaOperacion();
			lista = sesion.createQuery(getFilterQuery(obj)).list();
		} finally {
			sesion.close();
		}
		return lista;
	}

	@Override
	public int insert(ValidatedObject<T> obj) throws DAOException {
		try {
			iniciaOperacion();
			sesion.save(obj.getBaseObject());
			tx.commit();
//			clase.setId_categoria((int) id);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			if (sesion!=null)
				sesion.close();
		}
		
		// notificar cambios
        setChanged();
		notifyObservers();
		
		return 1;
	}

	@Override
	public int update(ValidatedObject<T> obj) throws DAOException {
		try {
			iniciaOperacion();
			sesion.update(obj.getBaseObject());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
		// notificar cambios
        setChanged();
		notifyObservers();
		
		return 1;
	}

	@Override
	public int delete(ValidatedObject<T> obj) throws DAOException {
		try {
			iniciaOperacion();
			sesion.delete(obj.getBaseObject());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
		// notificar cambios
        setChanged();
		notifyObservers();
		
		return 1;
	}

	/**
	 * Prepara la conexion con la BD
	 * @throws HibernateException
	 */
	private void iniciaOperacion() throws HibernateException {
		sesion = HibernateUtil.getSession();
		tx = sesion.beginTransaction();
	}

	/**
	 * Maneja las excepciones que pueden saltar de la conexion a la BD
	 * @param he
	 * @throws HibernateException
	 */
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}

	/**
	 * Devuelve un String con el formato "WHERE ..." a partir de los parametros dados excepto el ID
	 * @param clase
	 * @param equalsOrLike
	 * @param andOr
	 * @return
	 * @throws DAOException
	 */
	abstract String obtenWhere(T obj, String equalsOrLike, String andOr) throws DAOException;

	/**
	 * Devuelve un String con el formato "WHERE ..." a partir del ID proporcionado
	 * @param id
	 * @param equalsOrLike
	 * @return
	 * @throws DAOException
	 */
	abstract String obtenWhere(int id) throws DAOException;
	
	/**
	 * Devuelve la query para usar en la funcion getAll
	 * @return
	 */
	abstract String getAllQuery();
	
	/**
	 * Devuelve la query para usar en la funcion filter
	 * @return
	 * @throws DAOException 
	 */
	abstract String getFilterQuery(T obj) throws DAOException;
	
	/**
	 * Devuelve la query para usar en la funcion getObject
	 * @return
	 * @throws DAOException 
	 */
	abstract String getIDQuery(int id) throws DAOException;
}
