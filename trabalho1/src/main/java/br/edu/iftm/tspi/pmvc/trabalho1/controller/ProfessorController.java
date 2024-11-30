package br.edu.iftm.tspi.pmvc.trabalho1.controller;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Professor;
import br.edu.iftm.tspi.pmvc.trabalho1.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorRepository professorRepository;

    public ProfessorController() {
        this.professorRepository = new ProfessorRepository();
    }

    @GetMapping
    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Professor getProfessorById(@PathVariable int id) {
        return professorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Professor addProfessor(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable int id, @RequestBody Professor professor) {
        Professor existingProfessor = professorRepository.findById(id).orElse(null);
        if (existingProfessor != null) {
            existingProfessor.setNome(professor.getNome());
            existingProfessor.setEmail(professor.getEmail());
            existingProfessor.setTitulacao(professor.getTitulacao());
            return professorRepository.save(existingProfessor);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable int id) {
        professorRepository.deleteById(id);
    }
}