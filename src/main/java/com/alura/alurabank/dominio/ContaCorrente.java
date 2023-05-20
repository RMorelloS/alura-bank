package com.alura.alurabank.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class ContaCorrente {

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @JsonProperty
    public String banco;
    @JsonProperty
    public String agencia;
    @JsonProperty
    public String numero;

    @JsonIgnore
    private Correntista correntista;

    @JsonProperty
    private BigDecimal saldo;

    public ContaCorrente(String banco, String agencia, String numero, Correntista correntista) {
        this();
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.correntista = correntista;
    }

    public ContaCorrente() {
        this.saldo = BigDecimal.ZERO;
    }

    public boolean identificadaPor(String banco, String agencia, String numero){
        return this.banco.equals(banco) && this.agencia.equals(agencia) && this.numero.equals(numero);
    }

    public BigDecimal lerSaldo() {
        return this.saldo;
    }

    public void executar(Operacao operacao, BigDecimal valor){
        saldo= operacao.executar(saldo, valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaCorrente that = (ContaCorrente) o;
        return Objects.equals(banco, that.banco) && Objects.equals(agencia, that.agencia) && Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banco, agencia, numero);
    }

    public String getAgencia() {
        return this.agencia;
    }

    public String getNumero() {
        return this.numero;
    }

    public String getBanco() {
        return this.banco;
    }
}
