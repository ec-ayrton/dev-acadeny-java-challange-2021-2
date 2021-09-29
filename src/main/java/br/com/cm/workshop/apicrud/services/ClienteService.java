package br.com.cm.workshop.apicrud.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cm.workshop.apicrud.models.Cliente;
import br.com.cm.workshop.apicrud.repositories.ClienteRepository;


@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository repository;
    
    public List<Cliente> listarTodos(){
        return repository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente){
        return repository.saveAndFlush(cliente);
    }

    public Optional<Cliente> listarPorId(Long id) {
        return repository.findById(id);
    }

}
