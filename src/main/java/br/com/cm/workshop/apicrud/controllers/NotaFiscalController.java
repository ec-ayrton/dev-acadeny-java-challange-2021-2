package br.com.cm.workshop.apicrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import br.com.cm.workshop.apicrud.DTOs.NotaFiscalDTO;
import br.com.cm.workshop.apicrud.models.NotaFiscal;
import br.com.cm.workshop.apicrud.services.NotaFiscalService;


@RestController
@RequestMapping("/api/v1/notas-fiscais")
public class NotaFiscalController {
    
    @Autowired
    NotaFiscalService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<NotaFiscalDTO>> listarTodos(){
        List<NotaFiscal> list = service.listarTodos();
        List<NotaFiscalDTO> listResponse = new ArrayList<>();
        for(NotaFiscal nota: list){
            listResponse.add(nota.toNotaFiscalDTO());
        }
        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<NotaFiscalDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPorId(id).toNotaFiscalDTO());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public NotaFiscalDTO salvarNota(@RequestBody NotaFiscalDTO notaFiscal){

        
     

        return service.salvarNotaFiscal(notaFiscal).toNotaFiscalDTO();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public NotaFiscalDTO salvarNota(@PathVariable Long id, @RequestBody NotaFiscalDTO notaFiscal){
        return service.atualizarNotaFiscal(id, notaFiscal).toNotaFiscalDTO();
    }

}
