package com.api.moedaestudantil.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name = "professor")
public class ProfessorModel extends UsuarioModel {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 14)
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
