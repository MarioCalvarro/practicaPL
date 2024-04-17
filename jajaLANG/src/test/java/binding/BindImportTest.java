package test.java.binding;

import org.junit.jupiter.api.Test;

import main.java.ast.instrucciones.InsBucleWhile;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import main.java.ast.Ambito;
import main.java.ast.Contexto;
import main.java.ast.Programa;
import main.java.ast.declaraciones.DeclaracionFun;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BindImportTest {
    private static final String result = "";

    @Test
    void test() {
        // 1) Crear el AST
        // 2) Crear dos contextos. Uno con solo el while y otro con la
        // función. Con esto comprobamos que el total no tiene lo del while
        // 3) Imprimir los contextos
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/import.jaja"));
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
            //Se supone que el segundo hijo es la declaración del main
            main = (DeclaracionFun) pr.getAstHijos().get(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        Contexto ctxPr = new Contexto(pr, pr.getAmbitoGlobal());
        //Programa en el constructor puede ser nulo porque no hay importaciones
        Contexto ctxMain = new Contexto(pr, new Ambito());
        for (var hijo : pr.getAstHijos()) {
            hijo.bind(ctxPr); 
        }
        // for (var hijo : main.getAstHijos()) {
        //     hijo.bind(ctxMain);
        // }
        System.out.println(ctxPr);
        // System.out.println(ctxMain);

        String test1 = ctxPr.toString() + ctxMain.toString();
        assertEquals(test1, result);
    }
}
