package dados;

import java.util.ArrayList;
import java.util.List;

import exceptions.CPFInvalidoException;
import negocio.Aluno;
import negocio.Pessoa;
import negocio.Professor;
import negocio.RepositorioPessoas;

public class RepositorioPessoasLista implements RepositorioPessoas {

    private List<Pessoa> pessoas;

    public RepositorioPessoasLista() {
        this.pessoas = new ArrayList<Pessoa>();
    }

    @Override
    public void inserir(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    @Override
    public Pessoa procurar(String cpf) throws CPFInvalidoException {
        String cpfCorrigido = corrigirCpf(cpf);

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpfCorrigido)) {
                return pessoa;
            }
        }

        throw new CPFInvalidoException("o CPF não está cadastrado.");
    }

    @Override
    public void remover(String cpf) throws CPFInvalidoException {
        Pessoa p = procurar(cpf);
        pessoas.remove(p);
    }

    @Override
    public String toString() {
        String out = "";

        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                if (pessoa instanceof Aluno) {
                    Aluno a = (Aluno) pessoa;
                    out += a.toString() + "\n";
                } else if (pessoa instanceof Professor) {
                    Professor p = (Professor) pessoa;
                    out += p.toString() + "\n";
                }
            }
        }

        return out;
    }

    private String corrigirCpf(String cpf) throws CPFInvalidoException {
        String cpfCorrigido = cpf.replaceAll("[./-]", "");
        cpfCorrigido = cpfCorrigido.replaceAll("\\s+", "");

        if (cpfCorrigido.length() != 11) {
            throw new CPFInvalidoException("o CPF precisa conter 11 números.");
        }

        return cpfCorrigido;
    }
}
