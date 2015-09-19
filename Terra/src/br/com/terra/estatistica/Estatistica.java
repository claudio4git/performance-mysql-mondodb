package br.com.terra.estatistica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Estatistica {
	private long mediaMilissegundos = 0;
	private long contador = 0;
	private String nomeArquivo;
	private int idThread = 0;
	
	public void setNomearquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public void setIdThread(int idThread) {
		this.idThread = idThread;
	}
	
	public synchronized void duracaoDoComando(Instant inicio, Instant termino) {
		Duration duracao = Duration.between(inicio, termino);
		
		mediaMilissegundos += duracao.toMillis();
		++contador;
		
		String dados = String.format("Thread: [%d] Requisição: [%d] Duração: [%d] ms [%d] ns Média: [%d] ms\n", 
				idThread,
				contador,
				duracao.toMillis(),
				duracao.toNanos(),
				(mediaMilissegundos/contador));
		
		try {
			FileWriter saida = new FileWriter(new File(String.format("dados-saida/%s.txt", nomeArquivo)), true);
			BufferedWriter gravacao = new BufferedWriter(saida);
			gravacao.write(dados);
			gravacao.flush();
			gravacao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
