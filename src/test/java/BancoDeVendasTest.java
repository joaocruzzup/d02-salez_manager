import org.example.database.BancoDeVendas;
import org.example.model.Cliente;
import org.example.model.Venda;
import org.example.model.Vendedor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class BancoDeVendasTest {
    @Mock
    private BancoDeVendas bancoDeVendas;

    @Test
    public void cadastrarClienteTest(){
        Cliente cliente = new Cliente("Joao", "123456789", "joao@example.com");
        Vendedor vendedor = new Vendedor("Maria", "132456897", "maria@example.com");
        Venda venda = new Venda(cliente, vendedor, BigDecimal.valueOf(200.0), LocalDate.now(), "feijao");

        Mockito.when(bancoDeVendas.cadastrarVenda(venda, vendedor, cliente)).thenReturn(true);
        bancoDeVendas.cadastrarVenda(venda, vendedor, cliente);
        Mockito.verify(bancoDeVendas, Mockito.times(1)).cadastrarVenda(venda, vendedor, cliente);
    }
}
