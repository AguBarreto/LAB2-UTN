package GestorDeEmpleados;

import java.util.ArrayList;

class GestorEmpleados {
    private ArrayList<Empleado> empleados = new ArrayList<>();

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void modificarEmpleado(int indice, Empleado empleado) {
        empleados.set(indice, empleado);
    }

    public void eliminarEmpleado(int indice) {
        empleados.remove(indice);
    }

    public void listarEmpleados() {
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println("Empleado " + (i + 1) + ": " + empleados.get(i).nombre);
        }
    }
}