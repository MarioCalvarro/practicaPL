package test.java.binding;

import org.junit.jupiter.api.Test;

import main.java.ast.instrucciones.InsBucleFor;
import main.java.ast.instrucciones.InsBucleWhile;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import main.java.ast.Ambito;
import main.java.ast.Contexto;
import main.java.ast.Programa;
import main.java.ast.declaraciones.DeclaracionFun;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BindForTest {
    private static final String result = "Contexto:\n"
    		+ "Ámbito{v1=ent v1, cond=bin cond = fake, v3=ent v3}\n"
    		+ "Contexto:\n"
    		+ "Ámbito{vW1=ent vW1, v1=ent v1 = (2)+(2)}\n";

    @Test
    void test() {
        // 1) Crear el AST
        // 2) Crear dos contextos. Uno con solo el for y otro con la
        // función. Con esto comprobamos que el total no tiene lo del while
        // 3) Imprimir los contextos
        Reader input = null;
        String test1 = "";
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/for.jaja"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        AnalizadorSintacticoJaja as = new AnalizadorSintacticoJaja(al);
        Programa pr = null;
        try {
            pr = (Programa) as.parse().value;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        DeclaracionFun main = null;
        try {
            //Se supone que el único hijo es la declaración del main
            main = (DeclaracionFun) pr.getAstHijos().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        InsBucleFor bucle = null;
        try {
            //Se supone que la segunda instrucción (no hay parámetros es el
            //bucle)
            bucle = (InsBucleFor) main.getAstHijos().get(2);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //Programa en el constructor puede ser nulo porque no hay importaciones
        Contexto ctxMain = new Contexto(null, new Ambito());
        Contexto ctxBucle = new Contexto(null, new Ambito());
        // main.bind(ctxMain); No podemos hacer esto porque así al final el
        // contexto sería nulo
        for (var hijo : main.getAstHijos()) {
            hijo.bind(ctxMain);
        }
        for (var hijo : bucle.getAstHijos()) {
            hijo.bind(ctxBucle);
        }
        System.out.println(ctxMain);
        System.out.println(ctxBucle);

        test1 = ctxMain.toString() + ctxBucle.toString();
        assertEquals(test1, result);
    }
}
