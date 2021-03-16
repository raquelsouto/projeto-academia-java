package dados;

import java.util.ArrayList;
import java.util.List;

import negocio.Aluno;
import negocio.Pessoa;
import negocio.Professor;
import negocio.RepositorioPessoas;

public class RepositorioPessoasList implements RepositorioPessoas {

    private List<Pessoa> listPessoas = new ArrayList<Pessoa>();

    public void inserir(Pessoa pessoa) {
        listPessoas.add(pessoa);
    }

    public Pessoa procurar(String numCPF) {
        String cpfCorrigido = corrigirCpf(numCPF);
        Pessoa pessoaEncontrada = null;

        for (Pessoa pessoa : listPessoas) {
            if(pessoa.getCpf().equals(cpfCorrigido)) {
                return pessoaEncontrada = pessoa;
            }
        }
        return pessoaEncontrada;
    }

    public void remover(String numCPF) {
        //Chama a função para corrigir o cpf
        String cpfCorrigido = corrigirCpf(numCPF);

        int pos = -1;
        //Percorre a lista de pessoas e verifica se o cpf recebido pelos parâmetros é o mesmo de alguém contida na lista
        for (int i = 0; i < listPessoas.size(); i++) {
            if(listPessoas.get(i).getCpf().equals(cpfCorrigido)) {
                pos = i;
            }
        }
        //Caso encontre alguém compatível, guarda o indice e remove.
        if(pos >= 0) {
            listPessoas.remove(listPessoas.get(pos));
        } 
    }

    private String corrigirCpf(String numCPF) {
        String cpfCorrigido = numCPF.replaceAll("[./-]", "");
        cpfCorrigido = cpfCorrigido.replaceAll("\\s+","");

        if(cpfCorrigido.length() != 11) {
            System.out.println("O CPF precisa ter 11 números.");
            return "";
        }
        return cpfCorrigido;
    }
    
    @Override
    public String toString() {
        String out = "";

        out+="\n---------------- Lista de Pessoas ----------------\n ";
      //Percorre na lista de Pesssoas, e verifica se há null
        for (Pessoa pessoa : listPessoas) {
            if(pessoa != null) {
                out += "\n====================================\n";
                
              //Se não houver null, ele verifica se o objeto é uma instância da classe Aluno e assim, passar as informações 
              //contidas no toString de Aluno + toString da classe Pai (Pessoa)
                if (pessoa instanceof Aluno) {
                    Aluno al = (Aluno)pessoa;
                    out += al.toString();
               //Se não houver null, ele verifica se o objeto é uma instância da classe Professor e assim, passar as informações 
               //contidas no toString de Aluno + toString da classe Pai (Pessoa)
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
