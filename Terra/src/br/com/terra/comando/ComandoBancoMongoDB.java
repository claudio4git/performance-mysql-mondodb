package br.com.terra.comando;

import java.time.Instant;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import br.com.terra.conexao.ConexaoMongoDB;
import br.com.terra.estatistica.Estatistica;

public class ComandoBancoMongoDB {
	private final String COLECAO_COORDENADAS = "coordenadas";
	private Estatistica estatistica;
	
	public void setEstatistica(Estatistica estatistica) {
		this.estatistica = estatistica;
	}
	
	public void comandoInserir(Document documento) {
		ConexaoMongoDB conexao = new ConexaoMongoDB();
		MongoDatabase bancoDados = conexao.getBancoDados();
		
		Instant inicio = Instant.now();
		bancoDados.getCollection(COLECAO_COORDENADAS).insertOne(documento);
		estatistica.duracaoDoComando(inicio, Instant.now());
		
		conexao.fecharConexao();
	}
	
	public void comandoConsultar(Document documento) {
		ConexaoMongoDB conexao = new ConexaoMongoDB();
		MongoDatabase bancoDados = conexao.getBancoDados();
		
		Instant inicio = Instant.now();
		bancoDados.getCollection(COLECAO_COORDENADAS).find(documento);
		estatistica.duracaoDoComando(inicio, Instant.now());
		
		conexao.fecharConexao();
	}
}
