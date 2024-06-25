package org.compiladores.semantica;

import org.compiladores.datos.conversion.controller.FileUploadController;
import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.blancas.CaballosBlancas;
import org.compiladores.tablero.blancas.GestorCaballoBlancas;
import org.compiladores.tablero.blancas.PeonesBlancas;
import org.compiladores.tablero.negras.PeonesNegras;

import java.util.ArrayList;

public class SemanticaCaballoBlancas {

    private static SemanticaCaballoBlancas instance = null;

    GestorCaballoBlancas gestorCaballoBlancas = new GestorCaballoBlancas();
    Tablero tablero = Tablero.obtenerInstancia();

    Boolean coincidenciaNegras = false;
    Boolean coincidenciaBlancas = false;

    // Crear un arreglo de ArrayLists para almacenar pares de enteros (x, y) de los 2 caballos
    private static ArrayList<int[]>[] caballosList = new ArrayList[2];

    // Inicializador estático para inicializar los ArrayLists solo una vez
    static {
        for (int i = 0; i < caballosList.length; i++) {
            caballosList[i] = new ArrayList<>();
        }
    }

    // Constructor privado para el patrón Singleton
    private SemanticaCaballoBlancas() {
        inicializarMovimientos();
    }

    // Método para obtener la instancia única de la clase
    public static SemanticaCaballoBlancas obtenerInstancia() {
        if (instance == null) {
            instance = new SemanticaCaballoBlancas();
        }
        return instance;
    }

    // Método para inicializar movimientos de los caballos
    private void inicializarMovimientos() {
        for (int i = 0; i < caballosList.length; i++) {
            verticalDelanteDerecha(i);
            verticalDelanteIzquierda(i);
            verticalDetrasDerecha(i);
            verticalDetrasIzquierda(i);
            horizontalDerechaArriba(i);
            horizontalDerechaAbajo(i);
            horizontalIzquierdaArriba(i);
            horizontalIzquierdaAbajo(i);
        }
    }

