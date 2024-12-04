package br.edu.iftm.tspi.pmvc.trabalho1.controller;

import br.edu.iftm.tspi.pmvc.trabalho1.domain.Aluno;
import br.edu.iftm.tspi.pmvc.trabalho1.repository.AlunoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoRepository repository;

    public static final String URL_LISTA = "/aluno/lista";
    public static final String URL_FORM = "/aluno/form";
    public static final String URL_REDIRECT_LISTA = "redirect:/aluno";

    public static final String ATRIBUTO_MENSAGEM = "mensagem";
    public static final String ATRIBUTO_OBJETO = "aluno";
    public static final String ATRIBUTO_LISTA = "alunos";

    public AlunoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Aluno> alunos = repository.listar();
        model.addAttribute(ATRIBUTO_LISTA, alunos);
        return URL_LISTA;
    }

    @GetMapping("/buscar")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model) {
        List<Aluno> alunosBusca = repository.buscaPorNome(nome);
        model.addAttribute(ATRIBUTO_LISTA, alunosBusca);
        if (alunosBusca.isEmpty()) {
            model.addAttribute(ATRIBUTO_MENSAGEM, nome + " não encontrado.");
        }
        return URL_LISTA;
    }

    @GetMapping("/novo")
    public String abrirFormNovo(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute(ATRIBUTO_OBJETO, aluno);
        return URL_FORM;
    }

    @GetMapping("/editar/{codigo}")
    public String abrirFormEditar(@PathVariable("codigo") int codigo, Model model, RedirectAttributes redirectAttributes) {
        Aluno alunoBusca = repository.buscaPorCodigo(codigo);
        if (alunoBusca == null) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, codigo + " não encontrado.");
            return URL_REDIRECT_LISTA;
        } else {
            model.addAttribute(ATRIBUTO_OBJETO, alunoBusca);
            return URL_FORM;
        }
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("aluno") Aluno aluno, RedirectAttributes redirectAttributes) {
        repository.novo(aluno);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, aluno.getNome() + " salvo com sucesso");
        return URL_REDIRECT_LISTA;
    }

    @PostMapping(value = "/excluir/{codigo}")
    public String excluir(@PathVariable("codigo") int codigo, RedirectAttributes redirectAttributes) {
        repository.delete(codigo);
        redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, "Aluno excluído com sucesso.");
        return URL_REDIRECT_LISTA;
    }

    @PostMapping("/editar/{codigo}")
    public String atualizar(@PathVariable("codigo") int codigo, @ModelAttribute("aluno") Aluno aluno, RedirectAttributes redirectAttributes) {
        if (repository.update(aluno)) {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, aluno.getNome() + " atualizado com sucesso");
        } else {
            redirectAttributes.addFlashAttribute(ATRIBUTO_MENSAGEM, " Não foi possível atualizar " + aluno.getNome());
        }
        return URL_REDIRECT_LISTA;
    }
}