package exceptions;

public class CPFInvalidoException extends Exception {

	public CPFInvalidoException() {
		super("CPF inválido.");
	}
	
    public CPFInvalidoException(String msg) {
        super("CPF inválido: " + msg);
    }
    
}
