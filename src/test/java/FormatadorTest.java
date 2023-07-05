import org.example.utils.Formatador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.example.utils.Formatador.*;

@ExtendWith(MockitoExtension.class)
public class FormatadorTest {

    @Test
    public void formatarDataTest(){
        LocalDate data = LocalDate.of(2023, 06, 20);

        String dataFormatada =  formatarData(data);

        Assertions.assertEquals("20/06/2023", dataFormatada);

    }

    @Test
    public void formatarValorTest(){
        BigDecimal valor = new BigDecimal(200);

        String valorFormatado = formatarValor(valor);

        Assertions.assertEquals("R$200,00", valorFormatado);
    }

    @Test
    public void formatarCpfTest(){
        String cpf = "12345678910";

        String cpfFormatado = formatarCpf(cpf);

        Assertions.assertEquals("123.456.789-10", cpfFormatado);
    }
}
