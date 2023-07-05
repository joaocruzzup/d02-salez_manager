import org.example.Exceptions.*;
import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendas;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.model.Usuario;
import org.example.model.Venda;
import org.example.model.Vendedor;
import org.example.service.VendaService;
import org.example.service.VendaValidador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {
    @InjectMocks
    private VendaService vendaService;

    @InjectMocks
    private BancoDeClientes bancoDeClientes;

    @InjectMocks
    private BancoDeVendedores bancoDeVendedores;

    @InjectMocks
    private BancoDeVendas bancoDeVendas;

    @InjectMocks
    private VendaValidador vendaValidador;

    @Test
    public void cadastrarVendaTest() throws UsuarioNaoCadastradoException, CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");
        Venda venda = new Venda(clienteBase, vendedorBase, BigDecimal.valueOf(100.0), LocalDate.now(), "Café");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        bancoDeVendas.cadastrarVenda(venda, vendedorBase, clienteBase);

        vendaService.cadastrarUsuario(clienteBase);
        vendaService.cadastrarUsuario(vendedorBase);
        boolean valido = vendaService.cadastrarVenda(venda, vendedorBase, clienteBase);

        // Verificando se o retorno do vendaService.cadastrarVenda é TRUE
        Assertions.assertTrue(valido);
    }

    @Test
    public void cadastrarVendaSemUsuarioTest() {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");
        Venda venda = new Venda(clienteBase, vendedorBase, BigDecimal.valueOf(100.0), LocalDate.now(), "Café");

        // Verificando se a exceção lançada é a esperada
        assertThrows(UsuarioNaoCadastradoException.class, () -> vendaService.cadastrarVenda(venda, vendedorBase, clienteBase));
    }


    @Test
    public void cadastrarClienteCorretoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        boolean valido = vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o retorno do vendaService.cadastrarUsuario é TRUE
        Assertions.assertTrue(valido);
    }

    @Test
    public void cadastrarClienteCpfInvalidoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(CpfInvalidoException.class, () -> vendaService.cadastrarUsuario(clienteBase));
    }

    @Test
    public void cadastrarClienteCpfRepetidoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        Cliente clienteCpfRepetido = new Cliente("Victor", "12345678910", "joaocruz@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeClientes.cadastrarCliente(clienteCpfRepetido);

        vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(CpfJaExistenteException.class, () -> vendaService.cadastrarUsuario(clienteCpfRepetido));
    }

    @Test
    public void cadastrarClienteEmailInvalidoTest() {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joaoexample.com");

        bancoDeClientes.cadastrarCliente(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailInvalidoException.class, () -> vendaService.cadastrarUsuario(clienteBase));
    }

    @Test
    public void cadastrarClienteEmailRepetidoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        Cliente clienteEmailRepetido = new Cliente("Joao", "12345678911", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeClientes.cadastrarCliente(clienteEmailRepetido);

        vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailRepetidoException.class, () -> vendaService.cadastrarUsuario(clienteEmailRepetido));
    }

    @Test
    public void cadastrarVendedorCorretoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        boolean valido = vendaService.cadastrarUsuario(vendedorBase);

        // Verificando se o retorno do vendaService.cadastrarUsuario é TRUE
        Assertions.assertTrue(valido);
    }

    @Test
    public void cadastrarVendedorCpfInvalidoTest() {
        Vendedor vendedorBase = new Vendedor("Joao", "123", "joao@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(CpfInvalidoException.class, () -> vendaService.cadastrarUsuario(vendedorBase));
    }

    @Test
    public void cadastrarVendedorCpfRepetidoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");
        Vendedor vendedorCpfRepetido = new Vendedor("Victor", "12345678910", "joaocruz@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        bancoDeVendedores.cadastrarVendedor(vendedorCpfRepetido);

        vendaService.cadastrarUsuario(vendedorBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(CpfJaExistenteException.class, () -> vendaService.cadastrarUsuario(vendedorCpfRepetido));
    }

    @Test
    public void cadastrarVendedorEmailInvalidoTest() {
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joaoexample.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailInvalidoException.class, () -> vendaService.cadastrarUsuario(vendedorBase));
    }

    @Test
    public void cadastrarVendedorEmailRepetidoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "13245678910", "joao@example.com");
        Vendedor vendedorEmailRepetido = new Vendedor("Joao", "12345678911", "joao@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        bancoDeVendedores.cadastrarVendedor(vendedorEmailRepetido);

        vendaService.cadastrarUsuario(vendedorBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailRepetidoException.class, () -> vendaService.cadastrarUsuario(vendedorEmailRepetido));
    }

    @Test
    public void buscarVendedorTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException, UsuarioNaoCadastradoException {
        Vendedor vendedorBase = new Vendedor("Joao", "13245678910", "joao@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        vendaService.cadastrarUsuario(vendedorBase);

        Usuario vendedorBuscado = vendaService.buscarVendedor(vendedorBase.getCpf());

        Assertions.assertEquals(vendedorBase, vendedorBuscado);
    }

    @Test
    public void buscarVendedorInexistenteTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException, UsuarioNaoCadastradoException {
        Vendedor vendedorBase = new Vendedor("Joao", "13245678910", "joao@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);

        assertThrows(UsuarioNaoCadastradoException.class, () -> vendaService.buscarVendedor("53245678911"));
    }

    @Test
    public void buscarClienteTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException, UsuarioNaoCadastradoException {
        Cliente clienteBase = new Cliente("Joao", "13245678910", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        vendaService.cadastrarUsuario(clienteBase);

        Usuario clienteBuscado = vendaService.buscarCliente(clienteBase.getCpf());

        Assertions.assertEquals(clienteBase, clienteBuscado);
    }

    @Test
    public void buscarClienteInexistenteTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException, UsuarioNaoCadastradoException {
        Cliente clienteBase = new Cliente("Joao", "13245678910", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);

        assertThrows(UsuarioNaoCadastradoException.class, () -> vendaService.buscarCliente("53245678911"));
    }

}
