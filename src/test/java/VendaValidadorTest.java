import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.model.Vendedor;
import org.example.service.VendaValidador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class VendaValidadorTest {
    @Mock
    private VendaValidador vendaValidador;

    @InjectMocks
    private BancoDeClientes bancoDeClientes;

    @InjectMocks
    private BancoDeVendedores bancoDeVendedores;

    @Test
    @DisplayName("Teste Validar Email Correto dos CLIENTES")
    public void validarEmailClienteTest(){
        // Validando se o email correto (com @) retorna true
        Cliente cliente = new Cliente("Joao", "123456789", "joao@example.com");
        Mockito.when(vendaValidador.validarEmail(cliente)).thenReturn(true);
        Assertions.assertTrue(vendaValidador.validarEmail(cliente));

        // Validando se o email errado (sem @) retorna falso
        Cliente cliente1 = new Cliente("Joao", "123456789", "joaoexample.com");
        Mockito.when(vendaValidador.validarEmail(cliente1)).thenReturn(false);
        Assertions.assertFalse(vendaValidador.validarEmail(cliente1));
    }


    @Test
    @DisplayName("Teste Validar Email REPETIDO dos CLIENTES")
    public void validarEmailRepetidoClienteTest(){
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");
        Cliente clienteEmailRepetido = new Cliente("Joao", "435456888", "joao@example.com");
        Cliente clienteEmailNaoRepetido = new Cliente("Joao", "456985263", "joaovictor@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeClientes.cadastrarCliente(clienteEmailRepetido);
        bancoDeClientes.cadastrarCliente(clienteEmailNaoRepetido);

        // Verificando se o retorno do email repetido é true
        Mockito.when(vendaValidador.validarEmailRepetido(clienteEmailRepetido)).thenReturn(true);
        Assertions.assertTrue(vendaValidador.validarEmailRepetido(clienteEmailRepetido));

        // Verificando se o retorno do email não repetido é false
        Mockito.when(vendaValidador.validarEmailRepetido(clienteEmailNaoRepetido)).thenReturn(false);
        Assertions.assertFalse(vendaValidador.validarEmailRepetido(clienteEmailNaoRepetido));
    }

    @Test
    @DisplayName("Teste Validar CPF REPETIDO dos CLIENTES")
    public void validarCpfExisteCliente(){
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");
        Cliente clienteCpfRepetido = new Cliente("Joao", "123456789", "joaocruz@example.com");
        Cliente clienteCpfNaoRepetido = new Cliente("Joao", "456985263", "joaovictor@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeClientes.cadastrarCliente(clienteCpfRepetido);
        bancoDeClientes.cadastrarCliente(clienteCpfNaoRepetido);

        // Verificando se o retorno do cpf repetido é true
        Mockito.when(vendaValidador.validarCpfExiste(clienteCpfRepetido)).thenReturn(true);
        Assertions.assertTrue(vendaValidador.validarCpfExiste(clienteCpfRepetido));

        // Verificando se o retorno do cpf não repetido é false
        Mockito.when(vendaValidador.validarCpfExiste(clienteCpfNaoRepetido)).thenReturn(false);
        Assertions.assertFalse(vendaValidador.validarCpfExiste(clienteCpfNaoRepetido));
    }

    @Test
    @DisplayName("Teste Validar Email Correto dos VENDEDORES")
    public void validarEmailVendedorTest(){
        // Validando se o email correto (com @) retorna true
        Vendedor vendedor = new Vendedor("Joao", "123456789", "joao@example.com");
        Mockito.when(vendaValidador.validarEmail(vendedor)).thenReturn(true);
        Assertions.assertTrue(vendaValidador.validarEmail(vendedor));

        // Validando se o email errado (sem @) retorna falso
        Vendedor vendedor1 = new Vendedor("Joao", "123456789", "joaoexample.com");
        Mockito.when(vendaValidador.validarEmail(vendedor1)).thenReturn(false);
        Assertions.assertFalse(vendaValidador.validarEmail(vendedor1));
    }


    @Test
    @DisplayName("Teste Validar Email REPETIDO dos VENDEDORES")
    public void validarEmailRepetidoVendedorTest(){
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joao@example.com");
        Vendedor vendedorEmailRepetido = new Vendedor("Joao", "435456888", "joao@example.com");
        Vendedor vendedorEmailNaoRepetido = new Vendedor("Joao", "456985263", "joaovictor@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        bancoDeVendedores.cadastrarVendedor(vendedorEmailRepetido);
        bancoDeVendedores.cadastrarVendedor(vendedorEmailNaoRepetido);

        // Verificando se o retorno do email repetido é true
        Mockito.when(vendaValidador.validarEmailRepetido(vendedorEmailRepetido)).thenReturn(true);
        Assertions.assertTrue(vendaValidador.validarEmailRepetido(vendedorEmailRepetido));

        // Verificando se o retorno do email não repetido é false
        Mockito.when(vendaValidador.validarEmailRepetido(vendedorEmailNaoRepetido)).thenReturn(false);
        Assertions.assertFalse(vendaValidador.validarEmailRepetido(vendedorEmailNaoRepetido));
    }

    @Test
    @DisplayName("Teste Validar CPF REPETIDO dos VENDEDORES")
    public void validarCpfExisteVendedor(){
        Vendedor vendedorBase = new Vendedor("Joao", "123456789", "joao@example.com");
        Vendedor vendedorEmailRepetido = new Vendedor("Joao", "123456789", "joaocruz@example.com");
        Vendedor vendedorEmailNaoRepetido = new Vendedor("Joao", "456985263", "joaovictor@example.com");

        bancoDeVendedores.cadastrarVendedor(vendedorBase);
        bancoDeVendedores.cadastrarVendedor(vendedorEmailRepetido);
        bancoDeVendedores.cadastrarVendedor(vendedorEmailNaoRepetido);

        // Verificando se o retorno do cpf repetido é true
        Mockito.when(vendaValidador.validarCpfExiste(vendedorEmailRepetido)).thenReturn(true);
        Assertions.assertTrue(vendaValidador.validarCpfExiste(vendedorEmailRepetido));

        // Verificando se o retorno do cpf não repetido é false
        Mockito.when(vendaValidador.validarCpfExiste(vendedorEmailNaoRepetido)).thenReturn(false);
        Assertions.assertFalse(vendaValidador.validarCpfExiste(vendedorEmailNaoRepetido));
    }

}
