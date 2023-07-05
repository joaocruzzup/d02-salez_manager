import org.example.database.BancoDeVendedores;
import org.example.model.Vendedor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BancoDeVendedoresTest {
    @Mock
    private BancoDeVendedores bancoDeVendedores;

    @Test
    public void cadastrarClienteTest(){
        Vendedor vendedor = new Vendedor("Joao", "123456789", "joao@example.com");

        Mockito.when(bancoDeVendedores.cadastrarVendedor(vendedor)).thenReturn(true);
        bancoDeVendedores.cadastrarVendedor(vendedor);
        Mockito.verify(bancoDeVendedores, Mockito.times(1)).cadastrarVendedor(vendedor);
    }
}
