package Serializacion;
import java.io.Serializable;

public class Notas implements Serializable {
    String texto;

    public Notas(String texto) {
        this.texto = texto;
    }
}
