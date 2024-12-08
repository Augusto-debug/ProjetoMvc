package br.edu.iftm.tspi.pmvc.trabalho1.controller;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Aluno;
import br.edu.iftm.tspi.pmvc.trabalho1.domain.Disciplina;
import br.edu.iftm.tspi.pmvc.trabalho1.domain.Professor;
import br.edu.iftm.tspi.pmvc.trabalho1.repository.DisciplinaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaRepository repository;

    public static final String URL_LISTA = "disciplina/lista";
    public static final String URL_FORM = "disciplina/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/disciplina";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "disciplina";
    public static final String ATRIBUTO_LISTA = "disciplinas";

    public DisciplinaController(DisciplinaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Disciplina> disciplinas = repository.listar();
        model.addAttribute(ATRIBUTO_LISTA, disciplinas);
        return URL_LISTA;
    }

    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model) {
        List<Disciplina> disciplinasBusca = repository.buscaPorNome(nome);
        model.addAttribute(ATRIBUTO_LISTA, disciplinasBusca);
        if (disciplinasBusca.isEmpty()) {
            model.addAttribute(ATRIBUTO_MENSAGEM, nome + " não encontrado.");
        }
        return URL_LISTA;
    }

    @GetMapping("/novo")
    public String abrirFormNovo(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplina/form";
    }

    @GetMapping("/editar/{codigo}")
    public String abrirFormEditar(@PathVariable("codigo") int codigo, Model model, RedirectAttributes redirectAttributes) {
        Disciplina disciplina = repository.buscaPorCodigo(codigo);
        if (disciplina != null) {
            model.addAttribute("disciplina", disciplina);
            return "disciplina/form";
        }
        return "redirect:/disciplina";
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("disciplina") Disciplina disciplina, RedirectAttributes redirectAttributes) {
        if (disciplina.getAluno() != null && !disciplina.getAluno().getNome().isBlank()) {
            Aluno aluno = disciplina.getAluno();
        } else {
            disciplina.setAluno(new Aluno());
        }
        if (disciplina.getProfessor() != null && !disciplina.getProfessor().getNome().isBlank()) {
            Professor professor = disciplina.getProfessor();
        } else {
            disciplina.setProfessor(new Professor());
        }
        repository.novo(disciplina);
        redirectAttributes.addFlashAttribute("mensagem", "Disciplina salva com sucesso");
        return "redirect:/disciplina";
    }

    @PostMapping("/editar/{codigo}")
    public String atualizar(@PathVariable("codigo") int codigo,
                            @ModelAttribute("disciplina") Disciplina disciplina,
                            @RequestParam("alunoNome") String alunoNome,
                            @RequestParam("professorNome") String professorNome,
                            RedirectAttributes redirectAttributes) {
        if (alunoNome != null && !alunoNome.isBlank()) {
            Aluno aluno = new Aluno();
            aluno.setNome(alunoNome);
            disciplina.setAluno(aluno);
        }
        if (professorNome != null && !professorNome.isBlank()) {
            Professor professor = new Professor();
            professor.setNome(professorNome);
            disciplina.setProfessor(professor);
        }

        disciplina.setCodigoDisciplina(codigo);

        if (repository.update(disciplina)) {
            redirectAttributes.addFlashAttribute("mensagem", "Disciplina atualizada com sucesso");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar a disciplina");
        }

        return "redirect:/disciplina";
    }

    @PostMapping(value = "/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") int codigo, RedirectAttributes redirectAttributes) {
        repository.delete(codigo);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Disciplina excluída com sucesso.");
        return URL_REDIRECT_LISTA;
    }
}