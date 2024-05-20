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
            peonesList[i].add(new int[]{initialX, initialY + 1, 0});
            // Agragar mas movimientos iniciales
            peonesList[i].add(new int[]{initialX, initialY + 2, 0});
            movimientosDeCapturaDerecha(i);
            movimientosDeCapturaIzquierda(i);
        }
    }

    // Método para modificar la posición de un peón
    public void modificarPeon(int indice, int nuevaX, int nuevaY) {
        gestorPeones.moverPeon(indice, nuevaX, nuevaY);
    }

    // Método para validar un movimiento
    public void validacionDeMovimiento(int x, int y, int tipoMovimiento) {
        if (tipoMovimiento == 0){
            movimientoSimple(x, y, tipoMovimiento);
        } else if (tipoMovimiento == 1){
            movimientoDeCaptura(x, y, tipoMovimiento);
        }
    }

    // Método para revisar si las coordenadas coinciden con algún peón
    public void movimientoSimple(int x, int y, int tipoMovimiento) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == tipoMovimiento) {
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i);
                    gestorPeones.moverPeon(i, x, y);
                    reseteoYGeneracion(i);
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void movimientoDeCaptura(int x, int y, int tipoMovimiento) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == tipoMovimiento) {
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará una captura.");
                    gestorPeones.moverPeon(i, x, y);
                    reseteoYGeneracion(i);
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void reseteoYGeneracion(int indice){
        resetearCoordenadasPeon(indice);
        movimientos_Simples(indice);
        movimientosDeCapturaDerecha(indice);
        movimientosDeCapturaIzquierda(indice);
    }

    // Método para registrar movimientos simples de los peones
    public void movimientos_Simples(int indice) {
        Peones peon = tablero.obtenerPeon(indice);
        int newY = peon.getY() + 1;
        if (newY <= 8) {
            peonesList[indice].add(new int[]{peon.getX(), newY, 0});
        }
    }

    public void movimientosDeCapturaDerecha(int indice) {
        Peones peon = tablero.obtenerPeon(indice);
        int newY = peon.getY() + 1;
        int newX = peon.getX() + 1;
        if (newY <= 8 && newX <= 8 && newX >= 1) {
            peonesList[indice].add(new int[]{newX, newY, 1});
        }
    }

    public void movimientosDeCapturaIzquierda(int indice) {
        Peones peon = tablero.obtenerPeon(indice);
        int newY = peon.getY() + 1;
        int newX = peon.getX() - 1;
        if (newY <= 8 && newX <= 8 && newX >= 1) {
            peonesList[indice].add(new int[]{newX, newY, 1});
        }
    }
    // Método para resetear las coordenadas de un peón específico
    public void resetearCoordenadasPeon(int indice) {
        peonesList[indice].clear();
    }
}
