package com.towork.backendaplicacao.dominio;

import javax.persistence.*;

@Entity
public class Projeto {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjeto;
    @Column(length = 60)
    private String tituloProjeto;
    @Column(length = 1000)
    private String descricaoProjeto;

    @Column
    @Lob
    private byte[] imagemProjeto;

    private String dataHoraProjeto;
    private Integer totalVisualizacoesProjeto;
    private Integer totalCurtidasProjeto;
    private Boolean primeiraPergunta;
    private Boolean segundaPergunta;
    private Boolean terceiraPergunta;
    private Integer fkUsuario;

    private String nomeUsuario;

    private String primeiraLetraNome;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Imagem imagem;

    //Construtor
    public Projeto() {
    }

    //ToString

    @Override
    public String toString() {
        return "Projeto{" +
                "idProjeto=" + idProjeto +
                ", tituloProjeto='" + tituloProjeto + '\'' +
                ", descricaoProjeto='" + descricaoProjeto + '\'' +
                ", dataHoraProjeto='" + dataHoraProjeto + '\'' +
                ", totalVisualizacoesProjeto=" + totalVisualizacoesProjeto +
                ", totalCurtidasProjeto=" + totalCurtidasProjeto +
                ", primeiraPergunta=" + primeiraPergunta +
                ", segundaPergunta=" + segundaPergunta +
                ", terceiraPergunta=" + terceiraPergunta +
                ", fkUsuario=" + fkUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", primeiraLetraNome='" + primeiraLetraNome + '\'' +
                ", usuario=" + usuario +
                ", imagem=" + imagem +
                '}';
    }


    //Getters e Setters


    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getTituloProjeto() {
        return tituloProjeto;
    }

    public void setTituloProjeto(String tituloProjeto) {
        this.tituloProjeto = tituloProjeto;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public String getDataHoraProjeto() {
        return dataHoraProjeto;
    }

    public void setDataHoraProjeto(String dataHoraProjeto) {
        this.dataHoraProjeto = dataHoraProjeto;
    }

    public Integer getTotalVisualizacoesProjeto() {
        return totalVisualizacoesProjeto;
    }

    public void setTotalVisualizacoesProjeto(Integer totalVisualizacoesProjeto) {
        this.totalVisualizacoesProjeto = totalVisualizacoesProjeto;
    }

    public Integer getTotalCurtidasProjeto() {
        return totalCurtidasProjeto;
    }

    public void setTotalCurtidasProjeto(Integer totalCurtidasProjeto) {
        this.totalCurtidasProjeto = totalCurtidasProjeto;
    }

    public Boolean getPrimeiraPergunta() {
        return primeiraPergunta;
    }

    public void setPrimeiraPergunta(Boolean primeiraPergunta) {
        this.primeiraPergunta = primeiraPergunta;
    }

    public Boolean getSegundaPergunta() {
        return segundaPergunta;
    }

    public void setSegundaPergunta(Boolean segundaPergunta) {
        this.segundaPergunta = segundaPergunta;
    }

    public Boolean getTerceiraPergunta() {
        return terceiraPergunta;
    }

    public void setTerceiraPergunta(Boolean terceiraPergunta) {
        this.terceiraPergunta = terceiraPergunta;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getPrimeiraLetraNome() {
        return primeiraLetraNome;
    }

    public void setPrimeiraLetraNome(String primeiraLetraNome) {
        this.primeiraLetraNome = primeiraLetraNome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public byte[] getImagemProjeto() {
        return imagemProjeto;
    }

    public void setImagemProjeto(byte[] imagemProjeto) {
        this.imagemProjeto = imagemProjeto;
    }
}
