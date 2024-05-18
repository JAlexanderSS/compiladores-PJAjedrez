package org.compiladores.tablero;

import org.compiladores.tablero.blancas.Peones;

public class Tablero {
    private final Peones[] peones;

    public Tablero() {
        // Inicializar el arreglo de peones con ocho instancias de Peones
        peones = new Peones[8];
        for (int i = 0; i < 8; i++) {
            peones[i] = new Peones();
        }
    }

    // Método para acceder a un peón específico por su índice
    public Peones obtenerPeon(int indice) {
        if (indice >= 0 && indice < 8) {
            return peones[indice];
        } else {
            throw new IllegalArgumentException("Índice de peón inválido");
        }
    }

    public static void generacion() {
        // Crear el tablero
        Tablero tablero = new Tablero();
    }

    public static void obte(int indice) {
        // Crear el tablero
        Tablero tablero = new Tablero();
        // Obtener un peón específico
        Peones peon = tablero.obtenerPeon(indice);
        // Imprimir el estado del peón
        System.out.println("Peón " + indice + ": " + peon.getY());
    }
}