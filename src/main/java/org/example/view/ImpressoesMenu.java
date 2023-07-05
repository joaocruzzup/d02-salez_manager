package org.example.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ImpressoesMenu {
    public static void imprimirTelaInicial(){
        String esp = " ";
        String texto1 = "SaleZ Manager";
        String texto2 = "O gerenciador de vendas ";
        String texto3 = "feito para você!";
        String texto4 = "Pressione * ENTER * para entrar!";

        System.out.println(" ------------------------------------ ");
        System.out.printf("| %23s %10s |%n| %34s |%n| %28s %5s |%n| %24s %9s |%n| %34s |%n|"
                , texto1, esp, esp, texto2, esp, texto3, esp, esp);
        System.out.printf("   %13s |%n| %34s |%n ", texto4, esp);
        System.out.println("------------------------------------");
    }

    public static void imprimirMenuPrincipal(){
        System.out.println("----------- Menu Principal -----------");
        System.out.println(
                "1. Acessar seção Clientes\n" +
                "2. Acessar seção Vendedores\n"+
                "3. Acessar seção Vendas\n" +
                "4. Sair do programa");
    }

    public static void imprimirMenuCliente(){
        System.out.println("----------- Menu Clientes -----------");
        System.out.println(
                "1. Cadastrar Cliente\n" +
                "2. Listar Clientes Cadastrados\n" +
                "3. Pesquisar Compras de Cliente específico pelo CPF\n" +
                "4. Voltar ao Menu Principal");
    }

    public static void imprimirMenuVendedor(){
        System.out.println("----------- Menu Vendedores -----------");
        System.out.println(
                "1. Cadastrar Vendedor\n" +
                "2. Listar Vendedores Cadastrados\n" +
                "3. Pesquisar Vendedor Específico pelo E-mail\n" +
                "4. Voltar ao Menu Principal");
    }

    public static void imprimirMenuVendas(){
        System.out.println("----------- Menu Vendas -----------");
        System.out.println(
                "1. Cadastrar Venda\n" +
                "2. Listar Vendas Cadastradas\n" +
                "3. Voltar ao Menu principal");
    }

    public static int selecionarOpcao(int quantidadeOpcoes){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        try {
            System.out.print("\nDigite a opção que você deseja: ");
            opcao = sc.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Erro: Tipo de entrada inválida! Digite um número");
            sc.nextLine();
        } finally {
            if (opcao <=0 || opcao > quantidadeOpcoes){
                System.out.print("Opção inválida! (Digite um número de 1 a " + quantidadeOpcoes + ")\nDigite a opção que você deseja: ");
                opcao = sc.nextInt();
            }
        }
        return opcao;
    }

    public static void limparConsole(){
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static void exibirEnter(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite * ENTER * para retornar ao Menu");
        sc.nextLine();
        limparConsole();
    }
}
