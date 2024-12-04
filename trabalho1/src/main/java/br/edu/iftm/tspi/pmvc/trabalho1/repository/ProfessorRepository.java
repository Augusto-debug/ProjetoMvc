package br.edu.iftm.tspi.pmvc.trabalho1.repository;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Professor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {

    private final List<Professor> professores;

    public ProfessorRepository() {
        this.professores = new ArrayList<>();
        professores.add(new Professor(1, "João", "professor1@gmail.com"));
        professores.add(new Professor(2, "Maria", "professor2@gmail.com"));
    }

    public List<Professor> listar() {
        return this.professores;
    }

    public List<Professor> buscaPorNome(String nome) {
        List<Professor> professoresBusca = new ArrayList<>();
        for (Professor professor : this.professores) {
            if (professor.getNome().toLowerCase().contains(nome.toLowerCase())) {
                professoresBusca.add(professor);
            }
        }
        return professoresBusca;
    }

    public Professor buscaPorCodigo(int codigoProfessor) {
        for (Professor professor : this.professores) {
            if (professor.getCodigoProfessor() == codigoProfessor) {
                return professor;
            }
        }
        return null;
    }

    public void novo(Professor professor) {
        professores.add(professor);
    }

    public boolean delete(int codigoProfessor) {
        return professores.removeIf(professor -> professor.getCodigoProfessor() == codigoProfessor);
    }

    public boolean update(Professor professor) {
        int index = professores.indexOf(professor);
        if (index != -1) {
            professores.set(index, professor);
            return true;
        }
        return false;
    }
}