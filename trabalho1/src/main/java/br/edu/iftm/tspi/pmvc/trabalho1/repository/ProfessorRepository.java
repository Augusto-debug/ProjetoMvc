package br.edu.iftm.tspi.pmvc.trabalho1.repository;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Professor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorRepository {

    private final List<Professor> professores = new ArrayList<>();

    public List<Professor> findAll() {
        return new ArrayList<>(professores);
    }

    public Optional<Professor> findById(int codigoProfessor) {
        return professores.stream()
                .filter(professor -> professor.getCodigoProfessor() == codigoProfessor)
                .findFirst();
    }

    public Professor save(Professor professor) {
        Optional<Professor> existingProfessor = findById(professor.getCodigoProfessor());
        if (existingProfessor.isPresent()) {
            int index = professores.indexOf(existingProfessor.get());
            professores.set(index, professor);
        } else {
            professores.add(professor);
        }
        return professor;
    }

    public void deleteById(int codigoProfessor) {
        professores.removeIf(professor -> professor.getCodigoProfessor() == codigoProfessor);
    }
}