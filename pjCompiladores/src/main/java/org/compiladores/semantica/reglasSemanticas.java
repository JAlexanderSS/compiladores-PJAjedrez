package org.compiladores.semantica;
import org.compiladores.blancas.piezas;

public class reglasSemanticas {

    static String tipo = "";
    int x = 0;
    String y = "";

    public static void tipoPieza(String pieza){
        tipo = pieza;
    }

    public static void casilla(String fil, String col){
        x = Integer.parseInt(fil);
        y = col;
    }

    public static String  prueba(){
        return tipo;
    }

    public static void movimientoPeon (){

    }
}
