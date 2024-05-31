package org.compiladores.tablero;

import org.compiladores.tablero.blancas.PeonesBlancas;
import org.compiladores.tablero.negras.PeonesNegras;

public class Tablero {
    private static Tablero instancia;
    private final PeonesBlancas[] peonesBlancas;
    private final PeonesNegras[] peonesNegras;

    // Constructor privado para evitar la instanciación directa
    private Tablero() {
        peonesBlancas = new PeonesBlancas[8];
        peonesNegras = new PeonesNegras[8];
        for (int i = 0; i < 8; i++) {
            peonesBlancas[i] = new PeonesBlancas();
            peonesBlancas[i].setX(i + 1);
        }
        for (int i = 0; i < 8; i++) {
            peonesNegras[i] = new PeonesNegras();
            peonesNegras[i].setX(8-i);
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
    public PeonesBlancas obtenerPeonBlancas(int indice) {
        if (indice >= 0 && indice < 8) {
            return peonesBlancas[indice];
        } else {
            throw new IllegalArgumentException("Índice de peón inválido");
        }
    }

    public PeonesNegras obtenerPeonNegras(int indice) {
        if (indice >= 0 && indice < 8) {
            return peonesNegras[indice];
        } else {
            throw new IllegalArgumentException("Índice de peón inválido");
        }
    }

    // Método para obtener el índice de un peón basado en sus coordenadas
    public int obtenerIndicePeonPorCoordenadasBlancas(int x, int y) {
        for (int i = 0; i < peonesBlancas.length; i++) {
            if (peonesBlancas[i].getX() == x && peonesBlancas[i].getY() == y) {
                return i;
            }
        }
        return -1; // Indica que no se encontró ningún peón con esas coordenadas
    }

    public int obtenerIndicePeonPorCoordenadasNegras(int x, int y) {
        for (int i = 0; i < peonesNegras.length; i++) {
            if (peonesNegras[i].getX() == x && peonesNegras[i].getY() == y) {
                return i;
            }
        }
        return -1; // Indica que no se encontró ningún peón con esas coordenadas
    }
}