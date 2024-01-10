package Recursividad;

public class FactorialConSobrecarga {

    public int calcularFactorial(int n) {
        if (n <= 0) {
            return 1;
        } else {
            return n * calcularFactorial(n - 1);
        }
    }
    public int calcularFactorialIterativo(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        FactorialConSobrecarga calculator = new FactorialConSobrecarga();

        int numeroRecursivo = 5; // Cambia este valor para probar con diferentes números
        int factorialRecursivo = calculator.calcularFactorial(numeroRecursivo);
        System.out.println("Factorial de " + numeroRecursivo + " (recursivo): " + factorialRecursivo);

        int numeroIterativo = 7; // Cambia este valor para probar con diferentes números
        int factorialIterativo = calculator.calcularFactorialIterativo(numeroIterativo);
        System.out.println("Factorial de " + numeroIterativo + " (iterativo): " + factorialIterativo);
    }

}
