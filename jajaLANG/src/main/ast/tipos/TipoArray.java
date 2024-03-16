package main.ast.tipos;

import java.util.List;

public class TipoArray extends Tipo {
	private Tipo tipoElementos;
	private int tam;

    public TipoArray(Tipo base, int tam) {
        tipoElementos = base;
        this.tam = tam;
    }

	public TipoArray(Tipo base, List<Integer> lista_tam) {
        tipoElementos = base;
        //El último no lo recorremos porque es el base
        for (int i = 0; i < lista_tam.size() - 1; i++) {
            tipoElementos = new TipoArray(tipoElementos, lista_tam.get(i).intValue());
        }
        this.tam = lista_tam.get(lista_tam.size() - 1);
	}
}
