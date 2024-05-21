package com.theKitchen.controller;

import java.util.List;
import com.theKitchen.model.dao.IDAO;
import com.theKitchen.model.entity.Funcionario;

public class FuncionarioController {

  private final IDAO<Funcionario> funcionarioDAO;

  public FuncionarioController(IDAO<Funcionario> funcionarioDAO) {
    this.funcionarioDAO = funcionarioDAO;
  }

  public String cadastrarFuncionario(Funcionario funcionario) {
    funcionarioDAO.cadastrar(funcionario);
    return "Funcionário cadastrado com sucesso!";
  }

  public String atualizarFuncionario(Funcionario funcionario) {
    funcionarioDAO.atualizar(funcionario);
    return "Funcionário atualizado com sucesso!";
  }

  public String excluirFuncionario(int id) {
    funcionarioDAO.excluir(id);
    return "Funcionário excluído com sucesso!";
  }

  public Funcionario buscarFuncionario(int id) {
    return funcionarioDAO.buscar(id);
  }

  public List<Funcionario> listarFuncionarios() {
    return funcionarioDAO.listar();
  }
}
