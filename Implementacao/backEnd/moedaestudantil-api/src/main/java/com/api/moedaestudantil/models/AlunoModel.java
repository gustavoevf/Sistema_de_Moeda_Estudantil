package com.api.moedaestudantil.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Table(name = "aluno")
public class AlunoModel extends UsuarioModel {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, length = 14)
    private String rg;

    @Column(nullable = false, length = 200)
    private String endereco;

    @Column(nullable = false, length = 200)
    private String instituicaoEnsino;

    @Column(nullable = false, length = 200)
    private String curso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
