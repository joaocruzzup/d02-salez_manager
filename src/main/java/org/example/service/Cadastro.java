package org.example.service;

import org.example.model.Usuario;

import java.util.List;

public interface Cadastro {
    public abstract void cadastrar(Usuario usuario);
    public abstract void listar(List<Usuario> usuario);
}
