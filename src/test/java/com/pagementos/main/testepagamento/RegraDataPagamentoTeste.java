package com.pagementos.main.testepagamento;

import com.pagamentos.MainApplication;
import com.pagamentos.model.Pagamento;
import com.pagamentos.model.RegraDataPagamento;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MainApplication.class)
public class RegraDataPagamentoTeste {


    //OS TESTE FORAM REALIZADOS NO DIA 18/12/2024

    @Test
    public void testeDataAntes7Dias(){
        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(LocalDateTime.of(2024, 12, 11, 12, 0));
        Boolean resultado = new RegraDataPagamento().limitesDatasDePagamento(pagamento.getDataPagamento());

        assertFalse(resultado,"A regra não permite pagamentos com 7 dias de atraso");
    }

    @Test
    public void testeDepois10Anos(){
        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(LocalDateTime.of(2034, 12, 19, 12, 0));
        Boolean resultado = new RegraDataPagamento().limitesDatasDePagamento(pagamento.getDataPagamento());

        assertFalse(resultado,"A regra não permite pagamentos com mais de 10 anos");

    }

    @Test
    public void testeDiasDentroDasRegras(){
        Pagamento pagamento = new Pagamento();
        Pagamento pagamento1 = new Pagamento();
        Pagamento pagamento2 = new Pagamento();

        pagamento.setDataPagamento(LocalDateTime.of(2024, 12, 18, 12, 0));
        pagamento1.setDataPagamento(LocalDateTime.of(2024, 12, 12, 12, 0));
        pagamento2.setDataPagamento(LocalDateTime.of(2034, 12, 18, 12, 0));

        Boolean resultado = new RegraDataPagamento().limitesDatasDePagamento(pagamento.getDataPagamento());
        Boolean resultado1 = new RegraDataPagamento().limitesDatasDePagamento(pagamento1.getDataPagamento());
        Boolean resultado2 = new RegraDataPagamento().limitesDatasDePagamento(pagamento2.getDataPagamento());

        assertTrue(resultado, "resultado valido");
        assertTrue(resultado1, "resultado valido");
        assertTrue(resultado2, "resultado valido");


    }
}
