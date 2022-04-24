package com.towork.backendaplicacao.dominio;
import javax.persistence.*;

@Entity
public class Usuario {

    //Atributos - NOTA: Troquei o CPF e a data para String, testes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nomeUsuario;

    private String emailUsuario;
    private String senhaUsuario;
    private String dataNascimento;
    @Column(length = 1000)
    private String biografiaUsuario;
    private Double avaliacaoUsuario;
    private String cpfUsuario;
    private String cidadeUsuario;
    private String ufUsuario;
    private String planoUsuario;

    //Construtor
    public Usuario(Integer idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNascimento, String biografiaUsuario, Double avaliacaoUsuario, String cpfUsuario, String cidadeUsuario, String ufUsuario, String planoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.dataNascimento = dataNascimento;
        this.biografiaUsuario = biografiaUsuario;
        this.avaliacaoUsuario = avaliacaoUsuario;
        this.cpfUsuario = cpfUsuario;
        this.cidadeUsuario = cidadeUsuario;
        this.ufUsuario = ufUsuario;
        this.planoUsuario = planoUsuario;
    }

    public Usuario(){//Foi necessário criar um construtor Default
    }

    //ToString
    @Override
    public String toString() {
        return "Usuario{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", senhaUsuario='" + senhaUsuario + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", biografiaUsuario='" + biografiaUsuario + '\'' +
                ", avaliacaoUsuario=" + avaliacaoUsuario +
                ", cpfUsuario=" + cpfUsuario +
                ", cidadeUsuario='" + cidadeUsuario + '\'' +
                ", ufUsuario='" + ufUsuario + '\'' +
                ", planoUsuario='" + planoUsuario + '\'' +
                '}';
    }

    //Getters e Setters
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

    public String getPlanoUsuario() {
        return planoUsuario;
    }

    public void setPlanoUsuario(String planoUsuario) {
        this.planoUsuario = planoUsuario;
    }
}
