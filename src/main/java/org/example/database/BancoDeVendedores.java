package org.example.database;

import org.example.model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class BancoDeVendedores {
    private List<Vendedor> listaVendedores = new ArrayList<>();

    public void cadastrarVendedor(Vendedor vendedor){
        if (verificarVendedorExiste(vendedor.getCpf())){
            listaVendedores.add(vendedor);
        } else {
            throw new IllegalArgumentException("Erro: vendedor não existe");
        }
    }

    public boolean verificarVendedorExiste(String cpf){
        for (Vendedor vendedor: listaVendedores) {
            if (vendedor.getCpf().equalsIgnoreCase(cpf)){
                return true;
            }
        }
        return false;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }
}
