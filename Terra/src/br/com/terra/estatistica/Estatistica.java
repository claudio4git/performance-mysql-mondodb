package br.com.terra.estatistica;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Estatistica {
	private static ArrayList<String> lista = new ArrayList<>();

	public static void duracao(Instant inicio, Instant termino, String comando) {
		Duration duracao = Duration.between(inicio, termino);
		System.out.println(String.format("Dado: [%s] Duração: [%d] segundos", comando, duracao.toMillis()));
		lista.add(duracao.toString());
	}
	
	public void salvarEmDisco() {
		//
	}
}
