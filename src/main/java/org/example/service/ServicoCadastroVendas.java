package org.example.service;

import org.example.model.Usuario;

import java.util.List;

public class ServicoCadastroVendas {
    private Cadastro cadastro;

    public ServicoCadastroVendas(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public void cadastrar(Usuario usuario){
        cadastro.cadastrar(usuario);
    }

    public void listar(List<Usuario> lista){
        cadastro.listar(lista);
    }
}
