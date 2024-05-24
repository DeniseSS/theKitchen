package com.theKitchen.view;

import java.util.List;
import com.theKitchen.model.entity.Itens;

public class ItensView {

    public void mostrarDetalhesItem(Itens item) {
        System.out.println("Detalhes do item:");
        System.out.println("ID: " + item.getId());
        System.out.println("Nome: " + item.getNomeItem());
        System.out.println("Valor: " + item.getValorItem());
        System.out.println("Categoria: " + item.getCategoriaItem());
        System.out.println("Marca: " + item.getMarca());
    }

    public void mostrarListaItens(List<Itens> itens) {
        System.out.println("Lista de itens:");
        for (Itens item : itens) {
            System.out.println("ID: " + item.getId()+
                    ", Nome: " + item.getNomeItem() +
                    ", Valor: " + item.getValorItem() +
                    ", Categoria: " + item.getCategoriaItem() +
                    ", Marca: " + item.getMarca());
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
