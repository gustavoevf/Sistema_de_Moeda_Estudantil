package com.api.moedaestudantil.dtos;

import com.api.moedaestudantil.models.EmpresaModel;
import com.api.moedaestudantil.models.UsuarioModel;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class VantagemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String descricao;

    private String fotoProduto;

    @NotNull
    private double custo;
    @NotNull
    private EmpresaModel empresa;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(String fotoProduto) {
        this.fotoProduto = fotoProduto;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public EmpresaModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa) {
        this.empresa = empresa;
    }


}
