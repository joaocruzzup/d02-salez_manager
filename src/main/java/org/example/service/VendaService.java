package org.example.service;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.UsuarioNaoCadastradoException;
import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendas;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.model.Usuario;
import org.example.model.Venda;
import org.example.model.Vendedor;

import static org.example.utils.Formatador.*;

public class VendaService {
    private BancoDeVendedores bancoDeVendedores = new BancoDeVendedores();
    private BancoDeClientes bancoDeClientes = new BancoDeClientes();
    private BancoDeVendas bancoDeVendas = new BancoDeVendas();
    private VendaValidador vendaValidador = new VendaValidador(bancoDeClientes, bancoDeVendedores);



    public void cadastrarVenda(Venda venda, Vendedor vendedor, Cliente cliente) throws UsuarioNaoCadastradoException, CpfJaExistenteException {
        boolean existeClienteVendedor = vendaValidador.validarCpfExiste(vendedor) && vendaValidador.validarCpfExiste(cliente);
        if (existeClienteVendedor){
            bancoDeVendas.cadastrarVenda(venda, vendedor, cliente);
        } else {
            throw new UsuarioNaoCadastradoException("Erro: Cliente ou Vendedor não cadastrado");
        }
    }

    public void cadastrarUsuario(Usuario usuario) throws CpfJaExistenteException {
        boolean existeCliente = vendaValidador.validarCpfExiste(usuario);
        if (!existeCliente && usuario instanceof Cliente){
            bancoDeClientes.cadastrarCliente((Cliente) usuario);
        } else if (!existeCliente && usuario instanceof Vendedor){
            bancoDeVendedores.cadastrarVendedor((Vendedor) usuario);
        } else {
            throw new CpfJaExistenteException("Erro: CPF já cadastrado como cliente");
        }
    }

    public void listarVendas() {
        for (Venda venda: bancoDeVendas.getListaVendas()) {
            System.out.println(
                    "CPF VENDEDOR: " + formatarCpf(venda.getVendedor().getCpf())  +
                            " | CPF CLIENTE: " + formatarCpf(venda.getCliente().getCpf()) +
                            " | VALOR: " + formatarValor(venda.getValor()) +
                            " | DATA: " + formatarData(venda.getDataRegistro()));
        }
    }

    public void listarClientes() {
        for (Cliente cliente: bancoDeClientes.getListaClientes()) {
            System.out.println(
                    "NOME: " + cliente.getNome() +
                            " | CPF: " + formatarCpf(cliente.getCpf()) +
                            " | EMAIL: " + cliente.getEmail());
        }
    }

    public void listarVendedores() {
        for (Vendedor vendedor: bancoDeVendedores.getListaVendedores()) {
            System.out.println(
                    "NOME: " + vendedor.getNome() +
                            " | CPF: " + formatarCpf(vendedor.getCpf()) +
                            " | EMAIL: " + vendedor.getEmail());
        }
    }

}
