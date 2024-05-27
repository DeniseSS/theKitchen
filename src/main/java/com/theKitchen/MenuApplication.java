package com.theKitchen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.theKitchen.controller.ClienteController;
import com.theKitchen.controller.FuncionarioController;
import com.theKitchen.controller.ItensController;
import com.theKitchen.controller.ObterValorController;
import com.theKitchen.controller.PedidoController;
import com.theKitchen.controller.PratoController;
import com.theKitchen.model.dao.ObterValorDAO;
import com.theKitchen.model.entity.Cliente;
import com.theKitchen.model.entity.Funcionario;
import com.theKitchen.model.entity.Itens;
import com.theKitchen.model.entity.Pedido;
import com.theKitchen.model.entity.Prato;
import com.theKitchen.view.ClienteView;
import com.theKitchen.view.FuncionarioView;
import com.theKitchen.view.ItensView;
import com.theKitchen.view.PedidoView;
import com.theKitchen.view.PratoView;

import com.theKitchen.controller.PedidoDetalhadoController;
import com.theKitchen.model.entity.PedidoDetalhado;
import com.theKitchen.view.PedidoDetalhadoView;

public class MenuApplication {

    private ClienteController clienteController;
    private ClienteView clienteView;
    private FuncionarioController funcionarioController;
    private FuncionarioView funcionarioView;
    private PratoController pratoController;
    private PratoView pratoView;
    private ItensController itensController;
    private ItensView itensView;
    private PedidoController pedidoController;
    private PedidoView pedidoView;
    private PedidoDetalhadoController pedidoDetalhadoController;
    private PedidoDetalhadoView pedidoDetalhadoView;
    private ObterValorController obterValorController;
    private Scanner scanner;

    public MenuApplication(ClienteController clienteController, ClienteView clienteView,
            FuncionarioController funcionarioController, FuncionarioView funcionarioView,
            PratoController pratoController, PratoView pratoView,
            ItensController itensController, ItensView itensView,
            PedidoController pedidoController, PedidoView pedidoView,
            Scanner scanner, PedidoDetalhadoController pedidoDetalhadoController,
            PedidoDetalhadoView pedidoDetalhadoView, ObterValorController obterValorController) {
        this.clienteController = clienteController;
        this.clienteView = clienteView;
        this.funcionarioController = funcionarioController;
        this.funcionarioView = funcionarioView;
        this.pratoController = pratoController;
        this.pratoView = pratoView;
        this.itensController = itensController;
        this.itensView = itensView;
        this.pedidoController = pedidoController;
        this.pedidoView = pedidoView;
        this.pedidoDetalhadoController = pedidoDetalhadoController;
        this.pedidoDetalhadoView = pedidoDetalhadoView;
        this.obterValorController = new ObterValorController(new ObterValorDAO());
        this.scanner = scanner;

    }

