package br.com.bank.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {
    private String nome;

    public Banco(String nome) {
        this.nome = nome;
    }

    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Optional<Conta> pesquisarContaDoCliente(String cpf) {
        Conta conta = null;
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getCpf().equals(cpf)) {
                return Optional.ofNullable(contas.get(i));
            }
        }

        return Optional.ofNullable(conta);
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.stream().filter(filtro).collect(Collectors.toList());
    }

}
