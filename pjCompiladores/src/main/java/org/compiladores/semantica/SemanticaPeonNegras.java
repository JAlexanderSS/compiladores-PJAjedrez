package org.compiladores.semantica;

import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.blancas.GestorPeonesBlancas;
import org.compiladores.tablero.blancas.PeonesBlancas;
import org.compiladores.tablero.negras.PeonesNegras;
import org.compiladores.tablero.negras.GestorPeonesNegras;

import java.util.ArrayList;

public class SemanticaPeonNegras {
    GestorPeonesNegras gestorPeonesNegras = new GestorPeonesNegras();
    GestorPeonesBlancas gestorPeonesBlancas = new GestorPeonesBlancas();
    Tablero tablero = Tablero.obtenerInstancia();

    Boolean concidenciaNegras = false;
    Boolean concidenciaBlancas = false;
    Boolean captura = false;

    // Crear un arreglo de ArrayLists para almacenar pares de enteros (x, y) de los 8 peones
    private static ArrayList<int[]>[] peonesList = new ArrayList[8];

    // Inicializador estático para inicializar los ArrayLists solo una vez
    static {
        for (int i = 0; i < peonesList.length; i++) {
            peonesList[i] = new ArrayList<>();
        }
    }

    // Constructor
    public SemanticaPeonNegras() {
        // El arreglo de ArrayLists ya está inicializado estáticamente
        inicializarMovimientosSimples();
    }

    // Método para inicializar movimientos simples de los peones
    private void inicializarMovimientosSimples() {
        for (int i = 0; i < peonesList.length; i++) {
            movimientos_Simples(i);
            movimientos_dobles(i);
            movimientos_DeCapturaDerecha(i);
            movimientos_DeCapturaIzquierda(i);
        }
    }

    // Método para validar un movimiento
    public void validacionDeMovimiento(int x, int y, int tipoMovimiento, int origenX) {
        if (tipoMovimiento == 0){
            movimientoSimple(x, y);
        } else if (tipoMovimiento == 1){
            movimientoDeCaptura(x, y, origenX);
        }
    }

