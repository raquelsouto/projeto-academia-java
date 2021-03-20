package exceptions;

public class CodigoInvalidoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CodigoInvalidoException() {
		super("Código do curso inválido.");
	}
	
	public CodigoInvalidoException(String msg) {
		super("Código do curso inválido: " + msg);
	}
	
}
