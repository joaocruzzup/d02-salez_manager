package org.example.view;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Impressoes {
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
        System.out.println("----- Menu Principal -----");
        System.out.println(
                "1. Seção Clientes" +
                "2. Seção Vendedores "+
                "3. Seção Vendas" +
                "4. Sair");
    }

    public static void imprimirMenuCliente(){
        System.out.println("----- Menu Clientes -----");
        System.out.println(
                "1. Cadastrar Cliente" +
                "2. Listar Clientes Cadastrados" +
                "3. Pesquisar Compras de Cliente específico");
    }

    public static void imprimirMenuVendedor(){
        System.out.println("----- Menu Vendedores -----");
        System.out.println(
                "1. Cadastrar Vendedor" +
                "2. Listar Vendedores Cadastrados" +
                "3. Pesquisar Vendedor Específico");
    }

    public static void imprimirMenuVendas(){
        System.out.println("----- Menu Vendas -----");
        System.out.println(
                "1. Cadastrar Venda" +
                "2. Listar Vendas Cadastrados");
    }

    // Para esse metodo funcionar melhor, colocar num laço de repetição que irá repetir enquanto a opção for 0
    public static int selecionarOpcao(){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        try {
            System.out.print("Digite a opção: ");
            opcao = sc.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Erro: " + e.getMessage());
        }
        return opcao;
    }
}
