package org.example.controller;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.model.Usuario;
import org.example.service.Cadastro;
import org.example.service.PesquisaVenda;
import org.example.service.VendaService;

public class ClienteController implements Cadastro, PesquisaVenda {
    private VendaService vendaService;

    public ClienteController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Override
    public void cadastrar(Usuario usuario) throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        vendaService.cadastrarUsuario(usuario);
    }

    @Override
    public void listar() {
        vendaService.listarClientes();
    }

    @Override
    public void pesquisarVendas(String cpf) {
        vendaService.pesquisarComprasCliente(cpf);
    }
}
