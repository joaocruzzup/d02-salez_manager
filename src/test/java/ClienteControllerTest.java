import org.example.Exceptions.*;
import org.example.controller.ClienteController;
import org.example.model.Cliente;
import org.example.model.Usuario;
import org.example.service.VendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {
    @Mock
    private VendaService vendaService;

    @InjectMocks
    private ClienteController clienteController;


    @Test
    public void cadastrarTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");

        clienteController.cadastrar(clienteBase);

        Mockito.verify(vendaService, Mockito.times(1)).cadastrarUsuario(clienteBase);
    }

    @Test
    public void listarTest() {

        clienteController.listar();

        Mockito.verify(vendaService, Mockito.times(1)).imprimirClientes();
    }

    @Test
    public void pesquisarVendasClienteTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        clienteController.cadastrar(clienteBase);

        clienteController.pesquisarVendas("12345678910");

        Mockito.verify(vendaService, Mockito.times(1)).pesquisarComprasCliente("12345678910");
    }

    @Test
    public void buscarClienteTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException, UsuarioNaoCadastradoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        clienteController.cadastrar(clienteBase);

        clienteController.buscaUsuario(clienteBase.getCpf());

        Mockito.verify(vendaService, Mockito.times(1)).buscarCliente(clienteBase.getCpf());
    }
}
