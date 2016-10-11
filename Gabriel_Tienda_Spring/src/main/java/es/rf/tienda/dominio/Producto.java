package es.rf.tienda.dominio;

import java.util.Calendar;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre		Producto
 * Descripcion	Lista de productos
 * @author 		Estibaliz Torralbo
 * @author 		Gabriel Stan
 * @version		28 de sep. de 2016
 *
 */
public class Producto {
	
	private String id_producto;
	private String pro_descripcion;
	private String pro_desLarga;
	private double pro_precio;
	private int pro_stock;
	private Calendar pro_fecRepos;
	private Calendar fecActi;
	private Calendar pro_fecDesacti;
	private String pro_uniVenta;
	private double pro_cantXUniVenta;
	private String pro_uniUltNivel;
	private int id_pais;
	private String pro_usoRecomendado;
	private int id_categoria;
	private int pro_stkReservado;
	private int pro_nStkAlto;
	private int pro_nStkBajo;
	private char pro_stat = 'A';
	
	private static final int PRO_ID_LONG = 5;
	private static final int PRO_ID_DESCRIPCION_MIN = 5;
	private static final int PRO_ID_DESCRIPCION_MAX = 100;
	private static final int PRO_ID_DESCRIPCION_LARGA_MAX = 2000;
	private static final int PRO_UNIVENTA_MIN = 1;
	private static final int PRO_UNIVENTA_MAX = 10;
	private static final int PRO_USORECOMENDADO_MIN = 0;
	private static final int PRO_USORECOMENDADO_MAX = 2000;
	
	
	/**
	 * Comprueba la validez del objeto
	 * @return boolean true si el objeto es valido, falso si no es valido
	 */
	public boolean isValid() {
		// comprobamos los campos obligatorios
		return !Validator.isVacio(id_producto) && !Validator.isVacio(pro_descripcion) && pro_precio >= 0
				&& !Validator.isVacio(pro_uniVenta) && id_pais > 0 && id_categoria > 0;
	}

	public String getId_producto() {
		return id_producto;
	}
	
	public void setId_producto(String id_producto) throws DomainException {
		// se comprueba que el producto es alfanumerico
		if(!Validator.isAlfanumeric(id_producto))
			throw new DomainException("id_producto. No es alfanumérico");
		// se comprueba que el producto tiene la longitud correcta
		if (!Validator.cumpleLongitud(id_producto, PRO_ID_LONG, PRO_ID_LONG))
			throw new DomainException("user_dni."+ErrorMessages.PROERR_002);
		// se comprueba que el producto tiene el formato correcto
		if (!Validator.isIdProductoValido(id_producto))
			throw new DomainException("id_producto. "+ErrorMessages.PROERR_001);
		this.id_producto = id_producto;
	}
	
	public String getPro_descripcion() {
		return pro_descripcion;
	}
	
	public void setPro_descripcion(String pro_descripcion) throws DomainException {
		// se comprueba que la descripcion es alfanumerico
		if(!Validator.isAlfanumeric(pro_descripcion))
			throw new DomainException("pro_descripcion. No es alfanumérico");
		// se comprueba que la descripcion tiene la longitud correcta
		if (!Validator.cumpleLongitud(pro_descripcion, PRO_ID_DESCRIPCION_MIN, PRO_ID_DESCRIPCION_MAX))
			throw new DomainException("pro_descripcion."+ErrorMessages.PROERR_002);
	
		this.pro_descripcion = pro_descripcion;
	}
	
	public String getPro_desLarga() {
		return pro_desLarga;
	}
	
	public void setPro_desLarga(String pro_desLarga) throws DomainException {
		// se comprueba que la descripcion larga es alfanumerico
		if(!Validator.isAlfanumeric(pro_desLarga))
			throw new DomainException("pro_desLarga. No es alfanumérico");
		// se comprueba que la descripcion larga tiene la longitud correcta
		if (!Validator.cumpleLongitud(pro_desLarga, PRO_ID_DESCRIPCION_MIN, PRO_ID_DESCRIPCION_LARGA_MAX))
			throw new DomainException("pro_desLarga."+ErrorMessages.PROERR_002);
		
		this.pro_desLarga = pro_desLarga;
	}
	
	public double getPro_precio() {
		return pro_precio;
	}
	
	public void setPro_precio(double pro_precio) throws DomainException {
		// se comprueba que el rango es 0-100
		if(!(pro_precio>=0.0 && pro_precio<0.0))
			throw new DomainException("pro_precio. Rango fuera de 0-100");
		
		// se comprueba que tiene dos decimales
		if(!Validator.isPrecioValido(pro_precio))                 
			throw new DomainException("pro_precio. No contiene dos decimales.");
	
		this.pro_precio = pro_precio;
	}
	
	public int getPro_stock() {
		return pro_stock;
	}
	
	public void setPro_stock(int pro_stock) {
		this.pro_stock = pro_stock;
	}
	
	public Calendar getPro_fecRepos() {
		return pro_fecRepos;
	}
	
	public void setPro_fecRepos(Calendar pro_fecRepos) throws DomainException {
		//se comprueba que la fecha es mayor o igual que la actual
		if(!Validator.valDateMin(pro_fecRepos, Calendar.getInstance()))
			throw new DomainException("pro_fecRepos. La fecha no es mayor o igual que la actual");
		this.pro_fecRepos = pro_fecRepos;
	}
	
	public Calendar getFecActi() {
		return fecActi;
	}
	
