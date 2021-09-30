package br.com.cm.workshop.apicrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.com.cm.workshop.apicrud.models.NotaFiscal;
import br.com.cm.workshop.apicrud.services.NotaFiscalService;


@RestController
@RequestMapping("/api/v1/notas-fiscais")
public class NotaFiscalController {
    
    @Autowired
    NotaFiscalService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<NotaFiscal>> listarTodos(){
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<NotaFiscal> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public NotaFiscal salvarNota(@RequestBody NotaFiscal notaFiscal){
        return service.salvarNotaFiscal(notaFiscal);
    }

    // @PutMapping(value = "/{id}")
    // @ResponseStatus(code = HttpStatus.OK)
    // public NotaFiscal salvarNota(@PathVariable Long id, @RequestBody NotaFiscal notaFiscal){
    //     return service.atualizarNotaFiscal(id, notaFiscal);
    // }

}
