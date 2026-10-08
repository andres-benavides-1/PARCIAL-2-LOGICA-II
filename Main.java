import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // REGISTRAR PRODUCTO

        ArrayList<Producto> productos = new ArrayList<>();

        Producto cafe = new Producto(
            "CAF-001",
            "Cafe americano",
            5000,
            20
        );

        Producto capuchino = new Producto(
            "CAF-002",
            "Capuchino",
            7000,
            15
        );

        Producto sandwich = new Producto(
            "COM-001",
            "Sandwich",
            10000,
            12
        );

        Producto brownie = new Producto(
            "POS-001",
            "Brownie",
            6000,
            18
        );

        productos.add(cafe);
        productos.add(capuchino);
        productos.add(sandwich);
        productos.add(brownie);


        // MOSTRAR PRODUCTOS DISPONIBLES

        System.out.println("========================================");
        System.out.println("       PRODUCTOS DE LA CAFETERIA");
        System.out.println("========================================");

        for (Producto producto : productos) {

            producto.mostrarInformacion();

            System.out.println("----------------------------------------");
        }


        // BUSCAR PRODUCTO POR CODIGO

        System.out.println("\n========================================");
        System.out.println("       BUSQUEDA POR CODIGO");
        System.out.println("========================================");

        Producto resultado = buscarProducto(
            productos,
            "CAF-002"
        );

        if (resultado != null) {

            resultado.mostrarInformacion();

        } else {

            System.out.println("Producto no encontrado.");
        }


        // BUSCAR PRODUCTO POR NOMBRE

        System.out.println("\n========================================");
        System.out.println("       BUSQUEDA POR NOMBRE");
        System.out.println("========================================");

        resultado = buscarProducto(
            productos,
            "Sandwich"
        );

        if (resultado != null) {

            resultado.mostrarInformacion();

        } else {

            System.out.println("Producto no encontrado.");
        }


        // MODIFICAR INFORMACION DEL PRODUCTO

        System.out.println("\n========================================");
        System.out.println("       MODIFICAR PRODUCTO");
        System.out.println("========================================");

        System.out.println(
            "Precio anterior del cafe: $" +
            cafe.getPrecio()
        );

        cafe.actualizarPrecio(5500);

        System.out.println(
            "Precio nuevo del cafe: $" +
            cafe.getPrecio()
        );

        System.out.println(
            "Cantidad anterior: " +
            cafe.getCantidadDisponible()
        );

        cafe.aumentarCantidad(5);

        System.out.println(
            "Cantidad despues de recibir productos: " +
            cafe.getCantidadDisponible()
        );


        // REGISTRAR CLIENTES

        ArrayList<Cliente> clientes = new ArrayList<>();

        Cliente cliente1 = new Cliente(
            "1001",
            "Andres Benavides",
            "andres@gmail.com"
        );

        Cliente cliente2 = new Cliente(
            "1002",
            "Carlos Perez",
            "carlos@gmail.com"
        );

        clientes.add(cliente1);
        clientes.add(cliente2);


        // MOSTRAR CLIENTES

        System.out.println("\n========================================");
        System.out.println("              CLIENTES");
        System.out.println("========================================");

        for (Cliente cliente : clientes) {

            cliente.mostrarInformacion();

            System.out.println("----------------------------------------");
        }


        // CREAR PRIMER PEDIDO

        Pedido pedido1 = new Pedido(cliente1);


        // Agregar productos
        boolean agregado;

        agregado = pedido1.agregarProducto(cafe, 2);

        System.out.println(
            "\nAgregar 2 cafes: " +
            (agregado ? "Correcto" : "No fue posible")
        );


        agregado = pedido1.agregarProducto(capuchino, 1);

        System.out.println(
            "Agregar 1 capuchino: " +
            (agregado ? "Correcto" : "No fue posible")
        );


        agregado = pedido1.agregarProducto(brownie, 3);

        System.out.println(
            "Agregar 3 brownies: " +
            (agregado ? "Correcto" : "No fue posible")
        );


        // AGREGAR NUEVAMENTE EL MISMO PRODUCTO

        agregado = pedido1.agregarProducto(cafe, 2);

        System.out.println(
            "Agregar 2 cafes adicionales: " +
            (agregado ? "Correcto" : "No fue posible")
        );


       // MOSTRAR PRIMER PEDIDO

        pedido1.mostrarPedido();


        // CREAR SEGUNDO PEDIDO

        Pedido pedido2 = new Pedido(cliente2);

        pedido2.agregarProducto(sandwich, 2);
        pedido2.agregarProducto(capuchino, 2);

        pedido2.mostrarPedido();


        // PROBAR LIMITE DE 10 UNIDADES

        System.out.println("\n========================================");
        System.out.println("       PRUEBA DEL LIMITE DE 10");
        System.out.println("========================================");

        Pedido pedido3 = new Pedido(cliente1);

        agregado = pedido3.agregarProducto(
            brownie,
            11
        );

        if (agregado) {

            System.out.println(
                "Producto agregado correctamente."
            );

        } else {

            System.out.println(
                "ERROR: No se pueden agregar mas de " +
                Pedido.MAX_UNIDADES_POR_PRODUCTO +
                " unidades del mismo producto."
            );
        }


        // =====================================================
        // 13. CALCULAR TOTALES
        // =====================================================

        System.out.println("\n========================================");
        System.out.println("          TOTALES DE PEDIDOS");
        System.out.println("========================================");

        System.out.println(
            "Total pedido 1: $" +
            pedido1.calcularTotal()
        );

        System.out.println(
            "Total pedido 2: $" +
            pedido2.calcularTotal()
        );


        // INVENTARIO DESPUES DE LAS VENTAS

        System.out.println("\n========================================");
        System.out.println("      INVENTARIO DESPUES DE VENTAS");
        System.out.println("========================================");

        for (Producto producto : productos) {

            System.out.println(
                producto.getNombre() +
                " -> " +
                producto.getCantidadDisponible() +
                " unidades disponibles."
            );
        }
    }


    // METODO DE BUSQUEDA

    public static Producto buscarProducto(
        ArrayList<Producto> productos,
        String dato
    ) {

        for (Producto producto : productos) {

            if (
                producto.getCodigo().equalsIgnoreCase(dato)
                ||
                producto.getNombre().equalsIgnoreCase(dato)
            ) {

                return producto;
            }
        }

        return null;
    }
}