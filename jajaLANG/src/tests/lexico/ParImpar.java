package tests.lexico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParImpar {

	@Test
	void test() {
		Reader input = null;
		try {
			input = new InputStreamReader(new FileInputStream("src/tests/resources/parImpar.jaja"));
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
			System.out.println(unidad);
		} while (unidad.clase() != ClaseLexica.EOF);
	}

}
