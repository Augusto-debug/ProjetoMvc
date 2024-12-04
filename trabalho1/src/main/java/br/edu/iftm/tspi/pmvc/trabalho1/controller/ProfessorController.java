package br.edu.iftm.tspi.pmvc.trabalho1.controller;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Professor;
import br.edu.iftm.tspi.pmvc.trabalho1.repository.ProfessorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorRepository repository;

    public static final String URL_LISTA = "/professor/lista";
    public static final String URL_FORM = "/professor/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/professor";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "professor";
    public static final String ATRIBUTO_LISTA = "professores";

    public ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Professor> professores = repository.listar();
        model.addAttribute(ATRIBUTO_LISTA, professores);
        return URL_LISTA;
    }

    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model) {
        List<Professor> professoresBusca = repository.buscaPorNome(nome);
        model.addAttribute(ATRIBUTO_LISTA, professoresBusca);
        if (professoresBusca.isEmpty()) {
            model.addAttribute(ATRIBUTO_MENSAGEM, nome + " não encontrado.");
        }
        return URL_LISTA;
    }

    @GetMapping("/novo")
    public String abrirFormNovo(Model model) {
        Professor professor = new Professor();
        model.addAttribute(ATRIBUTO_OBJETO, professor);
        return URL_FORM;
    }

    @GetMapping("/editar/{codigo}")
    public String abrirFormEditar(@PathVariable("codigo") int codigo, Model model, RedirectAttributes redirectAttributes) {
        Professor professorBusca = repository.buscaPorCodigo(codigo);
        if (professorBusca == null) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, codigo + " não encontrado.");
            return URL_REDIRECT_LISTA;
        } else {
            model.addAttribute(ATRIBUTO_OBJETO, professorBusca);
            return URL_FORM;
        }
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("professor") Professor professor, RedirectAttributes redirectAttributes) {
        repository.novo(professor);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, professor.getNome() + " salvo com sucesso");
        return URL_REDIRECT_LISTA;
    }

    @PostMapping(value = "/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") int codigo, RedirectAttributes redirectAttributes) {
        repository.delete(codigo);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Professor excluído com sucesso.");
        return URL_REDIRECT_LISTA;
    }

    @PostMapping("/editar/{codigo}")
    public String atualizar(@PathVariable("codigo") int codigo, @ModelAttribute("professor") Professor professor, RedirectAttributes redirectAttributes) {
        if (repository.update(professor)) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, professor.getNome() + " atualizado com sucesso");
        } else {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, " Não foi possível atualizar " + professor.getNome());
        }
        return URL_REDIRECT_LISTA;
    }
}