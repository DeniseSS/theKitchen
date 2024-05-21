package com.theKitchen.view;

import java.util.List;
import com.theKitchen.model.entity.Prato;

public class PratoView {

    public void mostrarDetalhesPrato(Prato prato) {
        System.out.println("Detalhes do prato:");
        System.out.println("Nome: " + prato.getNomePrato());
        System.out.println("Composição: " + prato.getComposicao());
        System.out.println("Preço: " + prato.getPreco());
    }

    public void mostrarListaPratos(List<Prato> pratos) {
        System.out.println("Lista de pratos:");
        for (Prato prato : pratos) {
            System.out.println("Nome: " + prato.getNomePrato() +
                    ", Composição: " + prato.getComposicao() +
                    ", Preço: " + prato.getPreco());
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
