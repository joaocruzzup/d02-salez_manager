import org.example.Exceptions.*;
import org.example.controller.ClienteController;
import org.example.controller.VendaController;
import org.example.controller.VendedorController;
import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;
import org.example.service.VendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class VendaControllerTest {
    @Mock
    private VendaService vendaService;

    @InjectMocks
    private VendaController vendaController;


    @Test
    public void cadastrarVendaTest() throws CpfJaExistenteException, UsuarioNaoCadastradoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");
        Venda vendaBase = new Venda(clienteBase, vendedorBase, BigDecimal.valueOf(100), LocalDate.now(), "Café");

        vendaController.cadastrar(vendaBase, vendedorBase, clienteBase);

        Mockito.verify(vendaService, Mockito.times(1)).cadastrarVenda(vendaBase, vendedorBase, clienteBase);
    }


    @Test
    public void listarVendaTest() throws CpfJaExistenteException, UsuarioNaoCadastradoException {
        Cliente clienteBase = new Cliente("Joao", "12345678910", "joao@example.com");
        Vendedor vendedorBase = new Vendedor("Joao", "12345678910", "joao@example.com");
        Venda vendaBase = new Venda(clienteBase, vendedorBase, BigDecimal.valueOf(100), LocalDate.now(), "Café");

        vendaController.listar();

        vendaController.cadastrar(vendaBase, vendedorBase, clienteBase);

        Mockito.verify(vendaService, Mockito.times(1)).listarVendas();
    }
}
