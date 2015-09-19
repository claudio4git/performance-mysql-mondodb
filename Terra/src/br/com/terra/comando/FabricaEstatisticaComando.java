package br.com.terra.comando;

import org.bson.Document;

import br.com.terra.estatistica.Estatistica;

public class FabricaEstatisticaComando {
	Estatistica estatistica = new Estatistica();
	
	public void setIdThread(int idThread) {
		estatistica.setIdThread(idThread);
	}
	
	public void inserirBancoMySQL(String latitude, String longitude) {
		ComandoBancoMySQL comandoBanco = new ComandoBancoMySQL();
		estatistica.setNomearquivo("InsercaoMySQL");
		comandoBanco.setEstatistica(estatistica);
		
		comandoBanco.comandoInserir(String.format("INSERT INTO coordenadas(latitude,longitude) values(%s, %s)", 
				latitude.replace(",", "."), longitude.replace(",", ".")));
	}
	
	public void consultarBancoMySQL(String latitude, String longitude) {
		ComandoBancoMySQL comandoBanco = new ComandoBancoMySQL();
		estatistica.setNomearquivo("ConsultaMySQL");
		comandoBanco.setEstatistica(estatistica);
		
		comandoBanco.comandoConsultar(String.format("SELECT latitude,longitude FROM coordenadas WHERE latitude=%s AND longitude=%s", 
				latitude.replace(",", "."), longitude.replace(",", ".")));
	}
	
	public void inserirBancoMongoDB(String latitude, String longitude) {
		ComandoBancoMongoDB comandoBanco = new ComandoBancoMongoDB();
		Document documento = new Document().append("latitude", latitude).append("longitude", longitude);
		estatistica.setNomearquivo("InsercaoMongoDB");
		comandoBanco.setEstatistica(estatistica);
		
		comandoBanco.comandoInserir(documento);
	}
	
	public void consultarBancoMongoDB(String latitude, String longitude) {
		ComandoBancoMongoDB comandoBanco = new ComandoBancoMongoDB();
		Document documento = new Document().append("latitude", latitude).append("longitude", longitude);
		estatistica.setNomearquivo("ConsultaMongoDB");
		comandoBanco.setEstatistica(estatistica);
		
		comandoBanco.comandoConsultar(documento);
	}
}
