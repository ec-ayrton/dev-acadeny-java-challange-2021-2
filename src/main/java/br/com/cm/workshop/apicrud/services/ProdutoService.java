package br.com.cm.workshop.apicrud.services;

import java.util.List;

//import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cm.workshop.apicrud.models.Produto;
import br.com.cm.workshop.apicrud.repositories.ProdutoRepository;

@Service
public class ProdutoService {
    

    @Autowired
    ProdutoRepository repository;

    public List<Produto> buscarPorDescricao(String descricao){
        return repository.findByDescricao(descricao);
    }

}
