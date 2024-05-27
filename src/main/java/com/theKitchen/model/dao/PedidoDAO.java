package com.theKitchen.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.theKitchen.config.DbConfig;
import com.theKitchen.model.entity.Pedido;

public class PedidoDAO implements IDAO<Pedido> {

    @Override
    public void cadastrar(Pedido pedido) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO pedido (fk_cliente, status, fk_funcionario, data_hora, total) VALUES (?, ?, ?, ?, ?) RETURNING id_pedido")) {
            statement.setInt(1, pedido.getCliente());
            statement.setString(2, pedido.getStatus());
            statement.setInt(3, pedido.getFuncionario());
            statement.setString(4, pedido.getDataHora());
            statement.setDouble(5, pedido.getTotal());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idPedido = resultSet.getInt(1);
                insertPratos(connection, idPedido, pedido.getPratos());
                insertItens(connection, idPedido, pedido.getItens());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pedido pedido) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE pedido SET fk_cliente=?, status=?, fk_funcionario=?, data_hora=?, total=? WHERE id_pedido=?")) {
            statement.setInt(1, pedido.getCliente());
            statement.setString(2, pedido.getStatus());
            statement.setInt(3, pedido.getFuncionario());
            statement.setString(4, pedido.getDataHora());
            statement.setDouble(5, pedido.getTotal());
            statement.setInt(6, pedido.getIdPedido());
            statement.executeUpdate();

            // Atualiza os pratos do pedido
            updatePratos(connection, pedido.getIdPedido(), pedido.getPratos());

            // Atualiza os itens do pedido
            updateItens(connection, pedido.getIdPedido(), pedido.getItens());

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
                int idPedido = resultSet.getInt("id_pedido");
                List<Integer> pratos = buscarPratosDoPedido(idPedido);
                List<Integer> itens = buscarItensDoPedido(idPedido);

                pedido = new Pedido(idPedido,
                        resultSet.getInt("fk_cliente"),
                        resultSet.getString("status"),
                        pratos,
                        resultSet.getInt("fk_funcionario"),
                        resultSet.getString("data_hora"),
                        resultSet.getDouble("total"),
                        itens);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    private List<Integer> buscarPratosDoPedido(int idPedido) {
        List<Integer> pratos = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT id_prato FROM pedido_prato WHERE id_pedido=?")) {
            statement.setInt(1, idPedido);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pratos.add(resultSet.getInt("id_prato"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pratos;
    }

    private List<Integer> buscarItensDoPedido(int idPedido) {
        List<Integer> itens = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT id_item FROM pedido_item WHERE id_pedido=?")) {
            statement.setInt(1, idPedido);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                itens.add(resultSet.getInt("id_item"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM pedido")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idPedido = resultSet.getInt("id_pedido");
                List<Integer> pratos = buscarPratosDoPedido(idPedido);
                List<Integer> itens = buscarItensDoPedido(idPedido);

                Pedido pedido = new Pedido(idPedido,
                        resultSet.getInt("fk_cliente"),
                        resultSet.getString("status"),
                        pratos,
                        resultSet.getInt("fk_funcionario"),
                        resultSet.getString("data_hora"),
                        resultSet.getDouble("total"),
                        itens);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    private void insertPratos(Connection connection, int idPedido, List<Integer> pratos) throws SQLException {
        for (int pratoId : pratos) {
            if (pratoExists(connection, idPedido, pratoId)) {
                updatePratoQuantity(connection, idPedido, pratoId);
            } else {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO pedido_prato (id_pedido, id_prato, quantidade) VALUES (?, ?, 1)")) {
                    statement.setInt(1, idPedido);
                    statement.setInt(2, pratoId);
                    statement.executeUpdate();
                }
            }
        }
    }

    private boolean pratoExists(Connection connection, int idPedido, int pratoId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT quantidade FROM pedido_prato WHERE id_pedido = ? AND id_prato = ?")) {
            statement.setInt(1, idPedido);
            statement.setInt(2, pratoId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    private void updatePratoQuantity(Connection connection, int idPedido, int pratoId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE pedido_prato SET quantidade = quantidade + 1 WHERE id_pedido = ? AND id_prato = ?")) {
            statement.setInt(1, idPedido);
            statement.setInt(2, pratoId);
            statement.executeUpdate();
        }
    }

    private void updatePratos(Connection connection, int idPedido, List<Integer> pratos) throws SQLException {
        // Verifica e atualiza os pratos do pedido
        for (int pratoId : pratos) {
            if (pratoExists(connection, idPedido, pratoId)) {
                updatePratoQuantity(connection, idPedido, pratoId);
            } else {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO pedido_prato (id_pedido, id_prato, quantidade) VALUES (?, ?, 1)")) {
                    statement.setInt(1, idPedido);
                    statement.setInt(2, pratoId);
                    statement.executeUpdate();
                }
            }
        }
    }

    private void insertItens(Connection connection, int idPedido, List<Integer> itens) throws SQLException {
        for (int itemId : itens) {
            if (itemExists(connection, idPedido, itemId)) {
                updateItemQuantity(connection, idPedido, itemId);
            } else {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO pedido_item (id_pedido, id_item, quantidade) VALUES (?, ?, 1)")) {
                    statement.setInt(1, idPedido);
                    statement.setInt(2, itemId);
                    statement.executeUpdate();
                }
            }
        }
    }

    private boolean itemExists(Connection connection, int idPedido, int itemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT quantidade FROM pedido_item WHERE id_pedido = ? AND id_item = ?")) {
            statement.setInt(1, idPedido);
            statement.setInt(2, itemId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    private void updateItemQuantity(Connection connection, int idPedido, int itemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE pedido_item SET quantidade = quantidade + 1 WHERE id_pedido = ? AND id_item = ?")) {
            statement.setInt(1, idPedido);
            statement.setInt(2, itemId);
            statement.executeUpdate();
        }
    }

    private void updateItens(Connection connection, int idPedido, List<Integer> itens) throws SQLException {
        // Verifica e atualiza os itens do pedido
        for (int itemId : itens) {
            if (itemExists(connection, idPedido, itemId)) {
                updateItemQuantity(connection, idPedido, itemId);
            } else {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO pedido_item (id_pedido, id_item, quantidade) VALUES (?, ?, 1)")) {
                    statement.setInt(1, idPedido);
                    statement.setInt(2, itemId);
                    statement.executeUpdate();
                }
            }
        }
    }
}
