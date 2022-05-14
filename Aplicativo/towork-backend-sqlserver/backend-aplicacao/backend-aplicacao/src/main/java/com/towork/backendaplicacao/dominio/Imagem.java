package com.towork.backendaplicacao.dominio;

import javax.persistence.*;

@Entity
public class Imagem {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagem;
    @Column(length = 1000)
    private String imageUrl;
    private Integer fkProjeto;

    //@ManyToOne
    //private Projeto projeto;

    public Imagem() {
    }

    public Imagem(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //ToString
    @Override
    public String toString() {
        return "Imagem{" +
                "idImagem=" + idImagem +
                ", imageUrl='" + imageUrl + '\'' +
                ", fkProjeto=" + fkProjeto +
                '}';
    }

    //Getters e Setters
    public Integer getIdImagem() {
        return idImagem;
    }
    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Integer getFkProjeto() {
        return fkProjeto;
    }
    public void setFkProjeto(Integer fkProjeto) {
        this.fkProjeto = fkProjeto;
    }
}
