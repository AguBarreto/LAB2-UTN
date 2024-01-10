package GestorDeEmpleados;

class EmpleadoComision extends Empleado implements Impuesto {
    private double ventasRealizadas;

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase + (0.1 * ventasRealizadas); // Ejemplo: 10% de comisión
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo() * 0.15; // Ejemplo: 15% de impuesto
    }
}