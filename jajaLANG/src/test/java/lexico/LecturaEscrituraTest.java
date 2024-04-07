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

class LecturaEscrituraTest {
	private static String result = "[clase:ENT,fila:1,col:1][clase:ID,fila:1,col:5,lexema:num][clase:PUNTOCOMA,fila:1,col:8][clase:DIVER,fila:3,col:1][clase:ID,fila:3,col:7,lexema:tronco][clase:PAP,fila:3,col:13][clase:PCIERRE,fila:3,col:14][clase:LLAP,fila:3,col:16][clase:ID,fila:4,col:5,lexema:num][clase:ASIGNACION,fila:4,col:9][clase:ID,fila:4,col:11,lexema:leerEnt][clase:PAP,fila:4,col:18][clase:PCIERRE,fila:4,col:19][clase:PUNTOCOMA,fila:4,col:20][clase:BIN,fila:5,col:5][clase:ID,fila:5,col:9,lexema:cond][clase:ASIGNACION,fila:5,col:14][clase:ID,fila:5,col:16,lexema:leerBin][clase:PAP,fila:5,col:23][clase:PCIERRE,fila:5,col:24][clase:PUNTOCOMA,fila:5,col:25][clase:SI,fila:6,col:5][clase:ID,fila:6,col:8,lexema:cond][clase:LLAP,fila:6,col:13][clase:ID,fila:7,col:9,lexema:escribirEnt][clase:PAP,fila:7,col:20][clase:ID,fila:7,col:21,lexema:num][clase:PCIERRE,fila:7,col:24][clase:PUNTOCOMA,fila:7,col:25][clase:LLCIERRE,fila:8,col:5][clase:LLCIERRE,fila:9,col:1][clase:EOF,fila:10,col:1]";

	@Test
	void test() {
		StringBuilder test = new StringBuilder();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/test/resources/lecturaEscritura.jaja"));
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
