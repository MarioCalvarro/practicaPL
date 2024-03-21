package main.ast.expresiones;

public class OperadorUn extends Expresion{
	public enum Operadores {
		NEG, DIRECCION, PUNTERO, MENOS;

		public String toString() {
			switch (this) {
			case NEG:
				return "+";
			case MENOS:
				return "-";
            case DIRECCION:
                return "&";
            case PUNTERO:
                return "@";
			default:
				throw new IllegalArgumentException("Invalid operator");
			}
		}
	}

	private  Operadores op;
	private  Expresion derecha;

	public OperadorUn(Operadores op, Expresion derecha) {
		this.op = op;
		this.derecha = derecha;
	}

	public Expresion getRight() {
		return derecha;
	}

	public Operadores getOp() {
		return op;
	}

    @Override
	public String toString() {
		return op.toString() + derecha;
	}
}
