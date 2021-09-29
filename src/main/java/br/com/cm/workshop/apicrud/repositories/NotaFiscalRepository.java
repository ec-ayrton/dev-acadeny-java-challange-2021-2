package br.com.cm.workshop.apicrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cm.workshop.apicrud.models.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Long>{
    
}
