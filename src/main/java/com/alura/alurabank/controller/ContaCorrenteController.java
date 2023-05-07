package com.alura.alurabank.controller;

import com.alura.alurabank.dominio.Correntista;

import com.alura.alurabank.dominio.ContaCorrente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {
    @GetMapping
    public String consultarSaldo(

            @RequestParam(name="banco") String banco,
            @RequestParam(name="agencia") String agencia,
            @RequestParam(name="numero") String numero){
        return String.format("Banco: : %s Agencia: %s, Conta: %s", banco, agencia, numero );
    }
    @PostMapping
    public ResponseEntity<ContaCorrente>  criarNovaConta(@RequestBody Correntista correntista){

        ContaCorrente conta = new ContaCorrente("111", "222", "333");
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }
    public String fecharConta(ContaCorrente conta){
        return "conta fechada com sucesso";
    }
}
//http://localhost:8080/contas?banco=888&agencia=1111&numero=3333