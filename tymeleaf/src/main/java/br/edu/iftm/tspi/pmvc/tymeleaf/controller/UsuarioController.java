package br.edu.iftm.tspi.pmvc.tymeleaf.controller;

import br.edu.iftm.tspi.pmvc.tymeleaf.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UsuarioController {

    @GetMapping("/")
    public String init(Model model) {
        String nomeUsuario = "Carlos Eduardo";
        Usuario usuario = new Usuario();
        usuario.setNome(nomeUsuario);
        usuario.setAdministrador(false);
        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("usuario", usuario);
        List<String> nomes = Arrays.asList("Carlos", "Marco", "Amanda", "Maria");
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Carlos", "carlos", "123", true),
                new Usuario("Marco", "marco", "123", false),
                new Usuario("Amanda", "amanda", "123", true),
                new Usuario("Maria", "maria", "123", false)
        );
        model.addAttribute("nomes", nomes);
        model.addAttribute("usuarios", usuarios);
        return "userView";
    }
}
