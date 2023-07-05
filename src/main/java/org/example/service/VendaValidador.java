package org.example.service;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.model.Usuario;
import org.example.model.Vendedor;

public class VendaValidador implements ValidaUsuario {
    private BancoDeClientes bancoDeClientes;
    private BancoDeVendedores bancoDeVendedores;

    public VendaValidador(BancoDeClientes bancoDeClientes, BancoDeVendedores bancoDeVendedores) {
        this.bancoDeClientes = bancoDeClientes;
        this.bancoDeVendedores = bancoDeVendedores;
    }

    @Override
    public boolean validarEmail(Usuario usuario) {
        if (!usuario.getEmail().contains("@")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validarEmailRepetido(Usuario usuarioDigitado) {
        if (usuarioDigitado instanceof Cliente){
            for (Cliente usuario : bancoDeClientes.getListaClientes()) {
                if (usuario.getEmail().equalsIgnoreCase(usuarioDigitado.getEmail())) {
                    return true;
                }
            }
            return false;
        }
        if (usuarioDigitado instanceof Vendedor){
            for (Vendedor usuario : bancoDeVendedores.getListaVendedores()) {
                if (usuario.getEmail().equalsIgnoreCase(usuarioDigitado.getEmail())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean validarCpf(Usuario usuarioDigitado){
        String cpf = usuarioDigitado.getCpf().replaceAll("\\D", "");
        if (cpf.length() != 11){
            return false;
        }
        return true;
    }


    @Override
    public boolean validarCpfExiste(Usuario usuarioDigitado) {
        if (usuarioDigitado instanceof Cliente) {
            for (Cliente usuario : bancoDeClientes.getListaClientes()) {
                if (usuario.getCpf().equalsIgnoreCase(usuarioDigitado.getCpf())) {
                    return true;
                }
            }
            return false;
        }
        if (usuarioDigitado instanceof Vendedor) {
            for (Vendedor usuario : bancoDeVendedores.getListaVendedores()) {
                if (usuario.getCpf().equalsIgnoreCase(usuarioDigitado.getCpf())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
