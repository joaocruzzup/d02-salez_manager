package org.example.service;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.model.Vendedor;

public interface ValidaVendedor {
    public abstract boolean validarEmailVendedor(Vendedor vendedor) throws EmailInvalidoException;
    public abstract boolean validarEmailRepetidoVendedor(Vendedor vendedor) throws EmailRepetidoException;
    public abstract boolean validarCpfExisteVendedor(Vendedor vendedor) throws CpfJaExistenteException;
}
