package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoDeVendas {
    private List<Vendedor> listaVendedores = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private Map<String, List<Cliente>> vendedoresClientes = new HashMap<>() ;

    public void cadastrarCliente(Vendedor vendedor, Cliente cliente){
        listaClientes.add(cliente);
        if (!vendedoresClientes.containsKey(vendedor.getCpf())){
            // Refatorar futuramente esse código para chamar o método cadastrarVendedor
            List<Cliente> listaClienteDoVendedor = new ArrayList<>();
            listaClienteDoVendedor.add(cliente);
            vendedoresClientes.put(vendedor.getCpf(), listaClienteDoVendedor);
        }
        vendedoresClientes.get(vendedor.getCpf()).add(cliente);
    }

    public void cadastrarVendedor(Vendedor vendedor){
        listaVendedores.add(vendedor);
        vendedoresClientes.put(vendedor.getCpf(), new ArrayList<>());
    }

}
