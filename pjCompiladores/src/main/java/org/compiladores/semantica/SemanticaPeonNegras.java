package org.compiladores.semantica;

import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.blancas.PeonesBlancas;
import org.compiladores.tablero.negras.PeonesNegras;
import org.compiladores.tablero.negras.GestorPeonesNegras;

import java.util.ArrayList;

public class SemanticaPeonNegras {

    // Singleton instance
    private static SemanticaPeonNegras instance = null;

    GestorPeonesNegras gestorPeonesNegras = new GestorPeonesNegras();
    Tablero tablero = Tablero.obtenerInstancia();

    Boolean concidenciaNegras = false;
    Boolean concidenciaBlancas = false;
    Boolean captura = false;
    Boolean capturaAlPaso = false;

    // Crear un arreglo de ArrayLists para almacenar pares de enteros (x, y) de los 8 peones
    private static ArrayList<int[]>[] peonesList = new ArrayList[8];

    // Inicializador estático para inicializar los ArrayLists solo una vez
    static {
        for (int i = 0; i < peonesList.length; i++) {
            peonesList[i] = new ArrayList<>();
        }
    }

    // Constructor privado para el patrón Singleton
    private SemanticaPeonNegras() {
        inicializarMovimientosSimples();
    }

    // Método para obtener la instancia única de la clase
    public static SemanticaPeonNegras obtenerInstancia() {
        if (instance == null) {
            instance = new SemanticaPeonNegras();
        }
        return instance;
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
        if (capturaAlPaso){
            if(verificacionDeCapturaAlPaso(x, y)){
                System.out.println("Se realizará una captura al paso");
            }else if (tipoMovimiento == 0){
                movimientoSimple(x, y);
            } else if (tipoMovimiento == 2){
                movimientoDeCaptura(x, y, origenX);
            } else if(tipoMovimiento == 4){
                promocion(y);
            }
        }else{
            if (tipoMovimiento == 0){
                movimientoSimple(x, y);
            } else if (tipoMovimiento == 2){
                movimientoDeCaptura(x, y, origenX);
            }else if(tipoMovimiento == 4){
                promocion(y);
            }
        }
    }

    // Método para revisar si las coordenadas destino cooinciden con algun peon
    public void movimientoSimple(int x, int y) {
        SemanticaPeonBlancas semanticaPeonBlancas = SemanticaPeonBlancas.obtenerInstancia();
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y) {
                    if (coordenadas[2] == 0 && coordenadas[3] == 0) {
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará un movimiento simple.");
                        gestorPeonesNegras.moverPeon(i, x, y);
                        reseteoDePiezas();
                        reseteoYGeneracionGeneral();
                        return;
                    }else if (coordenadas[2] == 1 && coordenadas[3] == 0){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará un movimienot doble");
                        gestorPeonesNegras.moverPeon(i, x, y);
                        reseteoDePiezas();
                        semanticaPeonBlancas.Capturas_AlPaso(i, x);
                        reseteoYGeneracionGeneral();
                        return;
                    } else if (coordenadas[3] == 1){
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
                        int indiceBlancas = tablero.obtenerIndicePeonPorCoordenadasBlancas(x, y);
                        PeonesBlancas peonBlanco = tablero.obtenerPeonBlancas(indiceBlancas);
                        peonBlanco.setEstado(false);
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " Que parte de la casilla " + peon.getX()+ " , " + peon.getY() + " y se realizará una captura.");
                        gestorPeonesNegras.moverPeon(i, x, y);
                        reseteoDePiezas();
                        reseteoYGeneracionGeneral();
                    } else {
                        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
                    }
                    return;
                }
            }
        }
        System.out.println("El movimiento no es válido.");
    }

    public void reseteoDePiezas(){
        SemanticaCaballoBlancas semanticaCaballoBlancas = SemanticaCaballoBlancas.obtenerInstancia();
        SemanticaPeonBlancas semanticaPeonBlancas = SemanticaPeonBlancas.obtenerInstancia();
        SemanticaCaballosNegras semanticaCaballosNegras = SemanticaCaballosNegras.obtenerInstancia();
        semanticaPeonBlancas.reseteoYGeneracionGeneral();
        semanticaCaballoBlancas.reseteoYGeneracionGeneral();
        semanticaCaballosNegras.reseteoYGeneracionGeneral();
    }

    public void reseteoYGeneracionGeneral(){
        capturaAlPaso = false;
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

    public Boolean verificacionDeCapturaAlPaso(int x, int y) {
        boolean realizado = false;
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (capturaAlPaso && coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == 3) {
                    PeonesBlancas peonBlancas = tablero.obtenerPeonBlancas(coordenadas[4]);
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará una captura al paso.");
                    gestorPeonesNegras.moverPeon(i, x, y);
                    peonBlancas.setEstado(false);
                    reseteoDePiezas();
                    reseteoYGeneracionGeneral();
                    realizado = true;
                    return realizado;
                }
            }
        }
        return realizado;
    }

    public void Capturas_AlPaso(int indicePeonBlanco, int xBlanco){
        for (int i = 0; i < 8; i++){
            PeonesNegras peon = tablero.obtenerPeonNegras(i);
            if (peon.getX() + 1 == xBlanco && peon.getY() == 4){
                capturas_AlPasoIzquierda(i, indicePeonBlanco);
            } else if (peon.getX() - 1 == xBlanco && peon.getY() == 4){
                capturas_AlPasoDerecha(i, indicePeonBlanco);
            }
        }
    }

    public void capturas_AlPasoDerecha(int indice, int indicePeon) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newX = peon.getX() - 1;
        int newY = peon.getY() - 1;
        asignacionDeErroresAlPaso(indice, newX, newY, indicePeon);
        capturaAlPaso = true;
    }

    public void capturas_AlPasoIzquierda(int indice, int indicePeon) {
        PeonesNegras peon = tablero.obtenerPeonNegras(indice);
        int newX = peon.getX() + 1;
        int newY = peon.getY() - 1;
        asignacionDeErroresAlPaso(indice, newX, newY, indicePeon);
        capturaAlPaso = true;
    }

    private void asignacionDeErroresAlPaso(int indice, int newX, int newY, int indicePeonBlanco) {
        if(verificarCoincidenciaBlancas(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, 3, 1, indicePeonBlanco});
        }else if(verificarCoincidenciaNegras(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, 3, 2, indicePeonBlanco});
        } else {
            peonesList[indice].add(new int[]{newX, newY, 3, 0, indicePeonBlanco});
        }
    }

    private void promocion(int y){
        if (y == 1){
            System.out.println("Promocion");
        } else {
            System.out.println("No se puede realizar la promocion");
        }
    };

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
