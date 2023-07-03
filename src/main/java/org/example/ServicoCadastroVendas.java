package org.example;

import java.util.List;

public class ServicoCadastroVendas {
    private Cadastro cadastro;

    public ServicoCadastroVendas(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public void cadastrar(Object obj){
        cadastro.cadastrar(obj);
    }

    public void listar(List<Object> lista){
        cadastro.listar(lista);
    }
}
