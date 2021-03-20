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
import negocio.Professor;

public class RepositorioProfessoresDAO {

	private Connection con = null;

	public RepositorioProfessoresDAO() {
		this.con = ConexaoBD.getConnection();
	}

	public void adicionar(Professor professor) throws CPFInvalidoException {
		String sql = "INSERT INTO professor(Nome, Cpf, Idade, Salario) VALUES(?, ?, ?, ?)";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getCpf());
			stmt.setInt(3, professor.getIdade());
			stmt.setDouble(4, professor.getSalario());

			stmt.execute();
			stmt.close();

		} catch (SQLIntegrityConstraintViolationException s) {
			throw new CPFInvalidoException("o CPF já está cadastrado.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Professor procurar(String cpf) throws CPFInvalidoException {
		String sql = "SELECT * FROM professor WHERE Cpf=?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Professor(rs.getString("Nome"), rs.getString("Cpf"), rs.getInt("Idade"), rs.getDouble("Salario"));
			}

			stmt.close();
			rs.close();

			throw new CPFInvalidoException("o CPF não está cadastrado.");

		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void remover(String cpf) throws CPFInvalidoException {
		String sql = "DELETE FROM professor WHERE Cpf=?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.execute();
			stmt.close();

		} catch (SQLIntegrityConstraintViolationException s) {
			throw new CPFInvalidoException("o CPF já está cadastrado.");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Professor> getProfessores() {
		List<Professor> professores = new ArrayList<Professor>();
		String sql = "SELECT * FROM professor";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Professor p = new Professor(rs.getString("Nome"), rs.getString("Cpf"), rs.getInt("Idade"),
						rs.getDouble("Salario"));
				professores.add(p);
			}

			rs.close();
			stmt.close();

			return professores;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
