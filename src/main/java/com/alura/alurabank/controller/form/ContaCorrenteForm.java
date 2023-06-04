package com.alura.alurabank.controller.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class ContaCorrenteForm {
    @JsonProperty
    @Getter
    @Setter
    private String banco;
    @JsonProperty
    @Getter
    @Setter
    private String agencia;
    @JsonProperty
    @Getter
    @Setter
    private String numero;

}
