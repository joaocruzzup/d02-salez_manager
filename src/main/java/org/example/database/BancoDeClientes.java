package org.example.database;

import org.example.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class BancoDeClientes {
    private List<Cliente> listaClientes = new ArrayList<>();

    public boolean cadastrarCliente(Cliente cliente){
        listaClientes.add(cliente);
        return true;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

}
