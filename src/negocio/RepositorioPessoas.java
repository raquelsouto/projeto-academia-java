package negocio;

import excecoes.CpfInexistenteException;
import excecoes.LimiteAtingidoException;

public interface RepositorioPessoas {

	void inserir(Pessoa pessoa) throws LimiteAtingidoException;
	Pessoa procurar(String numCPF) throws CpfInexistenteException;
	void remover(String numCPF) throws CpfInexistenteException;
	
}
