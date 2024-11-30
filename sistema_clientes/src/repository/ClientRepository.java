import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRepository {
    private List<Cliente> clientes;
    public ClientRepository() {
    clientes = new ArrayList<>();
    clientes.add(new Cliente(1, "João", "Rua 1"));
    clientes.add(new Cliente(2, "Maria", "Rua 2"));
    }
    public List<Cliente> getClients() {
        return clientes;
    }
    public void addClientes(Cliente client) {
        clientes.add(client);
    }
}
