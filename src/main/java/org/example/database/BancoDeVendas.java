package org.example.database;

import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class BancoDeVendas {
    BancoDeClientes bancoDeClientes = new BancoDeClientes();
    BancoDeVendedores bancoDeVendedores = new BancoDeVendedores();
    private List<Venda> listaVendas = new ArrayList<>();

    public void cadastrarVenda(Venda venda, Vendedor vendedor, Cliente cliente){
        if (bancoDeClientes.verificarClienteExiste(cliente.getCpf()) && bancoDeVendedores.verificarVendedorExiste(vendedor.getCpf())) {
            venda.setCliente(cliente);
            venda.setVendedor(vendedor);
            listaVendas.add(venda);
        } else {
            throw new IllegalArgumentException("Erro: Vendedor ou Cliente não estão cadastrados no sistema");
        }
    }

    public List<Venda> getListaVendas() {
        return listaVendas;
    }
}
