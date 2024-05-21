# Compilador - Ajedrez

## Descripción

Un compilador es una herramienta fundamental en informática que se encarga de traducir un lenguaje de programación de alto nivel a un lenguaje de máquina, entendible por el computador. Se compone de tres etapas principales: el analizador léxico, el analizador sintáctico y el analizador semántico. Estas etapas trabajan en conjunto para analizar la estructura y el significado del código fuente, identificar errores y generar un código objeto ejecutable.

Este concepto aplicado a este proyecto implica que nuestro compilador tiene la tarea específica de procesar un archivo de entrada que contiene los movimientos de una partida de ajedrez expresados en notación algebraica en español. La notación algebraica es un sistema estándar para registrar los movimientos en el ajedrez, que utiliza letras y números para representar las casillas del tablero y las piezas involucradas en cada movimiento.

El objetivo principal del compilador es validar la secuencia de movimientos proporcionada en el archivo de entrada para garantizar que cumpla con las reglas del ajedrez. Esto implica verificar la sintaxis y la semántica de cada movimiento. El objetivo será encontrar una secuencia lógica de movimientos, es decir, que cada movimiento sea coherente con las reglas del ajedrez.

## Gramatica del Compilador

`CASILLA` = `fila` `columna`

`DESPLAZAMIENTO` = `pieza` `CASILLA`

`CAPTURA` = `DESPLAZAMIENTO` `x` `CASILLA`

`CAPTURA DE PEON` = `columna` `x` `CASILLA`

`CAPUTRA DE PEON AL PASO` = `columna` `x` `CASILLA` `a.p.`

`PROMOCION DE PEON` = `DESPLAZAMIENTO` `=` `pieza`

`DIFERENCIA POR COLUMNA` = `pieza` `columna` `CASILLA`

`DIFERENCIA POR FILA` = `pieza` `fila` `CASILLA`

`DIFERENCIA POR AMBAS` = `pieza` `CASILLA` `CASILLA`

`CAPTURA CON AMBIGUEDAD` = `pieza` `REGLA APLICADA` `x` `CASILLA`

`MOVIEMIENTO` = `CAPTURA` | `CAPTURA DE PEON` | `PROMOCION DE PEON` | `DIFERENCIA POR COLUMNA` | `DIFERENCIA POR FILA` | `DIFERENCIA POR AMBAS` | `CAPTURA CON AMBIGUEDAD`

`MOVIMIENTO CON JAQUE` = `DESPLAZAMIENTO` `jaque`

`MOVIMIENTO CON JAQUE MATE` = `DESPLAZAMIENTO` `jaque mate`

`MOVIMIENTO CON COMENTARIO` = `DESPLAZAMIENTO`  `comentario` | `MOVIMIENTO CON JAQUE` `comentario` | `MOVIMIENTO CON JAQUE MATE` `comentario`

`ENTRADA` = `MOVIMIENTO` | `MOVIMIENTO CON JAQUE` | `MOVIMIENTO CON JAQUE MATE` | `MOVIMIENTO CON COMENTARIO`

Plugin para utilizar cup en Intelij IDEA
Se debe de configurar el pom xml para proceguir

    <plugin>
        <groupId>com.github.vbmacher</groupId>
        <artifactId>cup-maven-plugin</artifactId>
        <version>11b-20160615-3</version>
        <executions>
          <execution>
            <goals>
              <goal>generate</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <outputDirectory>${project.build.directory}/generated-sources/cup</outputDirectory>
        </configuration>
      </plugin>

https://github.com/vbmacher/cup-maven-plugin

## Analizador Syntactic

package org.compiladores;
import java_cup.runtime.*;

class Parser;

terminal FILA, COLUMNA, REY, DAMA, ALFIL, CABALLO, TORRE, PEON, CAPTURA, PROMOCION, CAP, ENROQUELARGO, ENROQUECORTO, JAQUEMATE, JAQUE, COMENTARIO;

non terminal pieza, casilla, desplazamiento, captura, cdp, cdpap, pdp, dpc, dpf, dpa, cca, movimiento, jque, jquemate, movimientofinal, comentario, entrada;

start with entrada;

entrada ::= movimientofinal {: RESULT = "Se logro algo xd"; :};

movimientofinal ::= movimiento comentario | jque comentario | jquemate comentario;

movimiento ::= desplazamiento | captura | cdp | cdpap | pdp | dpc | dpf | dpa | ENROQUECORTO | ENROQUELARGO;

jque ::= movimiento JAQUE;

jquemate ::= movimiento JAQUEMATE;

pieza ::= REY | DAMA | ALFIL | CABALLO | TORRE | PEON;

casilla ::= FILA COLUMNA;

desplazamiento ::= pieza casilla;

captura ::= pieza CAPTURA casilla;

cdp ::= COLUMNA CAPTURA casilla;

cdpap ::= COLUMNA CAPTURA casilla PROMOCION CAP;

pdp ::= desplazamiento PROMOCION pieza;

cca ::= CAPTURA | ;

dpc ::= pieza COLUMNA cca casilla;

dpf ::= pieza FILA cca casilla;

dpa ::= pieza casilla cca casilla;

comentario ::= COMENTARIO | ;