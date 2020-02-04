package br.ppii.exceptions;

public class FotoInvalidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public FotoInvalidaException(String menssagem) {
		super(menssagem);
	}
	
}