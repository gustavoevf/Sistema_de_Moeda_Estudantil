package com.api.moedaestudantil.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "alunoVantagem")
public class AlunoVantagemModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "aluno")
    private AlunoModel aluno;
    @ManyToOne
    @JoinColumn(name = "vantagem")
    private VantagemModel vantagem;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public VantagemModel getVantagem() {
        return vantagem;
    }

    public void setVantagem(VantagemModel vantagem) {
        this.vantagem = vantagem;
    }
}
