package org.example.database;

import org.example.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class BancoDeClientes {
    private List<Cliente> listaClientes = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente){
        if (verificarClienteExiste(cliente.getCpf())){
            listaClientes.add(cliente);
        } else {
            throw new IllegalArgumentException("Erro: Cliente não cadastrado");
        }
    }

    public boolean verificarClienteExiste(String cpf){
        for (Cliente cliente: listaClientes) {
            if (cliente.getCpf().equalsIgnoreCase(cpf)){
                return true;
            }
        }
        return false;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
