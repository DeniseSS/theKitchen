package com.theKitchen.model.entity;

import java.util.List;

public class PedidoDetalhado {
    private int idPedido;
    private String nomeCliente;
    private String status;
    private List<String> nomesPratos;
    private List<String> nomesItens;
    private String nomeFuncionario;
    private String dataHora;
    private double total;

    // Construtor
    public PedidoDetalhado(int idPedido, String nomeCliente, String status, List<String> nomesPratos, List<String> nomesItens, String nomeFuncionario, String dataHora, double total) {
        this.idPedido = idPedido;
        this.nomeCliente = nomeCliente;
        this.status = status;
        this.nomesPratos = nomesPratos;
        this.nomesItens = nomesItens;
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

    public List<String> getNomesPratos() {
        return nomesPratos;
    }

    public void setNomesPratos(List<String> nomesPratos) {
        this.nomesPratos = nomesPratos;
    }

    public List<String> getNomesItens() {
        return nomesItens;
    }

    public void setNomesItens(List<String> nomesItens) {
        this.nomesItens = nomesItens;
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
