package main.ast.expresiones;

import main.ast.expresiones.OperadorBin.Operadores;

public class OperadorUn extends Expresion{
	
	public enum Operadores {
		NEG, DIRECCION, PUNTERO, MENOS;

		public String toString() {
			switch (this) {
			case NEG:
				return "+";
			case DIRECCION:
				return "&";
			case PUNTERO:
				return "@";
			case MENOS:
				return "-";
			default:
				throw new IllegalArgumentException("Invalid operator");
			}
		}
	}

	private  Operadores op;
	private  Expresion izquierda;
	private  Expresion derecha;

	public OperadorUn(Operadores op, Expresion izquierda, Expresion derecha) {
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
