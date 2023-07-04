package org.example.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatador {
    public static String formatarData(LocalDate data) {
        DateTimeFormatter dataFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(dataFormatador);
    }

    public static String formatarValor(BigDecimal valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return "R$" + decimalFormat.format(valor);
    }

    public static String formatarCpf(String cpf){
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9);
    }
}
