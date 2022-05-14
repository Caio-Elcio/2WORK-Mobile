package com.towork.backendaplicacao.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjetosCurtidos {//Como fazer com 2 chaves estrangeiras?

    //Atributos
    @Id
    private Integer fkUsuario;
    private String dataHoraCurtido;
    private Integer fkProjeto;

    @ManyToOne
    private Projeto projeto;

    //Construtor
    public ProjetosCurtidos() {
    }

    //ToString
    @Override
    public String toString() {
        return "ProjetosCurtidos{" +
                "fkUsuario=" + fkUsuario +
                ", dataHoraCurtido='" + dataHoraCurtido + '\'' +
                ", fkProjeto=" + fkProjeto +
                '}';
    }

    //Getters e Setters
    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getDataHoraCurtido() {
        return dataHoraCurtido;
    }

    public void setDataHoraCurtido(String dataHoraCurtido) {
        this.dataHoraCurtido = dataHoraCurtido;
    }

    public Integer getFkProjeto() {
        return fkProjeto;
    }

    public void setFkProjeto(Integer fkProjeto) {
        this.fkProjeto = fkProjeto;
    }
}
