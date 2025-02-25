package test.java.binding;

import main.java.ast.Ambito;
import main.java.ast.Contexto;
import main.java.ast.Programa;
import main.java.ast.declaraciones.DeclaracionVar;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BindVariablesTest {
    private static final String result = "Contexto:\n"
            + "Ámbito{var2=ent var2 = (var1)+(2), var1=ent var1 = 1}\n";

    @Test
    void test() {
        // 1) Crear el AST
        // 2) Crear dos variables. Uno que no dependa de otra y otro que dependa del primero.
        //Con esto comprobamos que el primero se ha añadido al ambito
        // 3) Imprimir el contexto
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
            var1 = (DeclaracionVar) pr.getAstHijos().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        DeclaracionVar var2 = null;
        try {

            var2 = (DeclaracionVar) pr.getAstHijos().get(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //Programa en el constructor puede ser nulo porque no hay importaciones
        Contexto ctx1 = new Contexto(null, new Ambito());

        for (var hijo : pr.getAstHijos()) {
            hijo.bind(ctx1);
        }
        System.out.println(ctx1);


        test1 = ctx1.toString();
        assertEquals(test1, result);
    }
}
