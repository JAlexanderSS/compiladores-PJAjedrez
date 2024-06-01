package org.compiladores.semantica;

import org.compiladores.tablero.Tablero;
import org.compiladores.tablero.blancas.CaballosBlancas;
import org.compiladores.tablero.blancas.PeonesBlancas;
import org.compiladores.tablero.negras.CaballosNegras;
import org.compiladores.tablero.negras.GestorCaballosNegras;
import org.compiladores.tablero.negras.PeonesNegras;

import java.util.ArrayList;

public class SemanticaCaballosNegras {
    private static SemanticaCaballosNegras instancia = null;

    GestorCaballosNegras gestorCaballosNegras = new GestorCaballosNegras();
    Tablero tablero = Tablero.obtenerInstancia();

    Boolean coincidenciaNegras = false;
    Boolean coincidenciaBlancas = false;

    private static ArrayList<int[]>[] caballosList = new ArrayList[2];

    static {
        for (int i = 0; i < caballosList.length; i++) {
            caballosList[i] = new ArrayList<>();
        }
    }

    private SemanticaCaballosNegras() {
        inicializarMovimientos();
    }

    public static SemanticaCaballosNegras obtenerInstancia() {
        if (instancia == null) {
            instancia = new SemanticaCaballosNegras();
        }
        return instancia;
    }

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
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() - 1;
        int newY = caballo.getY() - 2;
        if (newX >= 1 && newY >= 1) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDelanteIzquierda(int indice) {
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() + 1;
        int newY = caballo.getY() - 2;
        if (newX <= 8 && newY >= 1) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDetrasDerecha(int indice) {
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() - 1;
        int newY = caballo.getY() + 2;
        if (newX >= 1 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDetrasIzquierda(int indice) {
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() + 1;
        int newY = caballo.getY() + 2;
        if (newX <= 8 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalDerechaArriba(int indice) {
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() - 2;
        int newY = caballo.getY() - 1;
        if (newX >= 1 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalDerechaAbajo(int indice) {
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() - 2;
        int newY = caballo.getY() + 1;
        if (newX >= 1 && newY >= 1) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalIzquierdaArriba(int indice) {
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() + 2;
        int newY = caballo.getY() - 1;
        if (newX <= 8 && newY <= 8) {
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalIzquierdaAbajo(int indice) {
        CaballosNegras caballo = tablero.obtenerCaballoNegras(indice);
        int newX = caballo.getX() + 2;
        int newY = caballo.getY() + 1;
        if (newX <= 8 && newY >= 1) {
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
        for (int i = 0; i < 2; i++) {
            CaballosNegras caballo = tablero.obtenerCaballoNegras(i);
            if (caballo.getX() == x && caballo.getY() == y) {
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
                        gestorCaballosNegras.moverCaballo(i, x, y);
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteoDePiezas();
                        return;
                    } else if (datosCaballos[2] == 1) {
                        System.out.println("Existe una pieza enemiga en la casilla seleccionada");
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteoDePiezas();
                        return;
                    } else if (datosCaballos[2] == 2) {
                        System.out.println("Movimiento inválido, existe una pieza aliada en la casilla seleccionada para desplazar el caballo");
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteoDePiezas();
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
                        System.out.println("Movimiento no correspondiente a la notacion");
                        gestorCaballosNegras.moverCaballo(i, x, y);
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteoDePiezas();
                        return;
                    } else if (datosCaballos[2] == 1) {
                        System.out.println("Movimiento valido para la captura");
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteoDePiezas();
                        return;
                    } else if (datosCaballos[2] == 2) {
                        System.out.println("Movimiento inválido, existe una pieza aliada en la casilla seleccionada para desplazar el caballo");
                        movimientoValido = true;
                        reseteoYGeneracionGeneral();
                        reseteoDePiezas();
                        return;
                    }
                }
            }
        }
        if (!movimientoValido) {
            System.out.println("Movimiento inválido");
        }
    }

    public void reseteoDePiezas(){
        SemanticaCaballoBlancas semanticaCaballoBlancas = SemanticaCaballoBlancas.obtenerInstancia();
        SemanticaPeonNegras semanticaPeonNegras = SemanticaPeonNegras.obtenerInstancia();
        SemanticaPeonBlancas semanticaPeonBlancas = SemanticaPeonBlancas.obtenerInstancia();
        semanticaCaballoBlancas.reseteoYGeneracionGeneral();
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