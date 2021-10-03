package br.com.cm.workshop.apicrud.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cm.workshop.apicrud.DTOs.ItemResponseDTO;
import br.com.cm.workshop.apicrud.DTOs.NotaFiscalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_notaFiscal")
public class NotaFiscal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private String endereco;
    private String telefone;

    @PositiveOrZero(message = "Valor Total dos Produtos não pode ser negativo.")
    @NotNull
    private Double valorTotalProdutos;

    @PositiveOrZero(message = "Valor do Frete não pode ser negativo.")
    @NotNull
    private Double frete;

    @PositiveOrZero(message = "Valor Total não pode ser negativo.")
    @NotNull
    private Double valorTotal;
    
    private String Status = "PENDENTE";

    @ManyToMany
    @NotNull
    @NotEmpty(message = "lista de itens não pode estar vazia.")
    private List<ItemPedido> itens = new ArrayList<>();

   

    public NotaFiscal(String nomeCliente, String endereco, String telefone, Double valorTotalProdutos,Double frete, List<ItemPedido> itens, Double valorTotal) {
        this.nomeCliente=nomeCliente;
        this.endereco = endereco;
        this.telefone=telefone;
        this.valorTotalProdutos=valorTotalProdutos;
        this.frete = frete;
        this.valorTotal=valorTotal;
        this.itens=itens;
        this.Status = "PENDENTE" ;
    }


    @JsonIgnore
    public Double getTotalProdutos(){
        double total = 0.0;
        for(ItemPedido item:itens){
            total =total + item.getValorTotal();
        }
        return total;
    }


    public NotaFiscalDTO toNotaFiscalDTO(){
        NotaFiscalDTO notaResponse = new NotaFiscalDTO();
        notaResponse.setId(this.id);
        notaResponse.setNomeCliente(this.nomeCliente);
        notaResponse.setEndereco(this.endereco);
        notaResponse.setTelefone(this.telefone);
        notaResponse.setValorTotalProdutos(this.valorTotalProdutos);
        notaResponse.setFrete(this.frete);
        notaResponse.setValorTotal(this.valorTotal);
        notaResponse.setStatus(this.Status);
        

        for(ItemPedido pedido: this.itens){
            ItemResponseDTO itemResponse = new ItemResponseDTO();
            itemResponse = itemResponse.toItemResponse(pedido);
            notaResponse.getItens().add(itemResponse);
            
        }
        return notaResponse;
    }

}
