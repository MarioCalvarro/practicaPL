package alex;

import asint.ClaseLexica;

public class ALexOperations {
    private AnalizadorLexicoLista alex;
    public ALexOperations(AnalizadorLexicoLista alex) {
        this.alex = alex;   
    }
    public UnidadLexica unidadId() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDEN,
                alex.lexema()); 
    } 
    public UnidadLexica unidadPrint() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PRINT); 
    } 
    public UnidadLexica unidadLfilter() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LFILTER); 
    } 
    public UnidadLexica unidadLmap() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LMAP); 
    } 
    public UnidadLexica unidadLreduce() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LREDUCE); 
    } 
    public UnidadLexica unidadEnt() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENT,alex.lexema()); 
    } 
    public UnidadLexica unidadReal() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.REAL,alex.lexema()); 
    } 
    public UnidadLexica unidadSuma() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAS); 
    } 
    public UnidadLexica unidadResta() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOS); 
    } 
    public UnidadLexica unidadMul() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.POR); 
    } 
    public UnidadLexica unidadDiv() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV); 
    } 
    public UnidadLexica unidadMenor() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOR); 
    } 
    public UnidadLexica unidadMayor() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYOR); 
    } 
    public UnidadLexica unidadIgual() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL); 
    }
    public UnidadLexica unidadDesigual() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DESIGUAL); 
    } 
    public UnidadLexica unidadConcatenacion() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CONCATENACION); 
    } 
    public UnidadLexica unidadPAp() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PAP); 
    } 
    public UnidadLexica unidadPCierre() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCIERRE); 
    }
    public UnidadLexica unidadCAp() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CAP); 
    } 
    public UnidadLexica unidadCCierre() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CCIERRE); 
    }  
    public UnidadLexica unidadAsignacion() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ASIGNACION); 
    } 
    public UnidadLexica unidadComa() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA); 
    } 
    public UnidadLexica unidadEof() {
        return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF); 
    }
}
