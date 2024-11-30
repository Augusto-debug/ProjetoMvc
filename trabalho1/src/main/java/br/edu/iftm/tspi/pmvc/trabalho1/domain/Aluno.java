package br.edu.iftm.tspi.pmvc.trabalho1.domain;
import java.util.ArrayList;
import java.util.List;
public class Aluno {
    private int codigoAluno;
    private String nome;
    private String email;
    private String numeroMatricula;
    public Aluno(int codigoAluno, String email, String nome, String numeroMatricula) {
        this.codigoAluno = codigoAluno;
        this.email = email;
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
    }
    public Aluno (){};
    public int getCodigoAluno() {
        return codigoAluno;
    }
    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
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
    public String getNumeroMatricula() {
        return numeroMatricula;
    }
    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
}
