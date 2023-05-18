package com.api.moedaestudantil.dtos;

import com.api.moedaestudantil.models.TransacaoModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class UsuarioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String login;
    @NotNull
    private String senha;
    @NotNull
    private double valorCarteira;

    private String tipo;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getValorCarteira() {
        return valorCarteira;
    }

    public void setValorCarteira(double valorCarteira) {
        this.valorCarteira = valorCarteira;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
