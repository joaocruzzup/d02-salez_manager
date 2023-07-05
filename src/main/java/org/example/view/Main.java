package org.example.view;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.Exceptions.UsuarioNaoCadastradoException;
import org.example.controller.ClienteController;
import org.example.controller.VendaController;
import org.example.controller.VendedorController;
import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;
import org.example.service.VendaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws CpfJaExistenteException, UsuarioNaoCadastradoException, EmailRepetidoException, EmailInvalidoException {
        // Criação dos controladores
        VendaService vendaService = new VendaService();

        ClienteController clienteController = new ClienteController(vendaService);
        VendedorController vendedorController = new VendedorController(vendaService);
        VendaController vendaController = new VendaController(vendaService);

        List<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente1 = new Cliente("João", "12345678901", "joao@example.com");
        Cliente cliente2 = new Cliente("João", "12345678951", "joaov@example.com");
        Cliente cliente3 = new Cliente("João", "12345678951", "joaocruz@example.com"); // Cadastro cliente com CPF repetido
        Cliente cliente4 = new Cliente("João", "12345678952", "joaocruz@example.com"); // Cadastro cliente com e-mail repetido
        Cliente cliente5 = new Cliente("João", "12345678451", "joaovexample.com");    // Cadastro cliente com e-mail inválido
        Cliente cliente6 = new Cliente("João", "14454555555", "joaocruz@example.com"); // Cliente que não será cadastrado
        Cliente cliente7 = new Cliente("João", "14454555555", "joaovaa@example.com"); // Cliente que não será cadastrado
        listaClientes.add(cliente1);
        listaClientes.add(cliente2);
        listaClientes.add(cliente3);
        listaClientes.add(cliente4);
        listaClientes.add(cliente5);
        listaClientes.add(cliente6);


        for (Cliente cliente : listaClientes) {
            try {
                clienteController.cadastrar(cliente);
            } catch (CpfJaExistenteException e) {
                System.out.println(e.getMessage());
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (EmailRepetidoException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Vendedor> listaVendedores = new ArrayList<>();
        Vendedor vendedor1 = new Vendedor("João", "12345678901", "joao@example.com");
        Vendedor vendedor2 = new Vendedor("João", "12345678951", "joaov@example.com");
        Vendedor vendedor3 = new Vendedor("João", "12345678951", "joaocruz@example.com"); // Cadastro cliente com CPF repetido
        Vendedor vendedor4 = new Vendedor("João", "12345678952", "joaocruz@example.com"); // Cadastro cliente com e-mail repetido
        Vendedor vendedor5 = new Vendedor("João", "12345678451", "joaovexample.com");    // Cadastro cliente com e-mail inválido
        Vendedor vendedor6 = new Vendedor("João", "14454555555", "joaocruz@example.com"); // Cliente que não será cadastrado
        Vendedor vendedor7 = new Vendedor("João", "14454555557", "joaovaa@example.com"); // Cliente que não será cadastrado
        listaVendedores.add(vendedor1);
        listaVendedores.add(vendedor2);
        listaVendedores.add(vendedor3);
        listaVendedores.add(vendedor4);
        listaVendedores.add(vendedor5);
        listaVendedores.add(vendedor6);

        for (Vendedor vendedor : listaVendedores) {
            try {
                vendedorController.cadastrar(vendedor);
            } catch (CpfJaExistenteException e) {
                System.out.println(e.getMessage());
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (EmailRepetidoException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Venda> listaVendas = new ArrayList<>();
        Venda venda1 = new Venda(cliente1, vendedor1, BigDecimal.valueOf(100.0), LocalDate.now());
        Venda venda2 = new Venda(cliente2, vendedor2, BigDecimal.valueOf(200.0), LocalDate.now());
        Venda venda3 = new Venda(cliente3, vendedor3, BigDecimal.valueOf(200.0), LocalDate.now());
        Venda venda4 = new Venda(cliente4, vendedor4, BigDecimal.valueOf(200.0), LocalDate.now());
        Venda venda5 = new Venda(cliente5, vendedor5, BigDecimal.valueOf(200.0), LocalDate.now());
        Venda venda6 = new Venda(cliente6, vendedor6, BigDecimal.valueOf(200.0), LocalDate.now());
        listaVendas.add(venda1);
        listaVendas.add(venda2);
        listaVendas.add(venda3);
        listaVendas.add(venda4);
        listaVendas.add(venda5);
        listaVendas.add(venda6);

        int i = 0;
        for (Venda venda : listaVendas) {
            try {
                vendaController.cadastrar(venda, listaVendedores.get(i), listaClientes.get(i));
            } catch (CpfJaExistenteException e) {
                System.out.println(e.getMessage());
            } catch (UsuarioNaoCadastradoException e) {
                System.out.println(e.getMessage());
            } finally {
                i++;
            }
        }

        // Listagem de vendas, vendedores e clientes
        System.out.println("Vendas cadastradas:");
        vendaController.listar();

        System.out.println("\nVendedores cadastrados:");
        vendedorController.listar();

        System.out.println("\nClientes cadastrados:");
        clienteController.listar();

        System.out.println("\nLista Compras de Cliente");
        clienteController.pesquisarVendas("12345678901");

        System.out.println("\nLista Vendas do vendedor");
        vendedorController.pesquisarVendas("joao@example.com");


    }
}
