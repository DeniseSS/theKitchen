package com.theKitchen.model.dao;


import com.theKitchen.model.entity.AbstractEntity;

public interface ValorDAO<T extends AbstractEntity>  {

    double ObterValorItem(int id);
    double obterValorPrato(int id);

}