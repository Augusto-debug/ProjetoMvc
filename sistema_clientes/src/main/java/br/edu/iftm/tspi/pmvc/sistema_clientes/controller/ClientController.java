package br.edu.iftm.tspi.pmvc.sistema_clientes.controller;

import br.edu.iftm.tspi.pmvc.sistema_clientes.domain.Cliente;
import br.edu.iftm.tspi.pmvc.sistema_clientes.domain.Cliente;
import br.edu.iftm.tspi.pmvc.sistema_clientes.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {
    private ClienteRepository repository;

    public ClientController (ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clientes")
    public String getClientes(Model model) {
        model.addAttribute("clientes", repository.getClientes());
        return "clientesView";
    }

    @GetMapping("/add")
    public String exibirFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clienteAdd";
    }

    @PostMapping("/add")
    public String add(Model model, @ModelAttribute Cliente cliente) {
        repository.addCliente(cliente);
        return getClientes(model);
    }
}
