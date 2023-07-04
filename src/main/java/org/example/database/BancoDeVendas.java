package org.example.database;

import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class BancoDeVendas {
    private List<Venda> listaVendas = new ArrayList<>();

    public void cadastrarVenda(Venda venda, Vendedor vendedor, Cliente cliente){
        venda.setCliente(cliente);
        venda.setVendedor(vendedor);
        listaVendas.add(venda);
    }

    public List<Venda> getListaVendas() {
        return listaVendas;
    }
}
