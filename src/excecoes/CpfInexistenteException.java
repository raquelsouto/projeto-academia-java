package excecoes;

public class CpfInexistenteException extends Exception {
	
	public CpfInexistenteException() {
		super("Pessoa não encontrada! CPF inexistente!");
	}
}
