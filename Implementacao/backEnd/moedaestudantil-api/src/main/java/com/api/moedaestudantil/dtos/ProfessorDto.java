package com.api.moedaestudantil.dtos;

import javax.validation.constraints.NotNull;

public class ProfessorDto extends UsuarioDto {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String cpf;
    @NotNull
    private String rg;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
