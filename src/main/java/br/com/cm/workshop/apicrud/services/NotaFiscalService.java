package br.com.cm.workshop.apicrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cm.workshop.apicrud.repositories.NotaFiscalRepository;
import br.com.cm.workshop.apicrud.repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import br.com.cm.workshop.apicrud.models.NotaFiscal;
import br.com.cm.workshop.apicrud.models.Produto;

@Service
public class NotaFiscalService {
    
    @Autowired
    NotaFiscalRepository notarepository;

    @Autowired
    ProdutoRepository produtorepository;


    public List<NotaFiscal> listarTodos(){
        return notarepository.findAll();
    }

    public NotaFiscal listarPorId(Long id){
        Optional< NotaFiscal> nota = notarepository.findById(id);
        return nota.orElseThrow( ()-> new EntityNotFoundException("Nota fiscal não encontrada!")  );
    }

    public void remove(Long id) {
        if(notarepository.existsById(id)){
            notarepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Nota fiscal não encontrada!");
        }
        
    }

    public NotaFiscal salvarNotaFiscal(NotaFiscal nota){
        Boolean ItensEncontrados = ItensJaCadastrados(nota);
        if(ItensEncontrados){
            CalcularTotal(nota);
            return notarepository.saveAndFlush(nota);
        }else{
            throw new EntityNotFoundException("Há Produtos na lista de itens que não estão cadastrados no sistema.");
        }
    }

    public void CalcularTotal(NotaFiscal nota){
        List<Produto> itens = nota.getItens();
        Double valorTotalItens = 0.0;
        for(Produto p : itens){
            valorTotalItens += p.getValorTotal();
        }
        nota.setValorTotalProdutos(valorTotalItens);
        nota.setValorTotal(valorTotalItens+nota.getFrete());
    }


    public boolean ItensJaCadastrados(NotaFiscal nota){
        int qtdItensNota = nota.getItens().size();
        int qtdItensJaCadastrados = 0;
        List<Produto> listaANTIGA = nota.getItens();
        List<Produto> listaNova = new ArrayList<>();
        for (Produto produto : listaANTIGA) {
            String descricao = produto.getDescricao();
            List<Produto> listFromDb =  produtorepository.findByDescricao(descricao);
            for(Produto produtoFromDb: listFromDb) {       
             if(produtoFromDb.getDescricao().equals(descricao)){
                    listaNova.add(produtoFromDb);
                    qtdItensJaCadastrados++;
                }
            }
        }
        if(qtdItensNota==qtdItensJaCadastrados){
            nota.setItens(listaNova);
            return true;    
        }else{
            return false;
        }
    }

    // public NotaFiscal atualizarNotaFiscal(Long id, NotaFiscal notaFiscal) {
    //     Optional<NotaFiscal> notaBuscada = notarepository.findById(id);
    //     //notaBuscada.get().
        
    //     return null;
    // }
}
