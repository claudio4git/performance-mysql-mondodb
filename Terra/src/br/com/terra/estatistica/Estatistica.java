package br.com.terra.estatistica;

import java.time.Duration;
import java.time.Instant;

public class Estatistica {

	public static void duracao(Instant inicio, Instant termino) {
		Duration duracao = Duration.between(inicio, termino);
		System.out.println("Duração: " + duracao.toMillis());
	}
	
	public void salvarEmDisco() {
		//
	}
}
