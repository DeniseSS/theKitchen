package com.theKitchen.controller;

import java.util.List;
import com.theKitchen.model.dao.IDAO;
import com.theKitchen.model.entity.Itens;

public class ItensController {

  private final IDAO<Itens> itemDAO;

  public ItensController(IDAO<Itens> itemDAO) {
    this.itemDAO = itemDAO;
  }

  public String cadastrarItem(Itens item) {
    itemDAO.cadastrar(item);
    return "Item cadastrado com sucesso!";
  }

  public String atualizarItem(Itens item) {
    itemDAO.atualizar(item);
    return "Item atualizado com sucesso!";
  }

  public String excluirItem(int id) {
    itemDAO.excluir(id);
    return "Item excluído com sucesso!";
  }

  public Itens buscarItem(int id) {
    return itemDAO.buscar(id);
  }

  public List<Itens> listarItens() {
    return itemDAO.listar();
  }
}
