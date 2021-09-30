package br.com.cm.workshop.apicrud.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Produto {
    
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(unique = true)
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
