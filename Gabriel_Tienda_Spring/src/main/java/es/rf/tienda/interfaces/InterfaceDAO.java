package es.rf.tienda.interfaces;

import java.util.List;

import es.rf.tienda.exception.DAOException;


public interface InterfaceDAO<T> {
	
	/**
	 * Obtener un objeto de tipo T
	 * @param c Objeto de tipo T con los parametros a buscar
	 * @return Objeto encontrado
	 * @throws DAOException 
	 */
	public T getObject(int id) throws DAOException;
	
	/** 
	 * Obtener todos los objetos de tipo T
	 * @return Lista de objetos
	 */
	public List<T> getAll() throws DAOException;
	
	/**
	 * Filtrar objetos de tipo T por campos
	 * @param c Objeto de tipo T con campos a filtrar
	 * @return Lista de objetos de tipo T
	 */
	public List<T> filter(T obj) throws DAOException;
	
	/**
	 * Insertar objeto de tipo T
	 * @param c de tipo T a insertar
	 * @return numero objetos insertados
	 * @throws DAOException
	 */
	public int insert(ValidatedObject<T> obj) throws DAOException;
	
	/**
	 * Actualiza un objeto de tipo T
	 * @param c Objeto de tipo T a actualizar
	 * @return numero de objetos actualizados
	 * @throws DAOException
	 */
	public int update(ValidatedObject<T> obj) throws DAOException;
	
	/**
	 * Borra un objeto de tipo T
	 * @param c Objeto de tipo T a borrar
	 * @return numero de objetos borrados
	 * @throws DAOException
	 */
	public int delete(ValidatedObject<T> obj) throws DAOException;
	
}
