package com.towork.backendaplicacao.dominio;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
public class ProjetosCurtidos {//Como fazer com 2 chaves estrangeiras?

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurtida;

    private String dataHoraCurtido;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Projeto projeto;

    //Construtor
    public ProjetosCurtidos() {
    }

    //ToString

    @Override
    public String toString() {
        return "ProjetosCurtidos{" +
                "idCurtida=" + idCurtida +
                ", dataHoraCurtido='" + dataHoraCurtido + '\'' +
                ", usuario=" + usuario +
                ", projeto=" + projeto +
                '}';
    }


    //Getters e Setters

    public Integer getIdCurtida() {
        return idCurtida;
    }

    public void setIdCurtida(Integer idCurtida) {
        this.idCurtida = idCurtida;
    }

    public String getDataHoraCurtido() {
        return dataHoraCurtido;
    }

    public void setDataHoraCurtido(String dataHoraCurtido) {
        this.dataHoraCurtido = dataHoraCurtido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
