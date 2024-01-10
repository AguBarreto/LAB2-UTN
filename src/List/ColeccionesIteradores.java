package List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColeccionesIteradores {
    public static void main(String[] args) {

        List<String> lista = new ArrayList<>();
        lista.add("Hola");
        lista.add("Mundo");

        for (String list:lista
             ) {
            System.out.println(list);
        }

        Iterator<String> iterador = lista.iterator();

        while (iterador.hasNext()){
            System.out.println(iterador.next());
        }
     }
}
