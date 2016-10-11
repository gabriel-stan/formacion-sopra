package es.rf.tienda.daos;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;

import es.rf.tienda.dominio.CategoriaBase;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.util.dao.CategoriaDAOUtil;
import es.rf.tienda.util.string.SQLUtils;

@Repository
public class CategoriaDAOH extends GenericDAO<CategoriaBase> {
	
	@Override
	String obtenWhere(CategoriaBase clase, String equalsOrLike, String andOr) throws DAOException {
		
		ArrayList<String> condClaves = new ArrayList<String>();
		ArrayList<String> condValores = new ArrayList<String>();
		
		if(clase.getCat_nombre() != null){
			condClaves.add(CategoriaDAOUtil.COL_NOMBRE);
			condValores.add(String.valueOf(clase.getCat_nombre()));				
		}
		
		if(clase.getCat_descripcion() != null){
			condClaves.add(CategoriaDAOUtil.COL_DESCRIPCION);
			condValores.add(String.valueOf(clase.getCat_descripcion()));				
		}

		return SQLUtils.creaWhere(condClaves, condValores, equalsOrLike, andOr);
	}

	@Override
	String obtenWhere(int id) throws DAOException {
		
		ArrayList<String> condClaves = new ArrayList<String>();
		ArrayList<String> condValores = new ArrayList<String>();
		
		if (id > 0) {
			condClaves.add(CategoriaDAOUtil.COL_ID);
			condValores.add(String.valueOf(id));
		}
		
		return SQLUtils.creaWhere(condClaves, condValores, SQLUtils.EQUALS, null);
	}

	@Override
	String getAllQuery() {
		return "from " + CategoriaDAOUtil.TABLE + " ORDER BY " + CategoriaDAOUtil.COL_ID;
	}

	@Override
	String getFilterQuery(CategoriaBase obj) throws DAOException {

		String where = obtenWhere(obj, SQLUtils.LIKE, SQLUtils.AND);
		return "from " + CategoriaDAOUtil.TABLE + " " + where;
	}

	@Override
	String getIDQuery(int id) throws DAOException {		
		String where = obtenWhere(id);
		return "from " + CategoriaDAOUtil.TABLE + " " + where;
	}
}
