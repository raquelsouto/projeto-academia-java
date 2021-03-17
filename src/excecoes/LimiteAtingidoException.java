package excecoes;

public class LimiteAtingidoException extends Exception {

	public LimiteAtingidoException() {
		super("Não pode mais inserir pessoas. Limite atingido!");
	}
	
}
