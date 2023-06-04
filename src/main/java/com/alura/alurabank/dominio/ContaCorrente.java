package com.alura.alurabank.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
@EqualsAndHashCode(of={"banco", "agencia", "numero"})

public class ContaCorrente {

    @JsonProperty
    @Getter
    @Setter
    public String banco;
    @JsonProperty
    @Getter
    @Setter
    public String agencia;
    @JsonProperty
    @Getter
    @Setter
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

}
