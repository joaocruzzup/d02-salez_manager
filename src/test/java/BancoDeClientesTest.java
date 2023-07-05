import org.example.database.BancoDeClientes;
import org.example.model.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BancoDeClientesTest {
    @Mock
    private BancoDeClientes bancoDeClientes;

    @Test
    public void cadastrarClienteTest(){
        Cliente cliente = new Cliente("Joao", "123456789", "joao@example.com");

        Mockito.when(bancoDeClientes.cadastrarCliente(cliente)).thenReturn(true);
        bancoDeClientes.cadastrarCliente(cliente);
        Mockito.verify(bancoDeClientes, Mockito.times(1)).cadastrarCliente(cliente);
    }
}
