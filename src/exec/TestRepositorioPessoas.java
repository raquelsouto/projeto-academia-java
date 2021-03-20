package exec;

import dados.RepositorioPessoasLista;
import exceptions.CPFInvalidoException;
import exceptions.LimiteAtingidoException;
import negocio.Aluno;
import negocio.Curso;
import negocio.Pessoa;
import negocio.Professor;
import negocio.RepositorioPessoas;

public class TestRepositorioPessoas {

	public static void main(String[] args) throws LimiteAtingidoException, CPFInvalidoException {

		Curso curso1 = new Curso(15, "Sistemas");
		Curso curso2 = new Curso(99, "Medicina");
		Curso curso3 = new Curso(60, "Engenharia Elétrica");

		System.out.println(curso1.getNome());
		System.out.println(curso1.getCodigo());

		Pessoa p1 = new Aluno("Juliana", "12345687112", 30, curso1);
		Pessoa p2 = new Aluno("Bruno", "74185226315", 31, curso2);
		Pessoa p5 = new Aluno("Esther", "15664647896", 19, curso3);

		Pessoa p3 = new Professor("Maria Júlia", "78911145630", 34, 3520.50);
		Pessoa p4 = new Professor("Rubem", "00094578911", 37, 4450.80);

		// RepositorioPessoas rep = new RepositorioPessoasArray(5);
		RepositorioPessoas rep = new RepositorioPessoasLista();

		try {
			rep.inserir(p1);
		} catch (LimiteAtingidoException e) {
			e.printStackTrace();
		}

		try {
			rep.inserir(p2);
		} catch (LimiteAtingidoException e) {
			e.printStackTrace();
		}
		try {
			rep.inserir(p5);
		} catch (LimiteAtingidoException e) {
			e.printStackTrace();
		}

		try {
			rep.inserir(p3);
		} catch (LimiteAtingidoException e) {
			e.printStackTrace();
		}

		try {
			rep.inserir(p4);
		} catch (LimiteAtingidoException e) {
			e.printStackTrace();
		}

		System.out.print("\n" + rep.toString());

		System.out.println("PROCURANDO PESSOAS...\n");

		try {
			System.out.println(rep.procurar("74185226315"));
		} catch (CPFInvalidoException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(rep.procurar("00094578911"));
		} catch (CPFInvalidoException e) {
			e.printStackTrace();
		}

		System.out.println("REMOVENDO PESSOAS...\n");

		try {
			rep.remover("12345687112");
		} catch (CPFInvalidoException e) {
			e.printStackTrace();
		}

		try {
			rep.remover("15664647896");
		} catch (CPFInvalidoException e) {
			e.printStackTrace();
		}
		System.out.print(rep.toString());

	}

}