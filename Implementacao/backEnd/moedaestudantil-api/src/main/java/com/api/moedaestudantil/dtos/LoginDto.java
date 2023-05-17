package com.api.moedaestudantil.dtos;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LoginDto implements Serializable {

    @NotNull
    private String login;
    @NotNull
    private String senha;

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
}
