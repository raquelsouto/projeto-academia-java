package dados;

import excecoes.CpfInexistenteException;
import excecoes.LimiteAtingidoException;
import negocio.Aluno;
import negocio.Pessoa;
import negocio.Professor;
import negocio.RepositorioPessoas;

public class RepositorioPessoasArray implements RepositorioPessoas {
	
	Pessoa[] pessoas;
	int indice;
	
	public RepositorioPessoasArray(int tamanho) {
		indice = 0;
		pessoas = new Pessoa[tamanho];
	}
	
	public void inserir(Pessoa pessoa) throws LimiteAtingidoException {
		pessoas[indice] = pessoa;
		indice = indice + 1;
		if(indice > pessoas.length) {
			throw new LimiteAtingidoException();
		}
	}
	
	public Pessoa procurar(String numCPF) throws CpfInexistenteException {
		//Chama a fun��o para corrigir o cpf
		String cpfCorrigido = corrigirCpf(numCPF);
		for(int i = 0; i < indice; i++) {
			//Guarda numa vari�vel tempor�ria a pessoa e compara o cp
			Pessoa aux = this.pessoas[i];
			if(aux.getCpf().equals(cpfCorrigido)) {
				return pessoas[i];
			}
		}
		throw new CpfInexistenteException();
	}
	
	public void remover(String numCPF) throws CpfInexistenteException {
		boolean pessoaEncontrada = false;
        //Chama a fun��o para corrigir o cpf
        String cpfCorrigido = corrigirCpf(numCPF);
        //percorre toda a lista de Pessoas e guarda na vari�vel tempor�ria
        for (int i = 0; i < pessoas.length; i++) {
            Pessoa temporaria = this.pessoas[i]; 
            //Compara se o cpf da pessoa na lista � o mesmo do que recebe pelo par�metro
            if(temporaria != null && temporaria.getCpf().equals(cpfCorrigido)) {
                //Se for a mesma Pessoa, faz uma c�pia do �ltimo elemento do array sobre o elemento que ser� removido 
                pessoaEncontrada = true;
            	
            	this.pessoas[i] = this.pessoas[indice - 1];
                this.pessoas[indice - 1] = null; 
                indice--; //decrementa a quantidade de indice
            } 
        }
        if(!pessoaEncontrada) {
			throw new CpfInexistenteException();
		}
    }
	
	private String corrigirCpf(String numCPF) {
        String cpfCorrigido = numCPF.replaceAll("[./-]", "");
        cpfCorrigido = cpfCorrigido.replaceAll("\\s+","");

        if(cpfCorrigido.length() != 11) {
            System.out.println("O CPF precisa ter 11 n�meros.");
            return "";
        }
        return cpfCorrigido;
    }
	
	@Override
    public String toString() {
        String out = "";

        out+="\n---------------- Lista de Pessoas ----------------\n ";
        for (Pessoa pessoa : pessoas) {
        	//Percorre na lista de Pesssoas, e verifica se h� null
            if(pessoa != null) {
                out += "\n====================================\n";
                //Se n�o houver null, ele verifica se o objeto � uma inst�ncia da classe Aluno e assim, passar as informa��es 
                //contidas no toString de Aluno + toString da classe Pai (Pessoa)
                if (pessoa instanceof Aluno) {
                    Aluno al = (Aluno)pessoa;
                    out += al.toString();
                  //Se n�o houver null, ele verifica se o objeto � uma inst�ncia da classe Professor e assim, passar as informa��es 
                  //contidas no toString de Professor + toString da classe Pai (Pessoa)
                } else if (pessoa instanceof Professor) {
                    Professor pf = (Professor)pessoa;
                    out += pf.toString();
                }
                
                out += "====================================\n";
            }
        } 
        return out;
	}

}
