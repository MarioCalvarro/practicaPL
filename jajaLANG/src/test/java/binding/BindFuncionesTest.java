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

class BindFuncionesTest {
    private static final String result = "Contexto:\n"
    		+ "Ámbito{v1=ent v1, cond=bin cond = fake, v3=ent v3}"
    		+ "\n"
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
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/funcion.jaja"));
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
        DeclaracionFun fun1 = null;
        try {
            //Se supone que el único hijo es la declaración del main
            fun1 = (DeclaracionFun) pr.getAstHijos().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        DeclaracionFun fun2 = null;
        try {
            //Se supone que la segunda instrucción (no hay parámetros es el
            //bucle)
            fun2 = (DeclaracionFun) pr.getAstHijos().get(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //Programa en el constructor puede ser nulo porque no hay importaciones
        Contexto ctx1 = new Contexto(null, new Ambito());
        Contexto ctx2 = new Contexto(null, new Ambito());
        // main.bind(ctxMain); No podemos hacer esto porque así al final el
        // contexto sería nulo
        for (var hijo : fun1.getAstHijos()) {
            hijo.bind(ctx1);
        }
        for (var hijo : fun2.getAstHijos()) {
            hijo.bind(ctx2);
        }
        System.out.println(ctx1);
        System.out.println(ctx2);

        test1 = ctx1.toString() + ctx2.toString();
        assertEquals(test1, result);
    }
}
