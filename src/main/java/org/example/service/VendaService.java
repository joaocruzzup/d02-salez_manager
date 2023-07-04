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

    public void cadastrarUsuario(Usuario usuario) throws CpfJaExistenteException, EmailInvalidoException, EmailRepetidoException {
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
