package org.example.database;

import org.example.model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class BancoDeVendedores {
    private List<Vendedor> listaVendedores = new ArrayList<>();

    public boolean cadastrarVendedor(Vendedor vendedor){
        listaVendedores.add(vendedor);
        return true;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }
}
