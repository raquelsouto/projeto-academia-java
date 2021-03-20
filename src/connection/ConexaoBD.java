package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection conexao = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/pessoas?autoReconnect=true&useSSL=false", "root", "gabyx9122");
			return conexao;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
