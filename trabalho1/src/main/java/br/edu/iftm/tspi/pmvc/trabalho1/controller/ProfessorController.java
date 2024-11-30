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
    public Professor getProfessorById(@PathVariable Long id) {
        return professorRepository.findById(id).orElse(null);
    }
    @PostMapping
    public Professor addProfessor(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }
    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        Professor existingProfessor = professorRepository.findById(id).orElse(null);
        if (existingProfessor != null) {
            existingProfessor.setNome(professor.getNome());
            existingProfessor.setMatricula(professor.getMatricula());
            existingProfessor.setEndereco(professor.getEndereco());
            existingProfessor.setTelefone(professor.getTelefone());
            existingProfessor.setEmail(professor.getEmail());
            existingProfessor.setDisciplina(professor.getDisciplina());
            return professorRepository.save(existingProfessor);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable Long id) {
        professorRepository.deleteById(id);
    }
}