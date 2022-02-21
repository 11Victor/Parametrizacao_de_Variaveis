# Remoção do Hardcode
Retirar do código informações sensíveis e centralizar em um arquivo de parametrização!

# O que é Hardcode ?

Variáveis hard coded são aquelas cujos valores estão no código-fonte, mas que deveriam vir de fontes externas (por exemplo, um arquivo de configuração).

Exemplo de variável hard coded:

```java
String senhaDB = "admin123";
```

# Java.io

A melhor forma de trazer informações/dados de um **arquivo externo** para dentro do código é utilizando o Java.io.

Onde esse **arquivo externo** pode ser do tipo:

- .config
- .txt
- .properties
- entre outros...

# Automatização da Remoção do Hardcode

A automatização é composta por 3 arquivos java e um arquivo externo!

- parametrizacao.config → “Arquivo externo”
- 1 - Parametros.java
- 2 - ObjetoParametro.java
- 3 - MetodosParametrizacao.java

---

- Abaixo está o arquivo de parametrização(Arquivo externo com os dados sensíveis)

```java
username=Victor
password=admin123
urldb=https://www.youtube.com/
```

## 1. Arquivo “Paramatros.java”

Nesse arquivo é criado variáveis com valores embutidos, onde esses valores são parâmetros que são passados para identificar o atributo no arquivo externo!

- Abaixo está o arquivo Parametros.java

```java
public class Parametros {

    
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String URL_DB = "urldb";
    
    //Caso precise mudar o nome do arquivo de parametrização, é necessário mudar somente abaixo! 
    public static final String CONFIG_FILE_PATH = "parametrizacao.config";
   
}
```

---

## 2. Arquivo “ObjetoParametro.java”

Nesse arquivo vamos criar um objeto com os mesmos atributos que o arquivo de parametrização possui!

Onde ele terá get e setters, para futuramente ser inserido valores aos atributos do objeto e também buscar esses valores.

```java
public class ObjetoParametro {

    private String username;
    private String password;
    private String url_db;
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

		public String getUrl_db() {
				return url_db;
		}

		public void setUrl_db(String url_db) {
				this.url_db = url_db;
		}
}
```

---

## 3. Arquivo “MetodosParametrizacao.java”

Esse arquivo possui 3 métodos, onde eles são responsáveis por todo funcionamento da parametrização! Verifique abaixo!

### 1. Leitura do arquivo externo

Método para ler o arquivo externo e salvar os dados capturados em um objeto do tipo properties!

```java
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
```

### 2. Criando objeto e atribuindo valores a ele

O método abaixo recebe um arquivo do tipo Properties e cria um objeto do tipo “ObjetoParametro.java”, e logo em seguida atribui valores a esse objeto!

```java
static ObjetoParametro createAndSetObjeto(Properties config) {

		ObjetoParametro objeto = new ObjetoParametro();
		objeto.setUsername((String) config.get(Parametros.USERNAME));
		objeto.setPassword((String) config.get(Parametros.PASSWORD));
		objeto.setUrl_db((String) config.get(Parametros.URL_DB));

		return objeto;
	}
```

### 3. Metodo final para atribuir valor as variaveis de outras classes

```java
	public static ObjetoParametro AtribuirValor() {

		ObjetoParametro objeto = new ObjetoParametro();

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
```
