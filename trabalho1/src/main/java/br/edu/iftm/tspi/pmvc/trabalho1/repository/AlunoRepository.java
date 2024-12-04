package br.edu.iftm.tspi.pmvc.trabalho1.repository;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Aluno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepository {

    private final List<Aluno> alunos;

    public AlunoRepository() {
        this.alunos = new ArrayList<>();
        alunos.add(new Aluno(1, "João", "teste@gmail.com"));
        alunos.add(new Aluno(2, "Maria", "maria@gmail.com"));
    }

    public List<Aluno> listar() {
        return this.alunos;
    }

    public List<Aluno> buscaPorNome(String nome) {
        List<Aluno> alunosBusca = new ArrayList<>();
        for (Aluno aluno : this.alunos) {
            if (aluno.getNome().toLowerCase().contains(nome.toLowerCase())) {
                alunosBusca.add(aluno);
            }
        }
        return alunosBusca;
    }

    public Aluno buscaPorCodigo(int codigoAluno) {
        for (Aluno aluno : this.alunos) {
            if (aluno.getCodigoAluno() == codigoAluno) {
                return aluno;
            }
        }
        return null;
    }

    public void novo(Aluno aluno) {
        alunos.add(aluno);
    }

    public boolean delete(int codigoAluno) {
        return alunos.removeIf(aluno -> aluno.getCodigoAluno() == codigoAluno);
    }

    public boolean update(Aluno aluno) {
        int index = alunos.indexOf(aluno);
        if (index != -1) {
            alunos.set(index, aluno);
            return true;
        }
        return false;
    }
}