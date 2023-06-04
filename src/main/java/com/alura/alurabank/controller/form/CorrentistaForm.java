package com.alura.alurabank.controller.form;

import com.alura.alurabank.dominio.Correntista;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
public class CorrentistaForm {
    @JsonProperty
    @CPF
    @NotNull
    private String cpf;
    @JsonProperty
    @NotBlank(message="Nome do correntista é obrigatório e não pode ser nulo")

    private String nome;

    public Correntista toCorrentista() {
        return new Correntista(cpf, nome);
    }
}
