package br.com.cm.workshop.apicrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@Table(name = "tb_produto")
public class Produto {
    
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotEmpty(message = "Nome do Produto não pode estar vazio PRODUTO")
    private String descricao;

    @PositiveOrZero(message = "preco do produto não pode ser negativo")
    @NotNull(message = "preco unitario deve ser informado.")
    private Double precoUnitario;
   
    public Produto(@NotEmpty String descricao, @NotNull double precoUnitario) {
        this.descricao=descricao;
        this.precoUnitario=precoUnitario;
    }
}
