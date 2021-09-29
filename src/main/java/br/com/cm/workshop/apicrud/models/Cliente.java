package br.com.cm.workshop.apicrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class Cliente {
   
    
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;


    public Cliente(String nome, String endereco, String telefone) {
        this.nome=nome;
        this.endereco=endereco;
        this.telefone=telefone;
    }
}
