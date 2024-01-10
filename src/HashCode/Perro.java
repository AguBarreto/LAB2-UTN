package HashCode;

import java.util.Objects;

public class Perro {
    private int id;
    private String nombre;
    private String raza;
    private String edad;
    private String color;

    public Perro(String nombre, String raza, String edad, String color) {
        this.id = 5;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perro perro = (Perro) o;
        return id == perro.id && Objects.equals(nombre, perro.nombre) && Objects.equals(raza, perro.raza) && Objects.equals(edad, perro.edad) && Objects.equals(color, perro.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, raza, edad, color);
    }
}
