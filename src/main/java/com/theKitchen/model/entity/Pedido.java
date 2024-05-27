package com.theKitchen.model.entity;

import java.util.List;

public class Pedido extends AbstractEntity {

    private int idPedido;
    private int cliente;
    private String status;
    private List<Integer> pratos;
    private int funcionario;
    private String dataHora;
    private double total;
    private List<Integer> itens;

    public Pedido(int idPedido, int cliente, String status, List<Integer> pratos, int funcionario, String dataHora, double total,
            List<Integer> itens) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.status = status;
        this.pratos = pratos;
        this.funcionario = funcionario;
        this.dataHora = dataHora;
        this.total = total;
        this.itens = itens;
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

    public List<Integer> getPratos() {
        return pratos;
    }

    public void setPratos(List<Integer> pratos) {
        this.pratos = pratos;
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

    public List<Integer> getItens() {
        return itens;
    }

    public void setItens(List<Integer> itens) {
        this.itens = itens;
    }
}
