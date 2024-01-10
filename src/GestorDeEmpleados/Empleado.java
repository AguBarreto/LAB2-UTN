package GestorDeEmpleados;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Empleado {
    protected String nombre;
    protected int id;
    protected double sueldoBase;

    public Empleado(String nombre, int id, double sueldoBase) {
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;
    }

    public abstract double calcularSueldo();
}
