package com.alura.alurabank.controller;

import com.alura.alurabank.dominio.Investimentos;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/investimentos")
public class InvestimentosController {


    @GetMapping
    public ResponseEntity<Investimentos> criarInvestimentos(){
            Investimentos tipoInvestimentos = new Investimentos();
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoInvestimentos);
    }
  /* */
  @PostMapping
  public ResponseEntity<String> renderAporte(
          @RequestBody ObjectNode operacao){
      Investimentos produtosInvestimentos = new Investimentos();
      String tipoInvestimento = "";
      try{
         tipoInvestimento = operacao.get("tipoInvestimento").asText();
      }catch(Exception e){
          return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Parâmetro tipoInvestimento não pode ser nulo");
      }
      String valorAporteStr = "";
      try{
          valorAporteStr = operacao.get("valorAporte").asText();
      }catch(Exception e){
          return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Parâmetro valorAporte não pode ser nulo");
      }

      BigDecimal valorAporte = new BigDecimal(valorAporteStr);
      BigDecimal valorFinal = new BigDecimal(0);
    try {
         valorFinal = produtosInvestimentos.render(tipoInvestimento, valorAporte);
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
    }
      return ResponseEntity.status(HttpStatus.OK).body("Valor final " + valorFinal);
  }
}
