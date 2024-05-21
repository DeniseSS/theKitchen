package com.theKitchen.controller;

import java.util.List;
import com.theKitchen.model.dao.IDAO;
import com.theKitchen.model.entity.Prato;

public class PratoController {

  private final IDAO<Prato> pratoDAO;

  public PratoController(IDAO<Prato> pratoDAO) {
    this.pratoDAO = pratoDAO;
  }

  public String cadastrarPrato(Prato prato) {
    pratoDAO.cadastrar(prato);
    return "Prato cadastrado com sucesso!";
  }

  public String atualizarPrato(Prato prato) {
    pratoDAO.atualizar(prato);
    return "Prato atualizado com sucesso!";
  }

  public String excluirPrato(int id) {
    pratoDAO.excluir(id);
    return "Prato excluído com sucesso!";
  }

  public Prato buscarPrato(int id) {
    return pratoDAO.buscar(id);
  }

  public List<Prato> listarPratos() {
    return pratoDAO.listar();
  }
}
