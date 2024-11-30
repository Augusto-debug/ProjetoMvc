package br.edu.iftm.tspi.pmvc.trabalho1.repository;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Aluno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlunoRepository {

    private final List<Aluno> alunos = new ArrayList<>();

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos);
    }

    public Optional<Aluno> findById(long codigoAluno) {
        return alunos.stream()
                .filter(aluno -> aluno.getCodigoAluno() == codigoAluno)
                .findFirst();
    }

    public Aluno save(Aluno aluno) {
        Optional<Aluno> existingAluno = findById(aluno.getCodigoAluno());
        if (existingAluno.isPresent()) {
            int index = alunos.indexOf(existingAluno.get());
            alunos.set(index, aluno);
        } else {
            alunos.add(aluno);
        }
        return aluno;
    }

    public void deleteById(long codigoAluno) {
        alunos.removeIf(aluno -> aluno.getCodigoAluno() == codigoAluno);
    }
}