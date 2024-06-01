
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
    private static ArrayList<int[]>[] posiciones = new ArrayList[1];

    public List<String> movimientosContoller = new ArrayList<>();
    static String[] ey = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public static String letter(int x) {
        String val="";
        for (int i = 1; i <=8; i++) {
            if (i == x) {
                val = ey[i];
                break;
            }
        }
        return val;
    }

    public void extractMovements(int xInicial, int yInicial, int xFinal, int yFinal){
        posiciones[1].add(new int[] {xInicial, yInicial, xFinal,yFinal});
    }
//
//    public List<String> extractMovements(int xInicial, int yInicial, int xFinal, int yFinal) {
//        List<String>movimientos = new ArrayList<>();
//        char convertedChar;
//        switch (xInicial|xFinal) {
//            case 1:
//                convertedChar = 'a';
//                movimientos.add("a" + yInicial+" " + " a" + yFinal);
//                break;
//            case 2:
//                convertedChar = 'b';
//                movimientos.add("b" + yInicial+" " + " b" + yFinal);
//                break;
//            case 3:
//                convertedChar = 'c';
//                movimientos.add("c" + yInicial+" " + " c" + yFinal);
//                break;
//            case 4:
//                convertedChar = 'd';
//                movimientos.add("d" + yInicial+" " + " d" + yFinal);
//                break;
//            case 5:
//                convertedChar = 'e';
//                movimientos.add("e" + yInicial+" " + " e" + yFinal);
//                break;
//            case 6:
//                convertedChar = 'f';
//                movimientos.add("f" + yInicial+" " + " f" + yFinal);
//                break;
//            case 7:
//                convertedChar = 'g';
//                movimientos.add("g" + yInicial+" " + " g" + yFinal);
//
//                break;
//            case 8:
//                convertedChar = 'h';
//                movimientos.add("h" + yInicial+" " + " h" + yFinal);
//                break;
//            default:
//                convertedChar = '?'; // Valor por defecto para números fuera del rango
//                break;
//        }
//        return movimientos;
//    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        if (file.isEmpty()) {
            return ("archivo vacio");
        }

//         Invocar el compilador con el archivo como entrada

        try {
            BufferedReader fileCompilador = new BufferedReader(new InputStreamReader(file.getInputStream()));
            IDLexer lexer = new IDLexer(fileCompilador);
            Parser parser = new Parser(lexer);
            Symbol result = parser.parse();  // Aquí se realiza el análisis sintáctico
            // Aquí puedes manejar el resultado del análisis sintáctico
            // movimientos = extractMovements(result);  // Función para extraer movimientos del resultado
        } catch (Exception e) {
            System.out.println("Error al enviar el file al compilador");
            return "Error al enviar el file al compilador";
        }

//      pasa el txt sin analizar
        //StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//               //System.out.println("Line: " + line);
//                content.add(line);
//                //content.append(line).append("\n");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body(null);
//        }

        //System.out.println(content);
        for (int[] coordenadas : posiciones[1]) {
            String xInicial = "";
            String xFinal = "";

            xInicial = letter(coordenadas[0]);
            xFinal = letter(coordenadas[2]);

            movimientosContoller.add(xInicial+coordenadas[1] + " " + xFinal+coordenadas[3]);
        }
        return movimientosContoller.toString();
        //return "File content received:\n" + content.toString();

//        System.out.println(movimientos);
//        Mensaje mensaje = new Mensaje("Mensaje", movimientos);
//        return ResponseEntity.ok(movimientos);
    }

    //     Función ficticia para extraer movimientos del resultado del análisis sintáctico


}
