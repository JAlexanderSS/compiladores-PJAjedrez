package org.compiladores.tablero.negras;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeonesNegras {
    private int x;
    private int y = 7;
    private boolean jugado = false;
    private boolean estado = true;
}