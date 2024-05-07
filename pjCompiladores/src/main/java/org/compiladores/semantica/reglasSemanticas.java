package org.compiladores.semantica;

import org.compiladores.blancas.piezas;

public class reglasSemanticas {

    static String tipo = "";
    static int cordenadax = 0;
    static int cordenaday = 0;
    static String cy = "";

    static String[] ey =
            {
                    "a", "b", "c", "d", "e", "f", "g", "h"
            };

    public static void tipoPieza(String pieza) {
        tipo = pieza;
    }

    public static void casilla(String fil, String col) {
        cordenadax = Integer.parseInt(fil);
        cy = col;
    }

    public static String prueba() {
        return tipo;
    }

    public static void movimientoPeon() {
        int x = 0;
        while (!"g".equals(ey[x])){
            x++;
        }
        System.out.println(x+1);
    }

    public static void main(String[] args) {
        movimientoPeon();
    }
}
