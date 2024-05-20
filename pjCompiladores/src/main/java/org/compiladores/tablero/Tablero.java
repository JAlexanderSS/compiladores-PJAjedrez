package org.compiladores.tablero;

import org.compiladores.tablero.blancas.Peones;

public class Tablero {
    private static Tablero instancia;
    private final Peones[] peones;

    // Constructor privado para evitar la instanciación directa
    private Tablero() {
        peones = new Peones[8];
        for (int i = 0; i < 8; i++) {
            peones[i] = new Peones();
            peones[i].setX(i + 1);
        }
    }

    // Método estático para obtener la única instancia de Tablero
    public static synchronized Tablero obtenerInstancia() {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    // Método para acceder a un peón específico por su índice
    public Peones obtenerPeon(int indice) {
        if (indice >= 0 && indice < 8) {
            return peones[indice];
        } else {
            throw new IllegalArgumentException("Índice de peón inválido");
        }
    }

    // Método para obtener el índice de un peón basado en sus coordenadas
    public int obtenerIndicePeonPorCoordenadas(int x, int y) {
        for (int i = 0; i < peones.length; i++) {
            if (peones[i].getX() == x && peones[i].getY() == y) {
                return i;
            }
        }
        return -1; // Indica que no se encontró ningún peón con esas coordenadas
    }
}