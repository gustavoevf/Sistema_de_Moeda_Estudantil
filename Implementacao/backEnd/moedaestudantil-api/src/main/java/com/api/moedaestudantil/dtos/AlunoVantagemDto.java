package com.api.moedaestudantil.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.api.moedaestudantil.models.AlunoModel;
import com.api.moedaestudantil.models.VantagemModel;

public class AlunoVantagemDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    private AlunoModel aluno;
    @NotNull
    private VantagemModel vantagem;

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
