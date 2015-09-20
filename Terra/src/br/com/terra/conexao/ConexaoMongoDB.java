package br.com.terra.conexao;

import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import br.com.terra.configuracoes.Propriedades;

public class ConexaoMongoDB {
	MongoClient conexao;

	public synchronized MongoDatabase getBancoDados() {
		Properties propriedades = new Propriedades().getPropriedades();
		conexao = new MongoClient(propriedades.getProperty("mongodb.ip"), Integer.valueOf(propriedades.getProperty("mongodb.porta")));
		MongoDatabase banco = conexao.getDatabase("terra");
		
		return banco;
	}
	
	public synchronized void fecharConexao() {
		conexao.close();
	}
}
