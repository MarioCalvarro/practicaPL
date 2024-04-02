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

class RegistrosArraysTest {
	private static String result = "[clase:INCOGNITO,fila:1,col:1][clase:ID,fila:1,col:11,lexema:dato][clase:ASIGNACION,fila:1,col:16][clase:REGISTRO,fila:1,col:18][clase:LLAP,fila:1,col:27][clase:ID,fila:1,col:29,lexema:arr][clase:DOSPUNTOS,fila:1,col:32][clase:VECTOR,fila:1,col:34][clase:PAP,fila:1,col:40][clase:ENT,fila:1,col:41][clase:COMA,fila:1,col:44][clase:ENTERO,fila:1,col:46,lexema:3][clase:PCIERRE,fila:1,col:47][clase:COMA,fila:1,col:48][clase:LLCIERRE,fila:1,col:50][clase:PUNTOCOMA,fila:1,col:51][clase:DIVER,fila:3,col:1][clase:ID,fila:3,col:7,lexema:tronco][clase:PAP,fila:3,col:13][clase:PCIERRE,fila:3,col:14][clase:LLAP,fila:3,col:16][clase:ID,fila:4,col:5,lexema:dato][clase:ID,fila:4,col:10,lexema:x][clase:ASIGNACION,fila:4,col:12][clase:NUEVO,fila:4,col:14][clase:LLAP,fila:4,col:20][clase:ID,fila:4,col:22,lexema:arr][clase:ASIGNACION,fila:4,col:26][clase:NUEVO,fila:4,col:28][clase:CAP,fila:4,col:34][clase:ENTERO,fila:4,col:35,lexema:1][clase:COMA,fila:4,col:36][clase:ENTERO,fila:4,col:38,lexema:2][clase:COMA,fila:4,col:39][clase:ENTERO,fila:4,col:41,lexema:3][clase:CCIERRE,fila:4,col:42][clase:LLCIERRE,fila:4,col:44][clase:PUNTOCOMA,fila:4,col:45][clase:ID,fila:5,col:5,lexema:escribeEnt][clase:PAP,fila:5,col:15][clase:ID,fila:5,col:16,lexema:x][clase:PUNTO,fila:5,col:17][clase:ID,fila:5,col:18,lexema:arr][clase:CAP,fila:5,col:21][clase:ENTERO,fila:5,col:22,lexema:0][clase:CCIERRE,fila:5,col:23][clase:PCIERRE,fila:5,col:24][clase:PUNTOCOMA,fila:5,col:25][clase:ID,fila:6,col:5,lexema:x][clase:PUNTO,fila:6,col:6][clase:ID,fila:6,col:7,lexema:arr][clase:CAP,fila:6,col:10][clase:ENTERO,fila:6,col:11,lexema:0][clase:CCIERRE,fila:6,col:12][clase:ASIGNACION,fila:6,col:14][clase:ENTERO,fila:6,col:16,lexema:9][clase:PUNTOCOMA,fila:6,col:17][clase:ID,fila:7,col:5,lexema:escribeEnt][clase:PAP,fila:7,col:15][clase:ID,fila:7,col:16,lexema:x][clase:PUNTO,fila:7,col:17][clase:ID,fila:7,col:18,lexema:arr][clase:CAP,fila:7,col:21][clase:ENTERO,fila:7,col:22,lexema:0][clase:CCIERRE,fila:7,col:23][clase:PCIERRE,fila:7,col:24][clase:PUNTOCOMA,fila:7,col:25][clase:ENT,fila:10,col:5][clase:ID,fila:10,col:9,lexema:x][clase:ASIGNACION,fila:10,col:11][clase:ENTERO,fila:10,col:13,lexema:3][clase:PUNTOCOMA,fila:10,col:14][clase:ID,fila:11,col:5,lexema:x][clase:SUMAASIG,fila:11,col:7][clase:ENTERO,fila:11,col:10,lexema:2][clase:PUNTOCOMA,fila:11,col:11][clase:ID,fila:12,col:5,lexema:x][clase:RESTAASIG,fila:12,col:7][clase:ENTERO,fila:12,col:10,lexema:2][clase:PUNTOCOMA,fila:12,col:11][clase:ID,fila:14,col:5,lexema:bool][clase:ID,fila:14,col:10,lexema:a][clase:ASIGNACION,fila:14,col:12][clase:FACTO,fila:14,col:14][clase:PUNTOCOMA,fila:14,col:19][clase:ID,fila:15,col:5,lexema:a][clase:DISYASIG,fila:15,col:7][clase:FAKE,fila:15,col:10][clase:PUNTOCOMA,fila:15,col:14][clase:ID,fila:16,col:5,lexema:a][clase:CONJASIG,fila:16,col:7][clase:FACTO,fila:16,col:10][clase:PUNTOCOMA,fila:16,col:15][clase:LLCIERRE,fila:17,col:1][clase:EOF,fila:18,col:1]";

	@Test
	void test() {
		StringBuilder test = new StringBuilder();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/test/resources/registrosArrays.jaja"));
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
