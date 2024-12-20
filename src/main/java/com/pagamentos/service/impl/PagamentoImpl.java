package com.pagamentos.service.impl;

import com.pagamentos.model.Pagamento;
import com.pagamentos.model.RegraDataPagamento;
import com.pagamentos.repository.PagamentoRepository;
import com.pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PagamentoImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired(required = true)
    private RegraDataPagamento regraDataPagamento;

    @Override
    public Iterable<Pagamento> buscarTodos() {
        return pagamentoRepository.findAll();
    }

    @Override
    public Optional<Pagamento> buscarPorId(String id) {
        return Optional.ofNullable(pagamentoRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public void inserir(Pagamento pagamento)  {

        Boolean resultado = regraDataPagamento.limitesDatasDePagamento(pagamento.getDataPagamento());
        if (Boolean.FALSE.equals(resultado)) {
            throw new IllegalArgumentException();
        } else{
            pagamentoRepository.save(pagamento);
        }

    }

    @Override
    public void atualizar(String id, Pagamento pagamento) {
        Optional<Pagamento> pagamentoDb = pagamentoRepository.findById(id);

        if(pagamentoDb.isPresent()){
            Pagamento pagamentoExistente = pagamentoDb.get();
            Boolean resultado = regraDataPagamento.limitesDatasDePagamento(pagamento.getDataPagamento());
            if(Boolean.FALSE.equals(resultado)){
                throw new IllegalArgumentException();
            }
            pagamentoExistente.setDataPagamento(pagamento.getDataPagamento());
            pagamentoRepository.save(pagamentoExistente);
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public void deletar(String id) {
        if(pagamentoRepository.existsById(id)){
            pagamentoRepository.deleteById(id);
        }else{
            throw new NoSuchElementException();
        }
    }


}
