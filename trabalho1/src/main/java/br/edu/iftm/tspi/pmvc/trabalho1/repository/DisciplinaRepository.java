package br.edu.iftm.tspi.pmvc.trabalho1.repository;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Disciplina;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DisciplinaRepository {

    private final List<Disciplina> disciplinas;

    public DisciplinaRepository() {
        this.disciplinas = new ArrayList<>();
        disciplinas.add(new Disciplina(1, "Matemática"));
        disciplinas.add(new Disciplina(2, "Português"));
    }

    public List<Disciplina> listar() {
        return this.disciplinas;
    }

    public List<Disciplina> buscaPorNome(String nome) {
        List<Disciplina> disciplinasBusca = new ArrayList<>();
        for (Disciplina disciplina : this.disciplinas) {
            if (disciplina.getNome().toLowerCase().contains(nome.toLowerCase())) {
                disciplinasBusca.add(disciplina);
            }
        }
        return disciplinasBusca;
    }

    public Disciplina buscaPorCodigo(int codigoDisciplina) {
        for (Disciplina disciplina : this.disciplinas) {
            if (disciplina.getCodigoDisciplina() == codigoDisciplina) {
                return disciplina;
            }
        }
        return null;
    }

    public void novo(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public boolean delete(int codigoDisciplina) {
        return disciplinas.removeIf(disciplina -> disciplina.getCodigoDisciplina() == codigoDisciplina);
    }

    public boolean update(Disciplina disciplina) {
        int index = disciplinas.indexOf(disciplina);
        if (index != -1) {
            disciplinas.set(index, disciplina);
            return true;
        }
        return false;
    }
}