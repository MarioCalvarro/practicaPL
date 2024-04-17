package test.java.binding;

import org.junit.jupiter.api.Test;

import main.java.ast.instrucciones.InsBucleWhile;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import main.java.ast.Ambito;
import main.java.ast.Contexto;
import main.java.ast.Programa;
import main.java.ast.declaraciones.DeclaracionFun;
import main.java.ast.declaraciones.DeclaracionVar;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BindVariablesTest {
    private static final String result = "Contexto:\n"
    		+ "Ámbito{var2=ent var2 = (var1)+(2), var1=ent var1 = 1}\n";

    @Test
    void test() {
        // 1) Crear el AST
        // 2) Crear dos contextos. Uno con solo el while y otro con la
        // función. Con esto comprobamos que el total no tiene lo del while
        // 3) Imprimir los contextos
        Reader input = null;
        String test1 = "";
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/variables.jaja"));
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
        DeclaracionVar var1 = null;
        try {
            //Se supone que el único hijo es la declaración del main
            var1 = (DeclaracionVar) pr.getAstHijos().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        DeclaracionVar var2 = null;
        try {
            //Se supone que la segunda instrucción (no hay parámetros es el
            //bucle)
            var2 = (DeclaracionVar) pr.getAstHijos().get(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //Programa en el constructor puede ser nulo porque no hay importaciones
        Contexto ctx1 = new Contexto(null, new Ambito());
        // main.bind(ctxMain); No podemos hacer esto porque así al final el
        // contexto sería nulo
        for (var hijo : pr.getAstHijos()) {
            hijo.bind(ctx1);
        }
        System.out.println(ctx1);
        

        test1 = ctx1.toString();
        assertEquals(test1, result);
    }
}
