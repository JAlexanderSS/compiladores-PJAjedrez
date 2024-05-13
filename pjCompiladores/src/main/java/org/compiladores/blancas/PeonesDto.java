package org.compiladores.blancas;

import java.io.Serializable;

public class PeonesDto implements Serializable {
    private int x;
    private int y;
    private boolean jugado;
    private boolean estado;

    public PeonesDto(int x, int y, boolean jugado, boolean estado) {
        this.x = x;
        this.y = y;
        this.jugado = jugado;
        this.estado = estado;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
