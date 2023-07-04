package org.example.controller;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.model.Usuario;
import org.example.service.Cadastro;
import org.example.service.VendaService;

public class ClienteController implements Cadastro {
    private VendaService vendaService;

    public ClienteController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Override
    public void cadastrar(Usuario usuario) throws CpfJaExistenteException {
        vendaService.cadastrarUsuario(usuario);
    }

    @Override
    public void listar() {
        vendaService.listarClientes();
    }
}
