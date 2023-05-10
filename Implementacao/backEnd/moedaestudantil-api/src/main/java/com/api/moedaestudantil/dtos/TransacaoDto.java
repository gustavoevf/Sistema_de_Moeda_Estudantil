package com.api.moedaestudantil.dtos;

import com.api.moedaestudantil.models.TransacaoModel;
import com.api.moedaestudantil.models.UsuarioModel;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

public class TransacaoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private double valor;
    @NotNull
    private String descricao;
    @NotNull
    private UsuarioModel remetente;
    @NotNull
    private UsuarioModel destinatario;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioModel getRemetente() {
        return remetente;
    }

    public void setRemetente(UsuarioModel remetente) {
        this.remetente = remetente;
    }

    public UsuarioModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsuarioModel destinatario) {
        this.destinatario = destinatario;
    }
}
