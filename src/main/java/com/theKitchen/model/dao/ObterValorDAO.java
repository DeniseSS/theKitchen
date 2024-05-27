package com.theKitchen.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.theKitchen.config.DbConfig;
import com.theKitchen.model.entity.ValorItem;

public class ObterValorDAO implements ValorDAO<ValorItem> {

    @Override
    public double ObterValorItem(int id) {
        double valorItem = 0.0;
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT valor FROM itens WHERE id_item=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                valorItem = resultSet.getDouble("valor");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valorItem;
    }

    @Override
    public double obterValorPrato(int id) {
        double valorPrato = 0.0;
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT preco FROM prato WHERE id_prato=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                valorPrato = resultSet.getDouble("preco");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valorPrato;
    }
}
