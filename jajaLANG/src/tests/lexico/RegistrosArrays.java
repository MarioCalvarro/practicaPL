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

class RegistrosArrays {
	
	private static String result = "[clase:DIVER,fila:1,col:1][clase:ID,fila:1,col:7,lexema:f][clase:PAP,fila:1,col:8][clase:DIRECCION,fila:1,col:9][clase:ID,fila:1,col:10,lexema:a][clase:FLECHA,fila:1,col:12][clase:ENT,fila:1,col:15][clase:PCIERRE,fila:1,col:18][clase:FLECHA,fila:1,col:20][clase:ENT,fila:1,col:23][clase:LLAP,fila:1,col:27][clase:DEVUELVE,fila:2,col:5][clase:PUNTERO,fila:2,col:14][clase:ID,fila:2,col:15,lexema:a][clase:PUNTOCOMA,fila:2,col:16][clase:LLCIERRE,fila:3,col:1][clase:DIVER,fila:5,col:1][clase:ID,fila:5,col:7,lexema:tronco][clase:PAP,fila:5,col:13][clase:PCIERRE,fila:5,col:14][clase:LLAP,fila:5,col:16][clase:ENT,fila:6,col:5][clase:PUNTERO,fila:6,col:9][clase:ID,fila:6,col:11,lexema:punt][clase:ASIGNACION,fila:6,col:16][clase:ID,fila:6,col:18,lexema:reservar][clase:PAP,fila:6,col:26][clase:ID,fila:6,col:27,lexema:capacidad][clase:PAP,fila:6,col:36][clase:ENT,fila:6,col:37][clase:PCIERRE,fila:6,col:40][clase:PCIERRE,fila:6,col:41][clase:PUNTOCOMA,fila:6,col:42][clase:PUNTERO,fila:7,col:5][clase:ID,fila:7,col:6,lexema:punt][clase:ASIGNACION,fila:7,col:11][clase:ENTERO,fila:7,col:13,lexema:10][clase:PUNTOCOMA,fila:7,col:15][clase:ENT,fila:8,col:5][clase:ID,fila:8,col:9,lexema:b][clase:ASIGNACION,fila:8,col:11][clase:ID,fila:8,col:13,lexema:f][clase:PAP,fila:8,col:14][clase:ID,fila:8,col:15,lexema:punt][clase:PCIERRE,fila:8,col:19][clase:PUNTOCOMA,fila:8,col:20][clase:ID,fila:9,col:5,lexema:escribeEnt][clase:PAP,fila:9,col:15][clase:ID,fila:9,col:16,lexema:b][clase:PCIERRE,fila:9,col:17][clase:PUNTOCOMA,fila:9,col:18][clase:ID,fila:10,col:5,lexema:liberar][clase:PAP,fila:10,col:12][clase:ID,fila:10,col:13,lexema:punt][clase:PCIERRE,fila:10,col:17][clase:PUNTOCOMA,fila:10,col:18][clase:LLCIERRE,fila:11,col:1][clase:EOF,fila:12,col:1]";
	@Test
	void test() {
		StringBuilder test = new StringBuilder();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/tests/resources/registrosArrays.jaja"));
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
