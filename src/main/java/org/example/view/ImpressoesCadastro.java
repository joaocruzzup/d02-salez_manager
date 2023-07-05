package org.example.view;

import org.example.Exceptions.*;
import org.example.controller.ClienteController;
import org.example.controller.VendaController;
import org.example.controller.VendedorController;
import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ImpressoesCadastro {
    public static void imprimirCadastroCliente(ClienteController clienteController) {
        Scanner sc = new Scanner(System.in);

        boolean clienteCadastrado = false;

        while (!clienteCadastrado) {
            System.out.println("Digite o nome do cliente: ");
            String nome = sc.nextLine();

            System.out.println("Digite o CPF do cliente: ");
            String cpf = sc.nextLine();

            System.out.println("Digite o E-mail do cliente:");
            String email = sc.nextLine();

            Cliente cliente = new Cliente(nome, cpf, email);

            try {
                clienteController.cadastrar(cliente);
                clienteCadastrado = true;
                System.out.println("Cliente cadastrado com sucesso!");
            } catch (CpfJaExistenteException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            } catch (EmailRepetidoException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            } catch (CpfInvalidoException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            }
        }
    }

    public static void imprimirCadastroVendedor(VendedorController vendedorController) {
        Scanner sc = new Scanner(System.in);

        boolean vendedorCadastrado = false;

        while (!vendedorCadastrado) {
            System.out.println("Digite o nome do Vendedor: ");
            String nome = sc.nextLine();

            System.out.println("Digite o CPF do Vendedor: ");
            String cpf = sc.nextLine();

            System.out.println("Digite o E-mail do Vendedor:");
            String email = sc.nextLine();

            Vendedor vendedor = new Vendedor(nome, cpf, email);

            try {
                vendedorController.cadastrar(vendedor);
                vendedorCadastrado = true;
                System.out.println("Vendedor cadastrado com sucesso!");
            } catch (CpfJaExistenteException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            } catch (EmailRepetidoException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            } catch (CpfInvalidoException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            }
        }
    }

    public static void imprimirCadastroVenda(VendaController vendaController, ClienteController clienteController, VendedorController vendedorController) throws UsuarioNaoCadastradoException {
        Scanner sc = new Scanner(System.in);

        boolean vendaCadastrada = false;

        while (!vendaCadastrada) {
            System.out.println("Digite o nome do Produto: ");
            String nome = sc.nextLine();

            System.out.println("Digite o preço do Produto: ");
            BigDecimal preco = sc.nextBigDecimal();
            sc.nextLine();

            System.out.println("Digite a data da venda do Produto: no formato (dd/MM/yyyy)");
            String dataEmString = sc.nextLine();
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataEmString, formatador);

            System.out.println("Digite o CPF do Vendedor Responsável: ");
            String cpfVendedor = sc.nextLine();

            System.out.println("Digite o CPF do Cliente Responsável: ");
            String cpfCliente = sc.nextLine();

            Cliente cliente = (Cliente) clienteController.buscaUsuario(cpfCliente);
            Vendedor vendedor = (Vendedor) vendedorController.buscaUsuario(cpfVendedor);
            Venda venda = new Venda(cliente, vendedor, preco, data, nome);

            try {
                vendaController.cadastrar(venda, vendedor, cliente);
                vendaCadastrada = true;
                System.out.println("Venda cadastrado com sucesso!");
            } catch (CpfJaExistenteException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            } catch (UsuarioNaoCadastradoException e) {
                System.out.println(e.getMessage() + "\nTente Cadastrar novamente com os dados corretos.");
            }
        }
    }



}
