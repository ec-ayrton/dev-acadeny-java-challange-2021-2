package br.com.cm.workshop.apicrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<NotaFiscal>> listarTodos(){
        return ResponseEntity.ok().body(service.listarTodos());
    }
}
