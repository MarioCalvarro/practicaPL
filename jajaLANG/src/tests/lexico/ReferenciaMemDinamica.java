package tests.lexico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import main.lexico.*;
import main.sintactico.*;

class ReferenciaMemDinamica {

	
	private static String result = "[clase:INCOGNITO,fila:1,col:1][clase:ID,fila:1,col:11,lexema:dato][clase:ASIGNACION,fila:1,col:16][clase:REGISTRO,fila:1,col:18][clase:LLAP,fila:1,col:27][clase:ID,fila:1,col:29,lexema:arr][clase:DOSPUNTOS,fila:1,col:32][clase:ENT,fila:1,col:34][clase:CAP,fila:1,col:37][clase:CCIERRE,fila:1,col:38][clase:LLCIERRE,fila:1,col:40][clase:PUNTOCOMA,fila:1,col:41][clase:DIVER,fila:3,col:1][clase:ID,fila:3,col:7,lexema:tronco][clase:PAP,fila:3,col:13][clase:PCIERRE,fila:3,col:14][clase:LLAP,fila:3,col:16][clase:ID,fila:4,col:5,lexema:dato][clase:ID,fila:4,col:10,lexema:x][clase:ASIGNACION,fila:4,col:12][clase:REGISTRO,fila:4,col:14][clase:LLAP,fila:4,col:23][clase:ID,fila:4,col:25,lexema:arr][clase:ASIGNACION,fila:4,col:29][clase:CAP,fila:4,col:31][clase:ENTERO,fila:4,col:32,lexema:1][clase:COMA,fila:4,col:33][clase:ENTERO,fila:4,col:35,lexema:2][clase:COMA,fila:4,col:36][clase:ENTERO,fila:4,col:38,lexema:3][clase:CCIERRE,fila:4,col:39][clase:LLCIERRE,fila:4,col:41][clase:PUNTOCOMA,fila:4,col:42][clase:ID,fila:5,col:5,lexema:escribeEnt][clase:PAP,fila:5,col:15][clase:ID,fila:5,col:16,lexema:x][clase:PUNTO,fila:5,col:17][clase:ID,fila:5,col:18,lexema:arr][clase:CAP,fila:5,col:21][clase:ENTERO,fila:5,col:22,lexema:0][clase:CCIERRE,fila:5,col:23][clase:PCIERRE,fila:5,col:24][clase:PUNTOCOMA,fila:5,col:25][clase:ID,fila:6,col:5,lexema:x][clase:PUNTO,fila:6,col:6][clase:ID,fila:6,col:7,lexema:arr][clase:CAP,fila:6,col:10][clase:ENTERO,fila:6,col:11,lexema:0][clase:CCIERRE,fila:6,col:12][clase:ASIGNACION,fila:6,col:14][clase:ENTERO,fila:6,col:16,lexema:9][clase:PUNTOCOMA,fila:6,col:17][clase:ID,fila:7,col:5,lexema:escribeEnt][clase:PAP,fila:7,col:15][clase:ID,fila:7,col:16,lexema:x][clase:PUNTO,fila:7,col:17][clase:ID,fila:7,col:18,lexema:arr][clase:CAP,fila:7,col:21][clase:ENTERO,fila:7,col:22,lexema:0][clase:CCIERRE,fila:7,col:23][clase:PCIERRE,fila:7,col:24][clase:PUNTOCOMA,fila:7,col:25][clase:LLCIERRE,fila:8,col:1][clase:EOF,fila:9,col:1]";
	@Test
	void test() {
		StringBuilder test = new StringBuilder();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/tests/resources/referenciaDinamica.jaja"));
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
			test.append(unidad.toString());
		} while (unidad.clase() != ClaseLexica.EOF);
		assertEquals(test.toString(), result);
	}

}
