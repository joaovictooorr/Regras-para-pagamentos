package com.pagamentos.service;

import com.pagamentos.model.Pagamento;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;



public interface PagamentoService {
    Iterable<Pagamento> buscarTodos();

    Optional<Pagamento> buscarPorId(String id);

    void inserir(Pagamento pagamento) throws DataIntegrityViolationException;

    void atualizar(String id, Pagamento pagamento);

    void deletar(String id);
}
