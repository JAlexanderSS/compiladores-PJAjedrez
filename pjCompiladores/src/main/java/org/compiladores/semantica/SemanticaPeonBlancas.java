package org.compiladores.semantica;

import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.negras.PeonesNegras;
import org.compiladores.tablero.blancas.GestorPeonesBlancas;
import org.compiladores.tablero.blancas.PeonesBlancas;

import java.util.ArrayList;

public class SemanticaPeonBlancas {

    // Singleton instance
    private static SemanticaPeonBlancas instance = null;

    GestorPeonesBlancas gestorPeonesBlancas = new GestorPeonesBlancas();
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
    private SemanticaPeonBlancas() {
        inicializarMovimientosSimples();
    }

    // Método para obtener la instancia única de la clase
    public static SemanticaPeonBlancas obtenerInstancia() {
        if (instance == null) {
            instance = new SemanticaPeonBlancas();
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
            }else if(tipoMovimiento == 4){
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

    // Método para revisar si las coordenadas coinciden con algún peón
    public void movimientoSimple(int x, int y) {
        SemanticaPeonNegras semanticaPeonNegras = SemanticaPeonNegras.obtenerInstancia();
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (coordenadas[0] == x && coordenadas[1] == y) {
                    if (coordenadas[2] == 0 && coordenadas[3] == 0){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará un movimiento simple.");
                        gestorPeonesBlancas.moverPeon(i, x, y);
                        semanticaPeonNegras.reseteoYGeneracionGeneral();
                        reseteoYGeneracionGeneral();
                        return;
                    } else if (coordenadas[2] == 1 && coordenadas[3] == 0){
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará un movimienot doble");
                        gestorPeonesBlancas.moverPeon(i, x, y);
                        semanticaPeonNegras.reseteoYGeneracionGeneral();
                        semanticaPeonNegras.Capturas_AlPaso(i, x);
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
        SemanticaPeonNegras semantica = SemanticaPeonNegras.obtenerInstancia();
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
                if (coordenadas[0] == x && coordenadas[1] == y && peon.getX() == origenX) {
                    if (verificacionDeCapturaSimple(x, y)){
                        int indice = tablero.obtenerIndicePeonPorCoordenadasNegras(x, y);
                        PeonesNegras peonNegro = tablero.obtenerPeonNegras(indice);
                        peonNegro.setEstado(false);
                        System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " Que parte de la casilla " + peon.getX()+ " , " + peon.getY() + " y se realizará una captura.");
                        gestorPeonesBlancas.moverPeon(i, x, y);
                        semantica.reseteoYGeneracionGeneral();
                        reseteoYGeneracionGeneral();
                    } else {
                        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
                    }
                    return;
                }
            }
        }
        System.out.println("Las coordenadas (" + x + ", " + y + ") no coinciden con ningún peón.");
    }

    public void reseteoYGeneracionGeneral(){
        capturaAlPaso = false;
        for (int i = 0; i < 8; i++){
            reseteoYGeneracion(i);
        }
    }

    public void reseteoYGeneracion(int indice){
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
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
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        int newX = peon.getX();
        if (newY <= 8) {
            asignacionDeErrores(indice, peon, newX, newY, 0);
        }
    }

    public void movimientos_dobles(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 2;
        int newX = peon.getX();
        if (newY <= 8 && !peon.isJugado()) {
            asignacionDeErrores(indice, peon, newX, newY, 1);
        }
    }

    private void asignacionDeErrores(int indice, PeonesBlancas peon, int newX, int newY, int tipoMov) {
        if(verificarCoincidenciaBlancas(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, tipoMov, 1});
        }else if(verificarCoincidenciaNegras(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, tipoMov, 2});
        } else {
            peonesList[indice].add(new int[]{newX, newY, tipoMov, 0});
        }
    }

    public void movimientos_DeCapturaDerecha(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        int newX = peon.getX() + 1;
        if (newY <= 8 && newX <= 8 && newX >= 1) {
            asignacionDeErrores(indice, peon, newX, newY, 2);
        }
    }

    public void movimientos_DeCapturaIzquierda(int indice) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newY = peon.getY() + 1;
        int newX = peon.getX() - 1;
        if (newY <= 8 && newX <= 8 && newX >= 1) {
            asignacionDeErrores(indice, peon, newX, newY, 2);
        }
    }

    public Boolean verificacionDeCapturaAlPaso(int x, int y) {
        SemanticaPeonNegras semantica = SemanticaPeonNegras.obtenerInstancia();
        boolean realizado = false;
        for (int i = 0; i < peonesList.length; i++) {
            for (int[] coordenadas : peonesList[i]) {
                if (capturaAlPaso && coordenadas[0] == x && coordenadas[1] == y && coordenadas[2] == 3) {
                    PeonesNegras peonNegras = tablero.obtenerPeonNegras(coordenadas[4]);
                    System.out.println("Las coordenadas (" + x + ", " + y + ") coinciden con el peón en el índice " + i + " y se realizará una captura al paso.");
                    gestorPeonesBlancas.moverPeon(i, x, y);
                    peonNegras.setEstado(false);
                    semantica.reseteoYGeneracionGeneral();
                    reseteoYGeneracionGeneral();
                    realizado = true;
                    return realizado;
                }
            }
        }
        return realizado;
    }

    public void Capturas_AlPaso(int indicePeonNegro, int xNegro){
        for (int i = 0; i < 8; i++){
            PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
            if (peon.getX() + 1 == xNegro && peon.getY() == 5){
                capturas_AlPasoDerecha(i, indicePeonNegro);
            } else if (peon.getX() - 1 == xNegro && peon.getY() == 5){
                capturas_AlPasoIzquierda(i, indicePeonNegro);
            }
        }
    }

    public void capturas_AlPasoDerecha(int indice, int indicePeon) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newX = peon.getX() + 1;
        int newY = peon.getY() + 1;
        asignacionDeErroresAlPaso(indice, newX, newY, indicePeon);
        capturaAlPaso = true;
    }

    public void capturas_AlPasoIzquierda(int indice, int indicePeon) {
        PeonesBlancas peon = tablero.obtenerPeonBlancas(indice);
        int newX = peon.getX() - 1;
        int newY = peon.getY() + 1;
        asignacionDeErroresAlPaso(indice, newX, newY, indicePeon);
        capturaAlPaso = true;
    }

    private void asignacionDeErroresAlPaso(int indice, int newX, int newY, int indicePeonNegro) {
        if(verificarCoincidenciaNegras(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, 3, 1, indicePeonNegro});
        }else if(verificarCoincidenciaBlancas(newX, newY)){
            peonesList[indice].add(new int[]{newX, newY, 3, 2, indicePeonNegro});
        } else {
            peonesList[indice].add(new int[]{newX, newY, 3, 0, indicePeonNegro});
        }
    }

    private void promocion(int y){
        if (y == 8){
            System.out.println("Promocion");
        }
    }

    // Método para resetear las coordenadas de un peón específico
    public void resetearCoordenadasPeon(int indice) {
        peonesList[indice].clear();
    }

    public Boolean verificacionDeCapturaSimple(int capturaX, int capturaY){
        captura = false;
        for (int i = 0; i < 8; i++){
            PeonesNegras peon = tablero.obtenerPeonNegras(i);
            if (peon.getX() == capturaX && peon.getY() == capturaY && peon.isEstado()){
                captura = true;
                break;
            }
        }
        return captura;
    }
}
