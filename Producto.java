import java.util.ArrayList;


public class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private int cantidadDisponible;

    // Constructor
    public Producto(String codigo, String nombre, double precio, int cantidadDisponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    // Actualizar precio
    public void actualizarPrecio(double nuevoPrecio) {

        if (nuevoPrecio >= 0) {
            precio = nuevoPrecio;
        }
    }

    // Aumentar inventario
    public void aumentarCantidad(int cantidad) {

        if (cantidad > 0) {
            cantidadDisponible += cantidad;
        }
    }

    // Disminuir inventario
    public boolean disminuirCantidad(int cantidad) {

        if (cantidad > 0 && cantidad <= cantidadDisponible) {
            cantidadDisponible -= cantidad;
            return true;
        }

        return false;
    }

    // Mostrar información
    public void mostrarInformacion() {

        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Cantidad disponible: " + cantidadDisponible);
    }
}


// CLASE CLIENTE

class Cliente {

    private final String identificacion;
    private final String nombre;
    private final String correo;

    // Constructor
    public Cliente(String identificacion, String nombre, String correo) {

        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters
    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    // Mostrar información
    public void mostrarInformacion() {

        System.out.println("Identificacion: " + identificacion);
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
    }
}


// CLASE DETALLE PEDIDO

class DetallePedido {

    private String codigoProducto;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;

    // Constructor
    public DetallePedido(Producto producto, int cantidad) {

        this.codigoProducto = producto.getCodigo();
        this.nombreProducto = producto.getNombre();
        this.precioUnitario = producto.getPrecio();
        this.cantidad = cantidad;
    }

    // Getters
    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Aumentar cantidad del detalle
    public void aumentarCantidad(int cantidad) {

        this.cantidad += cantidad;
    }

    // Calcular valor del detalle
    public double calcularValor() {

        return precioUnitario * cantidad;
    }

    // Mostrar detalle
    public void mostrarDetalle() {

        System.out.println(
            codigoProducto +
            " | " +
            nombreProducto +
            " | Precio: $" +
            precioUnitario +
            " | Cantidad: " +
            cantidad +
            " | Valor: $" +
            calcularValor()
        );
    }
}

// CLASE PEDIDO

class Pedido {

    // Regla general de la cafeteria
    public static final int MAX_UNIDADES_POR_PRODUCTO = 10;

    private Cliente cliente;

    // Los detalles forman parte del pedido
    private ArrayList<DetallePedido> detalles;

    // Constructor
    public Pedido(Cliente cliente) {

        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

    // Agregar producto al pedido
    public boolean agregarProducto(Producto producto, int cantidad) {

        // La cantidad debe ser positiva
        if (cantidad <= 0) {
            return false;
        }

        // No puede superar 10 unidades
        if (cantidad > MAX_UNIDADES_POR_PRODUCTO) {
            return false;
        }

        // Debe existir suficiente inventario
        if (cantidad > producto.getCantidadDisponible()) {
            return false;
        }

        // Buscar si el producto ya existe en el pedido
        for (DetallePedido detalle : detalles) {

            if (detalle.getCodigoProducto().equals(producto.getCodigo())) {

                int nuevaCantidad = detalle.getCantidad() + cantidad;

                // Verificar nuevamente el limite de 10
                if (nuevaCantidad > MAX_UNIDADES_POR_PRODUCTO) {
                    return false;
                }

                // Descontar del inventario
                if (producto.disminuirCantidad(cantidad)) {

                    detalle.aumentarCantidad(cantidad);

                    return true;
                }

                return false;
            }
        }

        // Si el producto no estaba en el pedido
        if (producto.disminuirCantidad(cantidad)) {

            DetallePedido nuevoDetalle =
                new DetallePedido(producto, cantidad);

            detalles.add(nuevoDetalle);

            return true;
        }

        return false;
    }

    // Calcular total
    public double calcularTotal() {

        double total = 0;

        for (DetallePedido detalle : detalles) {

            total += detalle.calcularValor();
        }

        return total;
    }

    // Mostrar información del pedido
    public void mostrarPedido() {

        System.out.println("\n========================================");
        System.out.println("              PEDIDO");
        System.out.println("========================================");

        System.out.println(
            "Cliente: " +
            cliente.getNombre()
        );

        System.out.println(
            "Identificacion: " +
            cliente.getIdentificacion()
        );

        System.out.println("\nProductos:");

        for (DetallePedido detalle : detalles) {

            detalle.mostrarDetalle();
        }

        System.out.println("----------------------------------------");

        System.out.println(
            "TOTAL DEL PEDIDO: $" +
            calcularTotal()
        );
    }
}