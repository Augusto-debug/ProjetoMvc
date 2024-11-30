package br.edu.iftm.tspi.pmvc.trabalho1.controller;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Aluno;
import br.edu.iftm.tspi.pmvc.trabalho1.repository.AlunoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
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
    public Aluno getAlunoById(@PathVariable int id) {
        return alunoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Aluno addAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable int id, @RequestBody Aluno aluno) {
        Aluno existingAluno = alunoRepository.findById(id).orElse(null);
        if (existingAluno != null) {
            existingAluno.setNome(aluno.getNome());
            existingAluno.setEmail(aluno.getEmail());
            existingAluno.setNumeroMatricula(aluno.getNumeroMatricula());
            return alunoRepository.save(existingAluno);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable int id) {
        alunoRepository.deleteById(id);
    }
}