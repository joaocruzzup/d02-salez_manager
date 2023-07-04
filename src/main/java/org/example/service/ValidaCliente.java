package org.example.service;


import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.model.Cliente;

public interface ValidaCliente {
    public abstract boolean validarEmailCliente(Cliente cliente) throws EmailInvalidoException;
    public abstract boolean validarEmailRepetidoCliente(Cliente cliente) throws EmailRepetidoException;
    public abstract boolean validarCpfExisteCliente(Cliente cliente) throws CpfJaExistenteException;
}
