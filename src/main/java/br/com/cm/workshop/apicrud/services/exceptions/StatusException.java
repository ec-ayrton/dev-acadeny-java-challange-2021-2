package br.com.cm.workshop.apicrud.services.exceptions;

public class StatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StatusException(String msg) {
		super(msg);
	}
}