package org.example.view;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
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
            switch (opcao) {
                case 1:
                    boolean repetirMenuCliente = true;
                    while (repetirMenuCliente) {
                        imprimirMenuCliente();
                        int opcaoCliente = selecionarOpcao(4);
                        if (opcaoCliente == 1) {
                            imprimirCadastroCliente(clienteController);
                            sc.nextLine();
                        } else if (opcaoCliente == 2) {
                            clienteController.listar();
                            sc.nextLine();
                        } else if (opcaoCliente == 3) {
                            System.out.println("Digite o CPF que deseja buscar as vendas: ");
                            System.out.print("Digite aqui: ");
                            String cpf = sc.nextLine();
                            clienteController.pesquisarVendas(cpf);
                            sc.nextLine();
                        } else if (opcaoCliente == 4) {
                            repetirMenuCliente = false;
                        }
                    }
                    break;
                case 2:
                    boolean repetirMenuVendedor = true;
                    while (repetirMenuVendedor) {
                        imprimirMenuVendedor();
                        int opcaoVendedor = selecionarOpcao(4);
                        if (opcaoVendedor == 1) {
                            imprimirCadastroVendedor(vendedorController);
                            sc.nextLine();
                        } else if (opcaoVendedor == 2) {
                            vendaController.listar();
                            sc.nextLine();
                        } else if (opcaoVendedor == 3) {
                            System.out.println("Digite o EMAIL que deseja buscar as vendas: ");
                            System.out.print("Digite aqui: ");
                            String cpf = sc.nextLine();
                            vendedorController.pesquisarVendas(cpf);
                            sc.nextLine();
                        } else if (opcaoVendedor == 4) {
                            repetirMenuVendedor = false;
                        }
                    }
                    break;
                case 3:
                    boolean repetirMenuVendas = true;
                    while (repetirMenuVendas) {
                        imprimirMenuVendas();
                        int opcaoVendas = selecionarOpcao(3);
                        if (opcaoVendas == 1) {
                            imprimirCadastroVenda(vendaController, clienteController, vendedorController);
                            sc.nextLine();
                        } else if (opcaoVendas == 2) {
                            vendaController.listar();
                            sc.nextLine();
                        } else if (opcaoVendas == 3) {
                            repetirMenuVendas = false;
                        }
                    }
                    break;
                case 4:
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
