package com.theKitchen;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.theKitchen.controller.ClienteController;
import com.theKitchen.controller.FuncionarioController;
import com.theKitchen.controller.ItensController;
import com.theKitchen.controller.PratoController;
import com.theKitchen.controller.PedidoController;
import com.theKitchen.model.dao.ClienteDAO;
import com.theKitchen.model.dao.FuncionarioDAO;
import com.theKitchen.model.dao.ItensDAO;
import com.theKitchen.model.dao.PedidoDAO;
import com.theKitchen.model.dao.PratoDAO;
import com.theKitchen.view.ClienteView;
import com.theKitchen.view.FuncionarioView;
import com.theKitchen.view.ItensView;
import com.theKitchen.view.PedidoView;
import com.theKitchen.view.PratoView;
import com.theKitchen.config.DbConfig;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ClienteView clienteView = new ClienteView();
        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteController clienteController = new ClienteController(clienteDAO);

        FuncionarioView funcionarioView = new FuncionarioView();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioDAO);

        PratoView pratoView = new PratoView();
        PratoDAO pratoDAO = new PratoDAO();
        PratoController pratoController = new PratoController(pratoDAO);

        ItensView itensView = new ItensView();
        ItensDAO itensDAO = new ItensDAO();
        ItensController itensController = new ItensController(itensDAO);

        PedidoView pedidoView = new PedidoView();
        PedidoDAO pedidoDAO = new PedidoDAO();
        PedidoController pedidoController = new PedidoController(pedidoDAO);

        DbConfig.testConnection();

        Scanner scanner = new Scanner(System.in);
        MenuApplication sistema = new MenuApplication(clienteController, clienteView, funcionarioController,
                funcionarioView, pratoController, pratoView, itensController, itensView, pedidoController, pedidoView,
                scanner);

        sistema.iniciar();
    }
}
