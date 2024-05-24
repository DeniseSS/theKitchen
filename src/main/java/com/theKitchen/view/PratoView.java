package com.theKitchen.view;

import java.util.List;
import com.theKitchen.model.entity.Prato;

public class PratoView {

    public void mostrarDetalhesPrato(Prato prato) {
        System.out.println("Detalhes do prato:");
        System.out.println("ID: " + prato.getId());
        System.out.println("Nome: " + prato.getNomePrato());
        System.out.println("Composição: " + prato.getComposicao());
        System.out.println("Preço: " + prato.getPreco());
        System.out.println("Categoria" + prato.getCategoria());
        System.out.println("Tempo de preparo" + prato.getTempoPreparo());
    }

    public void mostrarListaPratos(List<Prato> pratos) {
        System.out.println("Lista de pratos:");
        for (Prato prato : pratos) {
            System.out.println("ID: " + prato.getId()+
                    ", Nome: " + prato.getNomePrato() +
                    ", Composição: " + prato.getComposicao() +
                    ", Preço: " + prato.getPreco() + 
                    ", Categoria" + prato.getCategoria() +
                    ", Tempo de preparo" + prato.getTempoPreparo()
                    );
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
