package org.example.view;
import org.example.controller.ClienteController;
import org.example.controller.VendaController;
import org.example.controller.VendedorController;
import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;
import org.example.service.VendaService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Criação dos controladores
        VendaService vendaService = new VendaService();

        ClienteController clienteController = new ClienteController(vendaService);
        VendedorController vendedorController = new VendedorController(vendaService);
        VendaController vendaController = new VendaController(vendaService);

        // Cadastro de clientes
        Cliente cliente1 = new Cliente("João", "12345678901", "joao@example.com");
        Cliente cliente2 = new Cliente("Maria", "98765432101", "maria@example.com");
        clienteController.cadastrar(cliente1);
        clienteController.cadastrar(cliente2);

        // Cadastro de vendedores
        Vendedor vendedor1 = new Vendedor("Pedro", "11111111111", "pedro@example.com");
        Vendedor vendedor2 = new Vendedor("Ana", "22222222222", "ana@example.com");
        vendedorController.cadastrar(vendedor1);
        vendedorController.cadastrar(vendedor2);

        // Cadastro de vendas
        Venda venda1 = new Venda(cliente1, vendedor1, BigDecimal.valueOf(100.0), LocalDate.now());
        Venda venda2 = new Venda(cliente2, vendedor2, BigDecimal.valueOf(200.0), LocalDate.now());
        vendaController.cadastrar(venda1, vendedor1, cliente1);
        vendaController.cadastrar(venda2, vendedor2, cliente2);

        // Listagem de vendas, vendedores e clientes
        System.out.println("Vendas cadastradas:");
        vendaController.listar();

        System.out.println("\nVendedores cadastrados:");
        vendedorController.listar();

        System.out.println("\nClientes cadastrados:");
        clienteController.listar();

    }
}
