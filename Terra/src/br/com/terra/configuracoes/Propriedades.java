package br.com.terra.configuracoes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Propriedades {

	private Properties propriedades = new Properties();

	public Propriedades() {
		try {
			propriedades.load(new FileInputStream(new File("configuracoes.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getPropriedades() {
		return propriedades;
	}
}
