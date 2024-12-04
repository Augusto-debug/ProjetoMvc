package br.edu.iftm.tspi.pmvc.trabalho1.controller;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Disciplina;
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
        Disciplina disciplina = new Disciplina();
        model.addAttribute(ATRIBUTO_OBJETO, disciplina);
        return URL_FORM;
    }

    @GetMapping("/editar/{codigo}")
    public String abrirFormEditar(@PathVariable("codigo") int codigo, Model model, RedirectAttributes redirectAttributes) {
        Disciplina disciplinaBusca = repository.buscaPorCodigo(codigo);
        if (disciplinaBusca == null) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, codigo + " não encontrado.");
            return URL_REDIRECT_LISTA;
        } else {
            model.addAttribute(ATRIBUTO_OBJETO, disciplinaBusca);
            return URL_FORM;
        }
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("disciplina") Disciplina disciplina, RedirectAttributes redirectAttributes) {
        repository.novo(disciplina);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, disciplina.getNome() + " salvo com sucesso");
        return URL_REDIRECT_LISTA;
    }

    @PostMapping(value = "/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") int codigo, RedirectAttributes redirectAttributes) {
        repository.delete(codigo);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Disciplina excluída com sucesso.");
        return URL_REDIRECT_LISTA;
    }

    @PostMapping("/editar/{codigo}")
    public String atualizar(@PathVariable("codigo") int codigo, @ModelAttribute("disciplina") Disciplina disciplina, RedirectAttributes redirectAttributes) {
        if (repository.update(disciplina)) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, disciplina.getNome() + " atualizado com sucesso");
        } else {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, " Não foi possível atualizar " + disciplina.getNome());
        }
        return URL_REDIRECT_LISTA;
    }
}