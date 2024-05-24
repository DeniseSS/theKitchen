package com.theKitchen.controller;

import com.theKitchen.model.entity.PedidoDetalhado;
import com.theKitchen.model.dao.PedidoDetalhadoDAO; // Supondo que você tenha uma classe PedidoDAO que acessa o banco de dados

import java.util.List;

public class PedidoDetalhadoController {

    private PedidoDetalhadoDAO pedidoDAO;

    public PedidoDetalhadoController() {
        this.pedidoDAO = new PedidoDetalhadoDAO();
    }

    public List<PedidoDetalhado> listarPedidosDetalhados() {
        return pedidoDAO.listarPedidosDetalhados();
    }
}
