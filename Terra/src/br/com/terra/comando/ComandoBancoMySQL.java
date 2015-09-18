package br.com.terra.comando;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

import br.com.terra.conexao.ConexaoMySQL;
import br.com.terra.estatistica.Estatistica;

public class ComandoBancoMySQL {

	public void comandoInserir(String comando) {
		ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
		Connection conexao = conexaoMySQL.getConexao();
		if (conexao == null) { return; };
		
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			Instant inicio = Instant.now();
			preparar.execute();
			Estatistica.duracao(inicio, Instant.now(), comando);
				
			conexaoMySQL.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void comandoConsultar(String comando) {
		ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
		Connection conexao = conexaoMySQL.getConexao();
		if (conexao == null) { return; };
		
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			Instant inicio = Instant.now();
			preparar.executeQuery();
			Estatistica.duracao(inicio, Instant.now(), comando);
				
			conexaoMySQL.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
