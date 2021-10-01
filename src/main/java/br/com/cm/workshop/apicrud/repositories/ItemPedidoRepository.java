package br.com.cm.workshop.apicrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cm.workshop.apicrud.models.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
    
}
