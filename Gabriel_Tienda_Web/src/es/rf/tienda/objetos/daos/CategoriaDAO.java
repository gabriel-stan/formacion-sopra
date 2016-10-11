package es.rf.tienda.objetos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dominio.CategoriaBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.InterfaceDAO;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.connection.ConnectionDAO;
import es.rf.tienda.util.dao.CategoriaDAOUtil;
import es.rf.tienda.util.dao.CategoriaDAOUtil.*;
import es.rf.tienda.util.string.SQLUtils;

public class CategoriaDAO implements InterfaceDAO<CategoriaBase, Categoria> {

	@Override
	public CategoriaBase getObject(int id) throws DAOException {
		String sql = "";
		ArrayList<String> condClaves = new ArrayList<>();
		ArrayList<String> condValores = new ArrayList<>();
		
		CategoriaDAOUtil.rellenaArraysWhere(id, condClaves, condValores);
		
		if(condClaves.isEmpty() || condValores.isEmpty()){
			// no me han pasado parametros
			throw new DAOException("No se han pasado parametros");
		}
		
		sql = SQLUtils.creaSelect(null, CategoriaDAOUtil.TABLE, condClaves, condValores, SQLUtils.EQUALS, SQLUtils.AND);
		ResultSet rs = ConnectionDAO.executeQuery(sql);
		
		return CategoriaDAOUtil.construirCategoria(rs, sql);
		
	}

	@Override
	public List<CategoriaBase> getAll() throws DAOException {
		String sql = SQLUtils.creaSelect(SQLUtils.ALL, CategoriaDAOUtil.TABLE, null, null, null);
		
		ResultSet rs = ConnectionDAO.executeQuery(sql);
		
		return CategoriaDAOUtil.construirCategorias(rs, sql);
		
	}

	@Override
	public List<CategoriaBase> filter(CategoriaBase c) throws DAOException {
		String sql = "";

		ArrayList<String> condClaves = new ArrayList<>();
		ArrayList<String> condValores = new ArrayList<>();
		
		CategoriaDAOUtil.rellenaArraysWhere(c, condClaves, condValores);		
		
		if(condClaves.isEmpty() || condValores.isEmpty()){
			// no me han pasado parametros
			throw new DAOException("No se han pasado parametros");
		}
		
		sql = SQLUtils.creaSelect(null, CategoriaDAOUtil.TABLE, condClaves, condValores, SQLUtils.LIKE, SQLUtils.OR);
		
		ResultSet rs = ConnectionDAO.executeQuery(sql);
		
		return CategoriaDAOUtil.construirCategorias(rs, sql);

	}

	@Override
	public int insert(Categoria c) throws DAOException {
		
		try {
			c.setId_categoria(ConnectionDAO.getNextKey(CategoriaDAOUtil.TABLE, CategoriaDAOUtil.COL_ID));
		} catch (DomainException e) {
			throw new DAOException(ErrorMessages.ERR_CATEGORIA_ID, e);
		}
		
		if(c.isValid()){
			
			ArrayList<String> columnas = new ArrayList<String>();
			ArrayList<String> valores = new ArrayList<String>();
			
			CategoriaDAOUtil.rellenaArraysInsert(c.getCategoriaBase(), columnas, valores);
			
			String sql = SQLUtils.creaInsert(CategoriaDAOUtil.TABLE, columnas, valores);
			
			return ConnectionDAO.execute(sql);
		} else {
			throw new DAOException("Los campos de la categoria no son validos");
		}
	}

	@Override
	public int update(Categoria c) throws DAOException {
		
		if(c.isValid()){			
			ArrayList<String> setClaves = new ArrayList<String>();
			ArrayList<String> setValores = new ArrayList<String>();
			
			CategoriaDAOUtil.rellenaArraysInsert(c.getCategoriaBase(), setClaves, setValores);
			
			String sql = SQLUtils.creaUpdate(CategoriaDAOUtil.TABLE, setClaves, setValores, CategoriaDAOUtil.COL_ID, String.valueOf(c.getId_categoria()));
			
			return ConnectionDAO.execute(sql);
		} else {
			throw new DAOException("Los campos de la categoria no son validos");
		}
	}

	@Override
	public int delete(Categoria c) throws DAOException {
		
		if(c.getId_categoria() > 0){

			String sql = SQLUtils.creaDelete(CategoriaDAOUtil.TABLE, CategoriaDAOUtil.COL_ID, String.valueOf(c.getId_categoria()));
			
			return ConnectionDAO.execute(sql);			
		} else {
			throw new DAOException("No se ha indicado id de categoria a borrar");			
		}
	}
	
	

}