    // Método para revisar si las coordenadas destino cooinciden con algun peon
    public void movimientoSimple(int x, int y) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y) {
                    if (coordenadas[2] == 0 && coordenadas[3] == 0) {
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará un movimiento simple.");
                        gestorPeonesNegras.moverPeon(i, x, y);
                        reseteoYGeneracionGeneral();
                        return;
                    }else if (coordenadas[2] == 1 && coordenadas[3] == 0){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará un movimienot doble");
                        gestorPeonesNegras.moverPeon(i, x, y);
                        reseteoYGeneracionGeneral();
                        return;
                    }
                    else if (coordenadas[3] == 1){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con una pieza aliada, no se puede realizar el desplazamiento");
                        return;
                    } else if (coordenadas[3] == 2){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con una pieza enemiga, no se puede realizar el desplazamiento");
                        return;
                    }
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void movimientoDeCaptura(int x, int y, int origenX) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                PeonesNegras peon = tablero.obtenerPeonNegras(i);
                if (coordenadas[0] == x && coordenadas[1] == y && peon.getX() == origenX) {
                    if (verificacionDeCapturaSimple(x, y)){
                        int indiceBlancas = tablero.obtenerIndicePeonPorCoordenadas(x, y);
                        PeonesBlancas peonBlanco = tablero.obtenerPeonBlancas(indiceBlancas);
                        peonBlanco.setEstado(false);
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " Que parte de la casilla " + peon.getX()+ " , " + peon.getY() + " y se realizará una captura.");
                        gestorPeonesNegras.moverPeon(i, x, y);
                        reseteoYGeneracionGeneral();
                        return;
                    } else {
                        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
                        return;
                    }
                }
            }
        }
        System.out.println("El movimiento no es válido.");
    }

    public void movimientoDeCapturaAlPaso(int x, int y, int tipoMovimiento) {
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == tipoMovimiento) {
                    PeonesNegras peon = tablero.obtenerPeonNegras(0);
                    if (peon.getY() == 4){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará una captura al paso.");
                        gestorPeonesBlancas.moverPeon(i, x, y);
                        gestorPeonesNegras.moverPeon(i, x, y);
                        reseteoYGeneracionGeneral();
                        return;
                    }
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará una captura.");
                    gestorPeonesNegras.moverPeon(i, x, y);
                    reseteoYGeneracionGeneral();
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void reseteoYGeneracionGeneral(){
        for (int i = 0; i < 8; i++){
            reseteoYGeneracion(i);
        }
    }

    public void reseteoYGeneracion(int indice){
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        if (peon.isEstado()){
            resetearCoordenadasPeon(indice);
            movimientos_Simples(indice);
            movimientos_DeCapturaDerecha(indice);
            movimientos_DeCapturaIzquierda(indice);
            if (peon.getY() == 4){
                capturas_AlPasoDerecha(indice);
                capturas_AlPasoIzquierda(indice);
            }
            if(!peon.isJugado()){
                movimientos_dobles(indice);
            }
        }
    }

    public boolean verificarCoincidenciaNegras(int x, int y){
        concidenciaNegras = false;
        for (int i = 0; i < peonesList.length; i++){
            PeonesNegras peon = tablero.obtenerPeonNegras(i);
            if (peon.getX() == x && peon.getY() == y){
                concidenciaNegras = true;
            }
        }
        return concidenciaNegras;
    }

    public boolean verificarCoincidenciaBlancas(int x, int y){
        concidenciaBlancas = false;
        for (int i = 0; i < peonesList.length; i++){
            PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
            if (peon.getX() == x && peon.getY() == y){
                concidenciaBlancas = true;
            }
        }
        return concidenciaBlancas;
    }

    // Método para registrar movimientos simples de los peones
    public void movimientos_Simples(int indice) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newY = peon.getY() - 1;
        int newX = peon.getX();
        if (newY >= 1) {
            asignacionDeErrores(indice, peon, newX, newY, 0);
        }
    }

    public void movimientos_dobles(int indice) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newY = peon.getY() - 2;
        int newX = peon.getX();
        if (newY >= 1 && !peon.isJugado()) {
            asignacionDeErrores(indice, peon, newX ,newY,1);
        }
    }

    private void asignacionDeErrores(int indice, PeonesNegras peon, int newX, int newY, int tipoMov) {
        if(verificarCoincidenciaNegras(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, tipoMov, 1});
        }else if(verificarCoincidenciaBlancas(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, tipoMov, 2});
        } else {
            peonesList[indice].add(new int[]{newX, newY, tipoMov, 0});
        }
    }

    public void movimientos_DeCapturaDerecha(int indice) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newY = peon.getY() - 1;
        int newX = peon.getX() - 1;
        if (newY >= 1 && newX <= 8 && newX >= 1) {
            asignacionDeErrores(indice, peon, newX, newY, 2);
        }
    }

    public void movimientos_DeCapturaIzquierda(int indice) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newY = peon.getY() - 1;
        int newX = peon.getX() + 1;
        if (newY <= 8 && newX <= 8 && newX >= 1) {
            asignacionDeErrores(indice, peon, newX, newY, 2);
        }
    }

    public void capturas_AlPasoDerecha(int indice) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newY = peon.getY() - 1;
        int newX = 4;
        if (newY <= 8) {
            asignacionDeErrores(indice, peon, newX, newY, 3);
        }
    }

    public void capturas_AlPasoIzquierda(int indice) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newY = peon.getY() + 1;
        int newX = 4;
        if (newY >= 1) {
            asignacionDeErrores(indice, peon, newX, newY, 3);
        }
    }

    // Método para resetear las coordenadas de un peón específico
    public void resetearCoordenadasPeon(int indice) {
        peonesList[indice].clear();
    }

    public Boolean verificacionDeCapturaSimple(int capturaX, int capturaY) {
        captura = false;
        for (int i = 0; i < 8; i++) {
            PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
            if (peon.getX() == capturaX && peon.getY() == capturaY && peon.isEstado()) {
                captura = true;
                break;
            }
        }
        return captura;
    }
}
