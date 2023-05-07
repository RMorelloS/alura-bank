package com.alura.alurabank.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class MovimentacaoConta {
    @JsonProperty
    private ContaCorrente conta;
    @JsonProperty
    private BigDecimal valor;
    @JsonProperty
    private Operacao Operacao;
}
