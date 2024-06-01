package org.compiladores.semantica;

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

    Boolean concidenciaNegras = false;
    Boolean concidenciaBlancas = false;
    Boolean captura = false;

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

    //Metodos para generar movimientos posibles
    public void verticalDelanteDerecha(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 1;
        int newY = caballo.getY() + 2;
        if(newX <= 8 && newY <= 8){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDelanteIzquierda(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 1;
        int newY = caballo.getY() + 2;
        if(newX >= 1 && newY <= 8){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDetrasDerecha(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 1;
        int newY = caballo.getY() - 2;
        if(newX <= 8 && newY >= 1){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void verticalDetrasIzquierda(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 1;
        int newY = caballo.getY() - 2;
        if(newX >= 1 && newY >= 1){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalDerechaArriba(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 2;
        int newY = caballo.getY() + 1;
        if(newX <= 8 && newY <= 8){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalDerechaAbajo(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() + 2;
        int newY = caballo.getY() - 1;
        if(newX <= 8 && newY >= 1){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalIzquierdaArriba(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 2;
        int newY = caballo.getY() + 1;
        if(newX >= 1 && newY <= 8){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public void horizontalIzquierdaAbajo(int indice){
        CaballosBlancas caballo = tablero.obtenerCaballoBlancas(indice);
        int newX = caballo.getX() - 2;
        int newY = caballo.getY() - 1;
        if(newX >= 1 && newY >= 1){
            asignacionDeErrores(indice, newX, newY);
        }
    }

    public boolean verificarCoincidenciaNegras(int x, int y){
        concidenciaNegras = false;
        for (int i = 0; i < 8; i++){
            PeonesNegras peon = tablero.obtenerPeonNegras(i);
            if (peon.getX() == x && peon.getY() == y){
                concidenciaNegras = true;
            }
        }
        return concidenciaNegras;
    }

    public boolean verificarCoincidenciaBlancas(int x, int y){
        concidenciaBlancas = false;
        for (int i = 0; i < 8; i++){
            PeonesBlancas peon = tablero.obtenerPeonBlancas(i);
            CaballosBlancas caballo = tablero.obtenerCaballoBlancas(i);
            if (peon.getX() == x && peon.getY() == y){
                concidenciaBlancas = true;
            } else if (caballo.getX() == x && caballo.getY() == y){
                concidenciaBlancas = true;
            }
        }
        return concidenciaBlancas;
    }

    private void asignacionDeErrores(int indice, int newX, int newY) {
        if(verificarCoincidenciaNegras(newX, newY)){
            caballosList[indice].add(new int[]{newX, newY, 1});
        }else if(verificarCoincidenciaBlancas(newX, newY)){
            caballosList[indice].add(new int[]{newX, newY, 2});
        } else {
            caballosList[indice].add(new int[]{newX, newY, 0});
        }
    }

    public void validacionDeMovimiento(int x, int y, int tipoMovimiento){
        if (tipoMovimiento == 0){
            desplazamientoCaballo(x, y);
        }else if (tipoMovimiento == 1){
            System.out.println("Movimiento invalido, el caballo no puede realizar capturas aun");
        }
    }

    public void desplazamientoCaballo(int x, int y){
        for (int i = 0; i < caballosList.length; i++) {
            for (int[] datosCaballos : caballosList[i]){
                if (datosCaballos[0] == x && datosCaballos[1]==y){
                    if (datosCaballos[2] == 0){
                        System.out.println("Movimiento valido");
                        gestorCaballoBlancas.moverCaballo(i, x, y);
                        return;
                    }
                    else if (datosCaballos[2] == 1){
                        System.out.println("Existe una pieza aliada en la casilla seleccionada");
                        return;
                    }
                    else if (datosCaballos[2] == 2){
                        System.out.println("Movimiento invalido, existe una pieza enemiga en la casilla seleccionada para desplazar el caballo");
                        return;
                    }
                }
                else{
                    System.out.println("Movimiento invalido");
                }
            }
        }
    }
}
