package test.java.binding;

import org.junit.jupiter.api.Test;

import main.java.ast.instrucciones.InsBucleWhile;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import main.java.ast.Ambito;
import main.java.ast.Contexto;
import main.java.ast.Programa;
import main.java.ast.declaraciones.DeclaracionAlias;
import main.java.ast.declaraciones.DeclaracionFun;
import main.java.ast.declaraciones.DeclaracionVar;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BindAliasTest {
    private static final String result = "Contexto:\n"
    		+ "Ámbito{a=int a = 2, int=INCOGNITO int = ent}\n";

    @Test
    void test() {
        // 1) Crear el AST
        // 2) Crear un alias y despues usarlo para declarar otra variable.
        // 3) Imprimir el contexto
        Reader input = null;
        String test1 = "";
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/alias.jaja"));
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
        DeclaracionAlias alias = null;
        try {
            alias = (DeclaracionAlias) pr.getAstHijos().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        DeclaracionVar var = null;
        try {
            var = (DeclaracionVar) pr.getAstHijos().get(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        Contexto ctx = new Contexto(null, new Ambito());
        
        for (var hijo : pr.getAstHijos()) {
            hijo.bind(ctx);
        }
        
        System.out.println(ctx);
        test1 = ctx.toString();
        assertEquals(test1, result);
    }
}
