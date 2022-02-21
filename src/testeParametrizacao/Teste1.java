package testeParametrizacao;

import parametrizacao.MetodosParametrizacao;

public class Teste1 {
	
	final static String password = MetodosParametrizacao.AtribuirValor().getPassword();
	final static String username = MetodosParametrizacao.AtribuirValor().getUsername();
	final static String url_db = MetodosParametrizacao.AtribuirValor().getUrl_db();


	public static void main(String[] args) {
		
		
		System.out.println(url_db);
		System.out.println(password);
		System.out.println(username);
	}

	
	
}
