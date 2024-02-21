package tests.lexico;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import main.lexico.*;
import main.sintactico.*;

class Fibonacci {

	@Test
	void test() {
        Reader input = new InputStreamReader(new FileInputStream("../resources/fibonacci.jaja"));
        AnalizadorLexicoJaja al = new AnalizadorLexicoJaja(input);
        UnidadLexica unidad;
        do {
            unidad = al.yylex();
            System.out.println(unidad);
        }
        while (unidad.clase() != ClaseLexica.EOF);
	}
	
}

