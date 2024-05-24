package com.theKitchen.model.entity;

public class PedidoDetalhado {
    private int idPedido;
    private String nomeCliente;
    private String status;
    private String nomePrato;
    private String item;
    private String nomeFuncionario;
    private String dataHora;
    private double total;


    // Construtor
    public PedidoDetalhado(int idPedido, String nomeCliente, String status, String nomePrato, String item, String nomeFuncionario, String dataHora, double total) {
        this.idPedido = idPedido;
        this.nomeCliente = nomeCliente;
        this.status = status;
        this.nomePrato = nomePrato;
        this.item = item;
        this.nomeFuncionario = nomeFuncionario;
        this.dataHora = dataHora;
        this.total = total;
    }

    // Getters e Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }
    public String getNomeItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
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
}
