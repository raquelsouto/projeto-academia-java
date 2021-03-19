package dados;

import exceptions.CPFInvalidoException;
import exceptions.LimiteAtingidoException;
import negocio.Aluno;
import negocio.Pessoa;
import negocio.Professor;
import negocio.RepositorioPessoas;

public class RepositorioPessoasArray implements RepositorioPessoas {
	
	private Pessoa[] pessoas;
    private int indice;
	
	public RepositorioPessoasArray(int tamanho) {
        this.pessoas = new Pessoa[tamanho];
		this.indice = 0;
	}
	
	@Override
	public void inserir(Pessoa pessoa) throws LimiteAtingidoException {
		if (indice == this.pessoas.length) {
            throw new LimiteAtingidoException();
        }

        this.pessoas[indice] = pessoa;
		indice++;
	}
	
	@Override
	public Pessoa procurar(String cpf) throws CPFInvalidoException {
		String cpfCorrigido = corrigirCpf(cpf);
		
        for (int i = 0; i < indice; i++) {
			if (pessoas[i].getCpf().equals(cpfCorrigido)) {
				return pessoas[i];
			}
		}

		throw new CPFInvalidoException("o CPF não está cadastrado.");
	}
	
	@Override
	public void remover(String cpf) throws CPFInvalidoException {
		boolean pessoaEncontrada = false;
        String cpfCorrigido = corrigirCpf(cpf);
        
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] != null && pessoas[i].getCpf().equals(cpfCorrigido)) {
                pessoaEncontrada = true;
            	pessoas[i] = pessoas[indice - 1];
                pessoas[indice - 1] = null; 
                indice--;
            } 
        }
        
        if (!pessoaEncontrada) {
			throw new CPFInvalidoException("o CPF não está cadastrado.");
		}
    }
	
	@Override
    public String toString() {
		String out = "";

        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                if (pessoa instanceof Aluno) {
                    Aluno a = (Aluno) pessoa;
                    out += a.toString();
                } else if (pessoa instanceof Professor) {
                    Professor p = (Professor) pessoa;
                    out += p.toString();
                }
            }
        } 
        
        return out;
	}
	
	private String corrigirCpf(String cpf) throws CPFInvalidoException {
        String cpfCorrigido = cpf.replaceAll("[./-]", "");
        cpfCorrigido = cpfCorrigido.replaceAll("\\s+","");

        if (cpfCorrigido.length() != 11) {
            throw new CPFInvalidoException("o CPF precisa conter 11 números.");
        }
        
        return cpfCorrigido;
    }

}