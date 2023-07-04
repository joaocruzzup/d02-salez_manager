package org.example.service;

import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendas;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.model.Usuario;
import org.example.model.Venda;
import org.example.model.Vendedor;

public class VendasService {
    private BancoDeVendedores bancoDeVendedores = new BancoDeVendedores();
    private BancoDeClientes bancoDeClientes = new BancoDeClientes();
    private BancoDeVendas bancoDeVendas = new BancoDeVendas();

    public void cadastrarVenda(Venda venda, Vendedor vendedor, Cliente cliente) {
        if ((bancoDeClientes.verificarClienteExiste(cliente.getCpf()) && bancoDeVendedores.verificarVendedorExiste(vendedor.getCpf()))) {
            bancoDeVendas.cadastrarVenda(venda, vendedor, cliente);
        } else {
            throw new IllegalArgumentException("Erro: Vendedor ou Cliente não cadastrado");
        }
    }

    public void cadastrarCliente(Usuario usuario) {
        if (bancoDeClientes.verificarClienteExiste(usuario.getCpf())){
            throw new IllegalArgumentException("Erro: O cliente já existe");
        }
        bancoDeClientes.cadastrarCliente((Cliente) usuario);
    }

    public void cadastrarVendedor(Usuario usuario) {
        if (bancoDeVendedores.verificarVendedorExiste(usuario.getCpf())){
            throw new IllegalArgumentException("Erro: O Vendedor já existe");
        }
        bancoDeVendedores.cadastrarVendedor((Vendedor) usuario);
    }

    public void listarVendas() {
        System.out.println(bancoDeVendas.getListaVendas());
    }

    public void listarClientes() {
        for (Cliente cliente: bancoDeClientes.getListaClientes()) {
            System.out.println(
                    "NOME: " + cliente.getNome() +
                            "CPF: " + cliente.getCpf() +
                            "EMAIL: " + cliente.getEmail());
        }
    }

    public void listarVendedores() {
        for (Vendedor vendedor: bancoDeVendedores.getListaVendedores()) {
            System.out.println(
                    "NOME: " + vendedor.getNome() +
                            "CPF: " + vendedor.getCpf() +
                            "EMAIL: " + vendedor.getEmail());
        }
    }

}
