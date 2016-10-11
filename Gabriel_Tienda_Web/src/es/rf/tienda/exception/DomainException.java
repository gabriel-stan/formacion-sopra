package es.rf.tienda.exception;

import java.text.ParseException;

@SuppressWarnings("serial")
public class DomainException extends Exception {
	
	public DomainException() {}
	
	public DomainException(String mensaje) {
		super(mensaje);
	}

	public DomainException(String mensaje, Throwable e) {
		super(mensaje, e);
	}

}
