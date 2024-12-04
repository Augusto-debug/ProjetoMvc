package br.edu.iftm.tspi.pmvc.trabalho1.domain;

public class Professor {
    private int codigoProfessor;
    private String nome;
    private String email;
    private String titulacao;

    public Professor(int codigoProfessor, String nome, String email, String titulacao, String areaAtuacao) {
        this.codigoProfessor = codigoProfessor;
        this.nome = nome;
        this.email = email;
    }

    public Professor() {
    }

    public Professor(int codigoProfessor, String nome, String email) {
        this.codigoProfessor = codigoProfessor;
        this.nome = nome;
        this.email = email;
    }
    public int getCodigoProfessor() {
        return codigoProfessor;
    }

    public void setCodigoProfessor(int codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}