package br.com.terra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

import br.com.terra.conexao.ConexaoMySQL;
import br.com.terra.configuracoes.Propriedades;

public class InserirMySQL implements Runnable {
	private int id;
	private static Properties propriedades = new Propriedades().getPropriedades();
	
	public static void main(String[] args) {
		for (int i = 0; i < Integer.valueOf(propriedades.getProperty("quantidade.concorrencia")); i++) {
			InserirMySQL inserir = new InserirMySQL();
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
		Instant inicio = Instant.now();
		Instant termino = null;
		System.out.println("Início: " + inicio.toString());
		ConexaoMySQL conexaoMySQL = new ConexaoMySQL();
		Connection conexao = conexaoMySQL.getConexao();
		
		try {
			FileReader entrada = new FileReader(new File(propriedades.getProperty("caminho.arquivo")));
			BufferedReader leitura = new BufferedReader(entrada);
			String linha = "";
			
			while ((linha = leitura.readLine()) != null) {
				String[] lista = linha.split(",");
				String longitude = lista[2];
				String latitude = lista[3];
				
				String comando = String.format("INSERT INTO coordenadas(latitude,longitude) values(%s, %s)", latitude, longitude);
				PreparedStatement preparar = conexao.prepareStatement(comando);
				preparar.execute();
			}
			
			entrada.close();
			leitura.close();
			conexaoMySQL.fecharConexao();
			
			termino = Instant.now();
			System.out.println("Término: " + inicio.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		Duration duracao = Duration.between(inicio, termino);
		System.out.println("Thread: " + id);
		System.out.println("Duração: " + duracao.toMinutes() + " minutos");
		System.out.println("Duração: " + duracao.getSeconds() + " segundos");
		System.out.println("Duração: " + duracao.toMillis() + " milissegundos");
		System.out.println("Duração: " + duracao.toNanos() + " nanosegundos");
	}
}
