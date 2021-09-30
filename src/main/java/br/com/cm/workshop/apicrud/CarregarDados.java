package br.com.cm.workshop.apicrud;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cm.workshop.apicrud.repositories.NotaFiscalRepository;
import br.com.cm.workshop.apicrud.repositories.ProdutoRepository;
import br.com.cm.workshop.apicrud.models.NotaFiscal;
import br.com.cm.workshop.apicrud.models.Produto;

@Configuration
@Profile("test")
public class CarregarDados  implements CommandLineRunner{


    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    NotaFiscalRepository notaFiscalRepository;


    @Override
    public void run(String... args) throws Exception {
       

        Produto p1 = new Produto("coca em lata",3.5,2,7.0);
        Produto p2 = new Produto("coca 1L",5.5,1,5.5);
        Produto p3 = new Produto("coca 2L",9.5,1,9.5);
        Produto p4 = new Produto("agua 500ml",2.5,4,10.0);
        Produto p5 = new Produto("Cerveja Brahma lata",3.5,1,3.5);
        Produto p6 = new Produto("refri",5.5,1,5.5);
        Produto p7 = new Produto("batatinha",5.0,1,5.0);
        Produto p8 = new Produto("Coxinha",3.0,1,3.0);

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));


        NotaFiscal nota1 = new NotaFiscal("Ayrton Sousa", "Rua Ednir Pinto", "889911223344",12.5 ,5.0, Arrays.asList(p1,p2),17.5);
        NotaFiscal nota2 = new NotaFiscal("JOSE FRANCISCO","Rua A, 500","8532795578",13.5,2.5,Arrays.asList(p6,p8,p7),16.0);
        NotaFiscal nota3 = new NotaFiscal("Ana","Rua Basilio Pinto, 32","40028922",10.0,5.0, Arrays.asList(p1,p8),15.0);
        
        notaFiscalRepository.saveAll(Arrays.asList(nota1,nota2,nota3));
    }
    
}
