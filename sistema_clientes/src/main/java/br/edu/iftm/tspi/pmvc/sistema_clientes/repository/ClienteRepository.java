package br.edu.iftm.tspi.pmvc.sistema_clientes.repository;

import br.edu.iftm.tspi.pmvc.sistema_clientes.domain.Cliente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteRepository {
    private List<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "João", "Rua 1"));
        clientes.add(new Cliente(2, "Maria", "Rua 2"));
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }
}
