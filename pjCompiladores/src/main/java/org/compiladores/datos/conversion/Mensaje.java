package org.compiladores.datos.conversion;

import java.util.List;

public class Mensaje {
    private String mensaje;
    private List<String> movimientos;

    public Mensaje(String message, List<String> movimientos) {
        this.mensaje = mensaje;
        this.movimientos = movimientos;
    }
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<String> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<String> movimientos) {
        this.movimientos = movimientos;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "message='" + mensaje + '\'' +
                ", movimientos=" + movimientos +
                '}';
    }
}
