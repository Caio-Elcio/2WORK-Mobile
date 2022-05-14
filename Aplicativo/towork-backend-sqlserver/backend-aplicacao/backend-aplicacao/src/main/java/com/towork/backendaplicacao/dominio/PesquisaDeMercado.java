package com.towork.backendaplicacao.dominio;

import javax.persistence.*;

@Entity
public class PesquisaDeMercado {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisa;

    private Integer fkProjeto;

    @ManyToOne
    private Projeto projeto;

    //Construtor
    public PesquisaDeMercado() {
    }

    //ToString

    //Getter e Setter

}
