package PrimerPrograma;
import java.util.Scanner;

public class PrimerRepo {
    public static void main(String[] args) {

        int arreglo[]= new int[3];

        //solicitar ingreso de numeros
        System.out.println("Ingrese 3 numeros");
        Scanner scanner = new Scanner(System.in);

        arreglo[0]= scanner.nextInt();
        arreglo[1]= scanner.nextInt();
        arreglo[2]= scanner.nextInt();

        int aux = 0;

        //ordenamiento de mayor a menor
        for(int j = 0; j < 3 ; j++ ) {
            
            for(int i = 0; i < 2; i++) {
                if(arreglo[i] < arreglo[i+1]) {

                    aux = arreglo[i+1];
                    arreglo[i+1] = arreglo[i];
                    arreglo[i] = aux;
                }
            
            }
        }

        //mostrar vector ordenado
        System.out.println("Arreglo de números ordenado de Mayor a menor:");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.println(arreglo[i]);
        }

        scanner.close();
    }

}