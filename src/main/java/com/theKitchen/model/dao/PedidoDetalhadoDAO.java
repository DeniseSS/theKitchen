package com.theKitchen.model.dao;

import com.theKitchen.model.entity.PedidoDetalhado;
import com.theKitchen.config.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDetalhadoDAO {
    public List<PedidoDetalhado> listarPedidosDetalhados() {
        List<PedidoDetalhado> pedidosDetalhados = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT p.id_pedido, c.nome_cliente, f.nome_funcionario, p.status, p.data_hora, " +
                                "SUM(pr.preco * pp.quantidade) + SUM(i.valor * pi.quantidade) AS total, " +
                                "ARRAY_AGG(DISTINCT pr.nome_prato || ' x' || pp.quantidade) AS nomes_pratos, " +
                                "ARRAY_AGG(DISTINCT i.nome_item || ' x' || pi.quantidade) AS nomes_itens " +
                                "FROM pedido p " +
                                "JOIN cliente c ON p.fk_cliente = c.id_cliente " +
                                "JOIN funcionario f ON p.fk_funcionario = f.id_funcionario " +
                                "LEFT JOIN pedido_prato pp ON p.id_pedido = pp.id_pedido " +
                                "LEFT JOIN prato pr ON pp.id_prato = pr.id_prato " +
                                "LEFT JOIN pedido_item pi ON p.id_pedido = pi.id_pedido " +
                                "LEFT JOIN itens i ON pi.id_item = i.id_item " +
                                "GROUP BY p.id_pedido, c.nome_cliente, f.nome_funcionario, p.status, p.data_hora")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PedidoDetalhado pedidoDetalhado = new PedidoDetalhado(
                        resultSet.getInt("id_pedido"),
                        resultSet.getString("nome_cliente"),
                        resultSet.getString("status"),
                        convertArrayToList(resultSet.getArray("nomes_pratos")),
                        convertArrayToList(resultSet.getArray("nomes_itens")),
                        resultSet.getString("nome_funcionario"),
                        resultSet.getString("data_hora"),
                        resultSet.getDouble("total"));
                pedidosDetalhados.add(pedidoDetalhado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidosDetalhados;
    }

    public PedidoDetalhado buscar(int id) {
        PedidoDetalhado pedidoDetalhado = null;
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT p.id_pedido, c.nome_cliente, f.nome_funcionario, p.status, p.data_hora, " +
                                "SUM(pr.preco * pp.quantidade) + SUM(i.valor * pi.quantidade) AS total, " +
                                "ARRAY_AGG(DISTINCT pr.nome_prato || ' x' || pp.quantidade) AS nomes_pratos, " +
                                "ARRAY_AGG(DISTINCT i.nome_item || ' x' || pi.quantidade) AS nomes_itens " +
                                "FROM pedido p " +
                                "JOIN cliente c ON p.fk_cliente = c.id_cliente " +
                                "JOIN funcionario f ON p.fk_funcionario = f.id_funcionario " +
                                "LEFT JOIN pedido_prato pp ON p.id_pedido = pp.id_pedido " +
                                "LEFT JOIN prato pr ON pp.id_prato = pr.id_prato " +
                                "LEFT JOIN pedido_item pi ON p.id_pedido = pi.id_pedido " +
                                "LEFT JOIN itens i ON pi.id_item = i.id_item " +
                                "WHERE p.id_pedido = ? " +
                                "GROUP BY p.id_pedido, c.nome_cliente, f.nome_funcionario, p.status, p.data_hora")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pedidoDetalhado = new PedidoDetalhado(
                        resultSet.getInt("id_pedido"),
                        resultSet.getString("nome_cliente"),
                        resultSet.getString("status"),
                        convertArrayToList(resultSet.getArray("nomes_pratos")),
                        convertArrayToList(resultSet.getArray("nomes_itens")),
                        resultSet.getString("nome_funcionario"),
                        resultSet.getString("data_hora"),
                        resultSet.getDouble("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidoDetalhado;
    }

    private List<String> convertArrayToList(java.sql.Array sqlArray) throws SQLException {
        if (sqlArray == null) {
            return new ArrayList<>();
        }
        String[] array = (String[]) sqlArray.getArray();
        List<String> list = new ArrayList<>(array.length);
        for (String item : array) {
            list.add(item);
        }
        return list;
    }
}
