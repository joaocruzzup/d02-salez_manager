package org.example.database;

import org.example.model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class BancoDeVendedores {
    private List<Vendedor> listaVendedores = new ArrayList<>();

    public void cadastrarVendedor(Vendedor vendedor){
        listaVendedores.add(vendedor);
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }
}
