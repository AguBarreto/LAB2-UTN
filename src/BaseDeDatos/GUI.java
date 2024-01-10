package BaseDeDatos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GUI {
    public static void main(String[] args) {
        // Establecer la conexión a la base de datos
        String jdbcURL = "jdbc:mysql://localhost:3306/universidad";
        String usuario = "root";
        String pass = "";


        try (Connection connection = DriverManager.getConnection(jdbcURL, usuario, pass)) {
            // Crear una sentencia SQL
            Statement statement = connection.createStatement();

            // Ejecutar una consulta SELECT
            String consultaSQL = "SELECT * FROM estudiantes";
            ResultSet resultSet = statement.executeQuery(consultaSQL);

            // Crear un modelo de tabla
            DefaultTableModel tableModel = new DefaultTableModel();

            // Agregar columnas al modelo de tabla
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            // Agregar filas al modelo de tabla
            while (resultSet.next()) {
                Object[] fila = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = resultSet.getObject(i + 1);
                }
                tableModel.addRow(fila);
            }

            // Crear una tabla y agregar el modelo de tabla
            JTable table = new JTable(tableModel);

            // Crear un JScrollPane para la tabla (en caso de que haya muchas filas)
            JScrollPane scrollPane = new JScrollPane(table);

            // Crear un JFrame y agregar el JScrollPane
            JFrame frame = new JFrame("Datos de la Base de Datos");
            frame.add(scrollPane);
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
