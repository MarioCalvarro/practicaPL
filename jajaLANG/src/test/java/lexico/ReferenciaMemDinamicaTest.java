package test.java.lexico;

import main.java.lexico.AnalizadorLexicoJaja;
import main.java.lexico.UnidadLexica;
import main.java.sintactico.ClaseLexica;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReferenciaMemDinamicaTest {
    private static final String result = "[clase:INCOGNITO,fila:1,col:1][clase:ID,fila:1,col:11,lexema:dato_t][clase:ASIGNACION,fila:1,col:18][clase:REGISTRO,fila:1,col:20][clase:LLAP,fila:1,col:29][clase:ID,fila:2,col:5,lexema:atr1][clase:DOSPUNTOS,fila:2,col:9][clase:ENT,fila:2,col:11][clase:PUNTERO,fila:2,col:15][clase:COMA,fila:2,col:16][clase:ID,fila:3,col:5,lexema:atr2][clase:DOSPUNTOS,fila:3,col:9][clase:VECTOR,fila:3,col:11][clase:PAP,fila:3,col:17][clase:ENT,fila:3,col:18][clase:PUNTERO,fila:3,col:22][clase:COMA,fila:3,col:23][clase:ENTERO,fila:3,col:25,lexema:2][clase:PCIERRE,fila:3,col:26][clase:COMA,fila:3,col:27][clase:LLCIERRE,fila:4,col:1][clase:PUNTOCOMA,fila:4,col:2][clase:DIVER,fila:6,col:1][clase:ID,fila:6,col:7,lexema:f][clase:PAP,fila:6,col:8][clase:DIRECCION,fila:6,col:9][clase:ID,fila:6,col:10,lexema:a][clase:DOSPUNTOS,fila:6,col:11][clase:ENT,fila:6,col:13][clase:PCIERRE,fila:6,col:16][clase:FLECHA,fila:6,col:18][clase:ENT,fila:6,col:21][clase:LLAP,fila:6,col:25][clase:ID,fila:7,col:2,lexema:a][clase:SUMAASIG,fila:7,col:4][clase:ENTERO,fila:7,col:7,lexema:1][clase:PUNTOCOMA,fila:7,col:8][clase:DEVUELVE,fila:8,col:5][clase:ID,fila:8,col:14,lexema:a][clase:PUNTOCOMA,fila:8,col:15][clase:LLCIERRE,fila:9,col:1][clase:DIVER,fila:11,col:1][clase:ID,fila:11,col:7,lexema:tronco][clase:PAP,fila:11,col:13][clase:PCIERRE,fila:11,col:14][clase:LLAP,fila:11,col:16][clase:ENT,fila:12,col:5][clase:PUNTERO,fila:12,col:9][clase:ID,fila:12,col:11,lexema:punt][clase:ASIGNACION,fila:12,col:16][clase:NUEVO,fila:12,col:18][clase:ENT,fila:12,col:24][clase:PUNTOCOMA,fila:12,col:27][clase:PUNTERO,fila:13,col:5][clase:ID,fila:13,col:6,lexema:punt][clase:ASIGNACION,fila:13,col:11][clase:ENTERO,fila:13,col:13,lexema:10][clase:PUNTOCOMA,fila:13,col:15][clase:ENT,fila:14,col:5][clase:ID,fila:14,col:9,lexema:a][clase:ASIGNACION,fila:14,col:11][clase:PUNTERO,fila:14,col:13][clase:ID,fila:14,col:14,lexema:punt][clase:PUNTOCOMA,fila:14,col:18][clase:ENT,fila:15,col:5][clase:ID,fila:15,col:9,lexema:b][clase:ASIGNACION,fila:15,col:11][clase:ID,fila:15,col:13,lexema:f][clase:PAP,fila:15,col:14][clase:ID,fila:15,col:15,lexema:a][clase:PCIERRE,fila:15,col:16][clase:PUNTOCOMA,fila:15,col:17][clase:ID,fila:16,col:5,lexema:escribirEnt][clase:PAP,fila:16,col:16][clase:ID,fila:16,col:17,lexema:b][clase:PCIERRE,fila:16,col:18][clase:PUNTOCOMA,fila:16,col:19][clase:ID,fila:17,col:5,lexema:liberar][clase:PAP,fila:17,col:12][clase:ID,fila:17,col:13,lexema:punt][clase:PCIERRE,fila:17,col:17][clase:PUNTOCOMA,fila:17,col:18][clase:ID,fila:22,col:5,lexema:dato_t][clase:PUNTERO,fila:22,col:12][clase:ID,fila:22,col:14,lexema:d][clase:ASIGNACION,fila:22,col:16][clase:NUEVO,fila:22,col:18][clase:ID,fila:22,col:24,lexema:dato_t][clase:PUNTOCOMA,fila:22,col:30][clase:PUNTERO,fila:23,col:5][clase:ID,fila:23,col:6,lexema:d][clase:ASIGNACION,fila:23,col:8][clase:NUEVO,fila:23,col:10][clase:LLAP,fila:23,col:16][clase:ID,fila:24,col:9,lexema:atr1][clase:ASIGNACION,fila:24,col:14][clase:NUEVO,fila:24,col:16][clase:ENT,fila:24,col:22][clase:COMA,fila:24,col:25][clase:ID,fila:25,col:9,lexema:atr2][clase:ASIGNACION,fila:25,col:14][clase:NUEVO,fila:25,col:16][clase:CAP,fila:25,col:22][clase:NUEVO,fila:25,col:23][clase:ENT,fila:25,col:29][clase:COMA,fila:25,col:32][clase:NUEVO,fila:25,col:34][clase:ENT,fila:25,col:40][clase:CCIERRE,fila:25,col:43][clase:LLCIERRE,fila:26,col:5][clase:PUNTOCOMA,fila:26,col:6][clase:PUNTERO,fila:27,col:5][clase:PAP,fila:27,col:6][clase:PUNTERO,fila:27,col:7][clase:ID,fila:27,col:8,lexema:d][clase:PCIERRE,fila:27,col:9][clase:PUNTO,fila:27,col:10][clase:ID,fila:27,col:11,lexema:atr1][clase:ASIGNACION,fila:27,col:16][clase:ENTERO,fila:27,col:18,lexema:4][clase:PUNTOCOMA,fila:27,col:19][clase:PUNTERO,fila:28,col:5][clase:PAP,fila:28,col:6][clase:PUNTERO,fila:28,col:7][clase:ID,fila:28,col:8,lexema:d][clase:PCIERRE,fila:28,col:9][clase:PUNTO,fila:28,col:10][clase:ID,fila:28,col:11,lexema:atr2][clase:CAP,fila:28,col:15][clase:ENTERO,fila:28,col:16,lexema:0][clase:CCIERRE,fila:28,col:17][clase:ASIGNACION,fila:28,col:19][clase:ENTERO,fila:28,col:21,lexema:1][clase:PUNTOCOMA,fila:28,col:22][clase:PUNTERO,fila:29,col:5][clase:PAP,fila:29,col:6][clase:PUNTERO,fila:29,col:7][clase:ID,fila:29,col:8,lexema:d][clase:PCIERRE,fila:29,col:9][clase:PUNTO,fila:29,col:10][clase:ID,fila:29,col:11,lexema:atr2][clase:CAP,fila:29,col:15][clase:ENTERO,fila:29,col:16,lexema:1][clase:CCIERRE,fila:29,col:17][clase:ASIGNACION,fila:29,col:19][clase:ENTERO,fila:29,col:21,lexema:2][clase:PUNTOCOMA,fila:29,col:22][clase:LLCIERRE,fila:30,col:1][clase:EOF,fila:31,col:1]";

    @Test
    void test() {
        StringBuilder test = new StringBuilder();
        Reader input = null;
        try {
            input = new InputStreamReader(new FileInputStream("src/test/resources/referenciaDinamica.jaja"));
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
