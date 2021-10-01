package br.com.cm.workshop.apicrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

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
    private Produto produto;
    private int quantidadeProduto;
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
