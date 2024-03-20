package main.ast.tipos;

import main.ast.literales.Entero;

public class TipoArray extends Tipo {
	private Tipo tipoElementos;
	private Entero tam;

	public TipoArray(Tipo base, Entero tam) {
		tipoElementos = base;
		this.tam = tam;
	}
	//
	// public TipoArray(Tipo base, List<Integer> lista_tam) {
	// 	tipoElementos = base;
	// 	// El último no lo recorremos porque es el base
	// 	for (int i = lista_tam.size() - 1; i > 0; i--) {
	// 		tipoElementos = new TipoArray(tipoElementos, lista_tam.get(i).intValue());
	// 	}
	// 	this.tam = lista_tam.get(0);
	// }
}
