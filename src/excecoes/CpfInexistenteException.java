package excecoes;

public class CpfInexistenteException extends Exception {
	
	public CpfInexistenteException() {
		super("Pessoa n�o encontrada! CPF inexistente!");
	}
}
