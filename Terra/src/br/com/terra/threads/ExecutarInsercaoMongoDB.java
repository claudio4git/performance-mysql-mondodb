package br.com.terra.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.com.terra.comando.FabricaEstatisticaComando;

public class ExecutarInsercaoMongoDB implements Runnable {
	private int id;
	private FabricaEstatisticaComando fabrica;
	private String nomeArquivoEntrada;

	public void setId(int id) {
		this.id = id;
	}

	public void setFabrica(FabricaEstatisticaComando fabrica) {
		this.fabrica = fabrica;
	}
	
	public void setNomeArquivoEntrada(String nomeArquivoEntrada) {
		this.nomeArquivoEntrada = nomeArquivoEntrada;
	}

	@Override
	public void run() {
		fabrica.setIdThread(id);
		
		try {
			FileReader entrada = new FileReader(new File(nomeArquivoEntrada));
			BufferedReader leitura = new BufferedReader(entrada);
			String linha = "";
			
			while ((linha = leitura.readLine()) != null) {
				String[] lista = linha.split(",");
				String longitude = lista[2];
				String latitude = lista[3];
				
				fabrica.inserirBancoMongoDB(latitude, longitude); // INSERCAO
			}
			
			entrada.close();
			leitura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
