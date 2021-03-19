package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexaoBD;
import exceptions.CodigoInvalidoException;
import negocio.Curso;

public class RepositorioCursoDAO {
	private Connection con = null;

	public RepositorioCursoDAO() {
		this.con = ConexaoBD.getConnection();
	}

	public void adicionar(Curso curso) throws CodigoInvalidoException {
		String sql = "INSERT INTO curso(Codigo, Nome) VALUES(?, ?)";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, curso.getCodigo());
			stmt.setString(2, curso.getNome());

			stmt.execute();
			stmt.close();

			System.out.println("Gravado!");
		} catch (SQLIntegrityConstraintViolationException s) {
			throw new CodigoInvalidoException("o curso já está cadastrado.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Curso procurar(double codigo) throws CodigoInvalidoException {
		String sql = "SELECT * FROM curso WHERE Codigo=?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, codigo);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Curso(rs.getDouble("Codigo"), rs.getString("Nome"));
			}

			stmt.close();
			rs.close();
			
			throw new CodigoInvalidoException("o curso não está cadastrado.");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void remover(double codigo) throws CodigoInvalidoException {
		String sql = "DELETE FROM curso WHERE Codigo=?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, codigo);
			stmt.execute();
			stmt.close();
		} catch (SQLIntegrityConstraintViolationException s) {
			throw new CodigoInvalidoException("não é possível remover curso.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Curso> getCursos() {
		List<Curso> cursos = new ArrayList<Curso>();
		String sql = "SELECT * FROM curso";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Curso c = new Curso(rs.getDouble("Codigo"), rs.getString("Nome"));
				cursos.add(c);
			}

			rs.close();
			stmt.close();

			return cursos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
