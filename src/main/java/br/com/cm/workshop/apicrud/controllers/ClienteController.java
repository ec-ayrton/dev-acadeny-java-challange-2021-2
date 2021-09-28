package br.com.cm.workshop.apicrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cm.workshop.apicrud.models.Cliente;
import br.com.cm.workshop.apicrud.services.ClienteService;
import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {
    
    @Autowired
    ClienteService service;


    @GetMapping
    public List<Cliente> listaTodos(){
        Cliente cliente1 = new Cliente(3L,"ayrton SILVA","rua jk","889988775511");
        Cliente cliente2 = new Cliente(6L,"JOSE GOMES","rua Basilio Pìnto","889988775522");
        Cliente cliente3 = new Cliente(9L,"RAI CUNHA","rua Monsenhor Tabosa","889988775533");
        Cliente cliente4 = new Cliente(10L,"CaBRAL","rua jk","889988775544");

        service.salvarCliente(cliente1);
        service.salvarCliente(cliente2);
        service.salvarCliente(cliente3);
        service.salvarCliente(cliente4);

        return service.listarTodos();
    }
}
