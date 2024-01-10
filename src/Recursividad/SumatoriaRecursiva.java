package Recursividad;

public class SumatoriaRecursiva {

    public static int calcularSumatoria(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + calcularSumatoria(n - 1);
        }
    }

    public static void main(String[] args) {
        int numero = 5; // Cambia este valor para probar con diferentes números
        int resultado = calcularSumatoria(numero);
        System.out.println("La sumatoria de los números enteros desde 1 hasta " + numero + " es: " + resultado);
    }
}
