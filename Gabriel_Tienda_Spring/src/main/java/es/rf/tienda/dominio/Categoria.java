package es.rf.tienda.dominio;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.ValidatedObject;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.Validator;

public class Categoria implements ValidatedObject<CategoriaBase>{
	
	/**
	 * Objeto categoria
	 */
	private CategoriaBase categoria;

	public Categoria() {
		this.categoria = new CategoriaBase();
	}

	public Categoria(CategoriaBase cat) {
		this.categoria = cat;
	}

	@Override
	public CategoriaBase getBaseObject() {
		return categoria;
	}
	
	
	/**
	 * Inserta el ID de la categoria realizando las validaciones
	 * @param id_categoria
	 * @throws DomainException
	 */
	public void setId_categoria(int id_categoria) throws DomainException {
		if(id_categoria <= 0){
			throw new DomainException(ErrorMessages.ERR_CATEGORIA_ID);
		}
		categoria.setId_categoria(id_categoria);
	}
	
	/**
	 * Inserta el nombre de la categoria realizando las validaciones
	 * @param cat_nombre
	 * @throws DomainException
	 */
	public void setCat_nombre(String cat_nombre) throws DomainException {
		if(Validator.isVacio(cat_nombre)){
			throw new DomainException(ErrorMessages.ERR_CATEGORIA_NOMBRE);			
		}
		categoria.setCat_nombre(cat_nombre);
	}
		
	/*
	 * Metodos de CategoriaBase
	 */
	public boolean isValid(){	
		return categoria.isValid();
	}
	
	public int getId_categoria() {
		return categoria.getId_categoria();
	}
	
	public String getCat_nombre() {
		return categoria.getCat_nombre();
	}
		
	public String getCat_descripcion() {
		return categoria.getCat_descripcion();
	}
	
	public void setCat_descripcion(String cat_descripcion) {
		categoria.setCat_descripcion(cat_descripcion);
	}


	@Override
	public int hashCode() {
		return categoria.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		return categoria.equals(obj);
	}


	@Override
	public String toString() {
		return categoria.toString();
	}

}
