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

9 - Jsons a seguir  que podem ser usados como exemplo nas requisições a seguir:

POST
```json
{
    "nomeCliente": "Rogerio",
    "endereco": "rua jk",
    "telefone": "889988775511",
    "valorTotalProdutos": 25.5,
    "frete":5.0,
    "valorTotal": 30.5,
    "itens": [
        {
            "descricao": "coca em lata",
            "precoUnitario": 3.5,
            "quantidade": 2,
            "valorTotal": 7.0
        },
        {
            "descricao": "coca 1L",
            "precoUnitario": 5.5,
            "quantidade": 1,
            "valorTotal": 5.5
        },
        {
            "descricao":"ovo",
            "precoUnitario":6.5,
            "quantidade":2,
            "valorTotal":13.0
        }
    ]
}
```
OBSERVE QUE O ID NÃO PRECISA SER FORNECIDO E OS VALORES INFORMADOS SERÃO VERIFICADOS ANTES DE SEREM PERSISTIDOS NO BANCO DE DADOS.
```json
{
"id":"123456",
"nomeCliente":"JOSE FRANCISCO",
"endereco":"Rua A, 500",
"telefone":"8532795578",
"valorTotalProdutos":13.50,
"frete":2.50,
"valorTotal":16.00,
"itens": 
    [{
        "descricao": "Refri",
        "precoUnitario": 5.5,
        "quantidade": 1,
        "valorTotal": 5.5
   },
   {
        "descricao": "Coxinha",
        "precoUnitario": 3.00,
        "quantidade": 1,
        "valorTotal": 3.00
   },
   {
        "descricao": "Batatinha",
        "precoUnitario": 5.00,
        "quantidade": 1,
        "valorTotal": 5.50
   }]
}
```
Observe que agora lançara uma exceção por causa do valor total do produto batatinha informado na nota(5.50) difere do valor real(5.00).
Assim como se for corrigo o valor unitário ao inves do Valor total, apenas. a exceção mudará apenas,
pois o valor total de todos os produtos vai continuar diferente do valor real.

-------------------------------------------------------------
SOBRE O STATUS DA NOTA FISCA:
É ESPERANDO UM JSON NESSE FORMATO:
```json
{
    "status":"CANCELADO"
}
```
```json
{
    "status":"EM_PROCESSAMENTO"
}


Regras de STATUS para os Endpoints:
  - Não É permitido alterar o status para CANCELADO, caso o status atual seja EM_PROCESSAMENTO ou CANCELADO;
  - É Permitido alterar o status para EM_PROCESSAMENTO, caso o status atual seja PENDENTE ou COM_ERRO;
  - É Permitido alterar o status para APROVADA, caso o status atual seja EM_PROCESSAMENTO.
