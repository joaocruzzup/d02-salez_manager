package org.example.service;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.model.Cliente;
import org.example.model.Usuario;

public interface ValidaUsuario {
    public abstract boolean validarEmail(Usuario usuario) throws EmailInvalidoException;
    public abstract boolean validarEmailRepetido(Usuario usuario) throws EmailRepetidoException;
    public abstract boolean validarCpfExiste(Usuario usuario) throws CpfJaExistenteException;
}
