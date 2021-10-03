package br.com.cm.workshop.apicrud.api.controllerTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cm.workshop.apicrud.DTOs.ItemResponseDTO;
import br.com.cm.workshop.apicrud.DTOs.NotaFiscalDTO;
import br.com.cm.workshop.apicrud.models.Status;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;


@SpringBootTest
public class NotaFiscalControllerTest {

    
    @Value("${server.port}")
    private int porta;


    private RequestSpecification requisicao;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void preparaRequisicao() {
        requisicao = new RequestSpecBuilder()
        .setBasePath("/api/v1/notas-fiscais")
        .setPort(porta)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();
    }

    ///////////////////////
    //METODOS POST/////////
    //////////////////////

    @Test
    public void deveriaCriarUmaNotaFiscalComSucesso() throws JsonProcessingException {
        
        NotaFiscalDTO  nota=
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(NotaFiscalDTO.class);
        assertNotNull(nota);
        assertNotNull(nota.getId());
        assertEquals(dadoUmaNotaFiscalPadrao().getNomeCliente(), nota.getNomeCliente());
        assertEquals(dadoUmaNotaFiscalPadrao().getItens(), nota.getItens());
        
    }

    @Test
    public void deveriaCriarUmaNotaFiscalComSucessoComProdutoNaoCadastrado() throws JsonProcessingException {
        
        NotaFiscalDTO  nota=
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadraoComProdutoNaoCadastrado()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(NotaFiscalDTO.class);
        assertNotNull(nota);
        assertNotNull(nota.getId());
        assertEquals(dadoUmaNotaFiscalPadraoComProdutoNaoCadastrado().getNomeCliente(), nota.getNomeCliente());
        assertEquals(dadoUmaNotaFiscalPadraoComProdutoNaoCadastrado().getItens(), nota.getItens());
        
    }
    @Test
    public void deveriaFalharValorTotalProdutoDiferenteDaNota() throws JsonProcessingException {
        
        
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComSomaProdutoDiferenteDaNota()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    @Test
    public void deveriaFalharValorTotalDOSProdutoSDiferenteDaNota() throws JsonProcessingException {
        
        
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComSomaItensDiferenteDaNota()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }


    @Test
    public void deveriaFalharValorTotalItensDiferenteDaNota() throws JsonProcessingException {
        
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComSomaTotalDiferenteDaNota()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }





     ///////////////////////
    //METODOS GET/////////
    //////////////////////
    @Test
    public void deveriaBuscarTodasAsNotasFiscalComSucesso() {
        given()
            .spec(requisicao)
        .expect()
            .statusCode(HttpStatus.SC_OK)
        .when()
            .get();
    }

    @Test
    public void deveriaBuscarUmaNotaFiscalComSucesso() throws JsonProcessingException {

        //CADASTRANDO UMA NOTA FISCAL VALIDA PARA SER BUSCADA
        NotaFiscalDTO  notaCadastrada=
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(NotaFiscalDTO.class);
        assertNotNull(notaCadastrada);
        assertNotNull(notaCadastrada.getId());
        assertEquals(dadoUmaNotaFiscalPadrao().getNomeCliente(), notaCadastrada.getNomeCliente());
        assertEquals(dadoUmaNotaFiscalPadrao().getItens(), notaCadastrada.getItens());

        //NOTA FISCAL A SER BUSCADA
        NotaFiscalDTO  notaBuscada =
        given()
            .spec(requisicao)
        .when()
            .get("/{id}", notaCadastrada.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
        .extract()
            .as(NotaFiscalDTO.class);

        assertNotNull(notaBuscada);
        assertNotNull(notaBuscada.getId());
        assertEquals(notaCadastrada.getId(), notaBuscada.getId());
        
    }

    @Test
    public void deveriaFalharAoBuscarUmaNotaFiscal() throws JsonProcessingException {

        //NOTA FISCAL A SER BUSCADA
        given()
            .spec(requisicao)
        .when()
            .get("/{id}", 1000L)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
          
    }

    ///////////////////////
    //METODOS DELETE///////
    //////////////////////
    @Test
    public void deveriaFalharAoDeletarUmaNotaFiscal() throws JsonProcessingException {

        //NOTA FISCAL A SER BUSCADA
        given()
            .spec(requisicao)
        .when()
            .delete("/{id}", 1000L)
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
          
    }

    @Test
    public void deveriaDeletarUmaNotaFiscalComSucesso() throws JsonProcessingException {

        //CADASTRANDO UMA NOTA FISCAL VALIDA PARA SER Deletada
        NotaFiscalDTO  notaCadastrada=
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(NotaFiscalDTO.class);
        assertNotNull(notaCadastrada);
        assertNotNull(notaCadastrada.getId());
        assertEquals(dadoUmaNotaFiscalPadrao().getNomeCliente(), notaCadastrada.getNomeCliente());
        assertEquals(dadoUmaNotaFiscalPadrao().getItens(), notaCadastrada.getItens());

       
        //DELETANDO A NOTA CADASTRADA
        given()
            .spec(requisicao)
        .when()
            .delete("/{id}", notaCadastrada.getId())
        .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    ///////////////////////
    //METODOS PUT//////////
    //////////////////////
    @Test
    public void deveriaAtualizarUmaNotaFiscalComSucesso() throws JsonProcessingException {

        //CADASTRANDO UMA NOTA FISCAL VALIDA PARA SER Atualizada
        NotaFiscalDTO  notaCadastrada1=
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(NotaFiscalDTO.class);
        assertNotNull(notaCadastrada1);
        assertNotNull(notaCadastrada1.getId());
        assertEquals(dadoUmaNotaFiscalPadrao().getNomeCliente(), notaCadastrada1.getNomeCliente());
        assertEquals(dadoUmaNotaFiscalPadrao().getItens(), notaCadastrada1.getItens());

        //instanciando UMA NOTA FISCAL VALIDA PARA SUBSTITUIR A OUTRA
        NotaFiscalDTO  notaCadastrada2= dadoUmaNotaFiscalPadrao2();
        notaCadastrada2.setId(notaCadastrada1.getId());
       
        //atualizando  a nota fiscal
        NotaFiscalDTO notaAtualizada =

        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(notaCadastrada2))
        .when()
            .put("/{id}", notaCadastrada1.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
        .extract()
            .as(NotaFiscalDTO.class);;

        assertEquals(notaAtualizada.getId(), notaCadastrada2.getId());
        assertEquals(notaAtualizada.getNomeCliente(), notaCadastrada2.getNomeCliente());
        assertEquals(notaAtualizada.getItens(), notaCadastrada2.getItens());
    }

    @Test
    public void DeveriaAtualizarStatusParaEmProcessamentoDePendentecomSucesso() throws JsonProcessingException{
        //CADASTRANDO UMA NOTA FISCAL VALIDA PARA TER O STATUS MUDADO 
        NotaFiscalDTO  notaCadastrada=
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(NotaFiscalDTO.class);

        assertEquals(notaCadastrada.getStatus(),"PENDENTE" );

        //
        Status status = DadoUmStatusPadrao();
        assertEquals(DadoUmStatusPadrao().getStatus(),"PENDENTE" );
        assertEquals(status.getStatus(),"PENDENTE" );
        
        status.setStatus("EM_PROCESSAMENTO");

        Status statusfinal =
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(status))
        .when()
            .put("/{id}/status", notaCadastrada.getId())
        .then()
            .statusCode(HttpStatus.SC_OK).extract()
            .as(Status.class);
        
        assertEquals(statusfinal.getStatus(), status.getStatus());
    }               
    @Test
    public void DeveriaAtualizarStatusParaEmProcessamentoDeComErrocomSucesso() throws JsonProcessingException{
        //CADASTRANDO UMA NOTA FISCAL VALIDA PARA TER O STATUS MUDADO 
        NotaFiscalDTO  notaCadastrada=
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.SC_CREATED)
        .extract()
            .as(NotaFiscalDTO.class);

        assertEquals(notaCadastrada.getStatus(),"PENDENTE" );

        //PREPARANDO MANUALMENTE PRA FAZER AS ALTERAÇÕES VIA REQUISIÇÃO
        Status status = DadoUmStatusPadrao();
        assertEquals(DadoUmStatusPadrao().getStatus(),"PENDENTE" );
        assertEquals(status.getStatus(),"PENDENTE" );
        notaCadastrada.setStatus("COM_ERRO");
        assertEquals(notaCadastrada.getStatus(),"COM_ERRO" );

        status.setStatus("EM_PROCESSAMENTO");
        

        Status statusfinal =
        given()
            .spec(requisicao)
            .body(objectMapper.writeValueAsString(status))
        .when()
            .put("/{id}/status", notaCadastrada.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
        .extract()
            .as(Status.class);
        
        assertEquals(statusfinal.getStatus(), status.getStatus());
    }   










    ///////////////////////
    //METODOS AUXILIARES//
    //////////////////////

    private Status DadoUmStatusPadrao() {
        Status status = new Status();
        return status;
    }

    private NotaFiscalDTO dadoUmaNotaFiscalPadrao() {
        ItemResponseDTO itemPadrao1 = new ItemResponseDTO("coca 1L",5.5,1,5.5);
        ItemResponseDTO itemPadrao2 = new ItemResponseDTO("Coxinha",3.0,1,3.0);
      
        NotaFiscalDTO nota = new NotaFiscalDTO("Ayrton Sousa", "Rua Ednir Pinto", "889911223344",8.5 ,5.0, Arrays.asList(itemPadrao1,itemPadrao2),13.5);
        return nota;
    }

    private NotaFiscalDTO dadoUmaNotaFiscalPadrao2() {
        ItemResponseDTO itemPadrao1 = new ItemResponseDTO("coca 1L",5.5,2,11.0);
      
        NotaFiscalDTO nota = new NotaFiscalDTO("Jose Sousa", "Rua JK", "889911223399",11.0 ,5.0, Arrays.asList(itemPadrao1),16.0);
        return nota;
    }




    private NotaFiscalDTO dadoUmaNotaFiscalPadraoComProdutoNaoCadastrado() {
        ItemResponseDTO itemPadrao1 = new ItemResponseDTO("coca 1L",5.5,1,5.5);
        ItemResponseDTO itemPadrao2 = new ItemResponseDTO("agua",2.0,1,2.0);
      
        NotaFiscalDTO nota = new NotaFiscalDTO("Ayrton Sousa", "Rua Ednir Pinto", "889911223344",7.5 ,2.5, Arrays.asList(itemPadrao1,itemPadrao2),10.0);
        return nota;
    }




    //TOTAL DA SOMA DO PRODUTO X QUANTIDADE ERRADO
    private NotaFiscalDTO dadoUmaNotaFiscalComSomaProdutoDiferenteDaNota() {
        ItemResponseDTO itemPadrao1 = new ItemResponseDTO("coca 1L",5.5,2,10.0);        
        ItemResponseDTO itemPadrao2 = new ItemResponseDTO("Coxinha",3.0,3,9.0);

        //NOTE QUE O VALOR TOTAL DOS PRODUTOS COINCIDE, MAS O VALOR TOTAL DO PRODUTO ESTA INCORRETO.
        NotaFiscalDTO nota = new NotaFiscalDTO("Ayrton Sousa", "Rua Ednir Pinto", "889911223344",19.0 ,5.0, Arrays.asList(itemPadrao1,itemPadrao2),24.0);        
        return nota;
    }
    //TOTAL DA SOMA DOS ITENS ERRADO
    private NotaFiscalDTO dadoUmaNotaFiscalComSomaItensDiferenteDaNota() {
        ItemResponseDTO itemPadrao1 = new ItemResponseDTO("coca 1L",5.5,2,11.0);        
        ItemResponseDTO itemPadrao2 = new ItemResponseDTO("Coxinha",3.0,3,9.0);

        //NOTE QUE O VALOR TOTAL POR PRODUTO COINCIDE, MAS A SOMA DOS ITENS ESTA ERRADA. SOMA TOTAL DOS ITENS COM FRETE COINCIDE.
        NotaFiscalDTO nota = new NotaFiscalDTO("Ayrton Sousa", "Rua Ednir Pinto", "889911223344",21.0 ,5.0, Arrays.asList(itemPadrao1,itemPadrao2),26.0);        
        return nota;
    }
    //TOTAL DA COMPRA ERRADO
    private NotaFiscalDTO dadoUmaNotaFiscalComSomaTotalDiferenteDaNota() {
        ItemResponseDTO itemPadrao1 = new ItemResponseDTO("coca 1L",5.5,2,11.0);
        ItemResponseDTO itemPadrao2 = new ItemResponseDTO("Coxinha",3.0,3,9.0);
        
        //NOTE QUE O VALOR TOTAL DOS PRODUTOS ESTA CORRETO E SENDO PASSADO PRO CONSTRUTOR DE FORMA CORRETA, MAS A SOMA DO TOTAL dos itens com o frete não está.
        NotaFiscalDTO nota = new NotaFiscalDTO("Ayrton Sousa", "Rua Ednir Pinto", "889911223344",20.0 ,5.0, Arrays.asList(itemPadrao1,itemPadrao2),20.5);
        return nota;
    }



}
