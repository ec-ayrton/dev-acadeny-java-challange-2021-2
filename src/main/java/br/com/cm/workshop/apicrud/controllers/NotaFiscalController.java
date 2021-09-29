package br.com.cm.workshop.apicrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import br.com.cm.workshop.apicrud.models.NotaFiscal;
import br.com.cm.workshop.apicrud.services.NotaFiscalService;

@RestController
@RequestMapping("/api/v1/notas-fiscais")
public class NotaFiscalController {
    
    @Autowired
    NotaFiscalService service;

    @GetMapping
    public ResponseEntity<List<NotaFiscal>> listarTodos(){
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<NotaFiscal>> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }
}
