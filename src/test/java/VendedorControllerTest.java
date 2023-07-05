import org.example.Exceptions.*;
import org.example.controller.ClienteController;
import org.example.controller.VendedorController;
import org.example.model.Cliente;
import org.example.model.Vendedor;
import org.example.service.VendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VendedorControllerTest {
    @Mock
    private VendaService vendaService;

    @InjectMocks
    private VendedorController vendedorController;


    @Test
    public void cadastrarTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");

        vendedorController.cadastrar(vendedorBase);

        Mockito.verify(vendaService, Mockito.times(1)).cadastrarUsuario(vendedorBase);
    }

    @Test
    public void listarTest() {

        vendedorController.listar();

        Mockito.verify(vendaService, Mockito.times(1)).imprimirVendedores();
    }

    @Test
    public void pesquisarVendasVendedorTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException {
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");
        vendedorController.cadastrar(vendedorBase);

        vendedorController.pesquisarVendas(vendedorBase.getEmail());

        Mockito.verify(vendaService, Mockito.times(1)).pesquisarVendasVendedor(vendedorBase.getEmail());
    }

    @Test
    public void buscarVendedorTest() throws CpfJaExistenteException, EmailRepetidoException, EmailInvalidoException, CpfInvalidoException, UsuarioNaoCadastradoException {
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");
        vendedorController.cadastrar(vendedorBase);

        vendedorController.buscaUsuario(vendedorBase.getCpf());

        Mockito.verify(vendaService, Mockito.times(1)).buscarVendedor(vendedorBase.getCpf());
    }




}
