package com.theKitchen.controller;

import java.util.List;
import com.theKitchen.model.dao.IDAO;
import com.theKitchen.model.entity.Cliente;

public class ClienteController {

  private final IDAO<Cliente> clienteDAO;

  public ClienteController(IDAO<Cliente> clienteDAO) {
    this.clienteDAO = clienteDAO;
  }

  public String cadastrarCliente(Cliente cliente) {
    clienteDAO.cadastrar(cliente);
    return "Cliente cadastrado com sucesso!";
  }

  public String atualizarCliente(Cliente cliente) {
    clienteDAO.atualizar(cliente);
    return "Cliente atualizado com sucesso!";
  }

  public String excluirCliente(int id) {
    clienteDAO.excluir(id);
    return "Cliente excluído com sucesso!";
  }

  public Cliente buscarCliente(int id) {
    return clienteDAO.buscar(id);
  }

  public List<Cliente> listarClientes() {
    return clienteDAO.listar();
  }
}
