package com.api.moedaestudantil.dtos;

import com.api.moedaestudantil.models.UsuarioModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class EnviarTransacaoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private double valor;
    @NotNull
    private String descricao;
    @NotNull
    private UUID remetente;
    @NotNull
    private UUID destinatario;

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

    public UUID getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UUID destinatario) {
        this.destinatario = destinatario;
    }

    public UUID getRemetente() {
        return remetente;
    }

    public void setRemetente(UUID remetente) {
        this.remetente = remetente;
    }
}
