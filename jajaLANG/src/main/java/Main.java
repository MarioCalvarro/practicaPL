package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;

import main.java.ast.GeneradorCodigo;
import main.java.ast.Programa;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Uso: java Main <tarea> <rutaFichero>");
            return;
        }

        String tarea = args[0];
        String ruta = args[1];

        File file = new File(ruta);

        if (!file.exists()) {
            System.out.println("File not found: " + ruta);
            return;
        }

        var parentFolder = file.getParentFile();

        Reader input = new InputStreamReader(new FileInputStream(file));
        AnalizadorLexicoJaja lexer = new AnalizadorLexicoJaja(input);
        AnalizadorSintacticoJaja parser = new AnalizadorSintacticoJaja(lexer);
        Programa programa = (Programa) parser.parse().value;

        switch (tarea) {
            case "ast":
                System.out.println(programa);
                break;
            case "bind": 
                programa.bind();
                break;
            case "typecheck":
                programa.typecheck();
                break;
            case "compilar":
                var output = new GeneradorCodigo();
                programa.compilar();

                File archivoCompilado = new File(parentFolder, String.format("bin/%s", file.getName().replace(".jaja", ".wat")));
                archivoCompilado.getParentFile().mkdirs();

                try (FileWriter escritor = new FileWriter(archivoCompilado)) {
                    escritor.write(output.toString());
                } catch (Exception e) {
                    System.err.println(String.format("No se ha podido escribir el código compilado en el archivo %s", archivoCompilado.getName()));
                }
                break;
            default:
                System.out.println("Tarea no válida. Posibles: 'ast', 'bind', 'typecheck' ó 'compilar'");
                return;
        }
    }
}
