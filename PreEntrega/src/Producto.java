public class Producto {
    private static int SIGUIENTE_ID = 1;
    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id = SIGUIENTE_ID;
        SIGUIENTE_ID++;
    }


    public void mostrarInfo(){
        System.out.println("#########################");
        System.out.printf("""
                Id: %s
                Nombre: %s
                Precio: %s
                Stock: %s
                """, this.id, this.nombre, this.precio, this.stock);
        System.out.println("#########################");

    }

    public boolean contieneNombre(String busqueda){
        String nombreMinuscula = this.nombre.toLowerCase();



        return nombreMinuscula.contains(busqueda.toLowerCase());
    }



    public void venderProducto(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a cero.");
        }

        if (cantidad > this.stock) {
            System.out.println(" Stock insuficiente para " + this.nombre + ". Disponible: " + this.stock);
        }else{

            this.stock -= cantidad;
            System.out.println(" Venta realizada: " + cantidad + " unidades de " + this.nombre);
        }

    }

    // GETTERS y SETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
