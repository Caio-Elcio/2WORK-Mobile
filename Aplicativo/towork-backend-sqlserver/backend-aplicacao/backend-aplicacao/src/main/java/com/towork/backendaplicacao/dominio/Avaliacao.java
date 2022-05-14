package com.towork.backendaplicacao.dominio;

import javax.persistence.*;

@Entity
public class Avaliacao {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;
    private Integer quantidadeEstrelas;
    private Integer fkUsuarioAvaliador;
    private Integer fkUsuarioAvaliado;

    @ManyToOne
    private Usuario usuarioAvaliador;

    @ManyToOne
    private Usuario usuarioAvaliado;

    //Construtor
    public Avaliacao() {
    }

    //ToString
    @Override
    public String toString() {
        return "Avaliacao{" +
                "idAvaliacao=" + idAvaliacao +
                ", quantidadeEstrelas=" + quantidadeEstrelas +
                ", fkUsuarioAvaliador=" + fkUsuarioAvaliador +
                ", fkUsuarioAvaliado=" + fkUsuarioAvaliado +
                '}';
    }

    //Getters e Setters
    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getQuantidadeEstrelas() {
        return quantidadeEstrelas;
    }

    public void setQuantidadeEstrelas(Integer quantidadeEstrelas) {
        this.quantidadeEstrelas = quantidadeEstrelas;
    }

    public Integer getFkUsuarioAvaliador() {
        return fkUsuarioAvaliador;
    }

    public void setFkUsuarioAvaliador(Integer fkUsuarioAvaliador) {
        this.fkUsuarioAvaliador = fkUsuarioAvaliador;
    }

    public Integer getFkUsuarioAvaliado() {
        return fkUsuarioAvaliado;
    }

    public void setFkUsuarioAvaliado(Integer fkUsuarioAvaliado) {
        this.fkUsuarioAvaliado = fkUsuarioAvaliado;
    }
}
