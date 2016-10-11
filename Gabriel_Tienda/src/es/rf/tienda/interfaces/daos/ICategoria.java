package es.rf.tienda.interfaces.daos;

import java.util.List;

import es.rf.tienda.dominio.CategoriaBase;

public interface ICategoria {
	public CategoriaBase getRegistro(int num);
	public List<CategoriaBase> lista(CategoriaBase c);
	public boolean guardar(CategoriaBase c);
}
