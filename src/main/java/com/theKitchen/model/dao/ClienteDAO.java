package com.theKitchen.model.dao;

import java.util.List;

import com.theKitchen.config.DbConfig;
import com.theKitchen.model.entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO implements IDAO<Cliente> {

    @Override
    public void cadastrar(Cliente entidade) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO cliente (nome_cliente, telefone, cpf, sobrenome, endereco) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, entidade.getNomeCliente());
            statement.setString(2, entidade.getTelefone());
            statement.setString(3, entidade.getCpf());
            statement.setString(4, entidade.getSobrenome());
            statement.setString(5, entidade.getEndereco());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Cliente entidade) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("UPDATE cliente SET nome_cliente=?, telefone=?, cpf=?, sobrenome=?, endereco=? WHERE id_cliente=?")) {
            statement.setString(1, entidade.getNomeCliente());
            statement.setString(2, entidade.getTelefone());
            statement.setString(3, entidade.getCpf());
            statement.setString(4, entidade.getSobrenome());
            statement.setString(5, entidade.getEndereco());
            statement.setInt(6, entidade.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM cliente WHERE id_cliente=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscar(int id) {
        Cliente cliente = null;
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE id_cliente=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente(resultSet.getInt("id_cliente"), resultSet.getString("nome_cliente"),
                        resultSet.getString("telefone"), resultSet.getString("cpf"), resultSet.getString("sobrenome"), resultSet.getString("endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt("id_cliente"), resultSet.getString("nome_cliente"),
                        resultSet.getString("telefone"), resultSet.getString("cpf"), resultSet.getString("sobrenome"), resultSet.getString("endereco"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
