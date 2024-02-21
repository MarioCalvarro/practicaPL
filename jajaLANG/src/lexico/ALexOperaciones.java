package lexico;

import sintactico.ClaseLexica;

public class ALexOperaciones {
	private AnalizadorLexicoJaja alex;

	public ALexOperaciones(AnalizadorLexicoJaja alex) {
        this.alex = alex;   
    }

    //Palabras clave
    public UnidadLexica unidadEnt(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENT);
    }
    public UnidadLexica unidadBin(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BIN);
    }
    public UnidadLexica unidadFacto(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FACTO);
    }
    public UnidadLexica unidadFake(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FAKE);
    }
    public UnidadLexica unidadSi(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SI);
    }
    public UnidadLexica unidadSino(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SINO);
    }
    public UnidadLexica unidadMientras(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MIENTRAS);
    }
    public UnidadLexica unidadPara(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PARA);
    }
    public UnidadLexica unidadDiver(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIVER);
    }
    public UnidadLexica unidadRegistro(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REGISTRO);
    }
    public UnidadLexica unidadIncognito(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INCOGNITO);
    }
    public UnidadLexica unidadDevuelve(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DEVUELVE);
    }
    public UnidadLexica unidadTraficar(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRAFICAR);
    }


    //Variables
    public UnidadLexica unidadId(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID, alex.lexema());
    }
    public UnidadLexica unidadEntero(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENTERO, alex.lexema());
    }

    //Operadores
    //Aritmeticos
    public UnidadLexica unidadPot(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POT);
    }
    public UnidadLexica unidadMul(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MUL);
    }
    public UnidadLexica unidadDiv(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV);
    }
    public UnidadLexica unidadMod(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MOD);
    }
    public UnidadLexica unidadSuma(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SUMA);
    }
    public UnidadLexica unidadResta(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RESTA);
    }

    //Relacionales
    public UnidadLexica unidadIgual(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
    }
    public UnidadLexica unidadDesigual(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DESIGUAL);
    }
    public UnidadLexica unidadMayor(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
    }
    public UnidadLexica unidadMenor(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR);
    }
    public UnidadLexica unidadMayorIgual(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL);
    }
    public UnidadLexica unidadMenorIgual(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL);
    }

    //Logicos
    public UnidadLexica unidadNeg(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEG);
    }
    public UnidadLexica unidadConj(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CONJ);
    }
    public UnidadLexica unidadDisy(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DISY);
    }

    //Otros
    public UnidadLexica unidadDireccion(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIRECCION);
    }
    public UnidadLexica unidadPuntero(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTERO);
    }
    public UnidadLexica unidadAsignacion(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION);
    }
    public UnidadLexica unidadPunto(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
    }
    public UnidadLexica unidadComa(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA);
    }
    public UnidadLexica unidadPuntoComa(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOCOMA);
    }
    public UnidadLexica unidadDosPuntos(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOSPUNTOS);
    }
    public UnidadLexica unidadFlecha(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FLECHA);
    }


    //Agrupaciones
    public UnidadLexica unidadCAp(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CAP);
    }
    public UnidadLexica unidadCCierre(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CCIERRE);
    }
    public UnidadLexica unidadPAp(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP);
    }
    public UnidadLexica unidadPCierre(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE);
    } 
    public UnidadLexica unidadLlAp(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAP);
    }
    public UnidadLexica unidadLlCierre(){
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLCIERRE);
    }


    //EOF
    public UnidadLexica unidadEof() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF);
    }

    //Error
    public void error() {
        System.err.println("***" + alex.fila() + ", " + alex.columna() + " Caracter inesperado: " + alex.lexema());
    }
}
