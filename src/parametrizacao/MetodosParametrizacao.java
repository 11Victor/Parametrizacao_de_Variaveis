 package parametrizacao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MetodosParametrizacao {
	
	/*
	 * Método para ler o arquivo de parametrização e salvar os dados capturados em
	 * um objeto do tipo Properties
	 */
	static Properties LerArquivo() throws FileNotFoundException {
		FileReader file = new FileReader(Parametros.CONFIG_FILE_PATH);
		Properties config = new Properties();

		try {
			config.load(file);
			file.close();
		} catch (IOException e) {
			System.out.println("Could not load properties from " + Parametros.CONFIG_FILE_PATH);
			return null;
		}
		return config;
	}
	
	
	
	
	
	/*
	 * Método para inserir dentro dos atributos do objeto os valores capturados no
	 * arquivo de parametrização
	 */
	static ObjetoParametro createAndSetObjeto(Properties config) {

		ObjetoParametro objeto = new ObjetoParametro();
		objeto.setUsername((String) config.get(Parametros.USERNAME));
		objeto.setPassword((String) config.get(Parametros.PASSWORD));
		objeto.setUrl_db((String) config.get(Parametros.URL_DB));

		return objeto;
	}
	
	
	
	
	
	//Metodo final para atribuir valor as variaveis de outras classes
	public static ObjetoParametro AtribuirValor() {

		// Criando objeto
		ObjetoParametro objeto = new ObjetoParametro();

		// Lendo Arquivo .config, é obrigatório ter um tratamento de excessões.
		// Pois o Java não sabe se o arquivo .config está realmente dentro do projeto
		Properties config = null;
		try {
			config = MetodosParametrizacao.LerArquivo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Pegando os valores do arquivo .config e atribuindo valores ao objeto
		objeto = MetodosParametrizacao.createAndSetObjeto(config);

		return objeto;
	}
}