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
	private static String result = "[clase:DIVER,fila:1,col:1][clase:ID,fila:1,col:7,lexema:tronco][clase:PAP,fila:1,col:13][clase:PCIERRE,fila:1,col:14][clase:LLAP,fila:1,col:16][clase:ENT,fila:2,col:5][clase:ID,fila:2,col:9,lexema:num][clase:ASIGNACION,fila:2,col:13][clase:ID,fila:2,col:15,lexema:leerEnt][clase:PAP,fila:2,col:22][clase:PCIERRE,fila:2,col:23][clase:PUNTOCOMA,fila:2,col:24][clase:BIN,fila:3,col:5][clase:ID,fila:3,col:9,lexema:cond][clase:ASIGNACION,fila:3,col:14][clase:ID,fila:3,col:16,lexema:leerBin][clase:PAP,fila:3,col:23][clase:PCIERRE,fila:3,col:24][clase:PUNTOCOMA,fila:3,col:25][clase:SI,fila:4,col:5][clase:ID,fila:4,col:8,lexema:cond][clase:LLAP,fila:4,col:13][clase:ID,fila:5,col:9,lexema:escribirEnt][clase:PAP,fila:5,col:20][clase:ID,fila:5,col:21,lexema:num][clase:PCIERRE,fila:5,col:24][clase:PUNTOCOMA,fila:5,col:25][clase:LLCIERRE,fila:6,col:5][clase:LLCIERRE,fila:7,col:1][clase:EOF,fila:8,col:1]";

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
