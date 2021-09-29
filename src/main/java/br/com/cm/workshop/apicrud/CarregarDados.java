package br.com.cm.workshop.apicrud;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cm.workshop.apicrud.repositories.ClienteRepository;
import br.com.cm.workshop.apicrud.repositories.NotaFiscalRepository;
import br.com.cm.workshop.apicrud.repositories.ProdutoRepository;
import br.com.cm.workshop.apicrud.models.Cliente;
import br.com.cm.workshop.apicrud.models.NotaFiscal;
import br.com.cm.workshop.apicrud.models.Produto;

@Configuration
@Profile("test")
public class CarregarDados  implements CommandLineRunner{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    NotaFiscalRepository notaFiscalRepository;


    @Override
    public void run(String... args) throws Exception {
       
        Cliente c1 = new Cliente("ayrton SILVA","rua jk","889988775511");
        Cliente c2 = new Cliente("JOSE GOMES","rua Basilio Pìnto","889988775522");
        Cliente c3 = new Cliente("RAI CUNHA","rua Monsenhor Tabosa","889988775533");
        Cliente c4 = new Cliente("CaBRAL","rua jk","889988775544");
        Cliente c5 = new Cliente("Vasco Da Gama","rua Portugal","889988775566");
        Cliente c6 = new Cliente("JOSE FRANCISCO","RUA A, 500","8532795578");


        clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));

        Produto p1 = new Produto("coca em lata",3.5,2);
        Produto p2 = new Produto("coca 1L",5.5,1);
        Produto p3 = new Produto("coca 2L",9.5,1);
        Produto p4 = new Produto("agua 500ml",2.5,4);
        Produto p5 = new Produto("Cerveja Brahma lata",3.5,1);
        Produto p6 = new Produto("refri",5.5,1);
        Produto p7 = new Produto("batatinha",5.0,1);
        Produto p8 = new Produto("Coxinha",3.0,1);

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));


        NotaFiscal nota1 = new NotaFiscal(c1.getNome(), c1.getEndereco(), c1.getTelefone(), 5.0, Arrays.asList(p1,p2));
        NotaFiscal nota2 = new NotaFiscal(c6.getNome(),c6.getEndereco(),c6.getTelefone(),2.5,Arrays.asList(p6,p7,p8));
        NotaFiscal nota3 = new NotaFiscal(c2.getNome(), c2.getEndereco(), c2.getTelefone(), 5.0, Arrays.asList(p1,p8));
        
        notaFiscalRepository.saveAll(Arrays.asList(nota1,nota2,nota3));
    }
    
}
