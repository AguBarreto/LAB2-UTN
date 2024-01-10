package ExamenParcial1;

import java.util.ArrayList;

class Paciente extends Persona implements Informacion {
        private int numeroTelefono;
        private int tipoSangre;
        private ArrayList<String> historialMedico = new ArrayList<>();

        public Paciente(String nombre, String dni, String fechaNacimiento, int numeroTelefono, int tipoSangre) {
                super(nombre, dni, fechaNacimiento);
                this.numeroTelefono = numeroTelefono;
                this.tipoSangre = tipoSangre;
        }

        public int getNumeroTelefono() {
                return numeroTelefono;
        }

        public int getTipoSangre() {
                return tipoSangre;
        }

        public void agregarEventoHistorial(String evento) {
                historialMedico.add(evento);
        }

        public void verHistorialDeEventos() {
                for (String evento : historialMedico) {
                        System.out.println(evento);
                }
        }

        public void setNombre(String nuevoNombre) {
        }

        public void setFechaNacimiento(String nuevaFechaNacimiento) {
        }

        public void setNumeroTelefono(int nuevoNumeroTelefono) {
        }

        public void setTipoSangre(int nuevoTipoSangre) {
        }
}