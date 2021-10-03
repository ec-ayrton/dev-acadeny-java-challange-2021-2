package br.com.cm.workshop.apicrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_ItemPedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @NotNull(message = "produto nao deve ser nulo")
    private Produto produto;

    @Positive( message = "A quantidade do item deve ser maior do que zero ! ++ ")
    private int quantidadeProduto;
    
    @PositiveOrZero(message = "O valor total do item não pode ser negativo ! ")
    private Double ValorTotal;

    public ItemPedido (Produto produto, int quantidadeProduto){
        this.produto=produto;
        this.quantidadeProduto=quantidadeProduto;
        this.ValorTotal=calculaValorTotal();
    }

    public Double calculaValorTotal(){
        return this.produto.getPrecoUnitario() * quantidadeProduto;
    }

}
