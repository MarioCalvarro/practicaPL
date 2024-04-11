package test.java.lexico;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.lexico.UnidadLexica;
import main.java.sintactico.ClaseLexica;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    private static final String result = "[clase:TRAFICAR,fila:2,col:1][clase:RUTA,fila:2,col:11,lexema:src/test/resources/parImpar.jaja][clase:COMO,fila:2,col:44][clase:ID,fila:2,col:49,lexema:r][clase:DIVER,fila:4,col:1][clase:ID,fila:4,col:7,lexema:fibonacci][clase:PAP,fila:4,col:16][clase:ID,fila:4,col:17,lexema:r][clase:DOSPUNTOS,fila:4,col:18][clase:ENT,fila:4,col:20][clase:PCIERRE,fila:4,col:23][clase:FLECHA,fila:4,col:25][clase:ENT,fila:4,col:28][clase:LLAP,fila:4,col:32][clase:SI,fila:5,col:5][clase:ID,fila:5,col:8,lexema:c][clase:IGUAL,fila:5,col:10][clase:ENTERO,fila:5,col:13,lexema:0][clase:LLAP,fila:5,col:15][clase:DEVUELVE,fila:6,col:9][clase:ENTERO,fila:6,col:18,lexema:1][clase:PUNTOCOMA,fila:6,col:19][clase:LLCIERRE,fila:7,col:5][clase:SI,fila:8,col:5][clase:ID,fila:8,col:8,lexema:c][clase:IGUAL,fila:8,col:10][clase:ENTERO,fila:8,col:13,lexema:1][clase:LLAP,fila:8,col:15][clase:DEVUELVE,fila:9,col:9][clase:ENTERO,fila:9,col:18,lexema:1][clase:PUNTOCOMA,fila:9,col:19][clase:LLCIERRE,fila:10,col:5][clase:DEVUELVE,fila:11,col:5][clase:ID,fila:11,col:14,lexema:fibonacci][clase:PAP,fila:11,col:23][clase:ID,fila:11,col:24,lexema:c][clase:RESTA,fila:11,col:25][clase:ENTERO,fila:11,col:26,lexema:1][clase:PCIERRE,fila:11,col:27][clase:SUMA,fila:11,col:29][clase:ID,fila:11,col:31,lexema:fibonacci][clase:PAP,fila:11,col:40][clase:ID,fila:11,col:41,lexema:c][clase:RESTA,fila:11,col:42][clase:ENTERO,fila:11,col:43,lexema:2][clase:PCIERRE,fila:11,col:44][clase:PUNTOCOMA,fila:11,col:45][clase:LLCIERRE,fila:12,col:1][clase:DIVER,fila:14,col:1][clase:ID,fila:14,col:7,lexema:tronco][clase:PAP,fila:14,col:13][clase:PCIERRE,fila:14,col:14][clase:LLAP,fila:14,col:16][clase:ID,fila:16,col:5,lexema:escribirBin][clase:PAP,fila:16,col:16][clase:ID,fila:16,col:17,lexema:r][clase:DOSPUNTOS,fila:16,col:18][clase:ID,fila:16,col:20,lexema:par][clase:PAP,fila:16,col:23][clase:ENTERO,fila:16,col:24,lexema:2][clase:PCIERRE,fila:16,col:25][clase:PCIERRE,fila:16,col:26][clase:PUNTOCOMA,fila:16,col:27][clase:PARA,fila:18,col:5][clase:ID,fila:18,col:10,lexema:i][clase:ASIGNACION,fila:18,col:12][clase:ENTERO,fila:18,col:14,lexema:0][clase:FLECHA,fila:18,col:16][clase:ENTERO,fila:18,col:19,lexema:10][clase:LLAP,fila:18,col:22][clase:ID,fila:19,col:9,lexema:escribirEntero][clase:PAP,fila:19,col:23][clase:ID,fila:19,col:24,lexema:fibonacci][clase:PAP,fila:19,col:33][clase:ID,fila:19,col:34,lexema:i][clase:PCIERRE,fila:19,col:35][clase:PCIERRE,fila:19,col:36][clase:PUNTOCOMA,fila:19,col:37][clase:LLCIERRE,fila:20,col:5][clase:LLCIERRE,fila:21,col:1][clase:EOF,fila:22,col:1]";

    @Test
    void test() {
        StringBuilder test = new StringBuilder();
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/fibonacci.jaja"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        UnidadLexica unidad = null;
        do {
            try {
                unidad = (UnidadLexica) al.next_token();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print(unidad.toString());
            test.append(unidad);
        } while (unidad.clase() != ClaseLexica.EOF);
        assertEquals(test.toString(), result);
    }
}
