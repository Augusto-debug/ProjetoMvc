package br.edu.iftm.tspi.pmvc.trabalho1.domain;

public class Disciplina {
    private int codigoDisciplina;
    private String nome;
    private Aluno aluno;
    private Professor professor;

    public Disciplina(int codigoDisciplina, String nome, Aluno aluno, Professor professor) {
        this.codigoDisciplina = codigoDisciplina;
        this.nome = nome;
        this.aluno = aluno;
        this.professor = professor;
    }

    public Disciplina(int codigoDisciplina, String nome) {
        this.codigoDisciplina = codigoDisciplina;
        this.nome = nome;
    }

    public Disciplina() {
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public long getCodigoDisciplina() {
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
}
