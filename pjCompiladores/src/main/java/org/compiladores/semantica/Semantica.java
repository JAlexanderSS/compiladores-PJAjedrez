package org.compiladores.semantica;
import org.compiladores.tablero.Tablero;

public class Semantica {
    static SemanticaPeon semanticaPeon = new SemanticaPeon();
    // Variables globales
    static String tipoPieza = "";
    static int cordenadax = 0;
    static int cordenaday = 0;

    // Definición valor de las columnas en números
    static String[] ey = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public static String prueba() {
        return "Prueba Exitosa";
    }

    // Recibimos el tipo de pieza que se utilizará
    public static void tipoPieza(String pieza) {
        tipoPieza = pieza;
    }

    // Recibimos las coordenadas de la pieza
    public static void casilla(String columna, String fila) {
        posiccionX(columna);
        cordenaday = Integer.parseInt(fila);
    }

    // Función para obtener la posición en Y de la pieza
    public static void posiccionX(String letraColumna) {
        for (int i = 0; i < ey.length; i++) {
            if (ey[i].equals(letraColumna)) {
                cordenadax = i + 1;
                break; // Una vez encontrado el elemento, se puede salir del bucle
            }
        }
    }

    // Función temporal para visualizar los valores obtenidos
    public static void valoresObtenidos() {
        System.out.println("Tipo de pieza: " + tipoPieza);
        System.out.println("Cordenada X: " + cordenadax);
        System.out.println("Cordenada Y: " + cordenaday);

        if (tipoPieza.equals("Peon")){
            semanticaPeon.validacionDeMovimiento(cordenadax, cordenaday);
        }
    }
}