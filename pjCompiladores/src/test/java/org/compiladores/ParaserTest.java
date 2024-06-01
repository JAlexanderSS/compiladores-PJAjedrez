package org.compiladores;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class ParaserTest {
    @Test
    public void parserAnalysis() throws Exception {
        String expresion = "Ch3 g5>b4 Cc6>Cxg5 Cxb4";
        IDLexer lexer = new IDLexer(new StringReader(expresion));
        Parser p = new Parser(lexer);
        String resultado = (String) p.parse().value;
    }
}

//"d4 e5>d5 e4>d6 cxd6>f4 d6" -Error en el desplazamiento simple por coincidencia de pieza aliada
//"e4 d6>e5 dxe5>f4 e5" -Error en el desplazamiento doble por coincidencia de peon enemigo
//"e5 dxe6" -Error en el desplazamiento de captura simple por no coincidencia de peon enemigo
//"d4 e5>d5 a6" -Verifiacion del tipo de movimiento
//"e4 d5>c4 b5>exd5 bxc4>b3 e6" -Verificacion de capturas
//"d4 h6>d5 e5>dxe6 h5" -Captura al paso de las blancas
//"h4 a5>h5 a4>b4 axb3>f3 g5>hxg6 bxc2" -Capturas al paso de blancas y negras
//"h4 a5>h5 a4>b4 axb3>f3 g5>d3 d6>hxg6 bxc2" -Capturas al paso de blancas y negras con error en una captura al paso
//"c4 c5>c5 f6" -Probar movimientos erroneos y concidencia negras

//"Ch3 g5>Cxg5 f6"