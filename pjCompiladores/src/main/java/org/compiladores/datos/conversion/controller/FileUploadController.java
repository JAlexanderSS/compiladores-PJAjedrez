package org.compiladores.datos.conversion.controller;

import java_cup.runtime.Symbol;
import org.compiladores.IDLexer;
import org.compiladores.Parser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileUploadController {
    private static ArrayList<int[]> posiciones = new ArrayList<>();

    private List<String> movimientosController = new ArrayList<>();
    static String[] ey = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public static String letter(int x) {
        String val = "";
        if (x >= 1 && x <= 8) {
            val = ey[x - 1];
        }
        return val;
    }

    public void extractMovements(int xInicial, int yInicial, int xFinal, int yFinal) {
        posiciones.add(new int[]{xInicial, yInicial, xFinal, yFinal});
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(List.of("archivo vacio"));
        }

        try {
            BufferedReader fileCompilador = new BufferedReader(new InputStreamReader(file.getInputStream()));
            IDLexer lexer = new IDLexer(fileCompilador);
            Parser parser = new Parser(lexer);
            Symbol result = parser.parse();  // Aquí se realiza el análisis sintáctico
            // Aquí puedes manejar el resultado del análisis sintáctico
            // movimientos = extractMovements(result);  // Función para extraer movimientos del resultado
        } catch (Exception e) {
            System.out.println("Error al enviar el file al compilador");
            return ResponseEntity.status(500).body(List.of("Error al enviar el file al compilador"));
        }

        for (int[] coordenadas : posiciones) {
            String xInicial = letter(coordenadas[0]);
            String xFinal = letter(coordenadas[2]);
            String mobBlancas = xInicial + coordenadas[1];
            String mobNegras = xFinal + coordenadas[3];
            movimientosController.add(mobBlancas + " " + mobNegras);
        }

        return ResponseEntity.ok(movimientosController);
    }
}