package br.com.terra.comando;

import org.bson.Document;

public class FabricaComandoCoordenadas {
	
	public void inserirBancoMySQL(int latitude, int longitude) {
		ComandoBancoMySQL comandoBanco = new ComandoBancoMySQL();
		comandoBanco.comandoInserir(String.format("INSERT INTO coordenadas(latitude,longitude) values(%d, %d)", latitude, longitude));
		System.out.println("Dado inserido no banco de dados MySQL");
	}
	
	public void consultarBancoMySQL(int latitude, int longitude) {
		ComandoBancoMySQL comandoBanco = new ComandoBancoMySQL();
		comandoBanco.comandoConsultar(String.format("SELECT latitude,longitude FROM coordenadas WHERE latitude=%d AND longitude=%d", latitude, longitude));
		System.out.println("Dado consultado no banco de dados MySQL");
	}
	
	public void inserirBancoMongoDB(int latitude, int longitude) {
		ComandoBancoMongoDB comandoBanco = new ComandoBancoMongoDB();
		Document documento = new Document().append("latitude", latitude).append("longitude", longitude);
		comandoBanco.comandoInserir(documento);
		System.out.println("Dado inserido no banco de dados MongoDB");
	}
	
	public void consultarBancoMongoDB(int latitude, int longitude) {
		ComandoBancoMongoDB comandoBanco = new ComandoBancoMongoDB();
		Document documento = new Document().append("latitude", latitude).append("longitude", longitude);
		comandoBanco.comandoConsultar(documento);
		System.out.println("Dado consultado no banco de dados MongoDB");
	}
}
