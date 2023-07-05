import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.Exceptions.UsuarioNaoCadastradoException;
import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendas;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
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
    public void cadastrarVendaTest() throws UsuarioNaoCadastradoException, CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joao@example.com");
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
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joao@example.com");
        Venda venda = new Venda(clienteBase, vendedorBase, BigDecimal.valueOf(100.0), LocalDate.now(), "Café");

        // Verificando se a exceção lançada é a esperada
        assertThrows(UsuarioNaoCadastradoException.class, () -> vendaService.cadastrarVenda(venda, vendedorBase, clienteBase));
    }


    @Test
    public void cadastrarClienteCorretoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        boolean valido = vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o retorno do vendaService.cadastrarUsuario é TRUE
        Assertions.assertTrue(valido);
    }

    @Test
    public void cadastrarClienteCpfRepetido() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");
        Cliente clienteCpfRepetido = new Cliente("Victor", "123456789", "joaocruz@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeClientes.cadastrarCliente(clienteCpfRepetido);

        vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(CpfJaExistenteException.class, () -> vendaService.cadastrarUsuario(clienteCpfRepetido));
    }

    @Test
    public void cadastrarClienteEmailInvalido() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123456789", "joaoexample.com");

        bancoDeClientes.cadastrarCliente(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailInvalidoException.class, () -> vendaService.cadastrarUsuario(clienteBase));
    }

    @Test
    public void cadastrarClienteEmailRepetido() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");
        Cliente clienteEmailRepetido = new Cliente("Joao", "123456788", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeClientes.cadastrarCliente(clienteEmailRepetido);

        vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailRepetidoException.class, () -> vendaService.cadastrarUsuario(clienteEmailRepetido));
    }

    @Test
    public void cadastrarVendedorCorretoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joao@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        boolean valido = vendaService.cadastrarUsuario(vendedorBase);

        // Verificando se o retorno do vendaService.cadastrarUsuario é TRUE
        Assertions.assertTrue(valido);
    }

    @Test
    public void cadastrarVendedorCpfRepetido() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joao@example.com");
        Vendedor vendedorCpfRepetido = new Vendedor("Victor", "123456789", "joaocruz@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        bancoDeVendedores.cadastrarVendedor(vendedorCpfRepetido);

        vendaService.cadastrarUsuario(vendedorBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(CpfJaExistenteException.class, () -> vendaService.cadastrarUsuario(vendedorCpfRepetido));
    }

    @Test
    public void cadastrarVendedorEmailInvalido() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joaoexample.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailInvalidoException.class, () -> vendaService.cadastrarUsuario(vendedorBase));
    }

    @Test
    public void cadastrarVendedorEmailRepetido() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joao@example.com");
        Vendedor vendedorEmailRepetido = new Vendedor("Joao", "123456788", "joao@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        bancoDeVendedores.cadastrarVendedor(vendedorEmailRepetido);

        vendaService.cadastrarUsuario(vendedorBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(EmailRepetidoException.class, () -> vendaService.cadastrarUsuario(vendedorEmailRepetido));
    }

}
