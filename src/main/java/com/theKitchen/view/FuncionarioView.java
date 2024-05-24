package com.theKitchen.view;

import java.util.List;

import com.theKitchen.model.entity.Funcionario;

public class FuncionarioView {
    public void mostrarDetalhesFuncionario(Funcionario funcionario) {
        System.out.println("Detalhes do funcionário:");
        System.out.println("ID: " + funcionario.getId());
        System.out.println("Nome: " + funcionario.getNomeFuncionario());
        System.out.println("Sobrenome: " + funcionario.getSobrenome());
        System.out.println("Telefone: " + funcionario.getTelefone());
        System.out.println("CPF: " + funcionario.getCpf());
        System.out.println("Cargo: " + funcionario.getCargo());
        System.out.println("Data de Nascimento: " + funcionario.getDataNascimento());
    }

    public void mostrarListaFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("Lista de funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID: " + funcionario.getId() +
                    ",  Nome: " + funcionario.getNomeFuncionario() +
                    ", Sobrenome: "+ funcionario.getSobrenome() +
                    ", Telefone: " + funcionario.getTelefone() +
                    ", CPF: " + funcionario.getCpf() +
                    ", Cargo: " + funcionario.getCargo() +
                    ", Data de Nascimento: " + funcionario.getDataNascimento());
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
