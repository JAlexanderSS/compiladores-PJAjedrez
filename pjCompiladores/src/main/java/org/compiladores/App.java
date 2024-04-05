package org.compiladores;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) {
        try {
            org.compiladores.AjedrezLexer lexer = new org.compiladores.AjedrezLexer(new FileReader("Lista.txt"));
            Token token;
            while ((token = lexer.yylex()) != null) {
                if (token.getTokenType() == TokenConstant.EOF) {
                    break;
                }
                System.out.println(token);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de lectura del archivo: " + e.getMessage());
        }
    }
}
