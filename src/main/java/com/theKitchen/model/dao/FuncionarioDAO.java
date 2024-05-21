package com.theKitchen.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.theKitchen.config.DbConfig;
import com.theKitchen.model.entity.Funcionario;

public class FuncionarioDAO implements IDAO<Funcionario> {

    @Override
    public void cadastrar(Funcionario entidade) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO funcionario (nome_funcionario, telefone, cpf, cargo, data_nascimento, sobrenome) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, entidade.getNomeFuncionario());
            statement.setString(2, entidade.getTelefone());
            statement.setString(3, entidade.getCpf());
            statement.setString(4, entidade.getCargo());
            statement.setDate(5, Date.valueOf(entidade.getDataNascimento()));
            statement.setString(6, entidade.getSobrenome()); 
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Funcionario entidade) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE funcionario SET nome_funcionario=?, telefone=?, cpf=?, cargo=?, data_nascimento=?, sobrenome=? WHERE id_funcionario=?")) {
            statement.setString(1, entidade.getNomeFuncionario());
            statement.setString(2, entidade.getTelefone());
            statement.setString(3, entidade.getCpf());
            statement.setString(4, entidade.getCargo());
            statement.setDate(5, Date.valueOf(entidade.getDataNascimento()));
            statement.setString(6, entidade.getSobrenome()); 
            statement.setInt(7, entidade.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM funcionario WHERE id_funcionario=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario buscar(int id) {
        Funcionario funcionario = null;
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM funcionario WHERE id_funcionario=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                funcionario = new Funcionario(resultSet.getInt("id_funcionario"),
                        resultSet.getString("nome_funcionario"), 
                        resultSet.getString("telefone"),
                        resultSet.getString("cpf"),
                        resultSet.getString("cargo"),
                        resultSet.getDate("data_nascimento").toLocalDate(),
                        resultSet.getString("sobrenome")); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM funcionario")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario(resultSet.getInt("id_funcionario"),
                        resultSet.getString("nome_funcionario"),
                        resultSet.getString("telefone"),
                        resultSet.getString("cpf"),
                        resultSet.getString("cargo"),
                        resultSet.getDate("data_nascimento").toLocalDate(),
                        resultSet.getString("sobrenome"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }
}
