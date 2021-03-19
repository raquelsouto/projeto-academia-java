package exceptions;

public class CodigoInvalidoException extends Exception {
	
	public CodigoInvalidoException() {
		super("Código do curso inválido.");
	}
	
	public CodigoInvalidoException(String msg) {
		super("Código do curso inválido: " + msg);
	}
	
}
