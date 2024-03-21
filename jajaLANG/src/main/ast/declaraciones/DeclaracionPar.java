package main.ast.declaraciones;

import main.ast.tipos.Tipo;

public class DeclaracionPar extends DeclaracionVar {
    private boolean porReferencia;

	public DeclaracionPar(String id, Tipo tipo, boolean porReferencia) {
        super(id, tipo);
        this.porReferencia = porReferencia;
    }

    @Override
	public String getId() {
        return id;
	}
    
    public String toString(){
    	if(porReferencia)
    		return id + ": " + tipo.toString() + 'n';
    	else
    		return "&" + id + ": " + tipo.toString() + '\n';
    }
}
