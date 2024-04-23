package main.java.ast;

import java.util.Stack;

public class Delta {
    private final Stack<Integer> pilaDeltas = new Stack<Integer>();
    private int c = 0;
    private int max;

    public void sntrarEnBloque() {
        pilaDeltas.push(c);
    }

    public void salirDeBloque() {
        c = pilaDeltas.pop();
    }

    public int actualizarPosicionDelta(int typeSize) {
        var currOffset = c;
        incrementarOffset(typeSize);
        return currOffset;
    }

    private void incrementarOffset(int typeSize) {
        c += typeSize;
        max = Math.max(max, c);
    }

    public int getMax() {
        return max;
    }
}