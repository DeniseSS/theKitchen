package com.theKitchen.model.dao;

import java.util.List;
import com.theKitchen.config.DbConfig;
import com.theKitchen.model.entity.Prato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PratoDAO implements IDAO<Prato> {

    @Override
    public void cadastrar(Prato entidade) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO prato (nome_prato, composicao, preco, categoria, tempo_preparo) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, entidade.getNomePrato());
            statement.setString(2, entidade.getComposicao());
            statement.setDouble(3, entidade.getPreco());
            statement.setString(4, entidade.getCategoria());
            statement.setInt(5, entidade.getTempoPreparo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Prato entidade) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE prato SET nome_prato=?, composicao=?, preco=?, categoria=?, tempo_preparo=? WHERE id_prato=?")) {
            statement.setString(1, entidade.getNomePrato());
            statement.setString(2, entidade.getComposicao());
            statement.setDouble(3, entidade.getPreco());
            statement.setString(4, entidade.getCategoria());
            statement.setInt(5, entidade.getTempoPreparo());
            statement.setInt(6, entidade.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM prato WHERE id_prato=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prato buscar(int id) {
        Prato prato = null;
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM prato WHERE id_prato=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                prato = new Prato(resultSet.getInt("id_prato"),
                        resultSet.getString("nome_prato"),
                        resultSet.getString("composicao"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("categoria"),
                        resultSet.getInt("tempo_preparo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prato;
    }

    @Override
    public List<Prato> listar() {
        List<Prato> pratos = new ArrayList<>();
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM prato")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Prato prato = new Prato(resultSet.getInt("id_prato"),
                        resultSet.getString("nome_prato"),
                        resultSet.getString("composicao"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("categoria"),
                        resultSet.getInt("tempo_preparo"));
                pratos.add(prato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pratos;
    }
}
