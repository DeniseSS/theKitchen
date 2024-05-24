package com.theKitchen.view;

import java.util.List;

import com.theKitchen.model.entity.Cliente;

public class ClienteView {
  public void mostrarDetalhesCliente(Cliente cliente) {
    System.out.println("Detalhes do cliente:");
    System.out.println("ID: " + cliente.getId());
    System.out.println("Nome: " + cliente.getNomeCliente());
    System.out.println("Sobrenome: " + cliente.getSobrenome());
    System.out.println("Telefone: " + cliente.getTelefone());
    System.out.println("CPF: " + cliente.getCpf());
    System.out.println("Endereço: " + cliente.getEndereco());
  }

  public void mostrarListaClientes(List<Cliente> clientes) {
    System.out.println("Lista de clientes:");
    for (Cliente cliente : clientes) {
      System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNomeCliente() +
          ", Telefone: " + cliente.getTelefone() +
          ", CPF: " + cliente.getCpf() +
          ", Sobrenome: " + cliente.getSobrenome() +
          ", Endereço: " + cliente.getEndereco());
    }
  }

  public void mostrarMensagem(String mensagem) {
    System.out.println(mensagem);
  }
}
