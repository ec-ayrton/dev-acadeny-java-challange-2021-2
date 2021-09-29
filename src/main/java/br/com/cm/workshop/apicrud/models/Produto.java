package br.com.cm.workshop.apicrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double precoUnitario;
    private int quantidade;
    private Double valorTotal;

    public Produto(String descricao, double precoUnitario, int quantidade) {
        this.descricao=descricao;
        this.precoUnitario=precoUnitario;
        this.quantidade=quantidade;
        this.valorTotal=quantidade*precoUnitario;
    }


}
