package br.com.cm.workshop.apicrud.models;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private String endereco;
    private String telefone;
    private Double valorTotalProdutos;
    private Double frete;
    private Double valorTotal;

    @OneToMany(mappedBy = "id")
    private List<Produto> itens = new ArrayList<>();

    public NotaFiscal(String nomeCliente, String endereco, String telefone, Double frete,List<Produto> produtos){
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.telefone = telefone;
        this.frete = frete;
        this.itens = produtos;
        
        this.valorTotalProdutos = getTotalProdutos();
        this.valorTotal = this.valorTotalProdutos+this.frete;
    }
    @JsonIgnore
    public Double getTotalProdutos(){
        double total = 0.0;
        for(Produto p:itens){
            total =total + p.getValorTotal();
        }
        return total;
    }


}
