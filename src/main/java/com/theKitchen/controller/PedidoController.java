package com.theKitchen.controller;

import java.util.List;
import com.theKitchen.model.dao.IDAO;
import com.theKitchen.model.entity.Pedido;

public class PedidoController {

  private final IDAO<Pedido> pedidoDAO;

  public PedidoController(IDAO<Pedido> pedidoDAO) {
    this.pedidoDAO = pedidoDAO;
  }

  public String cadastrarPedido(Pedido pedido) {
    pedidoDAO.cadastrar(pedido);
    return "Pedido cadastrado com sucesso!";
  }

  public String atualizarPedido(Pedido pedido) {
    pedidoDAO.atualizar(pedido);
    return "Pedido atualizado com sucesso!";
  }

  public String excluirPedido(int id) {
    pedidoDAO.excluir(id);
    return "Pedido excluído com sucesso!";
  }

  public Pedido buscarPedido(int id) {
    return pedidoDAO.buscar(id);
  }

  public List<Pedido> listarPedidos() {
    return pedidoDAO.listar();
  }
}
