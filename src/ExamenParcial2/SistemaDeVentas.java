/*=========================================================================
LABORATORIO EN PROGRAMACION II - SEGUNDO EXAMEN PARCIAL
Profesor: Facundo Uferer
Alumno: Barreto Agustin Alejandro
Fecha: 16/11/2023
=========================================================================*/

package ExamenParcial2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaDeVentas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===============================================================");
            System.out.println("Sistema de Gesstion de Ventas:");
            System.out.println("1. Generar informe de productos en stok");
            System.out.println("2. Obtenr un producto por ID");
            System.out.println("3. Obtener el produto mas vendido");
            System.out.println("4. Obtener listado de vendedores");
            System.out.println("5. Salir");
            System.out.println("===============================================================");
            System.out.print("Ingrese una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1:
                    Productos.generarInforme();
                    break;
                case 2:
                    System.out.print("Ingrese el ID del producto: ");
                    int idProducto = scanner.nextInt();
                    scanner.nextLine(); // limpia el buffer
                    Producto producto = Productos.obtenerProducto(idProducto);
                    if (producto != null) {
                        System.out.println("Producto obtenido: " + producto.toString());
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 3:
                    Producto productoMasVendido = Productos.obtenerProductoMasVendido();
                    if (productoMasVendido != null) {
                        System.out.println("Producto mas vendido: " + productoMasVendido.toString());
                    } else {
                        System.out.println("Todavai no hay productos vendidos.");
                    }
                    break;
                case 4:
                    ArrayList<Vendedor> vendedores = Comerciales.listadoDeVendedores();
                    if (!vendedores.isEmpty()) {
                        System.out.println("Listado de vendedores:");
                        for (Vendedor vendedor : vendedores) {
                            System.out.println(vendedor.toString());
                        }
                    } else {
                        System.out.println("No hay vendedores registrados.");
                    }
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al 5.");
            }
        }
    }
}

class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/ventas";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ejecutarConsulta(String consulta) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}


class Productos {
    public static void generarInforme() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM productos");

            System.out.println("Informe de Productos en Stock:");
            System.out.printf("%-30s %-8s %-8s %-8s\n", "Producto", "Stock", "Precio", "Total");
            System.out.println("------------------------------------------");

            double totalGeneral = 0;

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int stock = resultSet.getInt("stock");
                double precioPorUnidad = resultSet.getDouble("precio_por_unidad");
                double total = stock * precioPorUnidad;

                System.out.printf("%-30s %-8d %-8.2f %-8.2f\n", nombre, stock, precioPorUnidad, total);

                totalGeneral += total;
            }

            System.out.println("------------------------------------------");
            System.out.printf("%-46s %.2f\n", "Total:", totalGeneral);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResultSet(resultSet);
            DBHelper.closeStatement(statement);
            DBHelper.closeConnection(connection);
        }
    }


    public static Producto obtenerProducto(int productoID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBHelper.getConnection();
            String sql = "SELECT * FROM productos WHERE producto_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productoID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("producto_id");
                String nombre = resultSet.getString("nombre");
                double precioPorUnidad = resultSet.getDouble("precio_por_unidad");
                int stock = resultSet.getInt("stock");

                return new Producto(id, nombre, precioPorUnidad, stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResultSet(resultSet);
            DBHelper.closeStatement(preparedStatement);
            DBHelper.closeConnection(connection);
        }

        return null;
    }

    public static Producto obtenerProductoMasVendido() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT producto_id, SUM(cantidad_vendida) as total_vendido " +
                    "FROM ventas GROUP BY producto_id ORDER BY total_vendido DESC LIMIT 1");

            if (resultSet.next()) {
                int productoID = resultSet.getInt("producto_id");
                return obtenerProducto(productoID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResultSet(resultSet);
            DBHelper.closeStatement(statement);
            DBHelper.closeConnection(connection);
        }

        return null;
    }
}

class Producto {
    private int productoID;
    private String nombre;
    private double precioPorUnidad;
    private int stock;

    public Producto(int productoID, String nombre, double precioPorUnidad, int stock) {
        this.productoID = productoID;
        this.nombre = nombre;
        this.precioPorUnidad = precioPorUnidad;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("Producto ID: %d, Nombre: %s, Precio por Unidad: %.2f, Stock: %d",
                productoID, nombre, precioPorUnidad, stock);
    }
}

class Vendedor {
    private int vendedorID;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaNacimiento;
    private Date fechaContratacion;

    public Vendedor(int vendedorID, String nombre, String apellido, String dni, Date fechaNacimiento, Date fechaContratacion) {
        this.vendedorID = vendedorID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return String.format("Vendedor ID: %d, Nombre: %s, Apellido: %s, DNI: %s, Fecha de Nacimiento: %s, Fecha de Contratación: %s",
                vendedorID, nombre, apellido, dni, fechaNacimiento.toString(), fechaContratacion.toString());
    }
}

class Venta {
    private int ventaID;
    private int vendedorID;
    private int productoID;
    private int cantidadVendida;
    private Date fechaVenta;

}

class Comerciales {
    public static Vendedor obtenerVendedorPorID(int vendedorID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBHelper.getConnection();
            String sql = "SELECT * FROM vendedores WHERE vendedor_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, vendedorID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("vendedor_id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String dni = resultSet.getString("dni");
                Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                Date fechaContratacion = resultSet.getDate("fecha_contratacion");

                return new Vendedor(id, nombre, apellido, dni, fechaNacimiento, fechaContratacion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResultSet(resultSet);
            DBHelper.closeStatement(preparedStatement);
            DBHelper.closeConnection(connection);
        }

        return null;
    }

    public static ArrayList<Vendedor> listadoDeVendedores() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Vendedor> vendedores = new ArrayList<>();

        try {
            connection = DBHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vendedores");

            while (resultSet.next()) {
                int id = resultSet.getInt("vendedor_id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String dni = resultSet.getString("dni");
                Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                Date fechaContratacion = resultSet.getDate("fecha_contratacion");

                Vendedor vendedor = new Vendedor(id, nombre, apellido, dni, fechaNacimiento, fechaContratacion);
                vendedores.add(vendedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeResultSet(resultSet);
            DBHelper.closeStatement(statement);
            DBHelper.closeConnection(connection);
        }

        return vendedores;
    }
}


