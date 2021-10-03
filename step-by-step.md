1 - Faça o download do repositorio. Para isso voce pode usar:

git clone https://github.com/ec-ayrton/dev-acadeny-java-challange-2021-2.git 

2 - Abra o projeto a IDE de sua preferência, espere baixar as dependencias.

3 - Para iniciar a aplicação voce pode ir até a classe ApiCrudApplication.java e fazer uso a opção RUN, ou executar o comando:
.\gradlew bootRun

4 - por padrão a aplicação está disponivel no localhost:8080, onde 8080 é a porta padrão.

5 - O endpoint http://localhost:{porta}/api/v1/notas-fiscais estará disponivel nessa aplicação para o gerenciamento das notas fiscais.

6 - o endpoint http://localhost:{porta}/api/v1/notas-fiscais/{id}/status está disponivel para gerenciar os status das notas fiscais existentes.

7 - uma vez que a aplicação estiver rodando, voce pode acessar a documentação gerada pelo Swagger pelo link:
http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/

8 - Na classe NotaFiscalControllerTest.java é possivel encontrar os testes realizados nessa aplicação.
