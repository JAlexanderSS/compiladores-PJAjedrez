package org.compiladores;
import org.compiladores.tablero.Tablero;
import picocli.CommandLine;

import java.io.StringReader;
import java.util.Scanner;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(name = "Ajedrez", mixinStandardHelpOptions = true, version = "Ajedrez 1.0",
        description = "Juego de ajedrez")
public class App implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            System.out.println("Ingrese la jugada");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            Parser p = new Parser(new IDLexer(new StringReader(input)));
            System.out.println(p.parse().value);
        } while (true);
        return 0;
    }

    public static void main( String[] args )
    {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