    public void iniciar() {
        int opcao;
        do {
            mostrarMenuPrincipal();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    gerenciarPedidos();
                    break;
                case 2:
                    gerenciarPratos();
                    break;
                case 3:
                    gerenciarClientes();
                    break;
                case 4:
                    gerenciarFuncionarios();
                    break;
                case 5:
                    gerenciarItens();
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Gerenciar Pedidos");
        System.out.println("2. Gerenciar Pratos");
        System.out.println("3. Gerenciar Clientes");
        System.out.println("4. Gerenciar Funcionarios");
        System.out.println("5. Gerenciar itens");
        System.out.println("0. Sair");
        System.out.println("======================");
        System.out.print("Escolha uma opção: ");
    }

    private void gerenciarPedidos() {
        int opcao;
        do {
            mostrarMenuPedidos();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarPedido();
                    break;
                case 2:
                    listarPedidosDetalhados();
                    break;
                case 3:
                    atualizarPedido();
                    break;
                case 4:
                    excluirPedido();
                    break;
                case 5:
                    buscarPedido();
                    break;
                case 0:
                    pedidoView.mostrarMensagem("Voltando ao menu principal...");
                    break;
                default:
                    pedidoView.mostrarMensagem("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void mostrarMenuPedidos() {
        pedidoView.mostrarMensagem("=== Menu Pedidos ===");
        pedidoView.mostrarMensagem("1. Cadastrar Pedido");
        pedidoView.mostrarMensagem("2. Listar Pedidos");
        pedidoView.mostrarMensagem("3. Atualizar Pedido");
        pedidoView.mostrarMensagem("4. Excluir Pedido");
        pedidoView.mostrarMensagem("5. Buscar Pedido");
        pedidoView.mostrarMensagem("0. Voltar ao Menu Principal");
        pedidoView.mostrarMensagem("===========================");
        pedidoView.mostrarMensagem("Escolha uma opção:");
    }

    private void cadastrarPedido() {

        listarFuncionarios();
        pedidoView.mostrarMensagem("Digite o ID do funcionario:");
        int funcionarioId = scanner.nextInt();
        scanner.nextLine();

        listarClientes();
        pedidoView.mostrarMensagem("Digite o ID do cliente:");
        int clienteId = scanner.nextInt();
        scanner.nextLine();

        List<Integer> itensSelecionados = new ArrayList<>();
        List<Integer> pratosSelecionados = new ArrayList<>();

        boolean continuarPedido = true;

        while (continuarPedido) {
            boolean adicionarPrato = true;
            while (adicionarPrato) {
                listarPratos();
                pedidoView.mostrarMensagem("Digite o ID do prato:");
                int pratoId = scanner.nextInt();
                scanner.nextLine();
                pratosSelecionados.add(pratoId);

                pedidoView.mostrarMensagem("Deseja adicionar mais pratos ao pedido? (S/N)");
                String continuar = scanner.nextLine().toUpperCase();
                if (!continuar.equals("S")) {
                    adicionarPrato = false;
                }
            }

            boolean adicionarItem = true;
            while (adicionarItem) {
                listarItens();
                pedidoView.mostrarMensagem("Informe o item que deseja incluir:");
                int itemId = scanner.nextInt();
                scanner.nextLine();
                itensSelecionados.add(itemId);

                pedidoView.mostrarMensagem("Deseja adicionar mais itens ao pedido? (S/N)");
                String continuar = scanner.nextLine().toUpperCase();
                if (!continuar.equals("S")) {
                    adicionarItem = false;
                }
            }

            pedidoView.mostrarMensagem("Deseja continuar adicionando mais itens e pratos ao pedido? (S/N)");
            String continuar = scanner.nextLine().toUpperCase();
            if (!continuar.equals("S")) {
                continuarPedido = false;
            }
        }

        double totalItem = 0;
        double totalPrato = 0;

        for (int itemId : itensSelecionados) {
            double valorItem = obterValorController.obterValorItem(itemId);
            totalItem += valorItem;
        }

        for (int pratoId : pratosSelecionados) {
            double valorPrato = obterValorController.obterValorPrato(pratoId);
            totalPrato += valorPrato;
        }

        double total = totalPrato + totalItem;

        pedidoView.mostrarMensagem("O valor total dos itens é: " + totalItem);
        pedidoView.mostrarMensagem("O valor total dos pratos é: " + totalPrato);
        pedidoView.mostrarMensagem("O valor total do pedido é: " + total);

        System.out.println("___________________________________________");
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dataHora = dataHoraAtual.format(formatter);
        System.out.println("Horario: " + dataHora);
        System.out.println("___________________________________________");

        System.out.println("Digite o número do status do pedido:");
        System.out.println("1 = Em preparo");
        System.out.println("2 = Pronto");
        System.out.println("3 = Entregue");
        System.out.println("4 = Cancelado");

        int Escolhastatus = scanner.nextInt();
        String status;

        switch (Escolhastatus) {
            case 1:
                status = "Em preparo";
                break;
            case 2:
                status = "Pronto";
                break;
            case 3:
                status = "Entregue";
                break;
            case 4:
                status = "Cancelado";
                break;
            default:
                status = "Número inválido. Por favor, digite um número entre 1 e 4.";
                break;
        }

        Pedido pedido = new Pedido(0, clienteId, status, pratosSelecionados, funcionarioId, dataHora, total,
                itensSelecionados);
        String retorno = pedidoController.cadastrarPedido(pedido);
        pedidoView.mostrarMensagem(retorno);
    }

    private void listarPedidosDetalhados() {
        List<PedidoDetalhado> pedidos = pedidoDetalhadoController.listarPedidosDetalhados();
        pedidoDetalhadoView.mostrarListaPedidos(pedidos);
    }

    private void atualizarPedido() {
        pedidoView.mostrarMensagem("Digite o ID do pedido a ser atualizado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Pedido pedido = pedidoController.buscarPedido(id);
        if (pedido != null) {
            listarFuncionarios();
            pedidoView.mostrarMensagem("Digite o novo ID do funcionário:");
            int funcionarioId = scanner.nextInt();
            scanner.nextLine();

            listarClientes();
            pedidoView.mostrarMensagem("Digite o novo ID do cliente:");
            int clienteId = scanner.nextInt();
            scanner.nextLine();

            List<Integer> itensSelecionados = new ArrayList<>();
            List<Integer> pratosSelecionados = new ArrayList<>();

            boolean continuarPedido = true;

            while (continuarPedido) {
                boolean adicionarPrato = true;
                while (adicionarPrato) {
                    listarPratos();
                    pedidoView.mostrarMensagem("Digite o ID do novo prato:");
                    int pratoId = scanner.nextInt();
                    scanner.nextLine();
                    pratosSelecionados.add(pratoId);

                    pedidoView.mostrarMensagem("Deseja adicionar mais pratos ao pedido? (S/N)");
                    String continuar = scanner.nextLine().toUpperCase();
                    if (!continuar.equals("S")) {
                        adicionarPrato = false;
                    }
                }

                boolean adicionarItem = true;
                while (adicionarItem) {
                    listarItens();
                    pedidoView.mostrarMensagem("Informe o novo item que deseja incluir:");
                    int itemId = scanner.nextInt();
                    scanner.nextLine();
                    itensSelecionados.add(itemId);

                    pedidoView.mostrarMensagem("Deseja adicionar mais itens ao pedido? (S/N)");
                    String continuar = scanner.nextLine().toUpperCase();
                    if (!continuar.equals("S")) {
                        adicionarItem = false;
                    }
                }

                pedidoView.mostrarMensagem("Deseja continuar adicionando mais itens e pratos ao pedido? (S/N)");
                String continuar = scanner.nextLine().toUpperCase();
                if (!continuar.equals("S")) {
                    continuarPedido = false;
                }
            }

            double totalItem = 0;
            double totalPrato = 0;

            for (int itemId : itensSelecionados) {
                double valorItem = obterValorController.obterValorItem(itemId);
                totalItem += valorItem;
            }

            for (int pratoId : pratosSelecionados) {
                double valorPrato = obterValorController.obterValorPrato(pratoId);
                totalPrato += valorPrato;
            }

            double total = totalPrato + totalItem;

            pedidoView.mostrarMensagem("O valor total dos itens é: " + totalItem);
            pedidoView.mostrarMensagem("O valor total dos pratos é: " + totalPrato);
            pedidoView.mostrarMensagem("O valor total do pedido é: " + total);

            System.out.println("___________________________________________");
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String dataHora = dataHoraAtual.format(formatter);
            System.out.println("Horario: " + dataHora);
            System.out.println("___________________________________________");

            pedidoView.mostrarMensagem("Digite o novo número do status do pedido:");
            pedidoView.mostrarMensagem("1 = Em preparo");
            pedidoView.mostrarMensagem("2 = Pronto");
            pedidoView.mostrarMensagem("3 = Entregue");
            pedidoView.mostrarMensagem("4 = Cancelado");

            int Escolhastatus = scanner.nextInt();
            String status;

            switch (Escolhastatus) {
                case 1:
                    status = "Em preparo";
                    break;
                case 2:
                    status = "Pronto";
                    break;
                case 3:
                    status = "Entregue";
                    break;
                case 4:
                    status = "Cancelado";
                    break;
                default:
                    status = "Número inválido. Por favor, digite um número entre 1 e 4.";
                    break;
            }

            pedido.setStatus(status);
            pedido.setDataHora(dataHora);
            pedido.setTotal(total);
            pedido.setCliente(clienteId);
            pedido.setFuncionario(funcionarioId);

            String retorno = pedidoController.atualizarPedido(pedido);
            pedidoView.mostrarMensagem(retorno);
        } else {
            pedidoView.mostrarMensagem("Pedido não encontrado!");
        }
    }

    private void excluirPedido() {
        pedidoView.mostrarMensagem("Digite o ID do pedido a ser excluído:");
        int id = scanner.nextInt();
        scanner.nextLine();
        String retorno = pedidoController.excluirPedido(id);
        pedidoView.mostrarMensagem(retorno);
    }

    private void buscarPedido() {
        pedidoView.mostrarMensagem("Digite o ID do pedido a ser buscado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        pedidoDetalhadoController.buscarPedidoDetalhadoPorId(id);
    }

    private void gerenciarItens() {
        int opcao;
        do {
            mostrarMenuItens();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarItem();
                    break;
                case 2:
                    listarItens();
                    break;
                case 3:
                    atualizarItem();
                    break;
                case 4:
                    excluirItem();
                    break;
                case 5:
                    buscarItem();
                    break;
                case 0:
                    itensView.mostrarMensagem("Voltando ao menu principal...");
                    break;
                default:
                    itensView.mostrarMensagem("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void mostrarMenuItens() {
        itensView.mostrarMensagem("=== Menu Itens ===");
        itensView.mostrarMensagem("1. Cadastrar Item");
        itensView.mostrarMensagem("2. Listar Itens");
        itensView.mostrarMensagem("3. Atualizar Item");
        itensView.mostrarMensagem("4. Excluir Item");
        itensView.mostrarMensagem("5. Buscar Item");
        itensView.mostrarMensagem("0. Voltar ao Menu Principal");
        itensView.mostrarMensagem("===========================");
        itensView.mostrarMensagem("Escolha uma opção:");
    }

    private void cadastrarItem() {
        itensView.mostrarMensagem("Digite o nome do item:");
        String nome = scanner.nextLine();
        itensView.mostrarMensagem("Digite o valor do item:");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        itensView.mostrarMensagem("Digite a categoria do item:");
        String categoria = scanner.nextLine();
        itensView.mostrarMensagem("Digite a marca do item:");
        String marca = scanner.nextLine();

        Itens novoItem = new Itens(nome, valor, categoria, marca);
        String retorno = itensController.cadastrarItem(novoItem);
        itensView.mostrarMensagem(retorno);
    }

    private void listarItens() {
        itensView.mostrarMensagem("=== Itens Cadastrados ===");
        List<Itens> itens = itensController.listarItens();
        itensView.mostrarListaItens(itens);
        itensView.mostrarMensagem("===========================");
    }

    private void atualizarItem() {
        itensView.mostrarMensagem("Digite o ID do item a ser atualizado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Itens item = itensController.buscarItem(id);
        if (item != null) {
            itensView.mostrarMensagem("Digite o novo nome do item:");
            String nome = scanner.nextLine();
            item.setNomeItem(nome);
            itensView.mostrarMensagem("Digite o novo valor do item:");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            item.setValorItem(valor);
            itensView.mostrarMensagem("Digite a nova categoria do item:");
            String categoria = scanner.nextLine();
            item.setCategoriaItem(categoria);
            itensView.mostrarMensagem("Digite a nova marca do item:");
            String marca = scanner.nextLine();
            item.setMarca(marca);

            String retorno = itensController.atualizarItem(item);
            itensView.mostrarMensagem(retorno);
        } else {
            itensView.mostrarMensagem("Item não encontrado!");

        }
    }

    private void excluirItem() {
        itensView.mostrarMensagem("Digite o ID do item a ser excluído:");
        int id = scanner.nextInt();
        scanner.nextLine();
        String retorno = itensController.excluirItem(id);
        itensView.mostrarMensagem(retorno);
    }

    private void buscarItem() {
        itensView.mostrarMensagem("Digite o ID do item a ser buscado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Itens item = itensController.buscarItem(id);
        if (item != null) {
            itensView.mostrarDetalhesItem(item);
        } else {
            itensView.mostrarMensagem("Item não encontrado!");
        }
    }

    public void gerenciarPratos() {
        int opcao;
        do {
            mostrarMenuPratos();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarPrato();
                    break;
                case 2:
                    listarPratos();
                    break;
                case 3:
                    atualizarPrato();
                    break;
                case 4:
                    excluirPrato();
                    break;
                case 5:
                    buscarPrato();
                    break;
                case 0:
                    pratoView.mostrarMensagem("Voltando ao menu principal...");
                    break;
                default:
                    pratoView.mostrarMensagem("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarPrato() {
        pratoView.mostrarMensagem("Digite o nome do prato:");
        String nome = scanner.nextLine();
        pratoView.mostrarMensagem("Digite a composição do prato:");
        String composicao = scanner.nextLine();
        pratoView.mostrarMensagem("Digite o preço do prato:");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        pratoView.mostrarMensagem("Digite a categoria:");
        String categoria = scanner.nextLine();
        pratoView.mostrarMensagem("Digite o tempo de preparo:");
        Integer tempoPreparo = scanner.nextInt();

        Prato novoPrato = new Prato(nome, composicao, preco, categoria, tempoPreparo);
        String retorno = pratoController.cadastrarPrato(novoPrato);
        pratoView.mostrarMensagem(retorno);
    }

    private void listarPratos() {
        pratoView.mostrarMensagem("=== Pratos Cadastrados ===");
        List<Prato> pratos = pratoController.listarPratos();
        pratoView.mostrarListaPratos(pratos);
        pratoView.mostrarMensagem("===========================");
    }

    private void atualizarPrato() {
        pratoView.mostrarMensagem("Digite o ID do prato a ser atualizado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Prato prato = pratoController.buscarPrato(id);
        if (prato != null) {
            pratoView.mostrarMensagem("Digite o novo nome do prato:");
            String nome = scanner.nextLine();
            prato.setNomePrato(nome);
            pratoView.mostrarMensagem("Digite a nova composição do prato:");
            String composicao = scanner.nextLine();
            prato.setComposicao(composicao);
            pratoView.mostrarMensagem("Digite o novo preço do prato:");
            double preco = scanner.nextDouble();
            scanner.nextLine(); // Consumir a nova linha após nextDouble()
            prato.setPreco(preco);

            String retorno = pratoController.atualizarPrato(prato);
            pratoView.mostrarMensagem(retorno);
        } else {
            pratoView.mostrarMensagem("Prato não encontrado!");
        }
    }

    private void excluirPrato() {
        pratoView.mostrarMensagem("Digite o ID do prato a ser excluído:");
        int id = scanner.nextInt();
        scanner.nextLine();
        String retorno = pratoController.excluirPrato(id);
        pratoView.mostrarMensagem(retorno);
    }

    private void buscarPrato() {
        pratoView.mostrarMensagem("Digite o ID do prato a ser buscado:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Prato prato = pratoController.buscarPrato(id);
        if (prato != null) {
            pratoView.mostrarDetalhesPrato(prato);
        } else {
            pratoView.mostrarMensagem("Prato não encontrado!");
        }
    }

    private void mostrarMenuPratos() {
        pratoView.mostrarMensagem("=== Menu Pratos ===");
        pratoView.mostrarMensagem("1. Cadastrar Prato");
        pratoView.mostrarMensagem("2. Listar Pratos");
        pratoView.mostrarMensagem("3. Atualizar Prato");
        pratoView.mostrarMensagem("4. Excluir Prato");
        pratoView.mostrarMensagem("5. Buscar Prato");
        pratoView.mostrarMensagem("0. Voltar ao Menu Principal");
        pratoView.mostrarMensagem("===========================");
        pratoView.mostrarMensagem("Escolha uma opção:");
    }

    private void gerenciarClientes() {
        int opcao;
        do {
            mostrarMenuClientes();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    excluirCliente();
                    break;
                case 5:
                    buscarCliente();
                    break;
                case 0:
                    clienteView.mostrarMensagem("Voltando ao menu principal...");
                    break;
                default:
                    clienteView.mostrarMensagem("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        scanner.nextLine();
        clienteView.mostrarMensagem("Digite o nome do cliente:");
        String nome = scanner.nextLine();
        clienteView.mostrarMensagem("Digite o sobrenome do cliente:");
        String sobrenome = scanner.nextLine();
        clienteView.mostrarMensagem("Digite o telefone do cliente:");
        String telefone = scanner.nextLine();
        clienteView.mostrarMensagem("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        clienteView.mostrarMensagem("Digite o endereço do cliente:");
        String endereco = scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, telefone, cpf, sobrenome, endereco);
        String retorno = clienteController.cadastrarCliente(novoCliente);
        clienteView.mostrarMensagem(retorno);
    }

    private void listarClientes() {
        clienteView.mostrarMensagem("=== Clientes Cadastrados ===");
        List<Cliente> clientes = clienteController.listarClientes();
        clienteView.mostrarListaClientes(clientes);
        clienteView.mostrarMensagem("===========================");
    }

    private void atualizarCliente() {
        clienteView.mostrarMensagem("Digite o ID do cliente a ser atualizado:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.buscarCliente(id);
        if (cliente != null) {
            scanner.nextLine();
            clienteView.mostrarMensagem("Digite o novo nome do cliente:");
            String nome = scanner.nextLine();
            cliente.setNomeCliente(nome);
            clienteView.mostrarMensagem("Digite o novo nome do cliente:");
            String sobrenome = scanner.nextLine();
            cliente.setSobrenome(sobrenome);
            clienteView.mostrarMensagem("Digite o novo telefone do cliente:");
            String telefone = scanner.nextLine();
            cliente.setTelefone(telefone);
            clienteView.mostrarMensagem("Digite o novo CPF do cliente:");
            String cpf = scanner.nextLine();
            cliente.setCpf(cpf);
            clienteView.mostrarMensagem("Digite o novo endereço do cliente:");
            String endereco = scanner.nextLine();
            cliente.setEndereco(endereco);

            String retorno = clienteController.atualizarCliente(cliente);
            clienteView.mostrarMensagem(retorno);
        } else {
            clienteView.mostrarMensagem("Cliente não encontrado!");
        }
    }

    private void excluirCliente() {
        clienteView.mostrarMensagem("Digite o ID do cliente a ser excluído:");
        int id = scanner.nextInt();
        String retorno = clienteController.excluirCliente(id);
        clienteView.mostrarMensagem(retorno);
    }

    private void buscarCliente() {
        clienteView.mostrarMensagem("Digite o ID do cliente a ser buscado:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.buscarCliente(id);
        if (cliente != null) {
            clienteView.mostrarDetalhesCliente(cliente);
        } else {
            clienteView.mostrarMensagem("Cliente não encontrado!");
        }
    }

    private void mostrarMenuClientes() {
        clienteView.mostrarMensagem("=== Menu Clientes ===");
        clienteView.mostrarMensagem("1. Cadastrar Cliente");
        clienteView.mostrarMensagem("2. Listar Clientes");
        clienteView.mostrarMensagem("3. Atualizar Cliente");
        clienteView.mostrarMensagem("4. Excluir Cliente");
        clienteView.mostrarMensagem("5. Buscar Cliente");
        clienteView.mostrarMensagem("0. Voltar ao Menu Principal");
        clienteView.mostrarMensagem("===========================");
        clienteView.mostrarMensagem("Escolha uma opção:");
    }

    private void mostrarMenuFuncionarios() {
        funcionarioView.mostrarMensagem("=== Menu Funcionários ===");
        funcionarioView.mostrarMensagem("1. Cadastrar Funcionário");
        funcionarioView.mostrarMensagem("2. Listar Funcionários");
        funcionarioView.mostrarMensagem("3. Atualizar Funcionário");
        funcionarioView.mostrarMensagem("4. Excluir Funcionário");
        funcionarioView.mostrarMensagem("5. Buscar Funcionário");
        funcionarioView.mostrarMensagem("0. Voltar ao Menu Principal");
        funcionarioView.mostrarMensagem("===========================");
        funcionarioView.mostrarMensagem("Escolha uma opção:");

    }

    private void gerenciarFuncionarios() {
        int opcao;
        do {
            mostrarMenuFuncionarios();
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    atualizarFuncionario();
                    break;
                case 4:
                    excluirFuncionario();
                    break;
                case 5:
                    buscarFuncionario();
                    break;
                case 0:
                    funcionarioView.mostrarMensagem("Voltando ao menu principal...");
                    break;
                default:
                    funcionarioView.mostrarMensagem("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarFuncionario() {
        scanner.nextLine();
        funcionarioView.mostrarMensagem("Digite o nome do funcionário:");
        String nome = scanner.nextLine();
        funcionarioView.mostrarMensagem("Digite o nome do funcionário:");
        String sobrenome = scanner.nextLine();
        funcionarioView.mostrarMensagem("Digite o telefone do funcionário:");
        String telefone = scanner.nextLine();
        funcionarioView.mostrarMensagem("Digite o CPF do funcionário:");
        String cpf = scanner.nextLine();
        funcionarioView.mostrarMensagem("Digite o cargo do funcionário:");
        String cargo = scanner.nextLine();
        funcionarioView.mostrarMensagem("Digite a data de nascimento do funcionário (YYYY-MM-DD):");
        String dataNascimentoStr = scanner.nextLine();
        // Parse dataNascimentoStr to LocalDate, future implementation
        Funcionario novoFuncionario = new Funcionario(nome, telefone, cpf, cargo, null, sobrenome);
        String retorno = funcionarioController.cadastrarFuncionario(novoFuncionario);
        funcionarioView.mostrarMensagem(retorno);
    }

    private void listarFuncionarios() {
        funcionarioView.mostrarMensagem("=== Funcionários Cadastrados ===");
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        funcionarioView.mostrarListaFuncionarios(funcionarios);
        funcionarioView.mostrarMensagem("===========================");
    }

    private void atualizarFuncionario() {
        funcionarioView.mostrarMensagem("Digite o ID do funcionário a ser atualizado:");
        int id = scanner.nextInt();
        Funcionario funcionario = funcionarioController.buscarFuncionario(id);
        if (funcionario != null) {
            scanner.nextLine();
            funcionarioView.mostrarMensagem("Digite o novo nome do funcionário:");
            String nome = scanner.nextLine();
            funcionario.setNomeFuncionario(nome);
            funcionarioView.mostrarMensagem("Digite o novo sobrenome do funcionário:");
            String sobrenome = scanner.nextLine();
            funcionario.setNomeFuncionario(sobrenome);
            funcionarioView.mostrarMensagem("Digite o novo telefone do funcionário:");
            String telefone = scanner.nextLine();
            funcionario.setTelefone(telefone);
            funcionarioView.mostrarMensagem("Digite o novo CPF do funcionário:");
            String cpf = scanner.nextLine();
            funcionario.setCpf(cpf);
            funcionarioView.mostrarMensagem("Digite o novo cargo do funcionário:");
            String cargo = scanner.nextLine();
            funcionario.setCargo(cargo);
            funcionarioView.mostrarMensagem("Digite a nova data de nascimento do funcionário (YYYY-MM-DD):");
            String dataNascimentoStr = scanner.nextLine();
            String retorno = funcionarioController.atualizarFuncionario(funcionario);
            funcionarioView.mostrarMensagem(retorno);
        } else {
            funcionarioView.mostrarMensagem("Funcionário não encontrado!");
        }
    }

    private void excluirFuncionario() {
        funcionarioView.mostrarMensagem("Digite o ID do funcionário a ser excluído:");
        int id = scanner.nextInt();
        String retorno = funcionarioController.excluirFuncionario(id);
        funcionarioView.mostrarMensagem(retorno);
    }

    private void buscarFuncionario() {
        funcionarioView.mostrarMensagem("Digite o ID do funcionário a ser buscado");
    }

}