package com.theKitchen.model.entity;

public class Itens extends AbstractEntity {

    private String nomeItem;
    private double valorItem;
    private String categoriaItem;
    private String marca;

   
    public Itens(String nomeItem, double valorItem, String categoriaItem, String marca) {
        this.nomeItem = nomeItem;
        this.valorItem = valorItem;
        this.categoriaItem = categoriaItem;
        this.marca = marca;
    }

   
    public Itens(int id, String nomeItem, double valorItem, String categoriaItem, String marca) {
        super.setId(id);
        this.nomeItem = nomeItem;
        this.valorItem = valorItem;
        this.categoriaItem = categoriaItem;
        this.marca = marca;
    }

    // Getters e Setters
    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public String getCategoriaItem() {
        return categoriaItem;
    }

    public void setCategoriaItem(String categoriaItem) {
        this.categoriaItem = categoriaItem;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
