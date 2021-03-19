package negocio;

import exceptions.CPFInvalidoException;
import exceptions.LimiteAtingidoException;

public interface RepositorioPessoas {

	public void inserir(Pessoa pessoa) throws LimiteAtingidoException;
	public Pessoa procurar(String numCPF) throws CPFInvalidoException;
	public void remover(String numCPF) throws CPFInvalidoException;
	
}