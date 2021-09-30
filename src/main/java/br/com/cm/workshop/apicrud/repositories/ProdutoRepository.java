package br.com.cm.workshop.apicrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cm.workshop.apicrud.models.Produto;

import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
    
   // @Query("SELECT p FROM Produto p where p.descricao like %?1% ")
    List<Produto> findByDescricao(String descricao);
}
