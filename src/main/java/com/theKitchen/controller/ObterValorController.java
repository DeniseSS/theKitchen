package com.theKitchen.controller;

import com.theKitchen.model.dao.ValorDAO;
import com.theKitchen.model.entity.ValorItem;

public class ObterValorController {

    private final ValorDAO<ValorItem> valorDAO;

    public ObterValorController(ValorDAO<ValorItem> valorDAO) {
        this.valorDAO = valorDAO;
    }

    public double obterValorItem(int itemId) {
        return valorDAO.ObterValorItem(itemId);
    }

    public double obterValorPrato(int pratoId) {
        return valorDAO.obterValorPrato(pratoId);
    }
}
