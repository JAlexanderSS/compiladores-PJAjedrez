// Código integro
package org.compiladores;
import java.io.*;

%%
// Opciones y declaraciones
%class AjedrezLexer
%public
%line
%column
%char
%cup
%unicode
%ignorecase

//Filas
fila = [1-8]

//Columnas
columa = [a-h]

//Simgolos para cada pieza
rey = "R"
dama = "D"
torre = "T"
arfil = "A"
caballo = "C"
peon = "P"

//Cualquier pieza
pieza = {rey}|{dama}|{torre}|{arfil}|{caballo}|{peon}?

//Signos para capturas
captura = "x"
punto = "."
pasoa = "a"
pasop = "p"

//Capuras al paso
capturaap = {pasoa}{punto}{pasop}{punto}

//Signos para enroques
guion = "-"
cero = "0"

//Tipos de enroques
enroqueCorto = {cero}{guion}{cero}
enroqueLargo = {enroqueCorto}{guion}{cero}

//Promocion
igual = "="

//Signos para jaques
mas = "+"
numeral = "#"

//Jaques
jauqeMate = {numeral}|{mas}{2}
jaque = {mas}

//Signos para comentarios
admiracion = "!"
diagonal = "/"
infinito = "∞"
novedad = "N"
interrogacion = "?"

//Comentarios en el juego
jb = {admiracion} // !: jugada buena
jm = {interrogacion} //?: jugada mala
jmb = {admiracion}{2} //!!: jugada muy buena
jmm = {interrogacion}{2} //??: jugada muy mala
ji = {admiracion}{interrogacion} //!?: jugada interesante
jd = {interrogacion}{admiracion} //?!: jugada dudosa
joaj = {igual} //=: igualdad de oportunidades para ambos jugadores
lvb = {mas}{diagonal}{igual} //+/=: ligera ventaja blanca
lvn = {igual}{diagonal}{mas}//=/+: ligera ventaja negra
vb = {mas}{diagonal}{guion} //+/- (o también ±): ventaja blanca
vn = {guion}{diagonal}{mas} //-/+: ventaja negra
vdb = {mas}{guion} //+-: ventaja decisiva blanca
vdn = {guion}{mas} //-+: ventaja decisiva negra
pi = {infinito} //∞: posición incierta
jcpdm = {igual}{diagonal}{infinito}|{infinito}{igual} //=/∞ (o también ∞=): juego compensado a pesar de diferencia de material
nt = {novedad} //N: novedad teórica

//Comentarios en el juego
comentario = {jb}|{jm}|{jmb}|{jmm}|{ji}|{jd}|{joaj}|{lvb}|{lvn}|{vb}|{vn}|{vdb}|{vdn}|{pi}|{jcpdm}|{nt}
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
{fila}  {
printError();
return new Token(TokenConstant.HORIZONTAL, yytext(), yline, column);
}

{columa}  {
printError();
return new Token(TokenConstant.VERTICAL, yytext(), yline, column);
}

{pieza}  {
printError();
return new Token(TokenConstant.PIEZA, yytext(), yline, column);
}

{captura}  {
printError();
return new Token(TokenConstant.CAPTURA, yytext(), yline, column);
}

{capturaap}  {
printError();
return new Token(TokenConstant.SIGLASCAP, yytext(), yline, column);
}

{enroqueLargo}  {
printError();
return new Token(TokenConstant.ENROQUELARGO, yytext(), yline, column);
}

{enroqueCorto}  {
printError();
return new Token(TokenConstant.ENROQUECORTO, yytext(), yline, column);
}

{jauqeMate}  {
printError();
return new Token(TokenConstant.JAQUEMATE, yytext(), yline, column);
}

{jaque}  {
printError();
return new Token(TokenConstant.JAQUE, yytext(), yline, column);
}

{comentario}  {
printError();
return new Token(TokenConstant.COMENTARIO, yytext(), yline, column);
}

{whitespace}+  {/*Ignorar*/} // Ignorar espacios en blanco
{newline}  {printError();}
.     {
    currentToken.append(yytext());
}