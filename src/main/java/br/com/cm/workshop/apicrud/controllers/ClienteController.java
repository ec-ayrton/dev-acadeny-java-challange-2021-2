package br.com.cm.workshop.apicrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cm.workshop.apicrud.models.Cliente;
import br.com.cm.workshop.apicrud.services.ClienteService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {
    
    @Autowired
    ClienteService service;

    @GetMapping
    public List<Cliente> listaTodos(){
        return service.listarTodos();
    }
    @GetMapping( value = "/{id}")
    public ResponseEntity<Object> ListarPorId(@PathVariable Long id){
        
        Optional <Cliente> clienteOpt = service.listarPorId(id);
        if(clienteOpt.isPresent()){
            return ResponseEntity.ok().body(clienteOpt.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado !");
        }
    }
}
