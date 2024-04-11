package main.java.lexico;

import main.java.ast.literales.Entero;
import main.java.ast.literales.False;
import main.java.ast.literales.Nulo;
import main.java.ast.literales.True;
import main.java.sintactico.ClaseLexica;

public class ALexOperaciones {
    private final AnalizadorLexicoJaja alex;

    public ALexOperaciones(AnalizadorLexicoJaja alex) {
        this.alex = alex;
    }

    // Palabras clave
    public UnidadLexica unidadEnt() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENT, "ENT");
    }

    public UnidadLexica unidadBin() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BIN, "BIN");
    }

    public UnidadLexica unidadFacto() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FACTO, "FACTO", True.instancia());
    }

    public UnidadLexica unidadFake() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FAKE, "FAKE", False.instancia());
    }

    public UnidadLexica unidadSi() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SI, "SI");
    }

    public UnidadLexica unidadSino() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SINO, "SINO");
    }

    public UnidadLexica unidadMientras() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MIENTRAS, "MIENTRAS");
    }

    public UnidadLexica unidadPara() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PARA, "PARA");
    }

    public UnidadLexica unidadDiver() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIVER, "DIVER");
    }

    public UnidadLexica unidadRegistro() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REGISTRO, "REGISTRO");
    }

    public UnidadLexica unidadVector() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VECTOR, "VECTOR");
    }

    public UnidadLexica unidadNulo() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULO, "NULO", Nulo.instancia());
    }

    public UnidadLexica unidadIncognito() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INCOGNITO, "INCOGNITO");
    }

    public UnidadLexica unidadDevuelve() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DEVUELVE, "DEVUELVE");
    }

    public UnidadLexica unidadNuevo() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUEVO, "NUEVO");
    }

    public UnidadLexica unidadTraficar() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRAFICAR, "TRAFICAR");
    }

    public UnidadLexica unidadComo() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMO, "COMO");
    }

    // Variables
    public UnidadLexica unidadRuta() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RUTA, alex.lexema(), "RUTA", alex.lexema());
    }

    public UnidadLexica unidadId() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN, alex.lexema(), "ID", alex.lexema());
    }

    public UnidadLexica unidadEntero() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENTERO, alex.lexema(), "ENTERO",
                new Entero(alex.lexema()));
    }

    // Operadores
    // Aritmeticos
    public UnidadLexica unidadPot() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POT, "POT");
    }

    public UnidadLexica unidadMul() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MUL, "MUL");
    }

    public UnidadLexica unidadDiv() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV, "DIV");
    }

    public UnidadLexica unidadMod() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MOD, "MOD");
    }

    public UnidadLexica unidadSuma() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SUMA, "SUMA");
    }

    public UnidadLexica unidadResta() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RESTA, "RESTA");
    }

    // Relacionales
    public UnidadLexica unidadIgual() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "IGUAL");
    }

    public UnidadLexica unidadDesigual() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DESIGUAL, "DESIGUAL");
    }

    public UnidadLexica unidadMayor() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR, "MAYOR");
    }

    public UnidadLexica unidadMenor() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "MENOR");
    }

    public UnidadLexica unidadMayorIgual() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL, "MAYORIGUAL");
    }

    public UnidadLexica unidadMenorIgual() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL, "MENORIGUAL");
    }

    // Logicos
    public UnidadLexica unidadNeg() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEG, "NEG");
    }

    public UnidadLexica unidadConj() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CONJ, "CONJ");
    }

    public UnidadLexica unidadDisy() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DISY, "DISY");
    }

    // Otros
    public UnidadLexica unidadDireccion() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIRECCION, "DIRECCION");
    }

    public UnidadLexica unidadPuntero() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTERO, "PUNTERO");
    }

    public UnidadLexica unidadAsignacion() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION, "ASIGNACION");
    }

    public UnidadLexica unidadPotAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POTASIG, "POTASIG");
    }

    public UnidadLexica unidadMulAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MULASIG, "MULASIG");
    }

    public UnidadLexica unidadDivAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIVASIG, "DIVASIG");
    }

    public UnidadLexica unidadModAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MODASIG, "MODASIG");
    }

    public UnidadLexica unidadSumaAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SUMAASIG, "SUMAASIG");
    }

    public UnidadLexica unidadRestaAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RESTAASIG, "RESTAASIG");
    }

    public UnidadLexica unidadConjAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CONJASIG, "CONJASIG");
    }

    public UnidadLexica unidadDisyAsig() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DISYASIG, "DISYASIG");
    }

    public UnidadLexica unidadPunto() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, "PUNTO");
    }

    public UnidadLexica unidadComa() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, "COMA");
    }

    public UnidadLexica unidadPuntoComa() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOCOMA, "PUNTOCOMA");
    }

    public UnidadLexica unidadCuatroPuntos() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CUATROPUNTOS, "DOSPUNTOS");
    }

    public UnidadLexica unidadDosPuntos() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOSPUNTOS, "DOSPUNTOS");
    }

    public UnidadLexica unidadFlecha() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FLECHA, "FLECHA");
    }

    // Agrupaciones
    public UnidadLexica unidadCAp() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CAP, "CAP");
    }

    public UnidadLexica unidadCCierre() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CCIERRE, "CCIERRE");
    }

    public UnidadLexica unidadPAp() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP, "PAP");
    }

    public UnidadLexica unidadPCierre() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE, "PCIERRE");
    }

    public UnidadLexica unidadLlAp() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAP, "LLAP");
    }

    public UnidadLexica unidadLlCierre() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLCIERRE, "LLCIERRE");
    }

    // EOF
    public UnidadLexica unidadEof() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "EOF");
    }

    // Error
    public void error() {
        System.err.println("***" + alex.fila() + ", " + alex.columna() + " Caracter inesperado: " + alex.lexema());
    }
}
