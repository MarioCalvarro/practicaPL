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

	@Test
	void test() {
	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
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
			System.out.println(unidad);
		} while (unidad.clase() != ClaseLexica.EOF);
	}

}
