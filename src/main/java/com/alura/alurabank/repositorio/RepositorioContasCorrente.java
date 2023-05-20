package com.alura.alurabank.repositorio;


import com.alura.alurabank.dominio.ContaCorrente;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class RepositorioContasCorrente  {
    private Set<ContaCorrente> contas;
    public RepositorioContasCorrente(){
        contas = new HashSet<>();
    }
    public void salvar(ContaCorrente conta) {
        contas = new HashSet<>();
        contas.add(conta);
    }

    public Optional<ContaCorrente> buscar(String banco, String agencia, String numero){
        return contas
                .stream()
                .filter(contaCorrente -> contaCorrente.identificadaPor(banco, agencia, numero))
                        .findFirst();
    }


    public void fecharConta(ContaCorrente conta) {
        contas.remove(conta);
    }
}
