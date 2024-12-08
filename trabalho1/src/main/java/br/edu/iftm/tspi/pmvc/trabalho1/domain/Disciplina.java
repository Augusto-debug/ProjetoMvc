package br.edu.iftm.tspi.pmvc.trabalho1.domain;

import java.util.List;
import java.util.Objects;

public class Disciplina {
    private int codigoDisciplina;
    private String nome;
    private Aluno alunos;
    private Professor professor;

    public Disciplina(int codigoDisciplina, String nome, Aluno alunos, Professor professor) {
        this.codigoDisciplina = codigoDisciplina;
        this.nome = nome;
        this.alunos = alunos;
        this.professor = professor;
    }

    public Disciplina(int codigoDisciplina, String nome) {
        this.codigoDisciplina = codigoDisciplina;
        this.nome = nome;
    }

    public Disciplina() {
    }

    public Aluno getAluno() {
        return alunos;
    }

    public void setAluno(Aluno aluno) {
        this.alunos = aluno;
    }

    public int getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(int codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return codigoDisciplina == that.codigoDisciplina;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoDisciplina);
    }
}
