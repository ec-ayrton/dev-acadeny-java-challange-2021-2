// package br.com.cm.workshop.apicrud;

// import java.util.Arrays;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;

// import br.com.cm.workshop.apicrud.repositories.ItemPedidoRepository;
// import br.com.cm.workshop.apicrud.repositories.NotaFiscalRepository;
// import br.com.cm.workshop.apicrud.repositories.ProdutoRepository;
// import br.com.cm.workshop.apicrud.models.ItemPedido;
// import br.com.cm.workshop.apicrud.models.NotaFiscal;
// import br.com.cm.workshop.apicrud.models.Produto;

// @Configuration
// @Profile("test")
// public class CarregarDados  implements CommandLineRunner{


//     @Autowired
//     ProdutoRepository produtoRepository;

//     @Autowired
//     NotaFiscalRepository notaFiscalRepository;

//     @Autowired
//     ItemPedidoRepository itemPedidoRepository;

//     @Override
//     public void run(String... args) throws Exception {
       

//         Produto p1 = new Produto("coca em lata",3.5);
//         Produto p2 = new Produto("coca 1L",5.5);
//         Produto p3 = new Produto("coca 2L",9.5);
//         Produto p4 = new Produto("agua 500ml",2.5);
//         Produto p5 = new Produto("Cerveja Brahma lata",3.5);
//         Produto p6 = new Produto("refri",5.5);
//         Produto p7 = new Produto("batatinha",5.0);
//         Produto p8 = new Produto("Coxinha",3.0);

//         produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));

//         ItemPedido item1= new ItemPedido(p6,1);
//         ItemPedido item2= new ItemPedido(p7,1);
//         ItemPedido item3= new ItemPedido(p8,1);
//         ItemPedido item4= new ItemPedido(p1,2);

//         itemPedidoRepository.saveAll(Arrays.asList(item1,item2,item3,item4));

//          NotaFiscal nota1 = new NotaFiscal("Ayrton Sousa", "Rua Ednir Pinto", "889911223344",10.5 ,5.0, Arrays.asList(item1,item2),15.5);
//          NotaFiscal nota2 = new NotaFiscal("JOSE FRANCISCO","Rua A, 500","8532795578",13.5,2.5,Arrays.asList(item1,item2,item3),16.0);
//          NotaFiscal nota3 = new NotaFiscal("Ana","Rua Basilio Pinto, 32","40028922",12.5,5.0, Arrays.asList(item1,item4),17.5);
        
//          notaFiscalRepository.saveAll(Arrays.asList(nota1,nota2,nota3));
//     }
    
// }
