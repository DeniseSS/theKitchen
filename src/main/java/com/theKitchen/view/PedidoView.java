package com.theKitchen.view;

import java.util.List;

import com.theKitchen.model.entity.Pedido;

public class PedidoView {
    public void mostrarDetalhesPedido(Pedido pedido) {
        System.out.println("Detalhes do pedido:");
        System.out.println("ID do Pedido: " + pedido.getIdPedido());
        System.out.println("ID do Cliente: " + pedido.getCliente());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("ID do Funcionário: " + pedido.getFuncionario());
        System.out.println("Data e Hora do Pedido: " + pedido.getDataHora());
        System.out.println("Total do Pedido: " + pedido.getTotal());
        System.out.println("Pratos do Pedido:");
        for (Integer pratoId : pedido.getPratos()) {
            System.out.println(" - ID do Prato: " + pratoId);
        }
        for (Integer itemId : pedido.getItens()) {
            System.out.println(" - ID do Item: " + itemId);
        }
    }

    public void mostrarListaPedidos(List<Pedido> pedidos) {
        System.out.println("Lista de pedidos:");
        for (Pedido pedido : pedidos) {
            System.out.println("ID do Pedido: " + pedido.getIdPedido());
            System.out.println("ID do Cliente: " + pedido.getCliente());
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("Pratos do Pedido:");
            for (Integer pratoId : pedido.getPratos()) {
                System.out.println(" - ID do Prato: " + pratoId);
            }
            System.out.println("ID do Funcionário: " + pedido.getFuncionario());
            System.out.println("Data e Hora do Pedido: " + pedido.getDataHora());
            System.out.println("Total do Pedido: " + pedido.getTotal());
            System.out.println("Itens do Pedido:");
            for (Integer itemId : pedido.getItens()) {
                System.out.println(" - ID do Item: " + itemId);
            }
            System.out.println("-----------------------------------");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
