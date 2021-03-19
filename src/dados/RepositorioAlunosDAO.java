package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoBD;
import exceptions.CPFInvalidoException;
import exceptions.CodigoInvalidoException;
import negocio.Aluno;

public class RepositorioAlunosDAO {
	
	private Connection con = null;
	private RepositorioCursoDAO repCurso;

	public RepositorioAlunosDAO() {
		this.con = ConexaoBD.getConnection();
		this.repCurso = new RepositorioCursoDAO();
	}

	public void adicionar(Aluno aluno) throws CPFInvalidoException {
		String sql = "INSERT INTO aluno(Nome, Cpf, Idade, CodigoCurso) VALUES(?, ?, ?, ?)";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getCpf());
			stmt.setInt(3, aluno.getIdade());
			stmt.setDouble(4, aluno.getCurso().getCodigo());

			stmt.execute();
			stmt.close();

		} catch (SQLIntegrityConstraintViolationException s) {
			throw new CPFInvalidoException("o CPF já está cadastrado.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Aluno procurar(String cpf) throws CPFInvalidoException, CodigoInvalidoException {
		String sql = "SELECT * FROM aluno WHERE Cpf=?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Aluno(rs.getString("Nome"), rs.getString("Cpf"), rs.getInt("Idade"), repCurso.procurar(rs.getDouble("CodigoCurso")));
			}

			stmt.close();
			rs.close();

			throw new CPFInvalidoException("o CPF não está cadastrado.");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void remover(String cpf) throws CPFInvalidoException {
		String sql = "DELETE FROM aluno WHERE Cpf=?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.execute();
			stmt.close();
			
			
		} catch (SQLIntegrityConstraintViolationException s) {
			throw new CPFInvalidoException("o CPF não está cadastrado.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Aluno> getAlunos() throws CodigoInvalidoException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		String sql = "SELECT * FROM aluno";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Aluno a = new Aluno(rs.getString("Nome"), rs.getString("Cpf"), rs.getInt("Idade"), repCurso.procurar(rs.getDouble("CodigoCurso")));
				alunos.add(a);
			}

			rs.close();
			stmt.close();

			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
