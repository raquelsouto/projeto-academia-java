package excecoes;

public class LimiteAtingidoException extends Exception {

	public LimiteAtingidoException() {
		super("N�o pode mais inserir pessoas. Limite atingido!");
	}
	
}
