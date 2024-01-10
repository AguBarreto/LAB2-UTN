package modeloParcial2;
public class Doctores extends Persona{
    String especialidad;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Doctores(int id, String nombre, int edad, String especialidad) {
        super(id, nombre, edad);
        this.especialidad = especialidad;
    }
}