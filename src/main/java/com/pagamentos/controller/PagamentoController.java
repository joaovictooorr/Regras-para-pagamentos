package com.pagamentos.controller;

import com.pagamentos.model.Pagamento;
import com.pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<Iterable<Pagamento>> buscarTodos(){
        return ResponseEntity.ok(pagamentoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pagamento>> buscarPorId(@PathVariable String id){
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pagamento> salvar(@RequestBody Pagamento pagamento){
        pagamentoService.inserir(pagamento);
        return ResponseEntity.ok(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(@PathVariable String id,@RequestBody Pagamento pagamento){
        pagamentoService.atualizar(id,pagamento);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pagamento> deletar(@PathVariable String id){
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
