package Recursividad;

public class DivisiónConRestas  {
    

    // Enfoque recursivo
    public static int divisionRecursiva(int dividendo, int divisor) {
        if (divisor == 0) {
            System.out.println("Error: Divisor no puede ser cero.");
            return -1;
        }
        if (dividendo < divisor) {
            return 0;
        }
         return 1 + divisionRecursiva(dividendo - divisor, divisor);
        }
    
    // Enfoque iterativo con restas sucesivas
    public static int divisionIterativa(int dividendo, int divisor) {
        if (divisor == 0) {
            System.out.println("Error: Divisor no puede ser cero.");
            return -1;
        }
        int cociente = 0;
        while (dividendo >= divisor) {
                dividendo -= divisor;
                cociente++;
        }
        return cociente;

    }
}
