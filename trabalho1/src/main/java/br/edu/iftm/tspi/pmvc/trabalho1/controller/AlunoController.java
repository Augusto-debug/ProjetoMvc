package br.edu.iftm.tspi.pmvc.trabalho1.controller;
import br.edu.iftm.tspi.pmvc.trabalho1.domain.Aluno;
import br.edu.iftm.tspi.pmvc.trabalho1.repository.AlunoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/aluno") // http://localhost:8080/aluno
public class AlunoController {
    private final AlunoRepository alunoRepository;
    public AlunoController() {
        this.alunoRepository = new AlunoRepository();
    }
    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }
    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        return alunoRepository.findById(id).orElse(null);
    }
    @PostMapping
    public Aluno addAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno existingAluno = alunoRepository.findById(id).orElse(null);
        if (existingAluno != null) {
            existingAluno.setNome(aluno.getNome());
            existingAluno.setEndereco(aluno.getEndereco());
            existingAluno.setTelefone(aluno.getTelefone());
            existingAluno.setEmail(aluno.getEmail());
            existingAluno.setDisciplinas(aluno.getDisciplinas());
            return alunoRepository.save(existingAluno);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }
}