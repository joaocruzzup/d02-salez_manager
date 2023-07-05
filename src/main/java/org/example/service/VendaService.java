package org.example.service;

import org.example.Exceptions.*;
import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendas;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.model.Usuario;
import org.example.model.Venda;
import org.example.model.Vendedor;

import javax.swing.plaf.PanelUI;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Formatador.*;

public class VendaService {
    private BancoDeVendedores bancoDeVendedores = new BancoDeVendedores();
    private BancoDeClientes bancoDeClientes = new BancoDeClientes();
    private BancoDeVendas bancoDeVendas = new BancoDeVendas();
    private VendaValidador vendaValidador = new VendaValidador(bancoDeClientes, bancoDeVendedores);

    public boolean cadastrarVenda(Venda venda, Vendedor vendedor, Cliente cliente) throws UsuarioNaoCadastradoException {
        boolean existeClienteVendedor = vendaValidador.validarCpfExiste(vendedor) && vendaValidador.validarCpfExiste(cliente);
        if (existeClienteVendedor) {
            bancoDeVendas.cadastrarVenda(venda, vendedor, cliente);
            return true;
        } else {
            throw new UsuarioNaoCadastradoException("Erro: Cliente ou Vendedor não cadastrado");
        }
    }

    public boolean cadastrarUsuario(Usuario usuario) throws CpfJaExistenteException, EmailInvalidoException, EmailRepetidoException, CpfInvalidoException {
        boolean emailValido = vendaValidador.validarEmail(usuario);
        boolean cpfValido = vendaValidador.validarCpf(usuario);
        boolean existeEmail = vendaValidador.validarEmailRepetido(usuario);
        boolean existeUsuario = vendaValidador.validarCpfExiste(usuario);
        boolean validado = emailValido && cpfValido && !existeEmail && !existeUsuario;

        if (validado && usuario instanceof Cliente) {
            bancoDeClientes.cadastrarCliente((Cliente) usuario);
            return true;
        } else if (validado && usuario instanceof Vendedor) {
            bancoDeVendedores.cadastrarVendedor((Vendedor) usuario);
            return true;
        } else if (existeUsuario) {
            throw new CpfJaExistenteException("Erro: CPF já cadastrado nesse tipo de usuário");
        } else if (!emailValido) {
            throw new EmailInvalidoException("Erro: E-mail inválido. É necessário conter @");
        } else if (existeEmail) {
            throw new EmailRepetidoException("Erro: E-mail já cadastrado");
        } else if (!cpfValido) {
            throw new CpfInvalidoException("Erro: CPF inválido. É necessário haver 11 números");
        }
        return false;
    }

    public void listarVendas() {
        if (bancoDeVendas.getListaVendas().size() == 0) {
            System.out.println("Ainda não há vendas cadastradas no sistema");
        } else {
            for (Venda venda : bancoDeVendas.getListaVendas()) {
                System.out.println(
                        "PRODUTO: " + venda.getNomeProduto() +
                                " | CPF VENDEDOR: " + formatarCpf(venda.getVendedor().getCpf()) +
                                " | CPF CLIENTE: " + formatarCpf(venda.getCliente().getCpf()) +
                                " | VALOR: " + formatarValor(venda.getValor()) +
                                " | DATA: " + formatarData(venda.getDataRegistro()));
            }
        }
    }

    public void imprimirClientes() {
        List<Cliente> listaClientes = bancoDeClientes.getListaClientes();
        System.out.println("----------------------- Clientes Cadastrados -----------------------");
        if (bancoDeClientes.getListaClientes().size() == 0) {
            System.out.println("Ainda não há vendedores cadastrados no sistema");
        } else {
            System.out.println("|  Nome do Cliente |     CPF do Cliente    |   E-mail do Cliente  |");
            for (int i = 0; i < listaClientes.size(); i++) {
                System.out.printf("|     %-10s   |     %-10s    |   %-10s   |%n",
                        listaClientes.get(i).getNome(), formatarCpf(listaClientes.get(i).getCpf()), listaClientes.get(i).getEmail());
            }
        }
    }

    public void imprimirVendedores() {
        List<Vendedor> listaVendedores = bancoDeVendedores.getListaVendedores();
        System.out.println("----------------------- Vendedores Cadastrados -----------------------");
        if (bancoDeVendedores.getListaVendedores().size() == 0) {
            System.out.println("Ainda não há vendedores cadastrados no sistema");
        } else {
            System.out.println("| Nome do Vendedor |   CPF do Vendedor  |   E-mail do Vendedor     |");
            for (int i = 0; i < listaVendedores.size(); i++) {
                System.out.printf("|     %-10s   |     %-10s    |     %-10s     |%n",
                        listaVendedores.get(i).getNome(), listaVendedores.get(i).getCpf(), listaVendedores.get(i).getEmail());
            }
        }
    }

    public void pesquisarComprasCliente(String cpf) {
        List<Venda> listaCompras = new ArrayList<>();
        for (Venda venda : bancoDeVendas.getListaVendas()) {
            if (venda.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                listaCompras.add(venda);
            }
        }
        if (listaCompras.size() == 0) {
            System.out.println("----- Compras do cliente informado -----");
            System.out.println("Não há compras cadastradas para esse cliente");
        } else {
            System.out.println("----- Compras do cliente informado -----");
            for (Venda venda : listaCompras) {
                System.out.println("Produto comprado" + venda.getNomeProduto() +
                        "Preço da compra: " + formatarValor(venda.getValor()) +
                        " | Data da compra: " + formatarData(venda.getDataRegistro()) +
                        " | Vendedor responsável: " + venda.getVendedor().getEmail());
            }
        }
    }

    public void pesquisarVendasVendedor(String email) {
        List<Venda> listaVendas = new ArrayList<>();
        for (Venda venda : bancoDeVendas.getListaVendas()) {
            if (venda.getVendedor().getEmail().equalsIgnoreCase(email)) {
                listaVendas.add(venda);
            }
        }
        if (listaVendas.size() == 0) {
            System.out.println("----- Vendas do vendedor informado -----");
            System.out.println("Não há vendas cadastradas para esse vendedor");
        } else {
            System.out.println("----- Vendas do vendedor informado -----");
            for (Venda venda : listaVendas) {
                System.out.println("Produto comprado: " + venda.getNomeProduto() +
                        "Preço da compra: " + formatarValor(venda.getValor()) +
                        " | Data da compra: " + formatarData(venda.getDataRegistro()) +
                        " | Cliente: " + formatarCpf(venda.getCliente().getCpf()));
            }
        }
    }

    public Usuario buscarCliente(String cpf) throws UsuarioNaoCadastradoException {
        for (Cliente cliente : bancoDeClientes.getListaClientes()) {
            if (cliente.getCpf().equalsIgnoreCase(cpf)) {
                return cliente;
            }
        }
        throw new UsuarioNaoCadastradoException("Erro: Usuário não cadastrado!");
    }

    public Usuario buscarVendedor(String cpf) throws UsuarioNaoCadastradoException {
        for (Vendedor vendedor : bancoDeVendedores.getListaVendedores()) {
            if (vendedor.getCpf().equalsIgnoreCase(cpf)) {
                return vendedor;
            }
        }
        throw new UsuarioNaoCadastradoException("Erro: Usuário não cadastrado!");
    }
}
