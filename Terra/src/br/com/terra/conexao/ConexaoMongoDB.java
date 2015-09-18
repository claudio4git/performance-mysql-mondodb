package br.com.terra.conexao;

import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConexaoMongoDB {
	
	Properties propriedades;
	MongoClient conexao;

	public ConexaoMongoDB() {
		propriedades = new Propriedades().getPropriedades();
	}

	public MongoDatabase getBancoDados() {
		@SuppressWarnings("resource")
		MongoClient conexao = new MongoClient(propriedades.getProperty("mongodb.ip"), Integer.valueOf(propriedades.getProperty("mongodb.porta")));
		MongoDatabase banco = conexao.getDatabase("terra");
		
		return banco;
	}
	
	public void fecharConexao() {
		conexao.close();
	}
}
