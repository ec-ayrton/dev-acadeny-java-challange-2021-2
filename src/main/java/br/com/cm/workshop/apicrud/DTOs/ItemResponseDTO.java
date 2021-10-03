package br.com.cm.workshop.apicrud.DTOs;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


import br.com.cm.workshop.apicrud.models.ItemPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemResponseDTO implements Serializable{

    @NotEmpty(message = "O item deve possuir um nome ! DTO")
    @NotNull(message = "DTO DESCRI NULL")
    private String descricao;

    @NotBlank(message = "Preco deve ser informado.")
    @PositiveOrZero(message = "Preco não pode ser negativo.")
    private Double precoUnitario;

    @Positive( message = "A quantidade do item deve ser maior do que zero ! ")
    private int quantidade;
    
    @PositiveOrZero(message = "O valor total do item não pode ser negativo ! ")
    @NotBlank(message = "valor total nao pode ser nulo")
    private Double valorTotal;

    

    public ItemResponseDTO(@NotBlank String descricao, @NotBlank @PositiveOrZero Double precoUnitario, @Positive int quantidade, @PositiveOrZero Double valorTotal){
        this.descricao=descricao;
        this.precoUnitario=precoUnitario;
        this.quantidade=quantidade;
        this.valorTotal=valorTotal;
    }


    public ItemResponseDTO toItemResponse (ItemPedido pedido){
        ItemResponseDTO itemResponse = new ItemResponseDTO();

        itemResponse.setDescricao(pedido.getProduto().getDescricao());
        itemResponse.setPrecoUnitario(pedido.getProduto().getPrecoUnitario());
        itemResponse.setQuantidade(pedido.getQuantidadeProduto());
        itemResponse.setValorTotal(pedido.getValorTotal());
        return itemResponse;
    }

}
