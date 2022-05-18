package com.towork.backendaplicacao.dominio;

import javax.persistence.*;

@Entity
public class Usuario {

    // Atributos - NOTA: Troquei o CPF e a data para String, testes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @Column(length = 60)
    private String nomeUsuario;
    @Column(length = 60)
    private String emailUsuario;
    @Column(length = 11)
    private String telefoneUsuario;
    @Column(length = 60)
    private String senhaUsuario;
    @Column(length = 10)
    private String dataNascimento;
    @Column(length = 1000)
    private String biografiaUsuario;
    private Double avaliacaoUsuario;
    @Column(length = 11)
    private String cpfUsuario;
    @Column(length = 60)
    private String cidadeUsuario;
    @Column(length = 2)
    private String ufUsuario;

    private Integer quantidadeDeProjetos;

    private Integer quantidadeDeFavoritos;
    @Column(length = 10)
    private String planoUsuario;

    // Construtor
    public Usuario(Integer idUsuario, String nomeUsuario, String emailUsuario, String telefoneUsuario, String senhaUsuario, String dataNascimento, String biografiaUsuario, Double avaliacaoUsuario, String cpfUsuario, String cidadeUsuario, String ufUsuario, Integer quantidadeDeProjetos, Integer quantidadeDeFavoritos, String planoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.telefoneUsuario = telefoneUsuario;
        this.senhaUsuario = senhaUsuario;
        this.dataNascimento = dataNascimento;
        this.biografiaUsuario = biografiaUsuario;
        this.avaliacaoUsuario = avaliacaoUsuario;
        this.cpfUsuario = cpfUsuario;
        this.cidadeUsuario = cidadeUsuario;
        this.ufUsuario = ufUsuario;
        this.quantidadeDeProjetos = quantidadeDeProjetos;
        this.quantidadeDeFavoritos = quantidadeDeFavoritos;
        this.planoUsuario = planoUsuario;
    }

    public Usuario() { //Foi necessário criar um construtor Default
    }

    // toString
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", telefoneUsuario='" + telefoneUsuario + '\'' +
                ", senhaUsuario='" + senhaUsuario + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", biografiaUsuario='" + biografiaUsuario + '\'' +
                ", avaliacaoUsuario=" + avaliacaoUsuario +
                ", cpfUsuario='" + cpfUsuario + '\'' +
                ", cidadeUsuario='" + cidadeUsuario + '\'' +
                ", ufUsuario='" + ufUsuario + '\'' +
                ", quantidadeDeProjetos=" + quantidadeDeProjetos +
                ", quantidadeDeFavoritos=" + quantidadeDeFavoritos +
                ", planoUsuario='" + planoUsuario + '\'' +
                '}';
    }

    // Getters e Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getBiografiaUsuario() {
        return biografiaUsuario;
    }

    public void setBiografiaUsuario(String biografiaUsuario) {
        this.biografiaUsuario = biografiaUsuario;
    }

    public Double getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(Double avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getCidadeUsuario() {
        return cidadeUsuario;
    }

    public void setCidadeUsuario(String cidadeUsuario) {
        this.cidadeUsuario = cidadeUsuario;
    }

    public String getUfUsuario() {
        return ufUsuario;
    }

    public void setUfUsuario(String ufUsuario) {
        this.ufUsuario = ufUsuario;
    }

    public Integer getQuantidadeDeProjetos() {
        return quantidadeDeProjetos;
    }

    public void setQuantidadeDeProjetos(Integer quantidadeDeProjetos) {
        this.quantidadeDeProjetos = quantidadeDeProjetos;
    }

    public Integer getQuantidadeDeFavoritos() {
        return quantidadeDeFavoritos;
    }

    public void setQuantidadeDeFavoritos(Integer quantidadeDeFavoritos) {
        this.quantidadeDeFavoritos = quantidadeDeFavoritos;
    }

    public String getPlanoUsuario() {
        return planoUsuario;
    }

    public void setPlanoUsuario(String planoUsuario) {
        this.planoUsuario = planoUsuario;
    }
}