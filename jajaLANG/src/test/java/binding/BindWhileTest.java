package test.java.binding;

import org.junit.jupiter.api.Test;

import main.java.ast.instrucciones.InsBucleWhile;
import main.java.ast.instrucciones.Instruccion;
import main.java.ast.literales.False;
import main.java.lexico.AnalizadorLexicoJaja;
import main.java.sintactico.AnalizadorSintacticoJaja;
import main.java.ast.Ambito;
import main.java.ast.Contexto;
import main.java.ast.Programa;
import main.java.ast.declaraciones.DeclaracionFun;
import main.java.ast.expresiones.Expresion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BindWhileTest {
    @Test
    void test() {
        // 1) Crear el AST
        // 2) Crear dos contextos. Uno con solo el while y otro con la
        // función. Con esto comprobamos que el total no tiene lo del while
        // 3) Imprimir los contextos
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/bindingTypeChecking/while.jaja"));
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
        InsBucleWhile bucle = null;
        try {
            //Se supone que la segunda instrucción (no hay parámetros es el
            //bucle)
            bucle = (InsBucleWhile) main.getAstHijos().get(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //Programa en el constructor puede ser nulo porque no hay importaciones
        Contexto ctxMain = new Contexto(null, new Ambito());
        Contexto ctxBucle = new Contexto(null, new Ambito());
        main.bind(ctxMain);
        bucle.bind(ctxBucle);
    }
}
