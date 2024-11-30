import java.util.Date;
import java.util.List;

public class Pedido {
    private Integer id;
    private Date data;
    private Cliente cliente;
    private List<ItemPedido> itensPedidos;
    public Pedido(Integer id, Date data, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
    }
}
