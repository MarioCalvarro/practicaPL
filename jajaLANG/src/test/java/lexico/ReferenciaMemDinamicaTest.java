package test.java.lexico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import main.java.lexico.*;
import main.java.sintactico.*;

class ReferenciaMemDinamicaTest {
	private static String result = "[clase:INCOGNITO,fila:1,col:1][clase:ID,fila:1,col:11,lexema:dato_t][clase:ASIGNACION,fila:1,col:18][clase:REGISTRO,fila:1,col:20][clase:LLAP,fila:1,col:29][clase:ID,fila:2,col:5,lexema:atr1][clase:DOSPUNTOS,fila:2,col:9][clase:ENT,fila:2,col:11][clase:PUNTERO,fila:2,col:15][clase:COMA,fila:2,col:16][clase:ID,fila:3,col:5,lexema:atr2][clase:DOSPUNTOS,fila:3,col:9][clase:VECTOR,fila:3,col:11][clase:PAP,fila:3,col:17][clase:ENT,fila:3,col:18][clase:PUNTERO,fila:3,col:22][clase:COMA,fila:3,col:23][clase:ENTERO,fila:3,col:25,lexema:2][clase:PCIERRE,fila:3,col:26][clase:COMA,fila:3,col:27][clase:LLCIERRE,fila:4,col:1][clase:PUNTOCOMA,fila:4,col:2][clase:DIVER,fila:6,col:1][clase:ID,fila:6,col:7,lexema:f][clase:PAP,fila:6,col:8][clase:DIRECCION,fila:6,col:9][clase:ID,fila:6,col:10,lexema:a][clase:DOSPUNTOS,fila:6,col:11][clase:ENT,fila:6,col:13][clase:PCIERRE,fila:6,col:16][clase:FLECHA,fila:6,col:18][clase:ENT,fila:6,col:21][clase:LLAP,fila:6,col:25][clase:DEVUELVE,fila:7,col:5][clase:PUNTERO,fila:7,col:14][clase:ID,fila:7,col:15,lexema:a][clase:PUNTOCOMA,fila:7,col:16][clase:LLCIERRE,fila:8,col:1][clase:DIVER,fila:10,col:1][clase:ID,fila:10,col:7,lexema:tronco][clase:PAP,fila:10,col:13][clase:PCIERRE,fila:10,col:14][clase:LLAP,fila:10,col:16][clase:ENT,fila:11,col:5][clase:PUNTERO,fila:11,col:9][clase:ID,fila:11,col:11,lexema:punt][clase:ASIGNACION,fila:11,col:16][clase:NUEVO,fila:11,col:18][clase:ENT,fila:11,col:24][clase:PUNTOCOMA,fila:11,col:27][clase:PUNTERO,fila:12,col:5][clase:ID,fila:12,col:6,lexema:punt][clase:ASIGNACION,fila:12,col:11][clase:ENTERO,fila:12,col:13,lexema:10][clase:PUNTOCOMA,fila:12,col:15][clase:ENT,fila:13,col:5][clase:ID,fila:13,col:9,lexema:b][clase:ASIGNACION,fila:13,col:11][clase:ID,fila:13,col:13,lexema:f][clase:PAP,fila:13,col:14][clase:ID,fila:13,col:15,lexema:punt][clase:PCIERRE,fila:13,col:19][clase:PUNTOCOMA,fila:13,col:20][clase:ID,fila:14,col:5,lexema:escribeEnt][clase:PAP,fila:14,col:15][clase:ID,fila:14,col:16,lexema:b][clase:PCIERRE,fila:14,col:17][clase:PUNTOCOMA,fila:14,col:18][clase:ID,fila:15,col:5,lexema:liberar][clase:PAP,fila:15,col:12][clase:ID,fila:15,col:13,lexema:punt][clase:PCIERRE,fila:15,col:17][clase:PUNTOCOMA,fila:15,col:18][clase:ID,fila:20,col:5,lexema:dato_t][clase:PUNTERO,fila:20,col:12][clase:ID,fila:20,col:14,lexema:d][clase:ASIGNACION,fila:20,col:16][clase:NUEVO,fila:20,col:18][clase:ID,fila:20,col:24,lexema:dato_t][clase:PUNTOCOMA,fila:20,col:30][clase:PUNTERO,fila:21,col:5][clase:ID,fila:21,col:6,lexema:d][clase:ASIGNACION,fila:21,col:8][clase:NUEVO,fila:21,col:10][clase:LLAP,fila:21,col:16][clase:ID,fila:22,col:9,lexema:atr1][clase:ASIGNACION,fila:22,col:14][clase:NUEVO,fila:22,col:16][clase:ENT,fila:22,col:22][clase:COMA,fila:22,col:25][clase:ID,fila:23,col:9,lexema:atr2][clase:ASIGNACION,fila:23,col:14][clase:NUEVO,fila:23,col:16][clase:CAP,fila:23,col:22][clase:NUEVO,fila:23,col:23][clase:ENT,fila:23,col:29][clase:COMA,fila:23,col:32][clase:NUEVO,fila:23,col:34][clase:ENT,fila:23,col:40][clase:CCIERRE,fila:23,col:43][clase:LLCIERRE,fila:24,col:5][clase:PUNTOCOMA,fila:24,col:6][clase:PUNTERO,fila:25,col:5][clase:PAP,fila:25,col:6][clase:PUNTERO,fila:25,col:7][clase:ID,fila:25,col:8,lexema:d][clase:PCIERRE,fila:25,col:9][clase:PUNTO,fila:25,col:10][clase:ID,fila:25,col:11,lexema:atr1][clase:ASIGNACION,fila:25,col:16][clase:ENTERO,fila:25,col:18,lexema:4][clase:PUNTOCOMA,fila:25,col:19][clase:PUNTERO,fila:26,col:5][clase:PAP,fila:26,col:6][clase:PUNTERO,fila:26,col:7][clase:ID,fila:26,col:8,lexema:d][clase:PCIERRE,fila:26,col:9][clase:PUNTO,fila:26,col:10][clase:ID,fila:26,col:11,lexema:atr2][clase:CAP,fila:26,col:15][clase:ENTERO,fila:26,col:16,lexema:0][clase:CCIERRE,fila:26,col:17][clase:ASIGNACION,fila:26,col:19][clase:ENTERO,fila:26,col:21,lexema:1][clase:PUNTOCOMA,fila:26,col:22][clase:PUNTERO,fila:27,col:5][clase:PAP,fila:27,col:6][clase:PUNTERO,fila:27,col:7][clase:ID,fila:27,col:8,lexema:d][clase:PCIERRE,fila:27,col:9][clase:PUNTO,fila:27,col:10][clase:ID,fila:27,col:11,lexema:atr2][clase:CAP,fila:27,col:15][clase:ENTERO,fila:27,col:16,lexema:1][clase:CCIERRE,fila:27,col:17][clase:ASIGNACION,fila:27,col:19][clase:ENTERO,fila:27,col:21,lexema:2][clase:PUNTOCOMA,fila:27,col:22][clase:LLCIERRE,fila:28,col:1][clase:EOF,fila:29,col:1]";

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
			test.append(unidad.toString());
		} while (unidad.clase() != ClaseLexica.EOF);
		assertEquals(test.toString(), result);
	}
}
