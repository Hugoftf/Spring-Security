
![imagem local](/imagem_readme/logo.jpg)


# Spring-Security

  - [Tecnologias Usadas](#Tecnologias-Usadas)
  - [Sobre](#Sobre)
  - [Inicio](#Inicio)
  - [Configurações de Segurança](#Configuração)
  

 
## Tecnologias Usadas

[Java](https://www.java.com/pt-BR/) / [Spring](https://spring.io/projects/spring-boot) / [Docker](https://www.docker.com/) / [PostgresSQL](https://www.postgresql.org/) / [Pgadmin4](https://www.pgadmin.org/download/pgadmin-4-windows/)
 / [Postman](https://www.postman.com/)


## Sobre

Entendendo e se aprofundando em Spring Security, iremos usar como base já um projeto nosso [API-Rest](#https://github.com/Hugoftf/API-Rest) estruturado com API pronta, e também já mapeado com Spring Data


## Inicio

Para começar o projeto precisamos antes adicionar o starter do spring security para habilitar as ferreamentas que iremos trabalhar:


![imagem local](/imagem_readme/starter-security.png)


Depois de adicionar, atualizar o maven e iniciar para testar, se estiver tudo ok, notará uma pequena diferença no programa ao inicializar, no console da IDE o spring retornar uma mensagem seguido de um password:


![imagem local](/imagem_readme/chave_de_segurança.png)


Essa é a chave de segurança do nosso sistema, por padrão ao adicionar o starter do spring security o sistema já protege nossa aplicação com o [Basic Auth](#https://en.wikipedia.org/wiki/Basic_access_authentication), na qual ao tentar utilizar algum código HTTP no nosso browser ou Postman ele retornará acesso negado:


![imagem local](/imagem_readme/tentando_fazer_um_GET_postman.png)


Para conseguirmos utilizar nosso programa necessitamos inserir um usuario e senha, por padrão o usuario é "user", e a senha a cada vez que o programa for iniciado, irá gerar uma senha diferente. Depois de autenticar, podemos realizar operações no programa:


![imagem local](/imagem_readme//retorno_com_sucesso_postman.png)


## Configuração


Para começar a configurar na camada de segurança, precisamos criar uma classe para anotar com a @Configuration, ela será um Bean na nossa aplicação, e também será anotada com o @EnableWebSecurity ela vai servi para habilitar as ferramentas de configuração de security na nossa aplicação, e um metodo do tipo SecurityFilterChain que " é responsável por definir e personalizar a cadeia de filtros de segurança do Spring Security. Ele é usado para configurar autenticação, autorização e outros aspectos da segurança na aplicação".


![imagem local](//imagem_readme/confing_Security/class_SecurityConfiguration_and_metodo.png)




