package br.com.cm.workshop.apicrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cm.workshop.apicrud.repositories.ClienteRespository;
import br.com.cm.workshop.apicrud.models.Cliente;
import java.util.List;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRespository repository;
    
    public List<Cliente> listarTodos(){
        return repository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente){
        return repository.saveAndFlush(cliente);
    }

}
