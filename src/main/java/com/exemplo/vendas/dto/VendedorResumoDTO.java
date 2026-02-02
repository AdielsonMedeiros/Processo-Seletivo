package com.exemplo.vendas.dto;

import java.math.BigDecimal;

public class VendedorResumoDTO {

    private String nome;
    private Long totalVendas;
    private BigDecimal mediaVendasDiarias;

    public VendedorResumoDTO(String nome, Long totalVendas, BigDecimal mediaVendasDiarias) {
        this.nome = nome;
        this.totalVendas = totalVendas;
        this.mediaVendasDiarias = mediaVendasDiarias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Long totalVendas) {
        this.totalVendas = totalVendas;
    }

    public BigDecimal getMediaVendasDiarias() {
        return mediaVendasDiarias;
    }

    public void setMediaVendasDiarias(BigDecimal mediaVendasDiarias) {
        this.mediaVendasDiarias = mediaVendasDiarias;
    }
}
