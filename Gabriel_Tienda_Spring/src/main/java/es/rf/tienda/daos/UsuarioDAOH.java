package es.rf.tienda.daos;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import es.rf.tienda.dominio.UsuarioBase;
import es.rf.tienda.exception.DAOException;

import es.rf.tienda.util.dao.UsuarioDAOUtil;
import es.rf.tienda.util.string.SQLUtils;

@Repository
public class UsuarioDAOH extends GenericDAO<UsuarioBase> {

	@Override
	String obtenWhere(UsuarioBase obj, String equalsOrLike, String andOr) throws DAOException {
		
		ArrayList<String> condClaves = new ArrayList<String>();
		ArrayList<String> condValores = new ArrayList<String>();
		
		UsuarioDAOUtil.rellenaArrays(obj, condClaves, condValores);
				
		String where = SQLUtils.creaWhere(condClaves, condValores, equalsOrLike, andOr);

		return where;
	}
	
	@Override
	String obtenWhere(int id) throws DAOException {
		
		ArrayList<String> condClaves = new ArrayList<String>();
		ArrayList<String> condValores = new ArrayList<String>();
		
		UsuarioDAOUtil.rellenaArrays(id, condClaves, condValores);
		
		String where = SQLUtils.creaWhere(condClaves, condValores, SQLUtils.EQUALS, null);

		return where;
	}

	@Override
	String getAllQuery() {
		return "from " + UsuarioDAOUtil.TABLE + " ORDER BY " + UsuarioDAOUtil.COL_ID;
	}

	@Override
	String getFilterQuery(UsuarioBase obj) throws DAOException {		
		String where = obtenWhere(obj, SQLUtils.EQUALS, SQLUtils.AND);
		return "from " + UsuarioDAOUtil.TABLE + " " + where;
	}

	@Override
	String getIDQuery(int id) throws DAOException {		
		String where = obtenWhere(id);
		return "from " + UsuarioDAOUtil.TABLE + " " + where;
	}
	
}
