package br.edu.iftm.tspi.pmvc.trabalho1.repository;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Disciplina;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DisciplinaRepository {

    private final List<Disciplina> disciplinas = new ArrayList<>();

    public List<Disciplina> findAll() {
        return new ArrayList<>(disciplinas);
    }

    public Optional<Disciplina> findById(Long codigoDisciplina) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getCodigoDisciplina() == codigoDisciplina)
                .findFirst();
    }

    public Disciplina save(Disciplina disciplina) {
        Optional<Disciplina> existingDisciplina = findById(disciplina.getCodigoDisciplina());
        if (existingDisciplina.isPresent()) {
            int index = disciplinas.indexOf(existingDisciplina.get());
            disciplinas.set(index, disciplina);
        } else {
            disciplinas.add(disciplina);
        }
        return disciplina;
    }

    public void deleteById(Long codigoDisciplina) {
        disciplinas.removeIf(disciplina -> disciplina.getCodigoDisciplina() == codigoDisciplina);
    }
}