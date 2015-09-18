package br.com.terra.comando;

import java.time.Instant;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import br.com.terra.conexao.ConexaoMongoDB;
import br.com.terra.estatistica.Estatistica;

public class ComandoBancoMongoDB {
	MongoDatabase bancoDados;
	
	public ComandoBancoMongoDB() {
		ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
		bancoDados = conexaoMongoDB.getBancoDados();
	}
	
	public void comandoInserir(Document documento) {
		Instant inicio = Instant.now();
			bancoDados.getCollection("coordenadas").insertOne(documento);
		Estatistica.duracao(inicio, Instant.now());
	}
	
	public void comandoConsultar(Document documento) {
		Instant inicio = Instant.now();
			bancoDados.getCollection("coordenadas").find(documento);
		Estatistica.duracao(inicio, Instant.now());
	}
}
