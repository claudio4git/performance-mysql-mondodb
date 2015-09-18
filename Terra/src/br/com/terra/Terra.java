package br.com.terra;

import br.com.terra.comando.FabricaComandoCoordenadas;

public class Terra {

	public static void main(String[] args) {
		FabricaComandoCoordenadas comando = new FabricaComandoCoordenadas();
		comando.inserirBancoMySQL(3,9);
		comando.consultarBancoMySQL(3,9);
		
		comando.inserirBancoMongoDB(10,14);
		comando.consultarBancoMongoDB(10,14);
	}
}
