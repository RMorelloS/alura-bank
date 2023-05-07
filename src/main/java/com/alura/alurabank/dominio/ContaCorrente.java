package com.alura.alurabank.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaCorrente {

    @JsonProperty
    public String banco;
    @JsonProperty
    public String agencia;
    @JsonProperty
    public String numero;


    public ContaCorrente(String banco, String agencia, String numero) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
    }
}
