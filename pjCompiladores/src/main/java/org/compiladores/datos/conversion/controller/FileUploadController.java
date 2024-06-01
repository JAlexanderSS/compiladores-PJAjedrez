package org.compiladores.datos.conversion.controller;
import java_cup.runtime.Symbol;
import org.compiladores.IDLexer;
import org.compiladores.Parser;
import org.compiladores.datos.conversion.Mensaje;
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

    @PostMapping("/upload")
    public ResponseEntity<List<String>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        List<String> movimientos = new ArrayList<>();

//        List<String> content = new ArrayList<>();

        // Invocar el compilador con el archivo como entrada
        try {
            BufferedReader fileCompilador = new BufferedReader(new InputStreamReader(file.getInputStream()));
            IDLexer lexer = new IDLexer(fileCompilador);
            Parser parser = new Parser(lexer);
            Symbol result = parser.parse();  // Aquí se realiza el análisis sintáctico
            // Aquí puedes manejar el resultado del análisis sintáctico
            movimientos = extractMovements(result);  // Función para extraer movimientos del resultado

        } catch (Exception e) {
            System.out.println("Error al enviar el file al compilador");
            return ResponseEntity.status(500).body(null);
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
      // return ResponseEntity.ok(content);
        //return "File content received:\n" + content.toString();

        System.out.println(movimientos);
        Mensaje mensaje = new Mensaje("Mensaje", movimientos);
        return ResponseEntity.ok((List<String>) mensaje);
    }

    // Función ficticia para extraer movimientos del resultado del análisis sintáctico
    private List<String> extractMovements(Symbol result) {
        List<String> movimientos = new ArrayList<>();
         movimientos.add("e2 e4");
         movimientos.add("g1 f3");
        return movimientos;
    }

}
