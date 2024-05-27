package com.theKitchen.controller;

import com.theKitchen.model.dao.PedidoDetalhadoDAO;
import com.theKitchen.model.entity.PedidoDetalhado;
import com.theKitchen.view.PedidoDetalhadoView;

import java.util.List;

public class PedidoDetalhadoController {

    private PedidoDetalhadoDAO pedidoDAO;
    private PedidoDetalhadoView pedidoDetalhadoView;

    public PedidoDetalhadoController(PedidoDetalhadoDAO pedidoDAO, PedidoDetalhadoView pedidoDetalhadoView) {
        this.pedidoDAO = pedidoDAO;
        this.pedidoDetalhadoView = pedidoDetalhadoView;
    }

    public List<PedidoDetalhado> listarPedidosDetalhados() {
        return pedidoDAO.listarPedidosDetalhados();
    }

    public void buscarPedidoDetalhadoPorId(int id) {
        PedidoDetalhado pedidoDetalhado = pedidoDAO.buscar(id);
        if (pedidoDetalhado != null) {
            pedidoDetalhadoView.mostrarDetalhesPedido(pedidoDetalhado);
        } else {
            pedidoDetalhadoView.mostrarMensagem("Pedido não encontrado com o ID: " + id);
        }
    }
}
