package com.theKitchen.model.entity;

public class Prato extends AbstractEntity {

  private String nomePrato;
  private String composicao;
  private Double preco;
  private String categoria;
  private Integer tempoPreparo;

  public Prato(String nomePrato, String composicao, Double preco, String categoria, Integer tempoPreparo) {
    this.nomePrato = nomePrato;
    this.composicao = composicao;
    this.preco = preco;
    this.categoria = categoria;
    this.tempoPreparo = tempoPreparo;
  }

  public Prato(int id, String nomePrato, String composicao, Double preco, String categoria, Integer tempoPreparo) {
    super.setId(id);
    this.nomePrato = nomePrato;
    this.composicao = composicao;
    this.preco = preco;
    this.categoria = categoria;
    this.tempoPreparo = tempoPreparo;
  }

  public String getNomePrato() {
    return nomePrato;
  }

  public void setNomePrato(String nomePrato) {
    this.nomePrato = nomePrato;
  }

  public String getComposicao() {
    return composicao;
  }

  public void setComposicao(String composicao) {
    this.composicao = composicao;
  }

  public Double getPreco() {
    return preco;
  }
  public void setPreco(double preco) {
    this.preco = preco;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public Integer getTempoPreparo() {
    return tempoPreparo;
  }

  public void setTempoPreparo(Integer tempoPreparo) {
    this.tempoPreparo = tempoPreparo;
  }

  

}
