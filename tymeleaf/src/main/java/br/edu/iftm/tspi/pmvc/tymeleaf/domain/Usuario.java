package br.edu.iftm.tspi.pmvc.tymeleaf.domain;

public class Usuario {

    private String nome;
    private String login;
    private String senha;
    private Boolean administrador;

    public Usuario() {
    }

    public Usuario(String nome, String login, String senha, Boolean administrador) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.administrador = administrador;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }
    public boolean isAdministrador() {
        return administrador;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
