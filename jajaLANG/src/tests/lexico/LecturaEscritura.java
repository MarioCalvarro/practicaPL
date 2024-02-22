package tests.lexico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LecturaEscritura {

	@Test
	void test() {
		StringBuilder test = new StringBuilder();
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/tests/resources/lecturaEscritura.jaja"));
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
