import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        Scanner entrada = new Scanner(System.in);
        int opcionUsuario = 0;


        do {
            try {
                System.out.println("""
                        Menu principal:
                          1) Agregar producto
                          2) Listar productos
                          3) Buscar/Actualizar producto
                          4) Eliminar producto
                          5) Crear un pedido
                          6) Listar pedidos
                          7) Salir
                        
                        Elija una opción:
                        """);
                opcionUsuario = entrada.nextInt();

                switch (opcionUsuario) {
                    case 1 -> agregarProducto(productos);
                    case 2 -> listarProductos(productos);
                    case 3 -> buscarProducto(productos);
                    case 4 -> eliminarProducto(productos);
                    case 5 -> agregarPedido(pedidos,productos);
                    case 6 -> listarProductos(productos);
                    case 7 -> System.out.println("Gracias por usar la app!");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Hubo un error inesperado");
            }
        } while (opcionUsuario != 7);

    }


    public static void agregarPedido(ArrayList<Pedido> pedidos, ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        Pedido nuevoPedido = new Pedido();
        while (true) {
            System.out.println("Ingrese el nombre del producto que desea agregar (o escriba 'salir' para finalizar): ");
            String nombreProducto = entrada.nextLine().trim();

            if (nombreProducto.equalsIgnoreCase("salir")) {
                break;
            }

            ArrayList<Producto> productoSeleccionado = buscarProductoPorNombre(productos, nombreProducto);

            if (productoSeleccionado.isEmpty()) {
                System.out.println("No encontramos productos");
                continue;
            }
            Producto p = productoSeleccionado.get(0);
            System.out.printf("Ingrese la cantidad de '%s' que desea agregar: ", p.getNombre());
            int cantidad = entrada.nextInt();
            entrada.nextLine();

            nuevoPedido.agregarProductoAPedido(p, cantidad);
            System.out.println(" Producto agregado al pedido.");
        }

        pedidos.add(nuevoPedido);
        System.out.println(" Pedido registrado exitosamente!");
    }

    private static ArrayList<Producto> buscarProductoPorNombre(ArrayList<Producto> productos, String nombre) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.contieneNombre(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }


    private static void eliminarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el ID del producto a eliminar: ");
        int idAEliminar = entrada.nextInt();
        boolean eliminado = false;
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getId() == idAEliminar) {
                encontrado = true;
                System.out.println("El producto que quiere eliminar es el siguiente: ");
                producto.mostrarInfo();
                System.out.println("Confirma la accion?");
                System.out.println("1 - SI");
                System.out.println("2 - NO");
                int opcionEliminar = entrada.nextInt();
                if (opcionEliminar == 1) {
                    System.out.println("ELIMINANDO");
                    productos.remove(producto);
                    eliminado = true;
                    break;
                }
            }
        }

        if (!encontrado) {
            throw new RuntimeException("No encontramos ningun producto con el id: " + idAEliminar);
        }

        if (eliminado) {
            System.out.println("Eliminado con exito");
        }
    }

    private static void buscarProducto(ArrayList<Producto> productos) {
        System.out.println("Buscador de productos: ");
        Scanner entrada = new Scanner(System.in);
        String busqueda = entrada.nextLine();
        ArrayList<Producto> productosEncontrados = buscarProductoPorNombre(productos, busqueda);

        if (productosEncontrados.isEmpty()) {
            System.out.println("No encontramos productos");
        } else {
            for (Producto producto : productosEncontrados) {
                producto.mostrarInfo();
            }
        }
    }

    private static void listarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay producto todavia :(");
        } else {
            for (Producto producto : productos) {
                producto.mostrarInfo();
            }
        }
    }


    public static void agregarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("""
                Menu para agregar un producto
                Ingrese el nombre del producto:
                """)
        ;
        String nombre = entrada.nextLine();
        double precio = 0;
        while (true) {
            try {
                System.out.printf("Ingrese el precio de %s: ", nombre);
                precio = entrada.nextDouble();
                if (precio > 0) {
                    break;
                } else {
                    System.out.println("El precio debe ser un valor positivo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            }
        }

        int stock = 0;
        while (true) {
            try {
                System.out.printf("Ingrese el stock de %s: ", nombre);
                stock = entrada.nextInt();
                if (stock >= 0) {
                    break;
                } else {
                    System.out.println("El stock no puede ser negativo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                entrada.nextLine();
            }
        }


        Producto producto = new Producto(nombre, precio, stock);
        productos.add(producto);

        System.out.println("Producto cargado exitosamente!");
    }
}
