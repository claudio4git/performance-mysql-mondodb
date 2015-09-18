package br.com.terra.comando;

import java.time.Instant;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import br.com.terra.conexao.ConexaoMongoDB;
import br.com.terra.estatistica.Estatistica;

public class ComandoBancoMongoDB {
	private static String COLECAO_COORDENADAS = "coordenadas";
	
	public void comandoInserir(Document documento) {
		ConexaoMongoDB conexao = new ConexaoMongoDB();
		MongoDatabase bancoDados = conexao.getBancoDados();
		
		Instant inicio = Instant.now();
		bancoDados.getCollection(COLECAO_COORDENADAS).insertOne(documento);
		Estatistica.duracao(inicio, Instant.now(), documento.toString());
		
		conexao.fecharConexao();
	}
	
	public void comandoConsultar(Document documento) {
		ConexaoMongoDB conexao = new ConexaoMongoDB();
		MongoDatabase bancoDados = conexao.getBancoDados();
		
		Instant inicio = Instant.now();
		bancoDados.getCollection(COLECAO_COORDENADAS).find(documento);
		Estatistica.duracao(inicio, Instant.now(), documento.toString());
		
		conexao.fecharConexao();
	}
}
