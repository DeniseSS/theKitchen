package com.theKitchen.view;

import com.theKitchen.model.entity.PedidoDetalhado;

import java.util.List;

public class PedidoDetalhadoView {
    public void mostrarDetalhesPedido(PedidoDetalhado pedido) {
        System.out.println("Detalhes do pedido detalhado:");
        System.out.println("ID do Pedido: " + pedido.getIdPedido());
        System.out.println("Nome do Cliente: " + pedido.getNomeCliente());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("Nome do Prato: " + pedido.getNomePrato());
        System.out.println("Nome do Funcionário: " + pedido.getNomeFuncionario());
        System.out.println("Total do Pedido: " + pedido.getTotal());
    }

    public void mostrarListaPedidos(List<PedidoDetalhado> pedidos) {
        System.out.println("Meus Pedidos:");
        for (PedidoDetalhado pedido : pedidos) {
            System.out.println("Numero do pedido: " + pedido.getIdPedido() +
                    ", Nome do Cliente: " + pedido.getNomeCliente() +
                    ", Status: " + pedido.getStatus() +
                    ", Nome do Prato: " + pedido.getNomePrato() +
                    ", Nome do Funcionário: " + pedido.getNomeFuncionario() +
                    ", Total do Pedido: " + pedido.getTotal());
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
