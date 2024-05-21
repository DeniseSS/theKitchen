package com.theKitchen.model.entity;

public class Cliente extends AbstractEntity {

  private String nomeCliente;
  private String telefone;
  private String cpf;
  private String sobrenome;
  private String endereco;

  public Cliente(String nomeCliente, String telefone, String cpf, String sobrenome, String endereco) {
    this.nomeCliente = nomeCliente;
    this.telefone = telefone;
    this.cpf = cpf;
    this.sobrenome = sobrenome;
    this.endereco = endereco;
  }

  public Cliente(int id, String nomeCliente, String telefone, String cpf, String sobrenome, String endereco) {
    super.setId(id);
    this.nomeCliente = nomeCliente;
    this.telefone = telefone;
    this.cpf = cpf;
    this.sobrenome = sobrenome;
    this.endereco = endereco;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }
}
