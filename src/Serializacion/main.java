package Serializacion;

import java.io.*;
import java.util.Scanner;

public class main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        String addNota;


        System.out.println("Ingrese lo que quiere agregar a las notas: ");
        addNota = input.nextLine();


        Notas nota = new Notas(addNota);
        serializar(nota);

        Notas notaDeserializada = deserializar();

        System.out.println(notaDeserializada.texto);

    }
    public static void serializar(Notas nota){
        try {
            FileOutputStream fileOut = new FileOutputStream("src\\Serializacion\\Nota");
            ObjectOutputStream fluxOut = new ObjectOutputStream(fileOut);
            fluxOut.writeObject(nota);
            fluxOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Notas deserializar(){
        Notas nota = null;
        try{
            FileInputStream fileIn = new FileInputStream("src\\Serializacion\\Nota");
            ObjectInputStream fluxIn = new ObjectInputStream(fileIn);
            nota = (Notas) fluxIn.readObject();
            fluxIn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return nota;
    }
}
