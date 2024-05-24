package com.theKitchen.model.entity;

public class Pedido extends AbstractEntity {

    private int idPedido;
    private int cliente;
    private String status;
    private int prato;
    private int funcionario;
    private String dataHora;
    private double total;
    private int item;

    public Pedido(int idPedido, int cliente, String status, int prato, int funcionario, String dataHora, double total,
            int item) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.status = status;
        this.prato = prato;
        this.funcionario = funcionario;
        this.dataHora = dataHora;
        this.total = total;
        this.item = item;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrato() {
        return prato;
    }

    public void setPrato(int prato) {
        this.prato = prato;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
