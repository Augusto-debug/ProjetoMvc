package br.edu.iftm.tspi.pmvc.sistema_usuarios.domain;

import java.util.Objects;

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    private boolean administrador;
    public Usuario () {}
    public Usuario(String login) {
        this.login = login;
    }
    public Usuario (String login, String senha, String nome, boolean administrador) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.administrador = administrador;
    }
    public boolean isAdministrador() {
        return administrador;
    }
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(login);
    }
}
