package com.theKitchen.model.dao;

import java.util.List;
import com.theKitchen.config.DbConfig;
import com.theKitchen.model.entity.Itens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItensDAO implements IDAO<Itens> {

    @Override
    public void cadastrar(Itens item) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO itens (nome_item, valor, categoria, marca) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, item.getNomeItem());
            statement.setDouble(2, item.getValorItem());
            statement.setString(3, item.getCategoriaItem());
            statement.setString(4, item.getMarca());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Itens item) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE itens SET nome_item=?, valor=?, categoria=?, marca=? WHERE id_item=?")) {
            statement.setString(1, item.getNomeItem());
            statement.setDouble(2, item.getValorItem());
            statement.setString(3, item.getCategoriaItem());
            statement.setString(4, item.getMarca());
            statement.setInt(5, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM itens WHERE id_item=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Itens buscar(int id) {
        Itens item = null;
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM itens WHERE id_item=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                item = new Itens(resultSet.getInt("id_item"),
                        resultSet.getString("nome_item"),
                        resultSet.getDouble("valor"),
                        resultSet.getString("categoria"),
                        resultSet.getString("marca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<Itens> listar() {
        List<Itens> items = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM itens")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Itens item = new Itens(resultSet.getInt("id_item"),
                        resultSet.getString("nome_item"),
                        resultSet.getDouble("valor"),
                        resultSet.getString("categoria"),
                        resultSet.getString("marca"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

   
    
}
