package org.example.service;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.model.Usuario;

public interface Cadastro {
    public abstract void cadastrar(Usuario usuario) throws CpfJaExistenteException;
    public abstract void listar();
}
