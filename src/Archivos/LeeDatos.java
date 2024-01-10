package Archivos;

import java.io.FileReader;

public class LeeDatos {

    public static void main(String[] args) {

        try {
            FileReader entrada = new FileReader("src\\Archivos\\texto.txt");

            int caracter =  entrada.read();
            char letra = (char)caracter;

            while(caracter != -1){
                System.out.print(letra);
                caracter = entrada.read();
                letra = (char)caracter;
            }
            entrada.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
