package com.alura.alurabank.controller.form;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaCorrenteForm {
    @JsonProperty
    private String banco;
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @JsonProperty
    private String agencia;
    @JsonProperty
    private String numero;

}
