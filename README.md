
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


![imagem local](imagem_readme/confing_Security/metodo_securityFileterChain_editando_login.png)


Para editar, ao inves do padrão, chamar o nosso que foi personalizado. 

E o resultado é:


![imagem local](imagem_readme/retornos/pagina_login_bowser.png)



### Criação de Usuarios e Autenticação de senhas


Na nossa calsse SecurityConfiguration, teremos que criar um metodo para configurar e adicionar usuarios:


![imagem local](/imagem_readme/confing_Security/metodos_para_criacao_usuario_autent_senhas.png)


Não tem muito segredo para criar usuario e senha, porém o tipo de retorno, InMemoryUserDetailsManager, indica que os usuarios estão sendo criado em um banco de dados em memoria, existem outros tipos de retornos porém como é apenas para testar iremos fazer em um banco de dados em memoria, o metodo PasswordEncoder serve para criptogradfar as senhas, o retorno do metodo é o padrão, nele tem o parametro 10, que irá ditar quantas vezes a senha será criptografada.

Agora basta testar, a primeira coisa que você irá reparar é que como criamos usuarios ele não irá entregar o hash simples do basic.

Testando na página de login:


![imagem local](imagem_readme/retornos/pagina_login_bowser_com_user.png)


O retorno da requisição:


![imagem local](/imagem_readme/retornos/retorno_depois_de_login.png)


### Gerenciando Roles


Umas das ações mais importante do spring securty é o gerenciamento de roles, isso vai permitir que apenas usuarios com roles especificas estão autorizados a fazer ações que condiz com sua role, para isso, alterei o metodo securityFielterChain:


![imagem local](/imagem_readme/confing_Security/security_filter_chain_permissoes_para_roles.png)


Com isso você delega ações de acordo com hierarquia ou area de estudo, tudo de acordo com sua estratégia de arquitetura, e se alguém tentar fazer alguma ação que não condiz com sua role, o spring retornar acesso negado:


![imagem local](/imagem_readme/Postman/POST_nao_autorizado.png)


### Criando Usuario em todas as camadas


Agora iremos implementar o usuario em todas as camadas, para poder adicionar no banco de dados, delegar sua role para iniciar o processo de login a partir dos usuarios cadastrados.


Primeiro precisamos adionar uma dependencia no programa:


![imagem local](/imagem_readme/dependencia_hypersistence.png)


Ela vai servi especificamente para que quando mapearmos uma entidade, o spring consiga entender e transformar um tipo List em Array no banco de dados. Em seguida a criação da entidade Usuario:


![imagem local](/imagem_readme/entidade/classe_usuario_mapeada.png)


Agora criaremos uma Interface, UsuarioRepository na camada repository:


![imagem local](/imagem_readme/Repositoriy/interface_usuariorepository.png)


E também criamos um metodo findBylogin, será utilizado para comparar login e senha dos usuarios no banco de dados.

Agora na camada Service, a classe Usuario Service:


![imagem local](/imagem_readme/Service/classe_usuarioService.png)


A classe possui a dependencia com o UsuarioRepository para que possamos fazer operações com o banco de dados, e também com passwordEncoder, para que antes do usuario ser salvo no banco de dados, a sua senha possar ser criptografada. 

Agora, adotando o padrão DTO, criar o record UsuarioDTO:


![imagem local](imagem_readme/DTO/record_usuarioDTO.png)


E logo em seguida também criaremos o Mapper, para que o UsuarioDTO seja mepado para a entidade Usuario:


![imagem local](/imagem_readme/Mapper/classe_usuarioMapper.png)


Por fim na camada controller, UsuarioController:


![imagem local](/imagem_readme/controller/classe_usuariocontroller.png)


Agora antes de iniciar e cadastrar, preciso da a permissão para todos no sistema ter a permissão de criar um usuario:



![imagem local](/imagem_readme/confing_Security/metodo_secutirity_filter_permisaoparatodos_usuario.png)












