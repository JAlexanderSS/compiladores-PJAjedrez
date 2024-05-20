package org.compiladores.semantica;

import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.blancas.GestorPeones;
import org.compiladores.tablero.blancas.Peones;

import java.util.ArrayList;

public class SemanticaPeon {
    GestorPeones gestorPeones = new GestorPeones();
    Tablero tablero = Tablero.obtenerInstancia();

    // Crear un arreglo de ArrayLists para almacenar pares de enteros (x, y) de los 8 peones
    private static ArrayList<int[]>[] peonesList = new ArrayList[8];

    // Inicializador estático para inicializar los ArrayLists solo una vez
    static {
        for (int i = 0; i < peonesList.length; i++) {
            peonesList[i] = new ArrayList<>();
        }
    }

    // Constructor
    public SemanticaPeon() {
        // El arreglo de ArrayLists ya está inicializado estáticamente
        inicializarMovimientosSimples();
    }

    // Método para inicializar movimientos simples de los peones
    private void inicializarMovimientosSimples() {
        for (int i = 0; i < peonesList.length; i++) {
            Peones peon = tablero.obtenerPeon(i);
            int initialX = peon.getX();
            int initialY = peon.getY();
            peonesList[i].add(new int[]{initialX, initialY + 1});
            // Agragar mas movimientos iniciales
            peonesList[i].add(new int[]{initialX, initialY + 2});
        }
    }

    // Método para modificar la posición de un peón
    public void modificarPeon(int indice, int nuevaX, int nuevaY) {
        gestorPeones.moverPeon(indice, nuevaX, nuevaY);
    }

    // Método para mostrar la posición de un peón
    public void mostrarPosicionPeon(int indice) {
        Peones peon = tablero.obtenerPeon(indice);
        int resultado = peon.getX() + peon.getY();
        System.out.println("Posición del peón " + indice + ":");
        System.out.println("x: " + peon.getX());
        System.out.println("y: " + peon.getY());
        System.out.println("Suma: " + resultado);
    }

    // Método para validar un movimiento
    public void validacionDeMovimiento(int x, int y) {
        movimientoSimple(x, y);
    }

    // Método para revisar si las coordenadas coinciden con algún peón
    public void movimientoSimple(int x, int y) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y) {
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i);
                    gestorPeones.moverPeon(i, x, y);
                    resetearCoordenadasPeon(i);
                    movimientos_Simples(i);
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    // Método para registrar movimientos simples de los peones
    public void movimientos_Simples(int indice) {
        Peones peon = tablero.obtenerPeon(indice);
        int newY = peon.getY() + 1;
        if (newY <= 8) {
            peonesList[indice].add(new int[]{peon.getX(), newY});
        }

        System.out.println("Nuevas coordeadas en X " + peon.getX() + " En y " + peon.getY() + " Del peon: " + indice);
        System.out.println("La jugabilidad del peon es:  " + peon.isJugado());
    }

    // Método para resetear las coordenadas de un peón específico
    public void resetearCoordenadasPeon(int indice) {
        peonesList[indice].clear();
    }
}
