package com.api.moedaestudantil.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 200)
    private String login;
    @Column(nullable = false, length = 150)
    private String senha;
    @Column(nullable = false)
    private double valorCarteira;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

}
