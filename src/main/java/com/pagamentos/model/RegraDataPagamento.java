package com.pagamentos.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegraDataPagamento {

    public Boolean limitesDatasDePagamento(LocalDateTime dataPagamento){
        LocalDateTime dataAtual = LocalDateTime.now();

        LocalDateTime dataAtualMenos7 = dataAtual.minusDays(7);
        LocalDateTime dataAtualMais10 = dataAtual.plusYears(10);

        if(dataPagamento.isBefore(dataAtualMenos7) || dataPagamento.isAfter(dataAtualMais10)){
            return false;
        }

        return true;
    }
}
