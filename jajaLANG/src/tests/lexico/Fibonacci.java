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

class Fibonacci {
	
	private static String result = "[clase:DIVER,fila:1,col:1]"
			+ "[clase:ID,fila:1,col:7,lexema:fibonacci]"
			+ "[clase:PAP,fila:1,col:16]"
			+ "[clase:ID,fila:1,col:17,lexema:r]"
			+ "[clase:DOSPUNTOS,fila:1,col:18]"
			+ "[clase:ENT,fila:1,col:20]"
			+ "[clase:PCIERRE,fila:1,col:23]"
			+ "[clase:FLECHA,fila:1,col:25]"
			+ "[clase:ENT,fila:1,col:28]"
			+ "[clase:LLAP,fila:1,col:32]"
			+ "[clase:SI,fila:2,col:2]"
			+ "[clase:ID,fila:2,col:5,lexema:c]"
			+ "[clase:IGUAL,fila:2,col:7]"
			+ "[clase:ENTERO,fila:2,col:10,lexema:0]"
			+ "[clase:LLAP,fila:2,col:12]"
			+ "[clase:DEVUELVE,fila:3,col:3]"
			+ "[clase:ENTERO,fila:3,col:12,lexema:1]"
			+ "[clase:PUNTOCOMA,fila:3,col:13]"
			+ "[clase:LLCIERRE,fila:4,col:2]"
			+ "[clase:SI,fila:5,col:2]"
			+ "[clase:ID,fila:5,col:5,lexema:c]"
			+ "[clase:IGUAL,fila:5,col:7]"
			+ "[clase:ENTERO,fila:5,col:10,lexema:1]"
			+ "[clase:LLAP,fila:5,col:12]"
			+ "[clase:DEVUELVE,fila:6,col:3]"
			+ "[clase:ENTERO,fila:6,col:12,lexema:1]"
			+ "[clase:PUNTOCOMA,fila:6,col:13]"
			+ "[clase:LLCIERRE,fila:7,col:2]"
			+ "[clase:DEVUELVE,fila:9,col:2]"
			+ "[clase:ID,fila:9,col:11,lexema:fibonacci]"
			+ "[clase:PAP,fila:9,col:20]"
			+ "[clase:ID,fila:9,col:21,lexema:c]"
			+ "[clase:RESTA,fila:9,col:22]"
			+ "[clase:ENTERO,fila:9,col:23,lexema:1]"
			+ "[clase:PCIERRE,fila:9,col:24]"
			+ "[clase:SUMA,fila:9,col:26]"
			+ "[clase:ID,fila:9,col:28,lexema:fibonacci]"
			+ "[clase:PAP,fila:9,col:37]"
			+ "[clase:ID,fila:9,col:38,lexema:c]"
			+ "[clase:RESTA,fila:9,col:39]"
			+ "[clase:ENTERO,fila:9,col:40,lexema:2]"
			+ "[clase:PCIERRE,fila:9,col:41]"
			+ "[clase:PUNTOCOMA,fila:9,col:42]"
			+ "[clase:LLCIERRE,fila:10,col:1]"
			+ "[clase:DIVER,fila:12,col:1]"
			+ "[clase:ID,fila:12,col:7,lexema:tronco]"
			+ "[clase:PAP,fila:12,col:13]"
			+ "[clase:PCIERRE,fila:12,col:14]"
			+ "[clase:LLAP,fila:12,col:16]"
			+ "[clase:PARA,fila:13,col:2]"
			+ "[clase:ID,fila:13,col:7,lexema:i]"
			+ "[clase:ASIGNACION,fila:13,col:9]"
			+ "[clase:ENTERO,fila:13,col:11,lexema:0]"
			+ "[clase:FLECHA,fila:13,col:13]"
			+ "[clase:ENTERO,fila:13,col:16,lexema:10]"
			+ "[clase:LLAP,fila:13,col:19]"
			+ "[clase:ID,fila:14,col:3,lexema:escribirEntero]"
			+ "[clase:PAP,fila:14,col:17]"
			+ "[clase:ID,fila:14,col:18,lexema:fibonacci]"
			+ "[clase:PAP,fila:14,col:27]"
			+ "[clase:ID,fila:14,col:28,lexema:i]"
			+ "[clase:PCIERRE,fila:14,col:29]"
			+ "[clase:PCIERRE,fila:14,col:30]"
			+ "[clase:PUNTOCOMA,fila:14,col:31]"
			+ "[clase:LLCIERRE,fila:15,col:2]"
			+ "[clase:LLCIERRE,fila:16,col:1]"
			+ "[clase:EOF,fila:17,col:1]";

	@Test
	void test() {
		StringBuilder test = new StringBuilder();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/tests/resources/fibonacci.jaja"));
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
