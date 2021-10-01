package br.com.cm.workshop.apicrud.DTOs;



import java.util.ArrayList;
import java.util.List;

import br.com.cm.workshop.apicrud.models.ItemPedido;
import br.com.cm.workshop.apicrud.models.NotaFiscal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalDTO {
    private Long id;
    private String nomeCliente;
    private String endereco;
    private String telefone;
    private Double valorTotalProdutos;
    private Double frete;
    private Double valorTotal;

    private List<ItemResponseDTO> itens = new ArrayList<>();

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
        return notaFiscal;
    }


}