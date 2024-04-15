package main.java.ast.declaraciones;

import main.java.ast.Nodo;
import main.java.ast.Programa;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Import extends Declaracion {
    private final String ruta;
    private final String namespace;
    private Programa nuevoAST;

    public Import(String ruta, String namespace) {
        this.ruta = ruta;
        this.namespace = namespace;
    }

    public Thread iniciarParseo() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String workingDir = System.getProperty("user.dir");
                    System.out.println("Working Directory = " + workingDir);
                    Reader input = new InputStreamReader(new FileInputStream(ruta));
                    AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
                    AnalizadorSintacticoJaja as = new AnalizadorSintacticoJaja(al);
                    nuevoAST = (Programa) as.parse().value;
                } catch (Exception e) {
                    //TODO: Cambiar error
                    System.err.println("Error al parsear la importación " + namespace);
                    System.exit(1);
                }
            }
        });
        thread.start();
        return thread;
    }

    public String getNameSpace() {
        return namespace;
    }

    public Programa getAST() {
        return nuevoAST;
    }

    @Override
    public String toString() {
        return "#traficar " + ruta + " como " + namespace + ",\n" + nuevoAST.toString();
    }
}
