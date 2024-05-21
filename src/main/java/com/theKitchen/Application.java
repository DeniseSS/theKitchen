package com.theKitchen;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.theKitchen.controller.ClienteController;
import com.theKitchen.controller.FuncionarioController;
import com.theKitchen.controller.PratoController;
import com.theKitchen.model.dao.ClienteDAO;
import com.theKitchen.model.dao.FuncionarioDAO;
import com.theKitchen.model.dao.PratoDAO;
import com.theKitchen.view.ClienteView;
import com.theKitchen.view.FuncionarioView;
import com.theKitchen.view.PratoView;
import com.theKitchen.config.DbConfig;

@SpringBootApplication
public class Application  implements CommandLineRunner {

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

        DbConfig.testConnection();

        Scanner scanner = new Scanner(System.in);
        MenuApplication sistema = new MenuApplication(clienteController, clienteView, funcionarioController, funcionarioView, pratoView, pratoController, scanner);

        sistema.iniciar();
    }
}
