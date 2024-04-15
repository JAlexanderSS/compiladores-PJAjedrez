// Código integro
package org.compiladores;
import java.io.*;

%%
// Opciones y declaraciones
%public
%class AjedrezLexer
%line
%column

//Filas
fila = [1-8]

//Columnas
columa = [a-h]

//Piezas
rey = "R"
dama = "D"
torre = "T"
arfil = "A"
caballo = "C"
peon = "P"

//Movimientos
guion = "-"
cero = "0"
captura = "x"
igual = "="

//Jaques
mas = "+"
numeral = "#"

//Comentarios en la partida
diagonal = "/"
infinito = "∞"
novedad = "N"
interrogacion = "?"

//Saltos de linea y espacios
whitespace = [\t\r ]
newline = \n

%type Token

%eofval{
  printError();
  return new Token(TokenConstant.EOF,yytext(), yline, column);
%eofval}

%{
private StringBuilder currentToken = new StringBuilder();
String yline = "";
String column = "";
// Función para imprimir un error con la posición del token
private void printError() {
yline = Integer.toString(yyline + 1);
column = Integer.toString(yycolumn + 1);
    if (currentToken.length() > 0) {
        System.err.println("Error en la línea " + yline + ", columna " + yycolumn + ": Palabra no reconocida -> " + currentToken.toString());
        currentToken.setLength(0);
    }
}
%}

%%
{columnas}  {
printError();
return new Token(TokenConstant.HORIZONTAL, yytext(), yline, column);
}

{pVertical}  {
printError();
return new Token(TokenConstant.VERTICAL, yytext(), yline, column);
}

{rey}  {
printError();
return new Token(TokenConstant.REY, yytext(), yline, column);
}

{dama}  {
printError();
return new Token(TokenConstant.DAMA, yytext(), yline, column);
}

{torre}  {
printError();
return new Token(TokenConstant.TORRE, yytext(), yline, column);
}
{arfil}  {
printError();
return new Token(TokenConstant.ARFIL, yytext(), yline, column);
}

{caballo}  {
printError();
return new Token(TokenConstant.CABALLO, yytext(), yline, column);
}

{peon}  {
printError();
return new Token(TokenConstant.PEON, yytext(), yline, column);
}

{jaque}  {
printError();
return new Token(TokenConstant.JAQUE, yytext(), yline, column);
}

{enroque}  {
printError();
return new Token(TokenConstant.ENROQUE, yytext(), yline, column);
}

{captura}  {
printError();
return new Token(TokenConstant.CAPTURA, yytext(), yline, column);
}

{jaqueMate}  {
printError();
return new Token(TokenConstant.JAQUEMATE, yytext(), yline, column);
}

{whitespace}+  {/*Ignorar*/} // Ignorar espacios en blanco
{newline}  {printError();}
.     {
    currentToken.append(yytext());
}