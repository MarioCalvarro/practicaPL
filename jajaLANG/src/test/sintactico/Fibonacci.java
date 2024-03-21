package test.sintactico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.jupiter.api.Test;

import main.lexico.AnalizadorLexicoJaja;
import main.sintactico.AnalizadorSintacticoJaja;

class Fibonacci {
	private static String result = "[clase:DIVER,fila:1,col:1][clase:ID,fila:1,col:7,lexema:fibonacci][clase:PAP,fila:1,col:16][clase:ID,fila:1,col:17,lexema:r][clase:DOSPUNTOS,fila:1,col:18][clase:ENT,fila:1,col:20][clase:PCIERRE,fila:1,col:23][clase:FLECHA,fila:1,col:25][clase:ENT,fila:1,col:28][clase:LLAP,fila:1,col:32][clase:SI,fila:2,col:5][clase:ID,fila:2,col:8,lexema:c][clase:IGUAL,fila:2,col:10][clase:ENTERO,fila:2,col:13,lexema:0][clase:LLAP,fila:2,col:15][clase:DEVUELVE,fila:3,col:9][clase:ENTERO,fila:3,col:18,lexema:1][clase:PUNTOCOMA,fila:3,col:19][clase:LLCIERRE,fila:4,col:5][clase:SI,fila:5,col:5][clase:ID,fila:5,col:8,lexema:c][clase:IGUAL,fila:5,col:10][clase:ENTERO,fila:5,col:13,lexema:1][clase:LLAP,fila:5,col:15][clase:DEVUELVE,fila:6,col:9][clase:ENTERO,fila:6,col:18,lexema:1][clase:PUNTOCOMA,fila:6,col:19][clase:LLCIERRE,fila:7,col:5][clase:DEVUELVE,fila:8,col:5][clase:ID,fila:8,col:14,lexema:fibonacci][clase:PAP,fila:8,col:23][clase:ID,fila:8,col:24,lexema:c][clase:RESTA,fila:8,col:25][clase:ENTERO,fila:8,col:26,lexema:1][clase:PCIERRE,fila:8,col:27][clase:SUMA,fila:8,col:29][clase:ID,fila:8,col:31,lexema:fibonacci][clase:PAP,fila:8,col:40][clase:ID,fila:8,col:41,lexema:c][clase:RESTA,fila:8,col:42][clase:ENTERO,fila:8,col:43,lexema:2][clase:PCIERRE,fila:8,col:44][clase:PUNTOCOMA,fila:8,col:45][clase:LLCIERRE,fila:9,col:1][clase:DIVER,fila:11,col:1][clase:ID,fila:11,col:7,lexema:tronco][clase:PAP,fila:11,col:13][clase:PCIERRE,fila:11,col:14][clase:LLAP,fila:11,col:16][clase:PARA,fila:12,col:5][clase:ID,fila:12,col:10,lexema:i][clase:ASIGNACION,fila:12,col:12][clase:ENTERO,fila:12,col:14,lexema:0][clase:FLECHA,fila:12,col:16][clase:ENTERO,fila:12,col:19,lexema:10][clase:LLAP,fila:12,col:22][clase:ID,fila:13,col:9,lexema:escribirEntero][clase:PAP,fila:13,col:23][clase:ID,fila:13,col:24,lexema:fibonacci][clase:PAP,fila:13,col:33][clase:ID,fila:13,col:34,lexema:i][clase:PCIERRE,fila:13,col:35][clase:PCIERRE,fila:13,col:36][clase:PUNTOCOMA,fila:13,col:37][clase:LLCIERRE,fila:14,col:5][clase:LLCIERRE,fila:15,col:1][clase:EOF,fila:16,col:1]";

	@Test
	void test() {
		String test1 = new String();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/test/resources/fibonacci.jaja"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        AnalizadorSintacticoJaja as = new AnalizadorSintacticoJaja(al);
        try {
            test1 = as.parse().value.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(test1);
        assertEquals(test1, result);
	}
}
