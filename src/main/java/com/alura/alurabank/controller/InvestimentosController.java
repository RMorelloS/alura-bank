package com.alura.alurabank.controller;

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

    private HashMap<String, BigDecimal> investimentos;
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> criarInvestimentos(){
            investimentos = new HashMap<String, BigDecimal>();
            investimentos.put("Poupança", BigDecimal.valueOf(0.05));
            investimentos.put("Fundos de Investimento", BigDecimal.valueOf(0.15));
            investimentos.put("CDI", BigDecimal.valueOf(0.16));
            return ResponseEntity.status(HttpStatus.CREATED).body(investimentos);
    }
  /* */
  @PostMapping
  public ResponseEntity<String> renderAporte(
          @RequestBody ObjectNode investimento){
      String tipoInvestimento = "";
      try{
         tipoInvestimento = investimento.get("tipoInvestimento").asText();
      }catch(Exception e){
          return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Parâmetro tipoInvestimento não pode ser nulo");
      }
      String valorAporteStr = "";
      try{
          valorAporteStr = investimento.get("valorAporte").asText();
      }catch(Exception e){
          return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Parâmetro valorAporte não pode ser nulo");
      }

      BigDecimal valorAporte = new BigDecimal(valorAporteStr);
      BigDecimal percentualAplicacao = BigDecimal.valueOf(0);

      investimentos = new HashMap<String, BigDecimal>();
      investimentos.put("Poupança", BigDecimal.valueOf(0.05));
      investimentos.put("Fundos de Investimento", BigDecimal.valueOf(0.15));
      investimentos.put("CDI", BigDecimal.valueOf(0.16));

      percentualAplicacao = investimentos.get(tipoInvestimento);

      if(percentualAplicacao == null){
          return ResponseEntity.status(HttpStatus.OK).body("Tipo de investimento não encontrado: -" + tipoInvestimento+"-");
      }

      return ResponseEntity.status(HttpStatus.OK).body("Valor final" + valorAporte.multiply(percentualAplicacao).add(valorAporte));
  }
}
