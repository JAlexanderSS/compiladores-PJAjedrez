package org.compiladores.tablero;

import org.compiladores.tablero.blancas.CaballosBlancas;
import org.compiladores.tablero.blancas.PeonesBlancas;
import org.compiladores.tablero.negras.CaballosNegras;
import org.compiladores.tablero.negras.PeonesNegras;

public class Tablero {
    private static Tablero instancia;

    private final PeonesBlancas[] peonesBlancas;
    private final PeonesNegras[] peonesNegras;
    private final CaballosBlancas[] caballosBlancas;
    private final CaballosNegras[] caballosNegras;

    // Constructor privado para evitar la instanciación directa
    private Tablero() {
        peonesBlancas = new PeonesBlancas[8];
        peonesNegras = new PeonesNegras[8];
        caballosBlancas = new CaballosBlancas[2];
        caballosNegras = new CaballosNegras[2];

        for (int i = 0; i < 8; i++) {
            peonesBlancas[i] = new PeonesBlancas();
            peonesBlancas[i].setX(i + 1);
        }

        for (int i = 0; i < 8; i++) {
            peonesNegras[i] = new PeonesNegras();
            peonesNegras[i].setX(i + 1);
        }

        for (int i = 0; i <= 1; i++) {
            caballosBlancas[i] = new CaballosBlancas();
        }
        caballosBlancas[0].setX(2);
        caballosBlancas[1].setX(7);

        for (int i = 0; i <= 1; i++) {
            caballosNegras[i] = new CaballosNegras();
        }
        caballosNegras[0].setX(7);
        caballosNegras[1].setX(2);
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

    public CaballosBlancas obtenerCaballoBlancas(int indice) {
        if (indice >= 0 && indice < 2) {
            return caballosBlancas[indice];
        } else {
            throw new IllegalArgumentException("Índice de caballo inválido");
        }
    }

    public CaballosNegras obtenerCaballoNegras(int indice) {
        if (indice >= 0 && indice < 2) {
            return caballosNegras[indice];
        } else {
            throw new IllegalArgumentException("Índice de caballo inválido");
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

    public int obtenerIndiceCaballoBlancasPorCoordenadas(int x, int y) {
        for (int i = 0; i < caballosBlancas.length; i++) {
            if (caballosBlancas[i].getX() == x && caballosBlancas[i].getY() == y) {
                return i;
            }
        }
        return -1; // Indica que no se encontró ningún caballo con esas coordenadas
    }
}