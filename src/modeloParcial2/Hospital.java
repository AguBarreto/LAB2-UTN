package modeloParcial2;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Hospital {
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private ArrayList<Doctores> listaDoctores = new ArrayList<>();

    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(ArrayList<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public ArrayList<Doctores> getListaDoctores() {
        return listaDoctores;
    }

    public void setListaDoctores(ArrayList<Doctores> listaDoctores) {
        this.listaDoctores = listaDoctores;
    }

    public void cargarDatos(Connection conexion) throws SQLException {
        // Crea una declaraci�n SQL para ejecutar una consulta de selecci�n.
        Statement statementPacientes = conexion.createStatement();
        Statement statementDoctores = conexion.createStatement();
        // Define la consulta SQL para obtener ambas tablas.
        String consultaPacientes = "SELECT * FROM pacientes";
        String consultaDoctores = "SELECT * FROM doctores";

        // Ejecuta la consulta SQL y almacena los resultados en un ResultSet.
        ResultSet resultadoPacientes = statementPacientes.executeQuery(consultaPacientes);
        ResultSet resultadosDoctores = statementDoctores.executeQuery(consultaDoctores);

        // Itera a trav�s de los resultados y muestra los datos de cada estudiante en forma de tabla.
        while (resultadoPacientes.next()) {
            int id = resultadoPacientes.getInt("id");
            String nombre = resultadoPacientes.getString("nombre");
            int edad = resultadoPacientes.getInt("edad");
            String historialMedico = resultadoPacientes.getString("historial_medico");
            Date fecha_ingreso = resultadoPacientes.getDate("fecha_ingreso");
            int doctor = resultadoPacientes.getInt("doctor");

            Paciente cargandoPaciente = new Paciente(id, nombre, edad, historialMedico, fecha_ingreso, doctor);

            this.listaPacientes.add(cargandoPaciente);

            // Cierra el ResultSet y la declaraci�n para liberar recursos.

        }
        resultadoPacientes.close();
        statementPacientes.close();
    }

    public void mostrarListaPacientes(Connection conexion) throws SQLException {
        Statement statementPacientes = conexion.createStatement();
        String consultaPacientes = "SELECT * FROM pacientes";
        ResultSet resultadoPacientes = statementPacientes.executeQuery(consultaPacientes);


        System.out.println("ID\tNombre\tEdad\tHistorial médico\tFecha de ingreso\tDoctor");

        // Itera a trav�s de los resultados y muestra los datos de cada estudiante en forma de tabla.
        while (resultadoPacientes.next()) {
            int id = resultadoPacientes.getInt("id");
            String nombre = resultadoPacientes.getString("nombre");
            int edad = resultadoPacientes.getInt("edad");
            String historialMedico = resultadoPacientes.getString("historial_medico");
            Date fecha_ingreso = resultadoPacientes.getDate("fecha_ingreso");
            int doctor = resultadoPacientes.getInt("doctor");

            // Imprime los datos del estudiante con tabulaciones para formatear como una tabla.
            System.out.println(id + "\t" + nombre + "\t" + edad + "\t" + historialMedico + "\t" + fecha_ingreso.toString() + "\t" + doctor);


            // Cierra el ResultSet y la declaraci�n para liberar recursos.

        }
        resultadoPacientes.close();
        statementPacientes.close();
    }

    public static void agregarPaciente(Connection conexion, Scanner scanner) throws SQLException {
        // Solicita al usuario que ingrese los detalles del paciente.
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Historial médico: ");
        String historial_medico = scanner.nextLine();
        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fechaIngresoStr = scanner.nextLine();

        // Convierte la fecha de ingreso a un objeto Date.
        java.util.Date fechaIngreso;
        try {
            fechaIngreso = new SimpleDateFormat("yyyy-MM-dd").parse(fechaIngresoStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Utilice el formato YYYY-MM-DD.");
            return;
        }

        System.out.print("ID del doctor: ");
        int doctor = scanner.nextInt();

        // Consulta SQL para insertar un nuevo paciente en la base de datos.
        String consulta = "INSERT INTO pacientes (nombre, edad, historial_medico, fecha_ingreso, doctor) " +
                "VALUES (?, ?, ?, ?, ?)";

        // Crea un PreparedStatement para ejecutar la consulta SQL con valores reales.
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setString(1, nombre);
        preparedStatement.setInt(2, edad);
        preparedStatement.setString(3, historial_medico);
        preparedStatement.setDate(4, new java.sql.Date(fechaIngreso.getTime()));
        preparedStatement.setInt(5, doctor);

        // Ejecuta la consulta y obtiene el número de filas afectadas.
        int filasAfectadas = preparedStatement.executeUpdate();

        // Verifica si la inserción fue exitosa y muestra un mensaje apropiado.
        if (filasAfectadas > 0) {
            System.out.println("Paciente agregado exitosamente.");
        } else {
            System.out.println("No se pudo agregar el paciente.");
        }

        // Cierra el PreparedStatement para liberar recursos.
        preparedStatement.close();
    }

    public void eliminarPaciente(Connection conexion, Scanner scanner) throws SQLException {
        // Solicita al usuario ingresar el ID del paciente que desea eliminar.
        System.out.print("Ingrese el ID del paciente que desea eliminar: ");
        int idPaciente = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        // Consulta SQL para eliminar un paciente basado en su ID.
        String consulta = "DELETE FROM pacientes WHERE id = ?";

        // Crea un PreparedStatement para ejecutar la consulta SQL con el ID del paciente como parámetro.
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setInt(1, idPaciente);

        // Ejecuta la consulta y obtiene el número de filas afectadas.
        int filasAfectadas = preparedStatement.executeUpdate();

        // Verifica si la eliminación fue exitosa y muestra un mensaje apropiado.
        if (filasAfectadas > 0) {
            System.out.println("Paciente eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el paciente con el ID proporcionado.");
        }

        // Cierra el PreparedStatement para liberar recursos.
        preparedStatement.close();
    }

    public void mostrarListaDoctores(Connection conexion) throws SQLException {
        Statement statementDoctores = conexion.createStatement();
        String consultaDoctores = "SELECT * FROM doctores";
        ResultSet resultadoDoctores = statementDoctores.executeQuery(consultaDoctores);

        System.out.println("ID\tNombre\tEspecialidad");

        while (resultadoDoctores.next()) {
            int idDoctor = resultadoDoctores.getInt("id");
            String nombreDoctor = resultadoDoctores.getString("nombre");
            String especialidadDoctor = resultadoDoctores.getString("especialidad");

            System.out.println(idDoctor + "\t" + nombreDoctor + "\t" + especialidadDoctor);
        }

        resultadoDoctores.close();
        statementDoctores.close();
    }

    public void asignarDoctor(Connection conexion, Scanner scanner) throws SQLException {
        // Solicita al usuario ingresar el ID del paciente al que desea asignar un doctor.
        System.out.print("Ingrese el ID del paciente al que desea asignar un doctor: ");
        int idPaciente = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        // Solicita al usuario ingresar el ID del doctor que desea asignar al paciente.
        System.out.print("Ingrese el ID del doctor que desea asignar al paciente: ");
        int idDoctor = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        // Consulta SQL para actualizar el campo "doctor" en la tabla "pacientes" con el nuevo ID del doctor.
        String consulta = "UPDATE pacientes SET doctor = ? WHERE id = ?";

        // Crea un PreparedStatement para ejecutar la consulta SQL con los IDs del doctor y del paciente como parámetros.
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setInt(1, idDoctor);
        preparedStatement.setInt(2, idPaciente);

        // Ejecuta la consulta y obtiene el número de filas afectadas.
        int filasAfectadas = preparedStatement.executeUpdate();

        // Verifica si la asignación fue exitosa y muestra un mensaje apropiado.
        if (filasAfectadas > 0) {
            System.out.println("Doctor asignado al paciente correctamente.");
        } else {
            System.out.println("No se pudo realizar la asignación. Verifique los IDs proporcionados.");
        }

        // Cierra el PreparedStatement para liberar recursos.
        preparedStatement.close();
    }

    public void filtrarPorFecha(Connection conexion, Scanner scanner) throws SQLException {
        // Solicita al usuario ingresar la fecha de inicio para el filtro.
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String fechaInicioStr = scanner.nextLine();
        java.util.Date fechaInicio;

        try {
            fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Utilice el formato YYYY-MM-DD.");
            return;
        }

        // Solicita al usuario ingresar la fecha de fin para el filtro.
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        String fechaFinStr = scanner.nextLine();
        java.util.Date fechaFin;

        try {
            fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Utilice el formato YYYY-MM-DD.");
            return;
        }

        // Consulta SQL para seleccionar los pacientes que ingresaron entre las fechas especificadas.
        String consulta = "SELECT * FROM pacientes WHERE fecha_ingreso BETWEEN ? AND ?";

        // Crea un PreparedStatement para ejecutar la consulta SQL con las fechas como parámetros.
        PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
        preparedStatement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
        preparedStatement.setDate(2, new java.sql.Date(fechaFin.getTime()));

        // Ejecuta la consulta y obtiene el resultado.
        ResultSet resultadoFiltrado = preparedStatement.executeQuery();

        System.out.println("Pacientes que ingresaron entre " + fechaInicioStr + " y " + fechaFinStr + ":");

        // Muestra los resultados en forma de tabla.
        System.out.println("ID\tNombre\tEdad\tHistorial médico\tFecha de ingreso\tDoctor");
        while (resultadoFiltrado.next()) {
            int id = resultadoFiltrado.getInt("id");
            String nombre = resultadoFiltrado.getString("nombre");
            int edad = resultadoFiltrado.getInt("edad");
            String historialMedico = resultadoFiltrado.getString("historial_medico");
            Date fecha_ingreso = resultadoFiltrado.getDate("fecha_ingreso");
            int doctor = resultadoFiltrado.getInt("doctor");

            System.out.println(id + "\t" + nombre + "\t" + edad + "\t" + historialMedico + "\t" + fecha_ingreso.toString() + "\t" + doctor);
        }

        // Cierra el PreparedStatement y el ResultSet para liberar recursos.
        preparedStatement.close();
        resultadoFiltrado.close();
    }

    public void editarPaciente(Connection conexion, Scanner scanner) throws SQLException {
        // Solicita al usuario ingresar el ID del paciente que desea editar.
        System.out.print("Ingrese el ID del paciente que desea editar: ");
        int idPaciente = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        // Consulta SQL para seleccionar la información actual del paciente.
        String consultaSeleccionar = "SELECT * FROM pacientes WHERE id = ?";

        // Crea un PreparedStatement para ejecutar la consulta SQL con el ID del paciente como parámetro.
        PreparedStatement preparedStatementSeleccionar = conexion.prepareStatement(consultaSeleccionar);
        preparedStatementSeleccionar.setInt(1, idPaciente);

        // Ejecuta la consulta y obtiene el resultado.
        ResultSet resultadoSeleccionar = preparedStatementSeleccionar.executeQuery();

        // Verifica si se encontró el paciente con el ID proporcionado.
        if (!resultadoSeleccionar.next()) {
            System.out.println("No se encontró el paciente con el ID proporcionado.");
            return;
        }

        // Muestra la información actual del paciente.
        System.out.println("Información actual del paciente:");
        System.out.println("ID\tNombre\tEdad\tHistorial médico\tFecha de ingreso\tDoctor");
        int id = resultadoSeleccionar.getInt("id");
        String nombre = resultadoSeleccionar.getString("nombre");
        int edad = resultadoSeleccionar.getInt("edad");
        String historialMedico = resultadoSeleccionar.getString("historial_medico");
        Date fechaIngreso = resultadoSeleccionar.getDate("fecha_ingreso");
        int doctor = resultadoSeleccionar.getInt("doctor");
        System.out.println(id + "\t" + nombre + "\t" + edad + "\t" + historialMedico + "\t" + fechaIngreso.toString() + "\t" + doctor);

        // Solicita al usuario ingresar la nueva información del paciente.
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nueva edad: ");
        int nuevaEdad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Nuevo historial médico: ");
        String nuevoHistorialMedico = scanner.nextLine();
        System.out.print("Nueva fecha de ingreso (YYYY-MM-DD): ");
        String nuevaFechaIngresoStr = scanner.nextLine();

        // Convierte la nueva fecha de ingreso a un objeto Date.
        java.util.Date nuevaFechaIngreso;
        try {
            nuevaFechaIngreso = new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaIngresoStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Utilice el formato YYYY-MM-DD.");
            return;
        }

        // Consulta SQL para actualizar la información del paciente en la base de datos.
        String consultaActualizar = "UPDATE pacientes SET nombre = ?, edad = ?, historial_medico = ?, fecha_ingreso = ? WHERE id = ?";

        // Crea un PreparedStatement para ejecutar la consulta SQL con la nueva información y el ID del paciente como parámetros.
        PreparedStatement preparedStatementActualizar = conexion.prepareStatement(consultaActualizar);
        preparedStatementActualizar.setString(1, nuevoNombre);
        preparedStatementActualizar.setInt(2, nuevaEdad);
        preparedStatementActualizar.setString(3, nuevoHistorialMedico);
        preparedStatementActualizar.setDate(4, new java.sql.Date(nuevaFechaIngreso.getTime()));
        preparedStatementActualizar.setInt(5, idPaciente);

        // Ejecuta la consulta y obtiene el número de filas afectadas.
        int filasAfectadas = preparedStatementActualizar.executeUpdate();

        // Verifica si la actualización fue exitosa y muestra un mensaje apropiado.
        if (filasAfectadas > 0) {
            System.out.println("Información del paciente actualizada exitosamente.");
        } else {
            System.out.println("No se pudo actualizar la información del paciente. Verifique el ID proporcionado.");
        }

        // Cierra los PreparedStatement y el ResultSet para liberar recursos.
        preparedStatementSeleccionar.close();
        preparedStatementActualizar.close();
        resultadoSeleccionar.close();
    }
}