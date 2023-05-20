package com.alura.alurabank.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class MovimentacaoConta {
    @JsonProperty
    private ContaCorrente conta;
    @JsonProperty
    private BigDecimal valor;
    @JsonProperty
    private int operacao;

    public void ExecutarOperacao(ContaCorrente conta){
        Operacao operacao = Operacao.values()[this.operacao];
        conta.executar(operacao, valor);
    }

    public String getAgencia() {
        return conta.getAgencia();
    }

    public String getNumero() {
        return conta.getNumero();
    }

    public String getBanco() {
        return conta.getBanco();
    }
}
