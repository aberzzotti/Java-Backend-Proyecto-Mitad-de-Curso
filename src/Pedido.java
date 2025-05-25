import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final int id;
    private List<Producto> productos;

    public Pedido() {
        this.id = 0;
        this.productos = new ArrayList<>();
    }

    public boolean agregarProductoAPedido(Producto producto, int cantidad) {
        if (cantidad > producto.getStock()) {
            System.out.println("Stock insuficiente para " + producto.getNombre());
            return false;
        }

        this.productos.add(producto);
        producto.venderProducto(cantidad);

        System.out.println(" Producto agregado al pedido: " + producto.getNombre() + " (Cantidad: " + cantidad + ")");
        return true;
    }
}
