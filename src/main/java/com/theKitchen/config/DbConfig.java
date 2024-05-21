package com.theKitchen.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

  private static final String URL = "jdbc:postgresql://localhost:5432/cozinha";
  private static final String USER = "postgres";
  private static final String PASSWORD = "admin";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void testConnection() {
    try (Connection conn = getConnection()) {
      System.out.println("Conexão ao banco de dados bem-sucedida!");
    } catch (SQLException e) {
      System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
    }
  }
}
