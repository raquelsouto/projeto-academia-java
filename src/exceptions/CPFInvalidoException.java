package exceptions;

public class CPFInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    public CPFInvalidoException() {
		super("CPF inválido.");
	}
	
    public CPFInvalidoException(String msg) {
        super("CPF inválido: " + msg);
    }
    
}
