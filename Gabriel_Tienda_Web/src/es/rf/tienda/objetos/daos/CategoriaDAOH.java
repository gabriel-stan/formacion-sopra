package es.rf.tienda.objetos.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dominio.CategoriaBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.util.connection.HibernateUtil;
import es.rf.tienda.util.dao.CategoriaDAOUtil;
import es.rf.tienda.util.string.SQLUtils;
import es.rf.tienda.interfaces.daos.InterfaceDAO;

public class CategoriaDAOH extends Observable implements InterfaceDAO<CategoriaBase, Categoria> {

	private Session sesion;
	private Transaction tx;

	@Override
	public List<CategoriaBase> getAll() throws DAOException {
		List<CategoriaBase> listaBase = new ArrayList<CategoriaBase>();
		
		try {
			iniciaOperacion();
			listaBase = sesion.createQuery("from " + CategoriaDAOUtil.TABLE + " ORDER BY " + CategoriaDAOUtil.COL_ID).list();
		} finally {
			sesion.close();
		}
		
		return listaBase;
	}

	@Override
	public List<CategoriaBase> filter(CategoriaBase clase) throws DAOException {
				
		String where = obtenWhere(clase, SQLUtils.LIKE, SQLUtils.AND);
		
		List<CategoriaBase> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery("from " + CategoriaDAOUtil.TABLE + " " + where).list();
		} finally {
			sesion.close();
		}
		return lista;
	}

	@Override
	public CategoriaBase getObject(int id) throws DAOException {
		CategoriaBase cat = new CategoriaBase();
		cat.setId_categoria(id);
		
		List<CategoriaBase> lista = filter(cat);
		if (lista.size() == 1) {
			return lista.get(0);
		} else
			throw new DAOException("Demasiados registros en Categorias");
	}

	@Override
	public int update(Categoria clase) throws DAOException {
		CategoriaBase cat = clase.getCategoriaBase();
		try {
			iniciaOperacion();
			sesion.update(cat);
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
	public int insert(Categoria clase) throws DAOException {
		CategoriaBase cat = clase.getCategoriaBase();
		
		try {
			iniciaOperacion();
			sesion.save(cat);
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
	public int delete(Categoria clase) throws DAOException {
		CategoriaBase cat = clase.getCategoriaBase();
		try {
			iniciaOperacion();
			sesion.delete(cat);
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
	 * Devuelve un String con el formato "WHERE ..." a partir de los parametros dados
	 * @param clase
	 * @param equalsOrLike
	 * @param andOr
	 * @return
	 * @throws DAOException
	 */
	private String obtenWhere(CategoriaBase clase, String equalsOrLike, String andOr) throws DAOException {
		
		ArrayList<String> condClaves = new ArrayList<>();
		ArrayList<String> condValores = new ArrayList<>();
		
		if (clase.getId_categoria() > 0) {
			condClaves.add(CategoriaDAOUtil.COL_ID);
			condValores.add(String.valueOf(clase.getId_categoria()));
		} else {
			if(clase.getCat_nombre() != null){
				condClaves.add(CategoriaDAOUtil.COL_NOMBRE);
				condValores.add(String.valueOf(clase.getCat_nombre()));				
			}
			if(clase.getCat_descripcion() != null){
				condClaves.add(CategoriaDAOUtil.COL_DESCRIPCION);
				condValores.add(String.valueOf(clase.getCat_descripcion()));				
			}
		}
		
		String where = SQLUtils.creaWhere(condClaves, condValores, equalsOrLike, andOr);

		return where;
	}

//	private String obtenLista(Categoria clase, String separador) {
//		String salida = "";
//		if (clase.getId_categoria() > 0) {
//			salida = Rutinas.addSalida(salida, "idCategoria", clase.getIdCategoria(), separador);
//		}
//		if (clase.getTituloCategoria() != null && clase.getTituloCategoria().compareTo("") != 0) {
//			salida = Rutinas.addSalida(salida, "tituloCategoria", clase.getTituloCategoria(), separador);
//		}
//		return salida;
//	}

	private void iniciaOperacion() throws HibernateException {
		sesion = HibernateUtil.getSession();
		tx = sesion.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}
}
