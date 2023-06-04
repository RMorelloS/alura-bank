package com.alura.alurabank.controller;

import com.alura.alurabank.config.ValidatorBean;
import com.alura.alurabank.controller.form.ContaCorrenteForm;
import com.alura.alurabank.controller.form.CorrentistaForm;
import com.alura.alurabank.dominio.Correntista;

import com.alura.alurabank.dominio.ContaCorrente;
import com.alura.alurabank.dominio.MovimentacaoConta;
import com.alura.alurabank.repositorio.RepositorioContasCorrente;
import com.googlecode.jmapper.JMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/contas")
@ComponentScan({"com.alura.alurabank.repositorio"})
@ComponentScan({"com.alura.alurabank.config"})
public class ContaCorrenteController {
    @Autowired
    private RepositorioContasCorrente repositorioContasCorrente;

    @Autowired
    private JMapper<ContaCorrente, ContaCorrenteForm> contaCorrenteMapper;

    @Autowired
    private Validator validator;

    public ContaCorrenteController(RepositorioContasCorrente repositorioContasCorrente,
                                   JMapper<ContaCorrente, ContaCorrenteForm> contaCorrenteMapper) {
        this.repositorioContasCorrente = repositorioContasCorrente;
        this.contaCorrenteMapper = contaCorrenteMapper;
    }

    @GetMapping
    public String consultarSaldo(

            @RequestParam(name="banco") String banco,
            @RequestParam(name="agencia") String agencia,
            @RequestParam(name="numero") String numero){

            ContaCorrente contaCorrente = repositorioContasCorrente
                    .buscar(banco, agencia, numero)
                    .orElse(new ContaCorrente());
            return String.format("Banco: : %s Agencia: %s, Conta: %s, Saldo: %s",
                    banco, agencia, numero, contaCorrente.lerSaldo() );
    }
    @PostMapping
    public ResponseEntity  criarNovaConta(@RequestBody CorrentistaForm correntistaForm){
        Map<Path, String> violacoesToMap = validarInput(correntistaForm);
        if(!violacoesToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesToMap);
        }
        Correntista correntista = correntistaForm.toCorrentista();
        String banco = "111";
        String agencia = "222";

        String numero = Integer.toString(new Random().nextInt(Integer.MAX_VALUE));
        ContaCorrente conta = new ContaCorrente(banco, agencia, numero, correntista);
        repositorioContasCorrente.salvar(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    private <T> Map<Path, String> validarInput(T form) {
        Set<ConstraintViolation<T>> violacoes = validator.validate(form);
        Map<Path, String> violacoesToMap = violacoes.stream()
                .collect(Collectors.toMap(
                        violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesToMap;
    }

    @DeleteMapping
    public ResponseEntity fecharConta(@RequestBody ContaCorrenteForm contaForm){
        Map<Path, String> violacoesMap = validarInput(contaForm);

        if(!violacoesMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoesMap);
        }

        ContaCorrente conta = contaCorrenteMapper.getDestination(contaForm);
        repositorioContasCorrente.fecharConta(conta);
        return ResponseEntity.ok("Conta fechada com sucesso");
    }

    @PutMapping

    public ResponseEntity<String> movimentarConta(@RequestBody MovimentacaoConta movimentacao){
        Optional<ContaCorrente> opContaCorrente =
                repositorioContasCorrente.buscar(movimentacao.getBanco(),
                movimentacao.getAgencia(),
                movimentacao.getNumero());
        if(opContaCorrente.isEmpty()){
            return ResponseEntity.badRequest().body("Conta corrente não existe");
        }else{
            ContaCorrente contaCorrente = opContaCorrente.get();
            movimentacao.ExecutarOperacao(contaCorrente);
            repositorioContasCorrente.salvar(contaCorrente);
            return ResponseEntity.ok("Movimentação realizada com sucesso");
        }
    }
}


//http://localhost:8080/contas?banco=888&agencia=1111&numero=3333