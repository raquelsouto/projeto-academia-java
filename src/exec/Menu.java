package exec;

import java.util.Scanner;

import dados.RepositorioAlunosDAO;
import dados.RepositorioCursoDAO;
import dados.RepositorioProfessoresDAO;
import exceptions.CPFInvalidoException;
import exceptions.CodigoInvalidoException;
import negocio.Aluno;
import negocio.Curso;
import negocio.Professor;

public class Menu {

	private final static String NL = System.lineSeparator();
	private static Scanner sc = new Scanner(System.in);
	private static RepositorioProfessoresDAO repProfessores = new RepositorioProfessoresDAO();
	private static RepositorioCursoDAO repCursos = new RepositorioCursoDAO();
	private static RepositorioAlunosDAO repAlunos = new RepositorioAlunosDAO();

	public static void run() throws CPFInvalidoException, CodigoInvalidoException {
		System.out.print(showMenu());
		String opcao = sc.nextLine();

		do {
			switch (opcao.trim().toUpperCase()) {
			case "A":
				System.out.print(NL + "A - Aluno" + NL + "P - Professsor" + NL + "Opção> ");
				opcao = sc.nextLine().trim().toUpperCase();

				if (!opcao.equals("A") && !opcao.equals("P"))
					break;

				System.out.print("Nome: ");
				String nome = sc.nextLine();

				System.out.print("CPF: ");
				String cpf = sc.nextLine();

				System.out.print("Idade: ");
				int idade = sc.nextInt();

				if (opcao.equals("A")) {
					cadastrarAluno(nome, cpf, idade);
				} else if (opcao.trim().toUpperCase().equals("P")) {
					cadastrarProfessor(nome, cpf, idade);
				}

				break;

			case "B":
				cadastrarCurso();
				break;

			case "C":
				System.out.print(NL + "A - Aluno" + NL + "P - Professsor" + NL + "Opção> ");
				opcao = sc.nextLine();

				System.out.print("CPF: ");
				String cpfProcurado = sc.nextLine();

				if (opcao.trim().toUpperCase().equals("A")) {
					System.out.println(NL + repAlunos.procurar(cpfProcurado));
				} else if (opcao.trim().toUpperCase().equals("P")) {
					System.out.println(NL + repProfessores.procurar(cpfProcurado));
				}

				break;

			case "D":
				System.out.print(NL + procurarCurso());
				break;

			case "E":
				System.out.print(NL + "A - Aluno" + NL + "P - Professsor" + NL + "Opção> ");
				opcao = sc.nextLine();

				System.out.print("CPF: ");
				String cpfProc = sc.nextLine();

				if (opcao.trim().toUpperCase().equals("A")) {
					repAlunos.remover(cpfProc);
				} else if (opcao.trim().toUpperCase().equals("P")) {
					repProfessores.remover(cpfProc);
				}

				break;

			case "F":
				removerCurso();
				break;

			case "G":
				System.out.print(NL + "A - Aluno" + NL + "P - Professsor" + NL + "Opção> ");
				opcao = sc.nextLine();
				
				if (opcao.trim().toUpperCase().equals("A")) {
					System.out.println(NL + repAlunos.getAlunos());
				} else if (opcao.trim().toUpperCase().equals("P")) {
					System.out.println(NL + repProfessores.getProfessores());
				}
				
				break;
				
			case "H":
				System.out.println(repCursos.getCursos());
				break;
				
			case "S":
				System.exit(0);

			default:
				System.out.print("OPÇÃO INVÁLIDA!" + NL);
			}

			System.out.print(NL + showMenu());
			opcao = sc.nextLine();
		} while (!opcao.trim().toUpperCase().equals("S"));

	}

	public static void cadastrarAluno(String nome, String cpf, int idade)
			throws CPFInvalidoException, CodigoInvalidoException {
		System.out.print("Código do curso: ");
		double codigo = sc.nextDouble();
		sc.nextLine();

		repAlunos.adicionar(new Aluno(nome, cpf, idade, repCursos.procurar(codigo)));

		System.out.println(NL + "Cadastro realizado!");
	}

	private static void cadastrarProfessor(String nome, String cpf, int idade) throws CPFInvalidoException {
		System.out.print("Salário: ");
		double salario = sc.nextDouble();
		sc.nextLine();

		repProfessores.adicionar(new Professor(nome, cpf, idade, salario));

		System.out.println(NL + "Cadastro realizado!");
	}

	private static void cadastrarCurso() throws CodigoInvalidoException {
		System.out.print("Código: ");
		double codigo = sc.nextDouble();
		sc.nextLine();

		System.out.print("Nome: ");
		String nome = sc.nextLine();

		repCursos.adicionar(new Curso(codigo, nome));

		System.out.println(NL + "Cadastro realizado!");
	}

	private static Curso procurarCurso() throws CodigoInvalidoException {
		System.out.print("Código: ");
		double codigo = sc.nextDouble();
		sc.nextLine();

		return repCursos.procurar(codigo);
	}

	private static void removerCurso() throws CodigoInvalidoException {
		System.out.print("Código: ");
		double codigo = sc.nextDouble();
		sc.nextLine();

		repCursos.remover(codigo);
	}

	private static String showMenu() {
		return "A - Cadastrar Pessoa" + NL + "B - Cadastrar Curso" + NL + "C - Procurar Pessoa" + NL
				+ "D - Procurar Curso" + NL + "E - Remover Pessoa" + NL + "F - Remover Curso" + NL
				+ "G - Mostrar Pessoas" + NL + "H - Mostrar Cursos" + NL + "S - Sair" + NL + "Opção> ";
	}

}
