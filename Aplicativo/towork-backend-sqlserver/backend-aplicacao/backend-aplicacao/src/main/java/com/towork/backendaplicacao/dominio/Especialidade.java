package com.towork.backendaplicacao.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Especialidade {

    //Atributos
    @Column(length = 60)
    private String nomeEspecialidade;
    @Column(length = 60)
    private String nivelEspecialidade;
    @Id
    private Integer fkUsuario;

    @ManyToOne
    private Usuario usuario;

    //Construtor
    public Especialidade() {
    }

    //ToString
    @Override
    public String toString() {
        return "Especialidade{" +
                "nomeEspecialidade='" + nomeEspecialidade + '\'' +
                ", nivelEspecialidade='" + nivelEspecialidade + '\'' +
                ", fkUsuario=" + fkUsuario +
                '}';
    }

    //Getters e Setters
    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public String getNivelEspecialidade() {
        return nivelEspecialidade;
    }

    public void setNivelEspecialidade(String nivelEspecialidade) {
        this.nivelEspecialidade = nivelEspecialidade;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
