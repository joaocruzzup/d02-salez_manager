package org.example.service;

import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.Exceptions.UsuarioNaoCadastradoException;
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

    public void cadastrarVenda(Venda venda, Vendedor vendedor, Cliente cliente) throws UsuarioNaoCadastradoException {
        boolean existeClienteVendedor = vendaValidador.validarCpfExiste(vendedor) && vendaValidador.validarCpfExiste(cliente);
        if (existeClienteVendedor){
            bancoDeVendas.cadastrarVenda(venda, vendedor, cliente);
        } else {
            throw new UsuarioNaoCadastradoException("Erro: Cliente ou Vendedor não cadastrado");
        }
    }

    public boolean cadastrarUsuario(Usuario usuario) throws CpfJaExistenteException, EmailInvalidoException, EmailRepetidoException {
        boolean emailValido = vendaValidador.validarEmail(usuario);
        boolean existeEmail = vendaValidador.validarEmailRepetido(usuario);
        boolean existeUsuario = vendaValidador.validarCpfExiste(usuario);
        boolean validado = emailValido && !existeEmail && !existeUsuario;
        if (validado && usuario instanceof Cliente){
            bancoDeClientes.cadastrarCliente((Cliente) usuario);
        } else if (validado && usuario instanceof Vendedor){
            bancoDeVendedores.cadastrarVendedor((Vendedor) usuario);
        } else if (existeUsuario){
            throw new CpfJaExistenteException("Erro: CPF já cadastrado nesse tipo de usuário");
        } else if (!emailValido){
            throw new EmailInvalidoException("Erro: E-mail inválido é necessário ter @");
        } else if (existeEmail) {
            throw new EmailRepetidoException("Erro: E-mail já cadastrado");
        }
        return true;
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

    public void pesquisarComprasCliente(String cpf){
        List<Venda> listaCompras = new ArrayList<>();
        for (Venda venda: bancoDeVendas.getListaVendas()) {
            if (venda.getCliente().getCpf().equalsIgnoreCase(cpf)){
                listaCompras.add(venda);
            }
        }
        if (listaCompras.size() == 0){
            System.out.println("Não há compras cadastradas para esse cliente");
        }
        System.out.println("Compras do cliente do CPF: " + cpf);
        for (Venda venda: listaCompras) {
            System.out.println("Preço da compra: " + formatarValor(venda.getValor()) +
                    " | Data da compra: " + formatarData(venda.getDataRegistro())  +
                    " | Vendedor responsável: " + venda.getVendedor().getEmail());
        }
    }

    public void pesquisarVendasVendedor (String email){
        List<Venda> listaVendas = new ArrayList<>();
        for (Venda venda: bancoDeVendas.getListaVendas()) {
            if (venda.getVendedor().getEmail().equalsIgnoreCase(email)){
                listaVendas.add(venda);
            }
        }
        if (listaVendas.size() == 0){
            System.out.println("Não há vendas cadastradas para esse vendedor");
        }
        System.out.println("Vendas do Vendedor do EMAIL: " + email);
        for (Venda venda: listaVendas) {
            System.out.println("Preço da compra: " + formatarValor(venda.getValor()) +
                    " | Data da compra: " + formatarData(venda.getDataRegistro())  +
                    " | Cliente: " + formatarCpf(venda.getCliente().getCpf()));
        }
    }

}
