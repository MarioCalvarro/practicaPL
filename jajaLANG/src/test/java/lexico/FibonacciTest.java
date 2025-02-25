package test.java.lexico;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.lexico.UnidadLexica;
import main.java.sintactico.ClaseLexica;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    private static final String result = "[clase:TRAFICAR,fila:1,col:1][clase:RUTA,fila:1,col:11,lexema:src/test/resources/otro.jaja][clase:COMO,fila:1,col:40][clase:ID,fila:1,col:45,lexema:r][clase:DIVER,fila:3,col:1][clase:ID,fila:3,col:7,lexema:fibonacci][clase:PAP,fila:3,col:16][clase:ID,fila:3,col:17,lexema:c][clase:DOSPUNTOS,fila:3,col:18][clase:ENT,fila:3,col:20][clase:PCIERRE,fila:3,col:23][clase:FLECHA,fila:3,col:25][clase:ENT,fila:3,col:28][clase:LLAP,fila:3,col:32][clase:SI,fila:4,col:5][clase:ID,fila:4,col:8,lexema:c][clase:IGUAL,fila:4,col:10][clase:ENTERO,fila:4,col:13,lexema:0][clase:LLAP,fila:4,col:15][clase:DEVUELVE,fila:5,col:9][clase:ENTERO,fila:5,col:18,lexema:1][clase:PUNTOCOMA,fila:5,col:19][clase:LLCIERRE,fila:6,col:5][clase:SI,fila:7,col:5][clase:ID,fila:7,col:8,lexema:c][clase:IGUAL,fila:7,col:10][clase:ENTERO,fila:7,col:13,lexema:1][clase:LLAP,fila:7,col:15][clase:DEVUELVE,fila:8,col:9][clase:ENTERO,fila:8,col:18,lexema:1][clase:PUNTOCOMA,fila:8,col:19][clase:LLCIERRE,fila:9,col:5][clase:DEVUELVE,fila:10,col:5][clase:ID,fila:10,col:14,lexema:fibonacci][clase:PAP,fila:10,col:23][clase:ID,fila:10,col:24,lexema:c][clase:RESTA,fila:10,col:25][clase:ENTERO,fila:10,col:26,lexema:1][clase:PCIERRE,fila:10,col:27][clase:SUMA,fila:10,col:29][clase:ID,fila:10,col:31,lexema:fibonacci][clase:PAP,fila:10,col:40][clase:ID,fila:10,col:41,lexema:c][clase:RESTA,fila:10,col:42][clase:ENTERO,fila:10,col:43,lexema:2][clase:PCIERRE,fila:10,col:44][clase:PUNTOCOMA,fila:10,col:45][clase:LLCIERRE,fila:11,col:1][clase:DIVER,fila:13,col:1][clase:ID,fila:13,col:7,lexema:tronco][clase:PAP,fila:13,col:13][clase:PCIERRE,fila:13,col:14][clase:LLAP,fila:13,col:16][clase:ID,fila:15,col:5,lexema:escribirEnt][clase:PAP,fila:15,col:16][clase:ID,fila:15,col:17,lexema:r][clase:DOSPUNTOS,fila:15,col:18][clase:ID,fila:15,col:20,lexema:fun][clase:PAP,fila:15,col:23][clase:ENTERO,fila:15,col:24,lexema:2][clase:PCIERRE,fila:15,col:25][clase:PCIERRE,fila:15,col:26][clase:PUNTOCOMA,fila:15,col:27][clase:PARA,fila:17,col:5][clase:ID,fila:17,col:10,lexema:i][clase:ASIGNACION,fila:17,col:12][clase:ENTERO,fila:17,col:14,lexema:0][clase:FLECHA,fila:17,col:16][clase:ENTERO,fila:17,col:19,lexema:10][clase:LLAP,fila:17,col:22][clase:ID,fila:18,col:9,lexema:escribirEnt][clase:PAP,fila:18,col:20][clase:ID,fila:18,col:21,lexema:fibonacci][clase:PAP,fila:18,col:30][clase:ID,fila:18,col:31,lexema:i][clase:PCIERRE,fila:18,col:32][clase:PCIERRE,fila:18,col:33][clase:PUNTOCOMA,fila:18,col:34][clase:LLCIERRE,fila:19,col:5][clase:LLCIERRE,fila:20,col:1][clase:EOF,fila:21,col:1]";

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
