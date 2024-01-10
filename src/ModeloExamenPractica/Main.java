package ModeloExamenPractica;

import java.io.File;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        int caracter;
        char letra = ' ';
        String nombreHotel = "";
        try {
            FileReader entrada = new FileReader("src" + File.separator + "ModeloExamenPractica" + File.separator +"nombre.txt" );

             caracter =  entrada.read();
             letra = (char)caracter;

            while(caracter != -1){
                nombreHotel += letra;
                caracter = entrada.read();
                letra = (char)caracter;
            }
            entrada.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        
        System.out.println(nombreHotel);
        GestionarHabitaciones gestionHabitaciones = new GestionarHabitaciones(true);
        int opcion = 0;
        while (opcion != 6) {
            System.out.println("1. Ver la lista de habitaciones ");
            System.out.println("2. Reservar una habitación");
            System.out.println("3. Cancelar una reserva");
            System.out.println("4. Guardar reservas en un archivo");
            System.out.println("5. Cargar reservas desde un archivo");
            System.out.println("6. Salir");

            try {
                System.out.println("Ingrese su opción: ");
                opcion = input.nextInt();
            }catch (InputMismatchException e){
                opcion = 0;
                System.out.println("Ingrese un valor númerico! ");
                continue;
            }

            switch (opcion) {
                case 1:
                    gestionHabitaciones.mostrarLista();
                    break;
                case 2:
                    gestionHabitaciones.reservar();
                    break;
                case 3:
                    gestionHabitaciones.eliminarReserva();
                    break;
                case 4:
                    gestionHabitaciones.guardarDatos(gestionHabitaciones.getListaHabitaciones());
                    break;
                case 5:
                    gestionHabitaciones.setListaHabitaciones(gestionHabitaciones.cargar());

                    break;
                case 6:
                    break;
            }
        }
    }
}
