package Exceptions;
import java.io.IOException;
import java.util.Scanner;
public class ExcepcionesPersonalizadas {
    public static Scanner input = new Scanner(System.in);
    static class fueraDeRango extends IOException{
        public fueraDeRango(){}

        public fueraDeRango(String messageError){
            super(messageError);
        }
    }
    static void evaluarNum(int num) throws fueraDeRango{
        if(num>100 || num<1){
            fueraDeRango fueraRango = new fueraDeRango("Está fuera de rango");
            throw fueraRango;
        }
    }
    public static void main(String[] args) {
        int i;
        System.out.println("Ingrese un número del 1 al 100");
        i = input.nextInt();
        try {
            evaluarNum(i);
            System.out.println("Esta bien");
        } catch (fueraDeRango e) {
            System.out.println("El numero está fuera de rango");
        }
    }
}
