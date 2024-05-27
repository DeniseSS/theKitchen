package com.theKitchen.model.entity;


public class ValorItem extends AbstractEntity {

    private double valorItem;

    public ValorItem(int id, double valorItem) {
        super.setId(id);
        this.valorItem = valorItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }
}
