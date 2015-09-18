package br.com.terra.conexao;

import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConexaoMongoDB {
	MongoClient conexao;

	public MongoDatabase getBancoDados() {
		Properties propriedades = new Propriedades().getPropriedades();
		conexao = new MongoClient(propriedades.getProperty("mongodb.ip"), Integer.valueOf(propriedades.getProperty("mongodb.porta")));
		MongoDatabase banco = conexao.getDatabase("terra");
		
		return banco;
	}
	
	public void fecharConexao() {
		conexao.close();
	}
}
