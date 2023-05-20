package com.alura.alurabank.dominio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.HashMap;

public class Investimentos {

    private HashMap<String, BigDecimal> investimentos;

    public Investimentos(){
        investimentos = new HashMap<String, BigDecimal>();
        investimentos.put("Poupança", BigDecimal.valueOf(0.05));
        investimentos.put("Poupança1", BigDecimal.valueOf(0.055));
        investimentos.put("Fundos de Investimento", BigDecimal.valueOf(0.15));
        investimentos.put("CDI", BigDecimal.valueOf(0.16));
    }
    private BigDecimal buscaInvestimentos(String investimento){
        return investimentos.get(investimento);
    }

    public BigDecimal render(String tipoInvestimento, BigDecimal valorAporte) throws Exception {
        BigDecimal percentualAplicacao = buscaInvestimentos(tipoInvestimento);

        if(percentualAplicacao == null){
            throw new Exception("Tipologia de investimento não encontrada! " + tipoInvestimento);
        }
        return valorAporte.multiply(percentualAplicacao).add(valorAporte);
    }
}
