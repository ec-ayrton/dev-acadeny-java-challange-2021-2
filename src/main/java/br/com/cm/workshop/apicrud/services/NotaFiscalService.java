package br.com.cm.workshop.apicrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cm.workshop.apicrud.repositories.ItemPedidoRepository;
import br.com.cm.workshop.apicrud.repositories.NotaFiscalRepository;
import br.com.cm.workshop.apicrud.repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import br.com.cm.workshop.apicrud.DTOs.ItemResponseDTO;
import br.com.cm.workshop.apicrud.DTOs.NotaFiscalDTO;
import br.com.cm.workshop.apicrud.models.ItemPedido;
import br.com.cm.workshop.apicrud.models.NotaFiscal;
import br.com.cm.workshop.apicrud.models.Produto;

@Service
public class NotaFiscalService {
    
    @Autowired
    NotaFiscalRepository notarepository;

    @Autowired
    ProdutoRepository produtorepository;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;


    public List<NotaFiscal> listarTodos(){
        return notarepository.findAll();
    }

    public NotaFiscal listarPorId(Long id){
        Optional< NotaFiscal> nota = notarepository.findById(id);
        return nota.orElseThrow( ()-> new EntityNotFoundException("Nota fiscal não encontrada!")  );
    }

    public void remover(Long id) {
        if(notarepository.existsById(id)){
            notarepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Nota fiscal não encontrada!");
        }
    }

    public NotaFiscal salvarNotaFiscal(NotaFiscalDTO notafiscalDto){
        List<ItemPedido> itens = ValidarItens(notafiscalDto);
        NotaFiscal notaFiscalToSave = notafiscalDto.toModel(itens);
        if(ValidarCalculoTotal(notaFiscalToSave)){
            return notarepository.saveAndFlush(notaFiscalToSave);
        }else{
            throw new EntityNotFoundException("Há Produtos na lista de itens que não estão cadastrados no sistema.");
        }
    }
    public NotaFiscal atualizarNotaFiscal(Long id, NotaFiscalDTO notafiscalDto) {
        List<ItemPedido> itens = ValidarItens(notafiscalDto);
        NotaFiscal notaFiscalAtualizada = notafiscalDto.toModel(itens);
        
        if(notarepository.existsById(id)){
            if(id.equals(notafiscalDto.getId())){
                return notarepository.saveAndFlush(notaFiscalAtualizada);
            }else{
                throw new UnsupportedOperationException("Id informado é diferente do id da nota fiscal");
            }
            
        }else
            throw new EntityNotFoundException("Nota Fiscal não encontrada!");
    }



    ///METODOS AUXILIARES
    public Boolean ValidarCalculoTotal(NotaFiscal notaFiscal){
        List<ItemPedido> itens = notaFiscal.getItens();
        Double valorTotalItens = 0.0;
        
        for(ItemPedido item : itens){
            //CASO O VALOR TOTAL DO PRODUTO SEJA DIFERENTE DO VALOR TOTAL INFORMADO NA NOTA, EXEMPLO PRECO=3,QTD=3. VALORTOTALPRODUTO REAL=9, MAS NA NOTA PODERIA ESTAR 7. 
            if( item.getValorTotal() != ( item.getProduto().getPrecoUnitario() * item.getQuantidadeProduto() ) ){
                throw new UnsupportedOperationException("Valor total do produto"+ item.getProduto().getDescricao() +"é diferente do informado na nota Fiscal.");
            }
            valorTotalItens += item.getValorTotal();
        }
        //CASO A SOMATORIA DO VALOR TOTAL DE TODOS OS PRODUTOS SEJAM DIFERNTE DA SOMATORIA TOTAL INFORMADA NA NOTA. 
        if(valorTotalItens.doubleValue() != notaFiscal.getValorTotalProdutos().doubleValue()   ){
            throw new UnsupportedOperationException("Valor total dos Produtos é diferente do informado na nota Fiscal.");
        }
        //CASO A SOMATORIA DOS VALORES TOTAIS COM O FRETE RESULTE DIFERENTE DO INFORMADO NA NOTA.
        if((valorTotalItens+notaFiscal.getFrete()) != notaFiscal.getValorTotal()){
            throw new UnsupportedOperationException("Valor total real da nota fiscal é diferente do informado na nota Fiscal.");
        }
        else{
            return true;
        }   
    }

    //Garantir que todos os produtos estão ou serão cadastrados no sistema.
    private List<ItemPedido> ValidarItens(NotaFiscalDTO nota) {
        List<ItemResponseDTO> listaItens  = nota.getItens();
        List<ItemPedido> novaList = new ArrayList<>();
        
        //PERCORRE TODOS OS ITENS DO DTO
        for(ItemResponseDTO itemFromDto: listaItens){
            ItemPedido  itemPedidoToSave = new ItemPedido();

            Optional<Produto> produtoFromDb = produtorepository.findByDescricao(itemFromDto.getDescricao());
            //ACHADO UM PRODUTO JA CADASTRADO COM A DESCRICAO.
            if(produtoFromDb.isPresent()){
                itemPedidoToSave.setProduto(produtoFromDb.get());
        }   else{
            //O IDEAL AQUI SERIA LANÇAR UMA EXCEÇÃO UMA VEZ QUE O PRODUTO NAO FOI ENCONTRADO NO SISTEMA.MAS COMO NÃO HÁ REGRA DE NEGOCIO PREVIA.
            Produto novoProduto = new Produto(itemFromDto.getDescricao(),itemFromDto.getPrecoUnitario());
            novoProduto = produtorepository.save(novoProduto);
            itemPedidoToSave.setProduto(novoProduto);
            }
            itemPedidoToSave.setQuantidadeProduto(itemFromDto.getQuantidade());
            itemPedidoToSave.setValorTotal(itemFromDto.getValorTotal());
            itemPedidoToSave = itemPedidoRepository.save(itemPedidoToSave);
            novaList.add(itemPedidoToSave);
        }
        return novaList;
    }

    
}
