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
        ValidarItens(nota);
        if(ValidarCalculoTotal(nota)){
            return notarepository.saveAndFlush(nota);
        }else{
            throw new EntityNotFoundException("Há Produtos na lista de itens que não estão cadastrados no sistema.");
        }
    }

    public Boolean ValidarCalculoTotal(NotaFiscal nota){
        List<Produto> itens = nota.getItens();
        Double valorTotalItens = 0.0;
        
        for(Produto p : itens){
            //CASO O VALOR TOTAL DO PRODUTO SEJA DIFERENTE DO VALOR TOTAL INFORMADO NA NOTA, EXEMPLO PRECO=3,QTD=3. VALORTOTALPRODUTO REAL=9, MAS NA NOTA PODERIA ESTAR 7. 
            if( p.getValorTotal()!= (p.getValorTotal()) ){
                throw new UnsupportedOperationException("Valor total do produto"+ p.getDescricao() +"é diferente do informado na nota Fiscal.");
            }
            valorTotalItens += p.getValorTotal();
        }
        //CASO A SOMATORIA DO VALOR TOTAL DE TODOS OS PRODUTOS SEJAM DIFERNTE DA SOMATORIA TOTAL INFORMADA NA NOTA. 
        if(valorTotalItens.doubleValue() != nota.getValorTotalProdutos().doubleValue()   ){
            throw new UnsupportedOperationException("Valor total dos Produtos é diferente do informado na nota Fiscal.");
        }
        //CASO A SOMATORIA DOS VALORES TOTAIS COM O FRETE RESULTE DIFERENTE DO INFORMADO NA NOTA.
        if((valorTotalItens+nota.getFrete()) != nota.getValorTotal()){
            throw new UnsupportedOperationException("Valor total real da nota fiscal é diferente do informado na nota Fiscal.");
        }
        else{
            return true;
        }   
    }
    //Garantir que todos os produtos estão ou serão cadastrados no sistema.
    private void ValidarItens(NotaFiscal nota) {
    List<Produto> listaItens  = nota.getItens();
    List<Produto> novaList = new ArrayList<>();
    for(Produto p : listaItens){
        Optional<Produto> produtoFromDb = produtorepository.findByDescricao(p.getDescricao());
        if(produtoFromDb.isPresent()){
            novaList.add(produtoFromDb.get());
        }else{
            //O IDEAL AQUI SERIA LANÇAR UMA EXCEÇÃO UMA VEZ QUE O PRODUTO NAO FOI ENCONTRADO NO SISTEMA.MAS COMO NÃO HÁ REGRA DE NEGOCIO PREVIA.
            novaList.add(produtorepository.save(p));
        }
    }
    nota.setItens(novaList);
    }
}
