package org.compiladores;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class ParaserTest {
    @Test
    public void parserAnalysis() throws Exception {
        String expresion = "d4 e5>d5 a6";
        IDLexer lexer = new IDLexer(new StringReader(expresion));
        Parser p = new Parser(lexer);
        String resultado = (String) p.parse().value;
    }
}

//"d4 e5>d5 e4>d6 cxd6>f4 d6" -Error en el desplazamiento simple por coincidencia de pieza aliada
//"e4 d6>e5 dxe5>f4 e5" -Error en el desplazamiento doble por coincidencia de peon enemigo
//"e5 dxe6" -Error en el desplazamiento de captura simple por no coincidencia de peon enemigo