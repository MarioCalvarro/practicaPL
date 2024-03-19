package main.ast.expresiones;

public class OperadorBin {

	public class OperBin extends Expresion {
	    public enum Operadores {
	        SUMA,
	        RESTA,
	        MUL,
	        DIV,
	        MOD,
	        IGUAL,
	        DESIGUAL,
	        MENOR,
	        MAYOR,
	        MANORIGUAL,
	        MAYORIGUAL,
	        DISY,
	        CONJ,
	        POT,
	        NEG,
	    	DIRECCION,
	    	PUNTERO;

	        public String toString() {
	            switch (this) {
	                case SUMA:
	                    return "+";
	                case RESTA:
	                    return "-";
	                case MUL:
	                    return "*";
	                case DIV:
	                    return "/";
	                case MOD:
	                    return "%";
	                case IGUAL:
	                    return "==";
	                case DESIGUAL:
	                    return "!=";
	                case MENOR:
	                    return "<";
	                case MAYOR:
	                    return ">";
	                case MANORIGUAL:
	                    return "<=";
	                case MAYORIGUAL:
	                    return ">=";
	                case CONJ:
	                    return "&&";
	                case DISY:
	                    return "||";
	                case POT:
	                    return "^";
	                case DIRECCION:
	                    return "&";	  
	                case PUNTERO:
	                    return "@";	  
	                default:
	                    throw new IllegalArgumentException("Invalid operator");
	            }
	        }
	    }
	    private final Operadores op;
	    private final Expresion izquierda;
	    private final Expresion derecha;

	    public OperBin(Operadores op, Expresion izquierda, Expresion derecha) {
	        this.op = op;
	        this.izquierda = izquierda;
	        this.derecha = derecha;
	    }

	    public Expresion getLeft() {
	        return izquierda;
	    }

	    public Expresion getRight() {
	        return derecha;
	    }

	    public Operadores getOp() {
	        return op;
	    }
	    
	    public String toString() {
	    	return izquierda + op.toString() + derecha;
	    }

}
