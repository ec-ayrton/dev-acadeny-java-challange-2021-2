package br.com.cm.workshop.apicrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cm.workshop.apicrud.models.NotaFiscal;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Long>{
    
}
