package com.towork.backendaplicacao.dominio;

import javax.persistence.*;

@Entity
public class Contato {

    //Atributos
    @Column(length = 40)
    private String tipoContato;
    @Column(length = 60)
    private String conteudoContato;
    @Id
    private Integer fkUsuario;

    @ManyToOne
    private Usuario usuario;

    //Construtor
    public Contato() {
    }

    //ToString
    @Override
    public String toString() {
        return "Contato{" +
                "tipoContato='" + tipoContato + '\'' +
                ", conteudoContato='" + conteudoContato + '\'' +
                ", fkUsuario=" + fkUsuario +
                '}';
    }

    //Getters e Setters
    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getConteudoContato() {
        return conteudoContato;
    }

    public void setConteudoContato(String conteudoContato) {
        this.conteudoContato = conteudoContato;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
