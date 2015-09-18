package br.com.terra.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoMySQL {

	/** 
	 * Exemplo de URL de conexão: 
	 *   jdbc:mysql://localhost:3306/banco
	 */
	private final String URL_CONEXAO = "jdbc:mysql://%s:%s/%s";
	Connection conexao;
	Properties propriedades;

	public ConexaoMySQL() {
		propriedades = new Propriedades().getPropriedades();
	}

	public Connection getConexao() {
		String xUrl = String.format(URL_CONEXAO, 
				propriedades.getProperty("mysql.ip"),
				propriedades.getProperty("mysql.porta"), 
				propriedades.getProperty("mysql.banco"));

		Properties xPropriedades = new Properties();
		xPropriedades.setProperty("user", propriedades.getProperty("mysql.usuario"));
		xPropriedades.setProperty("password", propriedades.getProperty("mysql.senha"));

		try {
			conexao = DriverManager.getConnection(xUrl, xPropriedades);
			return conexao;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
