package negocio;

public interface RepositorioPessoas {
    
    void inserir(Pessoa pessoa);
    Pessoa procurar(String numCpf);
    void remover(String numCPF);

}
