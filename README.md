
![imagem local](/imagem_readme/logo.jpg)


# Spring-Security

  - [Tecnologias Usadas](#Tecnologias-Usadas)
  - [Sobre](#Sobre)
  - [Inicio](#Inicio)
  - [Configurações de Segurança](#Configuração)
  

 
## Tecnologias Usadas

[Java](https://www.java.com/pt-BR/) / [Spring](https://spring.io/projects/spring-boot) / [Docker](https://www.docker.com/) / [PostgresSQL](https://www.postgresql.org/) / [Pgadmin4](https://www.pgadmin.org/download/pgadmin-4-windows/) / [Thymeleaf](https://www.thymeleaf.org/) / [HTML5](https://pt.wikipedia.org/wiki/HTML5)
 / [Postman](https://www.postman.com/)


## Sobre

Entendendo e se aprofundando em Spring Security, iremos usar como base já um projeto nosso [API-Rest](https://github.com/Hugoftf/API-Rest) estruturado com API pronta, e também já mapeado com Spring Data


## Inicio

Para começar o projeto precisamos antes adicionar o starter do spring security para habilitar as ferreamentas que iremos trabalhar:


![imagem local](/imagem_readme/starter-security.png)


Depois de adicionar, atualizar o maven e iniciar para testar, se estiver tudo ok, notará uma pequena diferença no programa ao inicializar, no console da IDE o spring retornar uma mensagem seguido de um password:


![imagem local](/imagem_readme/chave_de_segurança.png)


Essa é a chave de segurança do nosso sistema, por padrão ao adicionar o starter do spring security o sistema já protege nossa aplicação com o [Basic Auth](#https://en.wikipedia.org/wiki/Basic_access_authentication), na qual ao tentar utilizar algum código HTTP no nosso browser ou Postman ele retornará acesso negado:


![imagem local](/imagem_readme/tentando_fazer_um_GET_postman.png)


Para conseguirmos utilizar nosso programa necessitamos inserir um usuario e senha, por padrão o usuario é "user", e a senha a cada vez que o programa for iniciado, irá gerar uma senha diferente. Depois de autenticar, podemos realizar operações no programa:


![imagem local](/imagem_readme/retorno_com_sucesso_postman.png)


## Configuração


Para começar a configurar na camada de segurança, precisamos criar uma classe para anotar com a @Configuration, ela será um Bean na nossa aplicação, e também será anotada com o @EnableWebSecurity ela vai servi para habilitar as ferramentas de configuração de security na nossa aplicação, e um metodo do tipo SecurityFilterChain que " é responsável por definir e personalizar a cadeia de filtros de segurança do Spring Security. Ele é usado para configurar autenticação, autorização e outros aspectos da segurança na aplicação".


![imagem local](/imagem_readme/confing_Security/class_SecurityConfiguration_and_metodo.png)


### Configurando formulario de login


Antes de começar a configurar o formulario de login, precisamos primeiro adicionar o starter do Thymeleaf:


![imagem local](/imagem_readme/starter_thymeleaf.png)


"O Thymeleaf é um mecanismo de templates para o Spring Boot, usado para processar e renderizar páginas HTML no lado do servidor. Ele permite a criação de páginas dinâmicas, integrando dados do backend diretamente no HTML."

Logo em seguida precisamos criar uma pasta chamada templates(exatamente como esse nome se não vai retornar erro) dentro da pasta resources. O thymeleaf irá procurar essa pasta para acessar nossos arquivos html. Em seguida criaremos um arquivo chamado login.html com o formato de html, e usar uma codificação simples de html para um formulario com configurações do thymeleaf:


![imagem local](/imagem_readme/login_html.png)


Agora iremos criar uma classe chamada WebConfiguration:


![imagem local](/imagem_readme/confing_Security/classe_webConfiguration.png)


Também anotada com @Configuration, e também a anotação @EnableWebMVC ela é usada para ativar a configuração avançada do Spring MVC. Seguido de uma implementação de interface WebMvcConfigure, para habilitar configurações do spring. O metodo addViewController é um metodo sobrescrito da interface que vai servi para settar as confirações feita no arquivo html.

Seguido para criação de uma nova classe na camada controller:


![imagem local](/imagem_readme/controller/classe_loginviewcontroller.png)


Ela servirá como ponte para quando vizermos uma requisição para http://localhost:8080/login, entrar para nossa pagina de login configurada no nosso spring. 


O último detalhe do sistema uma pequena mudança no metodo SecurityFilterChain:

img

Para editar, ao inves do padrão, chamar o nosso que foi personalizado. 


