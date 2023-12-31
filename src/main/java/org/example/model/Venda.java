package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {
    private Cliente cliente;
    private Vendedor vendedor;
    private BigDecimal valor;
    private LocalDate dataRegistro;
    private String nomeProduto;

    public Venda(Cliente cliente, Vendedor vendedor, BigDecimal valor, LocalDate dataRegistro, String nomeProduto) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.valor = valor;
        this.dataRegistro = dataRegistro;
        this.nomeProduto = nomeProduto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
