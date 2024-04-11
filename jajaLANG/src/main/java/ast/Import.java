package main.java.ast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;

public class Import extends Nodo {
	private String ruta;
	private String namespace;
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
                    System.err.println("Error al parsear la importación " + namespace);
                    System.exit(1);
                }
            }
        });
        thread.start();
        return thread;
    }

	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#traficar ").append(ruta).append(" como ").append(namespace).append(",\n").append(nuevoAST.toString());
		return sb.toString();
	}


}
