package com.alura.alurabank.dominio;

public class Correntista {
    private String cpf;
    private String correntista;

    public Correntista(String cpf, String correntista) {
        this.cpf = cpf;
        this.correntista = correntista;
    }

    public String getCorrentista() {
        return correntista;
    }

    public String getCpf() {
        return cpf;
    }
}
