package es.rf.tienda.exception;

@SuppressWarnings("serial")
public class DAOException extends Exception {
	
	public DAOException() {
		super();
	}

	public DAOException(String msj) {
		super(msj);
	}

	public DAOException(String msj, Exception e) {
		super(msj, e);
	}
	
	
}
