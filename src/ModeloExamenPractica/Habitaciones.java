package ModeloExamenPractica;

import java.util.ArrayList;
import java.util.Arrays;

class Habitaciones implements MostrarInformacion, java.io.Serializable {
    private int cantidad_de_camas;
    private int capacidad_de_huespedes;
    private String estado;
    private ArrayList<Huespedes> listaHuespedes = new ArrayList<Huespedes>();

    public Habitaciones() {

    }

    public Habitaciones(int cantidad_de_camas, int capacidad_de_huespedes, String estado) {
        this.cantidad_de_camas = cantidad_de_camas;
        this.capacidad_de_huespedes = capacidad_de_huespedes;
        this.estado = estado;
    }
    public Habitaciones(int cantidad_de_camas, int capacidad_de_huespedes, String estado,ArrayList<Huespedes> listaHuespedes) {
        this.cantidad_de_camas = cantidad_de_camas;
        this.capacidad_de_huespedes = capacidad_de_huespedes;
        this.estado = estado;
        this.listaHuespedes = listaHuespedes;
    }




    public int getCantidad_de_camas() {
        return cantidad_de_camas;
    }

    public void setCantidad_de_camas(int cantidad_de_camas) {
        this.cantidad_de_camas = cantidad_de_camas;
    }

    public int getCapacidad_de_huespedes() {
        return capacidad_de_huespedes;
    }

    public void setCapacidad_de_huespedes(int capacidad_de_huespedes) {
        this.capacidad_de_huespedes = capacidad_de_huespedes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Huespedes> getListaHuespedes() {
        return listaHuespedes;
    }

    public void setListaHuespedes(ArrayList<Huespedes> listaHuespedes) {
        this.listaHuespedes = listaHuespedes;
    }

    @Override
    public String mostrarInformacionRelevante() {
        String nombreApellidoHuespedes = "";

        if(listaHuespedes !=null) {
            for (Huespedes huesped : listaHuespedes) {
                nombreApellidoHuespedes += "*"  + huesped.getApellido() + "," + huesped.getNombre() + " " ;

            }
        }

       return this.cantidad_de_camas + "\t\t  " + this.capacidad_de_huespedes + "\t\t\t" + this.estado + " \t" + nombreApellidoHuespedes;

    }
}


