package org.compiladores.tablero.negras;

import org.compiladores.tablero.Tablero;

public class GestorCaballosNegras {
    public void moverCaballo(int indice, int nuevaX, int nuevaY) {
        Tablero tablero = Tablero.obtenerInstancia();
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        caballo.setX(nuevaX);
        caballo.setY(nuevaY);
    }
}
