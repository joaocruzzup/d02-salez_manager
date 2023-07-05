import org.example.Exceptions.CpfJaExistenteException;
import org.example.Exceptions.EmailInvalidoException;
import org.example.Exceptions.EmailRepetidoException;
import org.example.database.BancoDeClientes;
import org.example.database.BancoDeVendas;
import org.example.database.BancoDeVendedores;
import org.example.model.Cliente;
import org.example.service.VendaService;
import org.example.service.VendaValidador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {
    @InjectMocks
    private VendaService vendaService;

    @InjectMocks
    private BancoDeClientes bancoDeClientes;

    @InjectMocks
    private VendaValidador vendaValidador;

    @Test
    public void cadastrarUsuarioCorretoTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        boolean valido = vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o retorno do vendaService.cadastrarUsuario é TRUE
        Assertions.assertTrue(valido);
    }

    //toDo CORRIGIR: Metodo não está tendo acesso ao vendaValidador
    @Test
    public void cadastrarUsuarioCpfRepetido() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "123456789", "joao@example.com");
        Cliente clienteCpfRepetido = new Cliente("Victor", "123456789", "joaocruz@example.com");

        bancoDeClientes.cadastrarCliente(clienteBase);
        bancoDeClientes.cadastrarCliente(clienteCpfRepetido);

        vendaService.cadastrarUsuario(clienteBase);

        // Verificando se o método vendaService.cadastrarUsuario lança uma exceção
        assertThrows(CpfJaExistenteException.class, () -> vendaService.cadastrarUsuario(clienteCpfRepetido));
    }

}
