package org.example.service;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.model.Usuario;
import org.example.model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class VendaValidador implements ValidaCliente, ValidaVendedor{
    private BancoDeClientes bancoDeClientes;
    private BancoDeVendedores bancoDeVendedores;

    public VendaValidador(BancoDeClientes bancoDeClientes, BancoDeVendedores bancoDeVendedores) {
        this.bancoDeClientes = bancoDeClientes;
        this.bancoDeVendedores = bancoDeVendedores;
    }

    @Override
    public boolean validarEmailVendedor(Vendedor vendedorDigitado) throws EmailInvalidoException {
        if (!vendedorDigitado.getEmail().contains("@")){
            throw new EmailInvalidoException("Erro: E-mail não contém @");
        }
        return true;
    }

    @Override
    public boolean validarEmailRepetidoVendedor(Vendedor vendedorDigitado) throws EmailRepetidoException {
        for (Vendedor vendedor: bancoDeVendedores.getListaVendedores()) {
            if (vendedor.getEmail().equalsIgnoreCase(vendedorDigitado.getEmail())){
                throw new EmailRepetidoException("Erro: E-mail já cadastrado");
            }
        }
        return true;
    }

    @Override
    public boolean validarCpfExisteVendedor(Vendedor vendedorDigitado) throws CpfJaExistenteException {
        for (Vendedor vendedor: bancoDeVendedores.getListaVendedores()) {
            if (vendedor.getCpf().equalsIgnoreCase(vendedorDigitado.getCpf())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validarEmailCliente(Cliente clienteDigitado) throws EmailInvalidoException {
        if (!clienteDigitado.getEmail().contains("@")){
            throw new EmailInvalidoException("Erro: E-mail não contém @");
        }
        return true;
    }

    @Override
    public boolean validarEmailRepetidoCliente(Cliente clienteDigitado) throws EmailRepetidoException {
        for (Cliente cliente: bancoDeClientes.getListaClientes()) {
            if (cliente.getEmail().equalsIgnoreCase(clienteDigitado.getEmail())){
                throw new EmailRepetidoException("Erro: E-mail já cadastrado");
            }
        }
        return true;
    }

    @Override
    public boolean validarCpfExisteCliente(Cliente clienteDigitado) throws CpfJaExistenteException {
        for (Cliente cliente: bancoDeClientes.getListaClientes()) {
            if (cliente.getCpf().equalsIgnoreCase(clienteDigitado.getCpf())){
                return true;
            }
        }
        return false;
    }



}
