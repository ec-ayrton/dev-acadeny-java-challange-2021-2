package br.com.cm.workshop.apicrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cm.workshop.apicrud.repositories.NotaFiscalRepository;
import java.util.List;
import br.com.cm.workshop.apicrud.models.NotaFiscal;

@Service
public class NotaFiscalService {
    
    @Autowired
    NotaFiscalRepository repository;


    public List<NotaFiscal> listarTodos(){
        return repository.findAll();
    }

}
