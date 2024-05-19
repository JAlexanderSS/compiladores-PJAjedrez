package org.compiladores.semantica;
import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.blancas.GestorPeones;
import org.compiladores.tablero.blancas.Peones;

public class SemanticaPeon {
    GestorPeones gestorPeones = new GestorPeones();
    Tablero tablero = Tablero.obtenerInstancia();

    int[][] peon1 = {
    };
    int[][] peon2 = {
    };
    int[][] peon3 = {
    };
    int[][] peon4 = {
    };
    int[][] peon5 = {
    };
    int[][] peon6 = {
    };
    int[][] peon7 = {
    };
    int[][] peon8 = {
    };

    public void modificarPeon(int indice, int nuevaX, int nuevaY) {
        gestorPeones.moverPeon(indice, nuevaX, nuevaY);
    }

    public void mostrarPosicionPeon(int indice) {
        Peones peon = tablero.obtenerPeon(indice);
        int resultado = peon.getX() + peon.getY();
        System.out.println(peon.getX());
        System.out.println(peon.getY());
        System.out.println(resultado);
    }

    public void validacionDeMovimiento(int x, int y){
        int indice = tablero.obtenerIndicePeonPorCoordenadas(x, y);
        System.out.println("Coordenada en X " + x);
        System.out.println("Coordenada en Y " + y);
        System.out.println("Indice de las Coordenadas " + indice);
    }

    public void movimientoSimple(int indice, int x, int y){
    }

    public void movimientos_Simples(int indice) {
        Peones peon = tablero.obtenerPeon(indice);
        int x = peon.getX();
        int y = peon.getY();
    }
}
