package org.compiladores.semantica;

import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.negras.PeonesNegras;
import org.compiladores.tablero.blancas.GestorPeonesBlancas;
import org.compiladores.tablero.blancas.PeonesBlancas;

import java.util.ArrayList;

public class SemanticaPeonBlancas {
    GestorPeonesBlancas gestorPeonesBlancas = new GestorPeonesBlancas();
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
    public SemanticaPeonBlancas() {
        // El arreglo de ArrayLists ya está inicializado estáticamente
        inicializarMovimientosSimples();
    }

    // Método para inicializar movimientos simples de los peones
    private void inicializarMovimientosSimples() {
        for (int i = 0; i < peonesList.length; i++) {
            PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
            int initialX = peon.getX();
            int initialY = peon.getY();
            peonesList[i].add(new int[]{initialX, initialY + 1, 0});
            // Agragar mas movimientos iniciales
            peonesList[i].add(new int[]{initialX, initialY + 2, 0});
            movimientos_DeCapturaDerecha(i);
            movimientos_DeCapturaIzquierda(i);
        }
    }

    // Método para modificar la posición de un peón
    public void modificarPeon(int indice, int nuevaX, int nuevaY) {
        gestorPeonesBlancas.moverPeon(indice, nuevaX, nuevaY);
    }

    // Método para validar un movimiento
    public void validacionDeMovimiento(int x, int y, int tipoMovimiento, int origenX) {
        if (tipoMovimiento == 0){
            movimientoSimple(x, y, tipoMovimiento);
        } else if (tipoMovimiento == 1){
            movimientoDeCaptura(x, y, tipoMovimiento, origenX);
        }
    }

    // Método para revisar si las coordenadas coinciden con algún peón
    public void movimientoSimple(int x, int y, int tipoMovimiento) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == tipoMovimiento) {
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i);
                    gestorPeonesBlancas.moverPeon(i, x, y);
                    reseteoYGeneracion(i);
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void movimientoDeCaptura(int x, int y, int tipoMovimiento, int origenX) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
                if (coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == tipoMovimiento && peon.getX() == origenX) {
                    verificacionDeCapturaSimple(x, y);
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " Que parte de la casilla " + peon.getX()+ " , " + peon.getY() +  " y se realizará una captura.");
                    gestorPeonesBlancas.moverPeon(i, x, y);
                    reseteoYGeneracion(i);
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void verificacionDeCapturaSimple(int capturaX, int capturaY){
        for (int i = 0; i < 8; i++){
            PeonesNegras peon = tablero.obtenerPeonNegras(i);
            if (peon.getX() == capturaX && peon.getY() == capturaY && peon.isEstado()){
                System.out.println("Se ha realizado una captura simple.");
                return;
            } else {
                System.out.println("No hay ninguna pieza en esta casilla.");
            }
        }
    }

    public void movimientoDeCapturaAlPaso(int x, int y, int tipoMovimiento) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == tipoMovimiento) {
                    PeonesBlancas peon = tablero.obtenerPeonBlancas(0);
                    if (peon.getY() == 4){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará una captura al paso.");
                        gestorPeonesBlancas.moverPeon(i, x, y);
                        reseteoYGeneracion(i);
                        return;
                    }
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará una captura.");
                    gestorPeonesBlancas.moverPeon(i, x, y);
                    reseteoYGeneracion(i);
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void reseteoYGeneracion(int indice){
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        resetearCoordenadasPeon(indice);
        movimientos_Simples(indice);
        movimientos_DeCapturaDerecha(indice);
        movimientos_DeCapturaIzquierda(indice);
        if (peon.getY() < 5){
            capturas_AlPasoDerecha(indice);
            capturas_AlPasoIzquierda(indice);
        }
    }

    // Método para registrar movimientos simples de los peones
    public void movimientos_Simples(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        if (newY <= 8) {
            peonesList[indice].add(new int[]{peon.getX(), newY, 0});
        }
    }

    public void movimientos_DeCapturaDerecha(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        int newX = peon.getX() + 1;
        if (newY <= 8 && newX <= 8 && newX >= 1) {
            peonesList[indice].add(new int[]{newX, newY, 1});
        }
    }

    public void movimientos_DeCapturaIzquierda(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        int newX = peon.getX() - 1;
        if (newY <= 8 && newX <= 8 && newX >= 1) {
            peonesList[indice].add(new int[]{newX, newY, 1});
        }
    }

    public void capturas_AlPasoDerecha(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        int newX = 5;
        if (newY <= 8) {
            peonesList[indice].add(new int[]{newX, newY, 2});
        }
    }

    public void capturas_AlPasoIzquierda(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        int newX = 5;
        if (newY >= 1) {
            peonesList[indice].add(new int[]{newX, newY, 2});
        }
    }

    // Método para resetear las coordenadas de un peón específico
    public void resetearCoordenadasPeon(int indice) {
        peonesList[indice].clear();
    }
}
