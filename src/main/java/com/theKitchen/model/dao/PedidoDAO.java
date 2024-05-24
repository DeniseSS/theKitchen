package com.theKitchen.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.theKitchen.config.DbConfig;
import com.theKitchen.model.entity.Pedido;

public class PedidoDAO implements IDAO<Pedido> {

    @Override
    public void cadastrar(Pedido pedido) {
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO pedido (fk_cliente, status, fk_prato, fk_funcionario, data_hora, total, fk_item) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, pedido.getCliente());
            statement.setString(2, pedido.getStatus());
            statement.setInt(3, pedido.getPrato());
            statement.setInt(4, pedido.getFuncionario());
            statement.setString(5, pedido.getDataHora()); // Armazenando a data como string
            statement.setDouble(6, pedido.getTotal());
            statement.setInt(7, pedido.getItem());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pedido pedido) {
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "UPDATE pedido SET cliente=?, status=?, prato=?, funcionario=?, data_hora=?, total=?, item=? WHERE id_pedido=?")) {
            statement.setInt(1, pedido.getCliente());
            statement.setString(2, pedido.getStatus());
            statement.setInt(3, pedido.getPrato());
            statement.setInt(4, pedido.getFuncionario());
            Timestamp timestamp = Timestamp.valueOf(pedido.getDataHora()); // Convertendo a string para Timestamp
            statement.setTimestamp(5, timestamp);
            statement.setDouble(6, pedido.getTotal());
            statement.setInt(7, pedido.getItem());
            statement.setInt(8, pedido.getIdPedido());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM pedido WHERE id_pedido=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido buscar(int id) {
        Pedido pedido = null;
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido WHERE id_pedido=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pedido = new Pedido(resultSet.getInt("id_pedido"),
                        resultSet.getInt("cliente"),
                        resultSet.getString("status"),
                        resultSet.getInt("prato"),
                        resultSet.getInt("funcionario"),
                        resultSet.getString("data_hora"), // Obtendo a data como string
                        resultSet.getDouble("total"),
                        resultSet.getInt("item"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pedido pedido = new Pedido(resultSet.getInt("id_pedido"),
                        resultSet.getInt("cliente"),
                        resultSet.getString("status"),
                        resultSet.getInt("prato"),
                        resultSet.getInt("funcionario"),
                        resultSet.getString("data_hora"), // Obtendo a data como string
                        resultSet.getDouble("total"),
                        resultSet.getInt("item"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
}
