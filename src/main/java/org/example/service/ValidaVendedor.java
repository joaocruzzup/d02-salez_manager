package org.example.service;

import org.example.Exceptions.CpfRepetidoException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;

public interface ValidaVendedor {
    public abstract boolean validarEmailVendedor(Vendedor vendedor) throws EmailInvalidoException;
    public abstract boolean validarEmailRepetidoVendedor(Vendedor vendedor) throws EmailRepetidoException;
    public abstract boolean validarCpfRepetidoVendedor(Vendedor vendedor) throws CpfRepetidoException;
}
