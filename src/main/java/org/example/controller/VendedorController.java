package org.example.controller;


import org.example.Exceptions.CpfJaExistenteException;
import org.example.model.Usuario;
import org.example.model.Vendedor;
import org.example.service.Cadastro;
import org.example.service.VendaService;



public class VendedorController implements Cadastro {
    private VendaService vendaService;

    public VendedorController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Override
    public void cadastrar(Usuario usuario) throws CpfJaExistenteException {
        vendaService.cadastrarUsuario(usuario);
    }

    @Override
    public void listar() {
        vendaService.listarVendedores();
    }
}
