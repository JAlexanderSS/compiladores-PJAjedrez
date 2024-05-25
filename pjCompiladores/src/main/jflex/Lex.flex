// Código integro
package org.compiladores;
import java.io.*;
import java_cup.runtime.*;

%%
%class IDLexer
%public
%cup

//Filas
fila = [1-8]

//Columnas
columa = [a-h]

//Simgolos para cada pieza
rey = "R"
dama = "D"
torre = "T"
alfil = "A"
caballo = "C"
peon = "P"

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
space = [ ]
whitespace = [\t\r]
newline = \n

%eofval{
  return symbol(ParserSym.EOF);
%eofval}

%{
StringBuilder string = new StringBuilder();
private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
}

private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
}
%}

%%
{fila}  {
return symbol(ParserSym.FILA, yytext());
}

{columa}  {
return symbol(ParserSym.COLUMNA, yytext());
}

{rey}  {
return symbol(ParserSym.REY, yytext());
}

{dama}  {
return symbol(ParserSym.DAMA, yytext());
}

{torre}  {
return symbol(ParserSym.TORRE, yytext());
}

{alfil}  {
return symbol(ParserSym.ALFIL, yytext());
}

{caballo}  {
return symbol(ParserSym.CABALLO, yytext());
}

{peon}   {
return symbol(ParserSym.PEON, yytext());
}

{captura}  {
return symbol(ParserSym.CAPTURA, yytext());
}

{igual} {
return symbol(ParserSym.PROMOCION, yytext());
      }

{capturaap}  {
return symbol(ParserSym.CAP, yytext());
}

{enroqueLargo}  {
return symbol(ParserSym.ENROQUELARGO, yytext());
}

{enroqueCorto}  {
return symbol(ParserSym.ENROQUECORTO, yytext());
}

{jauqeMate}  {
return symbol(ParserSym.JAQUEMATE, yytext());
}

{jaque}  {
return symbol(ParserSym.JAQUE, yytext());
}

{comentario}  {
return symbol(ParserSym.COMENTARIO, yytext());
}

{space} {
          return symbol(ParserSym.ESPACIO, yytext());
      }

{whitespace}+  {/*Ignorar*/} // Ignorar espacios en blanco
{newline}  {/*Ignorar*/} // Ignorar saltos de linea
[^] { throw new Error("Caracter no valido: " + yytext()); }