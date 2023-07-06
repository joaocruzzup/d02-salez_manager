package org.example.controller;


import org.example.Exceptions.*;
import org.example.model.Usuario;
import org.example.interfaces.BuscaUsuario;
import org.example.interfaces.Cadastro;
import org.example.interfaces.PesquisaVenda;
import org.example.service.VendaService;



public class VendedorController implements Cadastro, PesquisaVenda, BuscaUsuario {
    private VendaService vendaService;

    public VendedorController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Override
    public void cadastrar(Usuario usuario) throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        vendaService.cadastrarUsuario(usuario);
    }

    @Override
    public void listar() {
        vendaService.imprimirVendedores();
    }

    @Override
    public void pesquisarVendas(String email) {
        vendaService.pesquisarVendasVendedor(email);
    }


    @Override
    public Usuario buscaUsuario(String cpf) throws UsuarioNaoCadastradoException {
        return vendaService.buscarVendedor(cpf);
    }
}
