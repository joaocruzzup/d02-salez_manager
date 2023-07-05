package org.example.controller;

import org.example.Exceptions.*;
import org.example.model.Usuario;
import org.example.service.BuscaUsuario;
import org.example.service.Cadastro;
import org.example.service.PesquisaVenda;
import org.example.service.VendaService;

public class ClienteController implements Cadastro, PesquisaVenda, BuscaUsuario {
    private VendaService vendaService;

    public ClienteController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Override
    public void cadastrar(Usuario usuario) throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        vendaService.cadastrarUsuario(usuario);
    }

    @Override
    public void listar() {
        vendaService.imprimirClientes();
    }

    @Override
    public void pesquisarVendas(String cpf) {
        vendaService.pesquisarComprasCliente(cpf);
    }

    @Override
    public Usuario buscaUsuario(String cpf) throws UsuarioNaoCadastradoException {
        return vendaService.buscarCliente(cpf);
    }
}
