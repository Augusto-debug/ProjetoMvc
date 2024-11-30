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
    public Disciplina getDisciplinaById(@PathVariable Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }
    @PostMapping
    public Disciplina addDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }
    @PutMapping("/{id}")
    public Disciplina updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        Disciplina existingDisciplina = disciplinaRepository.findById(id).orElse(null);
        if (existingDisciplina != null) {
            existingDisciplina.setNome(disciplina.getNome());
            existingDisciplina.setCodigo(disciplina.getCodigo());
            existingDisciplina.setCargaHoraria(disciplina.getCargaHoraria());
            existingDisciplina.setPeriodo(disciplina.getPeriodo());
            existingDisciplina.setTurno(disciplina.getTurno());
            existingDisciplina.setProfessor(disciplina.getProfessor());
            return disciplinaRepository.save(existingDisciplina);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteDisciplina(@PathVariable Long id) {
        disciplinaRepository.deleteById(id);
    }
}