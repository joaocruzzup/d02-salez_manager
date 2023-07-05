package org.example.view;

import org.example.Exceptions.UsuarioNaoCadastradoException;
import org.example.controller.ClienteController;
import org.example.controller.VendaController;
import org.example.controller.VendedorController;
import org.example.service.VendaService;

import java.util.Scanner;

import static org.example.view.ImpressoesCadastro.*;
import static org.example.view.ImpressoesMenu.*;

public class Main {

    public static void main(String[] args) throws UsuarioNaoCadastradoException{
        Scanner sc = new Scanner(System.in);

        VendaService vendaService = new VendaService();

        ClienteController clienteController = new ClienteController(vendaService);
        VendedorController vendedorController = new VendedorController(vendaService);
        VendaController vendaController = new VendaController(vendaService);

        imprimirTelaInicial();
        sc.nextLine();
        limparConsole();

        boolean repetir = true;
        while (repetir) {
            imprimirMenuPrincipal();
            int opcao = selecionarOpcao(4);
            limparConsole();
            switch (opcao) {
                case 1:
                    // Menu clientes
                    boolean repetirMenuCliente = true;
                    while (repetirMenuCliente) {
                        imprimirMenuCliente();
                        int opcaoCliente = selecionarOpcao(4);
                        limparConsole();
                        if (opcaoCliente == 1) {
                            imprimirCadastroCliente(clienteController);
                            exibirEnter();
                        } else if (opcaoCliente == 2) {
                            clienteController.listar();
                            exibirEnter();
                        } else if (opcaoCliente == 3) {
                            System.out.println("Digite o CPF que deseja buscar as vendas: ");
                            System.out.print("Digite aqui: ");
                            String cpf = sc.nextLine();
                            limparConsole();
                            clienteController.pesquisarVendas(cpf);
                            exibirEnter();
                        } else if (opcaoCliente == 4) {
                            repetirMenuCliente = false;
                        }
                    }
                    break;
                case 2:
                    // Menu Vendedores
                    boolean repetirMenuVendedor = true;
                    while (repetirMenuVendedor) {
                        imprimirMenuVendedor();
                        int opcaoVendedor = selecionarOpcao(4);
                        limparConsole();
                        if (opcaoVendedor == 1) {
                            imprimirCadastroVendedor(vendedorController);
                            exibirEnter();
                        } else if (opcaoVendedor == 2) {
                            vendedorController.listar();
                            exibirEnter();
                        } else if (opcaoVendedor == 3) {
                            System.out.println("Digite o EMAIL que deseja buscar as vendas: ");
                            System.out.print("Digite aqui: ");
                            String cpf = sc.nextLine();
                            limparConsole();
                            vendedorController.pesquisarVendas(cpf);
                            exibirEnter();
                        } else if (opcaoVendedor == 4) {
                            repetirMenuVendedor = false;
                        }
                    }
                    break;
                case 3:
                    // Menu vendas
                    boolean repetirMenuVendas = true;
                    while (repetirMenuVendas) {
                        imprimirMenuVendas();
                        int opcaoVendas = selecionarOpcao(3);
                        limparConsole();
                        if (opcaoVendas == 1) {
                            imprimirCadastroVenda(vendaController, clienteController, vendedorController);
                            exibirEnter();
                        } else if (opcaoVendas == 2) {
                            vendaController.listar();
                            exibirEnter();
                        } else if (opcaoVendas == 3) {
                            repetirMenuVendas = false;
                        }
                    }
                    break;
                case 4:
                    // Opção sair
                    System.exit(1);
                    sc.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
