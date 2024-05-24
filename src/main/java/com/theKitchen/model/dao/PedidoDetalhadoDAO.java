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
                        "SELECT p.id_pedido, pr.nome_prato AS nome_prato, i.nome_item AS nome_item, " +
                                "f.nome_funcionario AS nome_funcionario, c.nome_cliente AS nome_cliente, " +
                                "p.status, p.data_hora, p.total " +
                                "FROM pedido p " +
                                "JOIN prato pr ON p.fk_prato = pr.id_prato " +
                                "JOIN itens i ON p.fk_item = i.id_item " +
                                "JOIN funcionario f ON p.fk_funcionario = f.id_funcionario " +
                                "JOIN cliente c ON p.fk_cliente = c.id_cliente")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PedidoDetalhado pedidoDetalhado = new PedidoDetalhado(
                        resultSet.getInt("id_pedido"),
                        resultSet.getString("nome_cliente"),
                        resultSet.getString("status"),
                        resultSet.getString("nome_prato"),
                        resultSet.getString("nome_item"),
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
}
