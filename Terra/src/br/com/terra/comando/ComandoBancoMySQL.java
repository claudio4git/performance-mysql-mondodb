package br.com.terra.comando;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

import br.com.terra.conexao.ConexaoMySQL;
import br.com.terra.estatistica.Estatistica;

public class ComandoBancoMySQL {
	Estatistica estatistica;
	
	public void setEstatistica(Estatistica estatistica) {
		this.estatistica = estatistica;
	}
	
	public void comandoInserir(String comando) {
		ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
		Connection conexao = conexaoMySQL.getConexao();
		if (conexao == null) { return; }; // Sair da rotina, pois não temos uma conexão.
		
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			Instant inicio = Instant.now();
			preparar.execute();
			estatistica.duracaoDoComando(inicio, Instant.now());
				
			conexaoMySQL.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void comandoConsultar(String comando) {
		ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
		Connection conexao = conexaoMySQL.getConexao();
		if (conexao == null) { return; }; // Sair da rotina, pois não temos uma conexão.
		
		try {
			PreparedStatement preparar = conexao.prepareStatement(comando);
			Instant inicio = Instant.now();
			preparar.executeQuery();
			estatistica.duracaoDoComando(inicio, Instant.now());
				
			conexaoMySQL.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
