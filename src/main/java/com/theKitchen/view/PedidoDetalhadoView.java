package com.theKitchen.view;

import com.theKitchen.model.entity.PedidoDetalhado;

import java.util.List;

public class PedidoDetalhadoView {
    public void mostrarDetalhesPedido(PedidoDetalhado pedido) {
        System.out.println("_________________Pedido______________");
        System.out.println("Numero do pedido: " + pedido.getIdPedido());
        System.out.println("Nome do Cliente: " + pedido.getNomeCliente());
        System.out.println("Nomes dos Pratos: " + String.join(", ", pedido.getNomesPratos()));
        System.out.println("Itens: " + String.join(", ", pedido.getNomesItens()));
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("Atendido por: " + pedido.getNomeFuncionario());
        System.out.println("--------------------------------------");
        System.out.println("Total do Pedido: " + pedido.getTotal());
        System.out.println("-------------------------------------");
    }

    public void mostrarListaPedidos(List<PedidoDetalhado> pedidos) {
        System.out.println("-----------Meus Pedidos----------------:");

        for (PedidoDetalhado pedido : pedidos) {
            System.out.println("_________________Pedido______________");
            System.out.println("Numero do pedido: " + pedido.getIdPedido());
            System.out.println("Nome do Cliente: " + pedido.getNomeCliente());
            System.out.println("Nomes dos Pratos: " + String.join(", ", pedido.getNomesPratos()));
            System.out.println("Itens: " + String.join(", ", pedido.getNomesItens()));
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("Atendido por: " + pedido.getNomeFuncionario());
            System.out.println("--------------------------------------");
            System.out.println("Total do Pedido: " + pedido.getTotal());
            System.out.println("-------------------------------------");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
