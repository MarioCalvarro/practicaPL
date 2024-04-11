package test.java.lexico;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.lexico.UnidadLexica;
import main.java.sintactico.ClaseLexica;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParImparTest {
    private static final String result = "[clase:DIVER,fila:1,col:1][clase:ID,fila:1,col:7,lexema:par][clase:PAP,fila:1,col:10][clase:ID,fila:1,col:11,lexema:x][clase:DOSPUNTOS,fila:1,col:12][clase:ENT,fila:1,col:14][clase:PCIERRE,fila:1,col:17][clase:FLECHA,fila:1,col:19][clase:BIN,fila:1,col:22][clase:LLAP,fila:1,col:26][clase:SI,fila:2,col:5][clase:ID,fila:2,col:8,lexema:x][clase:IGUAL,fila:2,col:10][clase:ENTERO,fila:2,col:13,lexema:0][clase:LLAP,fila:2,col:15][clase:DEVUELVE,fila:3,col:9][clase:FACTO,fila:3,col:18][clase:PUNTOCOMA,fila:3,col:23][clase:LLCIERRE,fila:4,col:5][clase:SINO,fila:5,col:5][clase:ID,fila:5,col:10,lexema:x][clase:IGUAL,fila:5,col:12][clase:ENTERO,fila:5,col:15,lexema:1][clase:LLAP,fila:5,col:17][clase:DEVUELVE,fila:6,col:9][clase:FAKE,fila:6,col:18][clase:PUNTOCOMA,fila:6,col:22][clase:LLCIERRE,fila:7,col:5][clase:SINO,fila:8,col:5][clase:LLAP,fila:8,col:10][clase:DEVUELVE,fila:9,col:9][clase:ID,fila:9,col:18,lexema:impar][clase:PAP,fila:9,col:23][clase:ID,fila:9,col:24,lexema:x][clase:RESTA,fila:9,col:25][clase:ENTERO,fila:9,col:26,lexema:1][clase:PCIERRE,fila:9,col:27][clase:PUNTOCOMA,fila:9,col:28][clase:LLCIERRE,fila:10,col:5][clase:LLCIERRE,fila:11,col:1][clase:DIVER,fila:13,col:1][clase:ID,fila:13,col:7,lexema:impar][clase:PAP,fila:13,col:12][clase:ID,fila:13,col:13,lexema:x][clase:DOSPUNTOS,fila:13,col:14][clase:ENT,fila:13,col:16][clase:PCIERRE,fila:13,col:19][clase:FLECHA,fila:13,col:21][clase:BIN,fila:13,col:24][clase:LLAP,fila:13,col:28][clase:SI,fila:14,col:5][clase:ID,fila:14,col:8,lexema:x][clase:IGUAL,fila:14,col:10][clase:ENTERO,fila:14,col:13,lexema:0][clase:LLAP,fila:14,col:15][clase:DEVUELVE,fila:15,col:9][clase:FAKE,fila:15,col:18][clase:PUNTOCOMA,fila:15,col:22][clase:LLCIERRE,fila:16,col:5][clase:SINO,fila:17,col:5][clase:ID,fila:17,col:10,lexema:x][clase:IGUAL,fila:17,col:12][clase:ENTERO,fila:17,col:15,lexema:1][clase:LLAP,fila:17,col:17][clase:DEVUELVE,fila:18,col:9][clase:FACTO,fila:18,col:18][clase:PUNTOCOMA,fila:18,col:23][clase:LLCIERRE,fila:19,col:5][clase:SINO,fila:20,col:5][clase:LLAP,fila:20,col:10][clase:DEVUELVE,fila:21,col:9][clase:ID,fila:21,col:18,lexema:par][clase:PAP,fila:21,col:21][clase:ID,fila:21,col:22,lexema:x][clase:RESTA,fila:21,col:23][clase:ENTERO,fila:21,col:24,lexema:1][clase:PCIERRE,fila:21,col:25][clase:PUNTOCOMA,fila:21,col:26][clase:LLCIERRE,fila:22,col:5][clase:LLCIERRE,fila:23,col:1][clase:DIVER,fila:25,col:1][clase:ID,fila:25,col:7,lexema:tronco][clase:PAP,fila:25,col:13][clase:PCIERRE,fila:25,col:14][clase:LLAP,fila:25,col:16][clase:ID,fila:26,col:5,lexema:escribirBin][clase:PAP,fila:26,col:16][clase:ID,fila:26,col:17,lexema:impar][clase:PAP,fila:26,col:22][clase:ENTERO,fila:26,col:23,lexema:9][clase:PCIERRE,fila:26,col:24][clase:PCIERRE,fila:26,col:25][clase:PUNTOCOMA,fila:26,col:26][clase:ID,fila:27,col:5,lexema:escribirBin][clase:PAP,fila:27,col:16][clase:ID,fila:27,col:17,lexema:par][clase:PAP,fila:27,col:20][clase:ENTERO,fila:27,col:21,lexema:12][clase:PCIERRE,fila:27,col:23][clase:PCIERRE,fila:27,col:24][clase:PUNTOCOMA,fila:27,col:25][clase:LLCIERRE,fila:28,col:1][clase:EOF,fila:29,col:1]";

    @Test
    void test() {
        StringBuilder test = new StringBuilder();
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/parImpar.jaja"));
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
