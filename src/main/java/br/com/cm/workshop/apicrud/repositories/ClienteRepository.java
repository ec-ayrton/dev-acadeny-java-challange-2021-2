package br.com.cm.workshop.apicrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cm.workshop.apicrud.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    
}
