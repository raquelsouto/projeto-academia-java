package dados;

import negocio.Aluno;
import negocio.Pessoa;
import negocio.Professor;
import negocio.RepositorioPessoas;

public class RepositorioPessoasArray implements RepositorioPessoas {

    private Pessoa[] pessoas;
    private int indice;

    public RepositorioPessoasArray(int tamanho) {
        this.indice = 0;
        this.pessoas = new Pessoa[tamanho];
    }

    public void inserir(Pessoa pessoa) {
        pessoas[indice] = pessoa;
        indice++;
    }

    public Pessoa procurar(String numCPF) {
        //Chama a função para corrigir o cpf
        String cpfCorrigido = corrigirCpf(numCPF);

        Pessoa pessoaEncontrada = null;
        for (int i = 0; i < pessoas.length; i++) {
            //Guarda numa variável temporária a pessoa e compara o cpf
            Pessoa temporaria = this.pessoas[i];
            if(temporaria != null && temporaria.getCpf().equals(cpfCorrigido)) {
                return pessoaEncontrada = temporaria;
            }
        }
        return pessoaEncontrada;
    }

    public void remover(String numCPF) {
        //Chama a função para corrigir o cpf
        String cpfCorrigido = corrigirCpf(numCPF);

        //percorre toda a lista de Pessoas e guarda na variável temporária
        for (int i = 0; i < pessoas.length; i++) {
            Pessoa temporaria = this.pessoas[i]; 
            //Compara se o cpf da pessoa na lista é o mesmo do que recebe pelo parâmetro
            if(temporaria != null && temporaria.getCpf().equals(cpfCorrigido)) {
                //Se for a mesma Pessoa, faz uma cópia do último elemento do array sobre o elemento que será removido 
                this.pessoas[i] = this.pessoas[indice - 1];
                this.pessoas[indice - 1] = null; 
                indice--; //decrementa a quantidade de indice
            } 
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
        for (Pessoa pessoa : pessoas) {
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
