package com.api.moedaestudantil.dtos;

import javax.validation.constraints.NotBlank;

public class EmpresaDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String vantagens;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVantagens() {
        return vantagens;
    }

    public void setVantagens(String vantagens) {
        this.vantagens = vantagens;
    }
}