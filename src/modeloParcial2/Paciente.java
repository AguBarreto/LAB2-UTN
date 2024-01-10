package modeloParcial2;

import java.sql.Date;

public class Paciente extends Persona {
    private String historialMedico;
    private Date fechaIngreso;
    private int Doctor;

    public Paciente(int id, String nombre, int edad, String historialMedico, Date fechaIngreso, int doctor) {
        super(id, nombre, edad);
        this.historialMedico = historialMedico;
        this.fechaIngreso = fechaIngreso;
        Doctor = doctor;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getDoctor() {
        return Doctor;
    }

    public void setDoctor(int doctor) {
        Doctor = doctor;
    }
}