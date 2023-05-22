package com.api.moedaestudantil.dtos;

import com.api.moedaestudantil.models.AlunoModel;
import com.api.moedaestudantil.models.UsuarioModel;
import com.api.moedaestudantil.models.VantagemModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
