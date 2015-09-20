package br.com.terra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import br.com.terra.conexao.ConexaoMongoDB;
import br.com.terra.configuracoes.Propriedades;

public class InserirMongoDB implements Runnable {
	private int id;
	private static final String COLECAO_COORDENADAS = "coordenadas";
	private static Properties propriedades = new Propriedades().getPropriedades();
	
	public static void main(String[] args) {
		for (int i = 0; i < Integer.valueOf(propriedades.getProperty("quantidade.concorrencia")); i++) {
			InserirMongoDB inserir = new InserirMongoDB();
			inserir.setId(i);
			Thread thread = new Thread(inserir);
			thread.start();
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		int contador = 0;
		Instant inicio = Instant.now();
		Instant termino = null;
		System.out.println("Início: " + inicio.toString());
		ConexaoMongoDB conexao = new ConexaoMongoDB();
		MongoDatabase banco = conexao.getBancoDados();
		
		try {
			FileReader entrada = new FileReader(new File(propriedades.getProperty("caminho.arquivo")));
			BufferedReader leitura = new BufferedReader(entrada);
			String linha = "";
			
			while ((linha = leitura.readLine()) != null) {
				String[] lista = linha.split(",");
				String longitude = lista[2];
				String latitude = lista[3];
				
				Document documento = new Document().append("latitude", latitude).append("longitude", longitude);
				banco.getCollection(COLECAO_COORDENADAS).insertOne(documento);
				++contador;
			}
			
			entrada.close();
			leitura.close();
			conexao.fecharConexao();
			
			termino = Instant.now();
			System.out.println("Término: " + inicio.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		Duration duracao = Duration.between(inicio, termino);
		System.out.println("Thread: " + id);
		System.out.println("Dados inseridos: " + contador);
		System.out.println("Duração: " + duracao.getSeconds() + " segundos");
		System.out.println("Duração: " + duracao.toMillis() + " milissegundos");
		System.out.println("Duração: " + duracao.toNanos() + " nanosegundos");
	}
}
