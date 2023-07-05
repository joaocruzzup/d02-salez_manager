package org.example.controller;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.UsuarioNaoCadastradoException;
import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;
import org.example.service.VendaService;

public class VendaController  {
    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    public void cadastrar(Venda venda, Vendedor vendedor, Cliente cliente) throws CpfJaExistenteException, UsuarioNaoCadastradoException {
        vendaService.cadastrarVenda(venda, vendedor, cliente);
    }

    public void listar() {
        vendaService.listarVendas();
    }
}
