package org.example.service;

import org.example.Exceptions.CpfInvalidoException;
import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.model.Usuario;

public interface Cadastro {
    public abstract void cadastrar(Usuario usuario) throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException;
    public abstract void listar();
}
