package br.com.terra;

import br.com.terra.comando.FabricaEstatisticaComando;
import br.com.terra.threads.ExecutarConsultaMongoDB;
import br.com.terra.threads.ExecutarConsultaMySQL;
import br.com.terra.threads.ExecutarInsercaoMongoDB;
import br.com.terra.threads.ExecutarInsercaoMySQL;

public class Terra {
	private static final int NUMERO_DE_THREADS = 100;
	private static final String NOME_ARQUIVO_ENTRADA = "dados-entrada/1_00.txt";

	public static void main(String[] args) {
		String banco = "";
		String acao = "";
		if (args.length == 2) {
			banco = args[0];
			acao = args[1];
		} else {
			System.out.println("Informar parâmetros\nExemplo:\n -Inserir MySQL\n -Inserir MongoDB\n -Consultar MySQL\n -Consultar MongoDB");
			System.exit(0);
		}
		
		if (banco.equalsIgnoreCase("MySQL")) {
			if (acao.equalsIgnoreCase("Inserir")) {
				System.out.println("TESTE DE INSERCAO BANCO DE DADOS MYSQL");
				FabricaEstatisticaComando fabricaInserirMySQL = new FabricaEstatisticaComando();
				for (int i = 0; i < NUMERO_DE_THREADS; i++) {
					ExecutarInsercaoMySQL insercao = new ExecutarInsercaoMySQL();
					insercao.setNomeArquivoEntrada(NOME_ARQUIVO_ENTRADA);
					insercao.setFabrica(fabricaInserirMySQL);
					insercao.setId(i);
					
					Thread thread = new Thread(insercao);
					thread.start();
				}
			} else if (acao.equalsIgnoreCase("Consultar")) {
				System.out.println("TESTE DE CONSULTA BANCO DE DADOS MYSQL");
				FabricaEstatisticaComando fabricaConsultarMySQL = new FabricaEstatisticaComando();
				for (int i = 0; i < NUMERO_DE_THREADS; i++) {
					ExecutarConsultaMySQL consulta = new ExecutarConsultaMySQL();
					consulta.setNomeArquivoEntrada(NOME_ARQUIVO_ENTRADA);
					consulta.setFabrica(fabricaConsultarMySQL);
					consulta.setId(i);
					
					Thread thread = new Thread(consulta);
					thread.start();
				}
			}
		} else if (banco.equalsIgnoreCase("MongoDB")) {
			if (acao.equalsIgnoreCase("Inserir")) {
				System.out.println("TESTE DE INSERCAO BANCO DE DADOS MONGODB");
				FabricaEstatisticaComando fabricaInserirMongoDB = new FabricaEstatisticaComando();
				for (int i = 0; i < NUMERO_DE_THREADS; i++) {
					ExecutarInsercaoMongoDB insercao = new ExecutarInsercaoMongoDB();
					insercao.setNomeArquivoEntrada(NOME_ARQUIVO_ENTRADA);
					insercao.setFabrica(fabricaInserirMongoDB);
					insercao.setId(i);
					
					Thread thread = new Thread(insercao);
					thread.start();
				}
			} else if (acao.equalsIgnoreCase("Consultar")) {
				System.out.println("TESTE DE CONSULTA BANCO DE DADOS MONGODB");
				FabricaEstatisticaComando fabricaConsultarMongoDB = new FabricaEstatisticaComando();
				for (int i = 0; i < NUMERO_DE_THREADS; i++) {
					ExecutarConsultaMongoDB consulta = new ExecutarConsultaMongoDB();
					consulta.setNomeArquivoEntrada(NOME_ARQUIVO_ENTRADA);
					consulta.setFabrica(fabricaConsultarMongoDB);
					consulta.setId(i);
					
					Thread thread = new Thread(consulta);
					thread.start();
				}
			}
		}
	}
}
