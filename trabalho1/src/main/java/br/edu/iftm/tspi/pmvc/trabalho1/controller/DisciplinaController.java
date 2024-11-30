package br.edu.iftm.tspi.pmvc.trabalho1.controller;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Disciplina;
import br.edu.iftm.tspi.pmvc.trabalho1.repository.DisciplinaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaController() {
        this.disciplinaRepository = new DisciplinaRepository();
    }

    @GetMapping
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Disciplina getDisciplinaById(@PathVariable int id) {
        return disciplinaRepository.findById((long) id).orElse(null);
    }

    @PostMapping
    public Disciplina addDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @PutMapping("/{id}")
    public Disciplina updateDisciplina(@PathVariable int id, @RequestBody Disciplina disciplina) {
        Disciplina existingDisciplina = disciplinaRepository.findById((long) id).orElse(null);
        if (existingDisciplina != null) {
            existingDisciplina.setNome(disciplina.getNome());
            existingDisciplina.setCodigoDisciplina((int)disciplina.getCodigoDisciplina());
            existingDisciplina.setAluno(disciplina.getAluno());
            existingDisciplina.setProfessor(disciplina.getProfessor());
            return disciplinaRepository.save(existingDisciplina);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDisciplina(@PathVariable int id) {
        disciplinaRepository.deleteById((long) id);
    }
}