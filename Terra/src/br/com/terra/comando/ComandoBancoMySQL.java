package br.com.terra.comando;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

import br.com.terra.conexao.ConexaoMySQL;
import br.com.terra.estatistica.Estatistica;

public class ComandoBancoMySQL {
	Connection conexao;
	
	public ComandoBancoMySQL() {
		ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
		conexao = conexaoMySQL.getConexao();
	}
	
	public void comandoInserir(String comando) {
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			Instant inicio = Instant.now();
				preparar.execute();
				Estatistica.duracao(inicio, Instant.now());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void comandoConsultar(String comando) {
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			Instant inicio = Instant.now();
				preparar.executeQuery();
				Estatistica.duracao(inicio, Instant.now());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
