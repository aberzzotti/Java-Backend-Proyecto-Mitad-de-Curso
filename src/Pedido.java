import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final int id;
    private List<Producto> productos;

    public Pedido() {
        this.id = 0;
        this.productos = new ArrayList<>();
    }

    public void agregarProductoAPedido(Producto producto){
        this.productos.add(producto);
    }
}
