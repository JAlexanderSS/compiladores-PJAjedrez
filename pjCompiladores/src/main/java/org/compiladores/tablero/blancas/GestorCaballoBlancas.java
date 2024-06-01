package org.compiladores.tablero.blancas;

import org.compiladores.tablero.Tablero;

public class GestorCaballoBlancas {

    public void moverCaballo(int indice, int nuevaX, int nuevaY) {
        Tablero tablero = Tablero.obtenerInstancia();
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        caballo.setX(nuevaX);
        caballo.setY(nuevaY);
    }
}
