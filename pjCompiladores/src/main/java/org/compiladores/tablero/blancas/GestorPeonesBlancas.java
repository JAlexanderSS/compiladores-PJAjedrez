package org.compiladores.tablero.blancas;

import org.compiladores.tablero.Tablero;

public class GestorPeonesBlancas {

    public void moverPeon(int indice, int nuevaX, int nuevaY) {
        Tablero tablero = Tablero.obtenerInstancia();
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);

        if (!peon.isJugado()) {
            peon.setJugado(true);
        }

        peon.setX(nuevaX);
        peon.setY(nuevaY);
    }

    public void mostrarPosicionPeon(int indice) {
        Tablero tablero = Tablero.obtenerInstancia();
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        System.out.println("Posición del peón " + indice + ": (" + peon.getX() + ", " + peon.getY() + ")");
    }

    public void mostrarIndicePeonPorCoordenadas(int x, int y) {
        Tablero tablero = Tablero.obtenerInstancia();
        int indice = tablero.obtenerIndicePeonPorCoordenadas(x, y);
        if (indice != -1) {
            System.out.println("El índice del peón en las coordenadas (" + x + ", " + y + ") es: " + indice);
        } else {
            System.out.println("No se encontró ningún peón en las coordenadas (" + x + ", " + y + ")");
        }
    }
}
