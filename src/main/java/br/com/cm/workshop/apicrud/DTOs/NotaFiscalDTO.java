package br.com.cm.workshop.apicrud.DTOs;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


import br.com.cm.workshop.apicrud.models.ItemPedido;
import br.com.cm.workshop.apicrud.models.NotaFiscal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotaFiscalDTO implements Serializable{
    private Long id;

    @NotNull(message = "nome do cliente precisa ser informado.")
    private String nomeCliente;

    @NotNull(message = "endereco do cliente precisa ser informado.")
    private String endereco;

    @NotNull(message = "telefone do cliente precisa ser informado.")
    private String telefone;

    @PositiveOrZero(message = "Valor não pode ser negativo.")
    @NotNull
    private Double valorTotalProdutos;

    @PositiveOrZero(message = "Valor não pode ser negativo.")
    @NotNull
    private Double frete;

    @PositiveOrZero(message = "Valor não pode ser negativo.")
    @NotNull
    private Double valorTotal;

    
    private String status;

    @NotEmpty(message = "lista de itens não pode estar vazia")
    private List<ItemResponseDTO> itens = new ArrayList<>();

    public NotaFiscalDTO(@NotNull  String nomeCliente,@NotNull String endereco,@NotNull String telefone,@NotNull double valorTotalProdutos,@NotNull double frete,
            @Valid @NotEmpty List<ItemResponseDTO> itens, double valorTotal) {
                this.nomeCliente = nomeCliente;
                this.endereco = endereco;
                this.telefone=telefone;
                this.valorTotalProdutos=valorTotalProdutos;
                this.frete = frete;
                this.valorTotal=valorTotal;
                this.itens=itens;
    }

    public NotaFiscal toModel(List<ItemPedido> itens2) {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(this.id);
        notaFiscal.setNomeCliente(this.nomeCliente);
        notaFiscal.setEndereco(this.endereco);
        notaFiscal.setTelefone(this.telefone);
        notaFiscal.setValorTotalProdutos(this.valorTotalProdutos);
        notaFiscal.setFrete(this.frete);
        notaFiscal.setValorTotal(this.valorTotal);
        notaFiscal.setItens(itens2);
        notaFiscal.setStatus(this.status);
        return notaFiscal;
    }

    


}