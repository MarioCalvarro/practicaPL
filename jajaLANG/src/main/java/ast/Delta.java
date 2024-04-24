package main.java.ast;

import java.util.Stack;

public class Delta {
    private final Stack<Integer> pilaDeltas = new Stack<Integer>();
    private int c = 0;
    private int max;

    public void entrarEnBloque() {
        pilaDeltas.push(c);
    }

    public void salirDeBloque() {
        c = pilaDeltas.pop();
    }

    public int actualizarPosicionDelta(int tam) {
        var currOffset = c;
        incrementarOffset(tam);
        return currOffset;
    }

    private void incrementarOffset(int tam) {
        c += tam;
        max = Math.max(max, c);
    }

    public int getMax() {
        return max;
    }
}