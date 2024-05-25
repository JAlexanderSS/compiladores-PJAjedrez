package org.compiladores.tablero.blancas;

import lombok.*;

@Getter
@Setter
public class PeonesBlancas {
    private int x;
    private int y = 2;
    private boolean jugado = false;
    private boolean estado = true;
}