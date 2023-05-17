package com.api.moedaestudantil.dtos;

import javax.validation.constraints.NotNull;

public class EmpresaDto extends UsuarioDto {

    @NotNull
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}