    // Métodos para generar movimientos posibles
    public void verticalDelanteDerecha(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 1;
        int newY = caballo.getY() + 2;
        if (newX <= 8 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDelanteIzquierda(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 1;
        int newY = caballo.getY() + 2;
        if (newX >= 1 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDetrasDerecha(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 1;
        int newY = caballo.getY() - 2;
        if (newX <= 8 && newY >= 1) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDetrasIzquierda(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 1;
        int newY = caballo.getY() - 2;
        if (newX >= 1 && newY >= 1) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalDerechaArriba(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 2;
        int newY = caballo.getY() + 1;
        if (newX <= 8 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalDerechaAbajo(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 2;
        int newY = caballo.getY() - 1;
        if (newX <= 8 && newY >= 1) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalIzquierdaArriba(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 2;
        int newY = caballo.getY() + 1;
        if (newX >= 1 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalIzquierdaAbajo(int indice) {
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 2;
        int newY = caballo.getY() - 1;
        if (newX >= 1 && newY >= 1) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public boolean verificarCoincidenciaNegras(int x, int y) {
        coincidenciaNegras = false;
        for (int i = 0; i < 8; i++) {
            PeonesNegras peon = tablero.obtenerPeonNegras(i);
            if (peon.getX() == x && peon.getY() == y) {
                coincidenciaNegras = true;
            }
        }
        return coincidenciaNegras;
    }

    public boolean verificarCoincidenciaBlancas(int x, int y) {
        coincidenciaBlancas = false;
        for (int i = 0; i < 8; i++) {
            PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
            if (peon.getX() == x && peon.getY() == y) {
                coincidenciaBlancas = true;
            }
        }
        for (int i = 0; i < 2; i++) {
            CaballosBlancas caballo = tablero.obtenerCaballoBlancas(i);
            if (caballo.getX() == x && caballo.getY() == y) {
                coincidenciaBlancas = true;
            }
        }
        return coincidenciaBlancas;
    }

    private void asignacionDeErrores(int indice, int newX, int newY) {
        if (verificarCoincidenciaNegras(newX, newY)) {
            caballosList[indice].add(new int[]{newX, newY, 2});
        } else if (verificarCoincidenciaBlancas(newX, newY)) {
            caballosList[indice].add(new int[]{newX, newY, 1});
        } else {
            caballosList[indice].add(new int[]{newX, newY, 0});
        }
    }

    public void validacionDeMovimiento(int x, int y, int tipoMovimiento) {
        if (tipoMovimiento == 0) {
            desplazamientoCaballo(x, y);
        } else if (tipoMovimiento == 2) {
            capturaDeCaballo(x, y);
        }
    }

    public void desplazamientoCaballo(int x, int y) {
        boolean movimientoValido = false;
        for (int i = 0; i < caballosList.length; i++) {
            for (int[] datosCaballos : caballosList[i]) {
                if (datosCaballos[0] == x && datosCaballos[1] == y) {
                    if (datosCaballos[2] == 0) {
                        System.out.println("Movimiento válido");
                        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(i);
                        FileUploadController fileUploadController = new FileUploadController();
                        fileUploadController.extractMovements(caballo.getX(), caballo.getY(), x, y);
                        gestorCaballoBlancas.moverCaballo(i, x, y);
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteDePiezas();
                        return;
                    } else if (datosCaballos[2] == 1) {
                        System.out.println("Existe una pieza aliada en la casilla seleccionada");
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteDePiezas();
                        return;
                    } else if (datosCaballos[2] == 2) {
                        System.out.println("Movimiento inválido, existe una pieza aliada en la casilla seleccionada para desplazar el caballo");
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteDePiezas();
                        return;
                    }
                }
            }
        }
        if (!movimientoValido) {
            System.out.println("Movimiento inválido");
        }
    }

    public void capturaDeCaballo(int x, int y){
        boolean movimientoValido = false;
        for (int i = 0; i < caballosList.length; i++) {
            for (int[] datosCaballos : caballosList[i]) {
                if (datosCaballos[0] == x && datosCaballos[1] == y) {
                    if (datosCaballos[2] == 0) {
                        System.out.println("Movimiento válido");
                        movimientoValido = true;

                        reseteoYGeneracionGeneral();
                        reseteDePiezas();
                        return;
                    } else if (datosCaballos[2] == 1) {
                        System.out.println("Movimiento inválido, existe una pieza aliada en la casilla seleccionada para desplazar el caballo");
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteDePiezas();
                        return;
                    } else if (datosCaballos[2] == 2) {
                        System.out.println("Movimiento valido para una captura de caballo.");
                        movimientoValido = true;
                        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(i);
                        FileUploadController fileUploadController = new FileUploadController();
                        fileUploadController.extractMovements(caballo.getX(), caballo.getY(), x, y);
                        gestorCaballoBlancas.moverCaballo(i, x, y);
                        reseteoYGeneracionGeneral();
                        reseteDePiezas();
                        return;
                    }
                }
            }
        }
        if (!movimientoValido) {
            System.out.println("Movimiento inválido");
        }
    }

    public void reseteDePiezas(){
        SemanticaCaballosNegras semanticaCaballosNegras = SemanticaCaballosNegras.obtenerInstancia();
        SemanticaPeonNegras semanticaPeonNegras = SemanticaPeonNegras.obtenerInstancia();
        SemanticaPeonBlancas semanticaPeonBlancas = SemanticaPeonBlancas.obtenerInstancia();
        semanticaCaballosNegras.reseteoYGeneracionGeneral();
        semanticaPeonNegras.reseteoYGeneracionGeneral();
        semanticaPeonBlancas.reseteoYGeneracionGeneral();
    }

    public void reseteoYGeneracionGeneral(){
        for (int i = 0; i < 2; i++){
            reseteoYGeneracion(i);
            generacionDeMovimientos(i);
        }
    }

    public void reseteoYGeneracion(int indice) {
        caballosList[indice].clear();
    }

    public void generacionDeMovimientos(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        if (caballo.isEstado()){
            verticalDelanteDerecha(indice);
            verticalDelanteIzquierda(indice);
            verticalDetrasDerecha(indice);
            verticalDetrasIzquierda(indice);
            horizontalDerechaArriba(indice);
            horizontalDerechaAbajo(indice);
            horizontalIzquierdaArriba(indice);
            horizontalIzquierdaAbajo(indice);
        }
    }
}
