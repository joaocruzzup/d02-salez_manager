package org.example.database;

import org.example.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class BancoDeClientes {
    private List<Cliente> listaClientes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente){
        listaClientes.add(cliente);
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
