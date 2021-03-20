package exceptions;

public class LimiteAtingidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LimiteAtingidoException() {
		super("Limite Atingido: Não é possível inserir pessoa.");
	}
	
}
