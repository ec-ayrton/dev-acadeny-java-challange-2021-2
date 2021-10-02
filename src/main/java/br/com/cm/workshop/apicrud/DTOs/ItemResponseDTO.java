package br.com.cm.workshop.apicrud.DTOs;

import java.io.Serializable;

import br.com.cm.workshop.apicrud.models.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO implements Serializable{
    private String descricao;
    private Double precoUnitario;
    private int quantidade;
    private Double valorTotal;

    public ItemResponseDTO toItemResponse (ItemPedido pedido){
        ItemResponseDTO itemResponse = new ItemResponseDTO();

        itemResponse.setDescricao(pedido.getProduto().getDescricao());
        itemResponse.setPrecoUnitario(pedido.getProduto().getPrecoUnitario());
        itemResponse.setQuantidade(pedido.getQuantidadeProduto());
        itemResponse.setValorTotal(pedido.getValorTotal());
        return itemResponse;
    }

}
