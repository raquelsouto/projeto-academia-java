package dados;

import negocio.Aluno;
import negocio.Curso;
import negocio.Pessoa;
import negocio.Professor;
import negocio.RepositorioPessoas;

public class TestRepositorioPessoas {

    public static void main(String[] args) {

        Curso curso1 = new Curso(75.6, "Sistemas de Informação");
        Curso curso2 = new Curso(99, "Engenharia Civil");

        Pessoa p1 = new Aluno("Juliana", "12345687112", 30, curso1);
        Pessoa p2 = new Aluno("Bruno", "74185226315", 31, curso2);

        Pessoa p3 = new Professor("Maria Júlia", "78911145630", 34, 3520.50);
        Pessoa p4 = new Professor("Rubem", "00094578911", 37, 4450.80);

        RepositorioPessoas rep = new RepositorioPessoasArray(5);
        //RepositorioPessoas rep = new RepositorioPessoasList();

        rep.inserir(p1);
        rep.inserir(p2);
        rep.inserir(p3);
        rep.inserir(p4);
        System.out.println(rep.toString());

        System.out.println("\nPROCURANDO PESSOAS...\n");
        System.out.println(rep.procurar("123.456 . 871 - 12"));
        System.out.println(rep.procurar(" 000945789-11"));
        System.out.println(rep.procurar(" 000536124-11"));

        System.out.println("\nREMOVENDO PESSOAS...");
        rep.remover("123.456.871-12");
        System.out.println(rep.toString());
    }

}
