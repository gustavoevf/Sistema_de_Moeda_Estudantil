package com.api.moedaestudantil.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "transacao")
public class TransacaoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private double valor;
    @Column(nullable = false, length = 250)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "remetente")
    private UsuarioModel remetente;
    @ManyToOne
    @JoinColumn(name = "destinatario")
    private UsuarioModel destinatario;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