	public void setFecActi(Calendar fecActi) throws DomainException {		
		//se comprueba que la fecha es mayor o igual que la actual
		if(!Validator.valDateMin(fecActi, Calendar.getInstance()))
			throw new DomainException("fecActi. La fecha no es mayor o igual que la actual");
		this.fecActi = fecActi;
	}
	
	public Calendar getPro_fecDesacti() {
		return pro_fecDesacti;
	}
	
	public void setPro_fecDesacti(Calendar pro_fecDesacti) throws DomainException {
		// se comprueba si la fecha de desactivacion es mayor o igual a la actual
		if(!Validator.valDateMin(pro_fecDesacti, Calendar.getInstance()))
			throw new DomainException("pro_fecDesacti. La fecha de desactivacion no es mayor o igual que la actual");
		// se comprueba que si tiene fecha de activacion, esta sea menor que la fecha de desactivacion
		if(fecActi!=null && !Validator.valDateMin(pro_fecDesacti, fecActi))
			throw new DomainException("pro_fecDesacti. La fecha de desactivacion es menor que la fecha de activacion");
		
		this.pro_fecDesacti = pro_fecDesacti;
	}
	
	public String getPro_uniVenta() {
		return pro_uniVenta;
	}
	
	public void setPro_uniVenta(String pro_uniVenta) throws DomainException {
		// se comprueba que la unidad de venta es alfanumerico
		if(!Validator.isAlfanumeric(pro_uniVenta))
			throw new DomainException("pro_uniVenta. No es alfanumérico");
		// se comprueba que la unidad de venta tiene la longitud correcta
		if (!Validator.cumpleLongitud(pro_uniVenta, PRO_UNIVENTA_MIN, PRO_UNIVENTA_MAX))
			throw new DomainException("pro_uniVenta."+ErrorMessages.PROERR_002);
		
		this.pro_uniVenta = pro_uniVenta;
	}
	
	public double getPro_cantXUniVenta() {
		return pro_cantXUniVenta;
	}
	
	public void setPro_cantXUniVenta(double pro_cantXUniVenta) throws DomainException {
		// se comprueba que tiene dos decimales
		if(!Validator.isPrecioValido(pro_cantXUniVenta))                 
			throw new DomainException("pro_cantXUniVenta. No contiene dos decimales.");
		
		this.pro_cantXUniVenta = pro_cantXUniVenta;
	}
	
	public String getPro_uniUltNivel() {
		return pro_uniUltNivel;
	}
	
	public void setPro_uniUltNivel(String pro_uniUltNivel) throws DomainException {
		// se comprueba que la unidad ultima de venta es alfanumerico
		if(!Validator.isAlfanumeric(pro_uniUltNivel))
			throw new DomainException("pro_uniUltNivel. No es alfanumérico");
		
		this.pro_uniUltNivel = pro_uniUltNivel;
	}
	
	public int getId_pais() {
		return id_pais;
	}
	
	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}
	public String getPro_usoRecomendado() {
		return pro_usoRecomendado;
	}
	public void setPro_usoRecomendado(String pro_usoRecomendado) throws DomainException {
		// se comprueba que uso recomendado es alfanumerico
		if(!Validator.isAlfanumeric(pro_usoRecomendado))
			throw new DomainException("pro_usoRecomendado. No es alfanumérico");
		// se comprueba que la descripcion tiene la longitud correcta
		if (!Validator.cumpleLongitud(pro_usoRecomendado, PRO_USORECOMENDADO_MIN, PRO_USORECOMENDADO_MAX))
			throw new DomainException("pro_usoRecomendado."+ErrorMessages.PROERR_002);
		
		this.pro_usoRecomendado = pro_usoRecomendado;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	
	public int getPro_stkReservado() {
		return pro_stkReservado;
	}
	
	public void setPro_stkReservado(int pro_stkReservado) {
		this.pro_stkReservado = pro_stkReservado;
	}
	
	public int getPro_nStkAlto() {
		return pro_nStkAlto;
	}
	
	public void setPro_nStkAlto(int pro_nStkAlto) {
		this.pro_nStkAlto = pro_nStkAlto;
	}
	
	public int getPro_nStkBajo() {
		return pro_nStkBajo;
	}
	
	public void setPro_nStkBajo(int pro_nStkBajo) {
		this.pro_nStkBajo = pro_nStkBajo;
	}
	
	public char getPro_stat() {
		return pro_stat;
	}
	
	public void setPro_stat(char pro_stat) throws DomainException {
		// se comprueba que toma valor A o B mayuscula
		if(!(pro_stat=='A' || pro_stat=='B'))
			throw new DomainException("pro_stat. Debe tomar valor A o B.");
		this.pro_stat = pro_stat;
	}
	

	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", pro_descripcion=" + pro_descripcion + ", pro_desLarga="
				+ pro_desLarga + ", pro_precio="+ pro_precio + ", pro_stock="+ pro_stock + ", pro_fecRepos="+ pro_fecRepos
				+ ", fecActi="+ fecActi + ", pro_fecDesacti="+ pro_fecDesacti+ ", pro_uniVenta="+ pro_uniVenta
				+ ", pro_cantXUniVenta="+ pro_cantXUniVenta + ", pro_uniUltNivel="+ pro_uniUltNivel+ ", id_pais="+ id_pais
				+ ", pro_usoRecomendado="+ pro_usoRecomendado + ", id_categoria="+ id_categoria+ ", pro_stkReservado="+ pro_stkReservado
				+ ", pro_nStkAlto="+ pro_nStkAlto + ", pro_nStkBajo="+ pro_nStkBajo+ ", pro_stat="+ pro_stat+"]";
	}

}
