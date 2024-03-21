package main.ast.instrucciones;

import java.util.List;

import main.ast.expresiones.Expresion;

public class InsCond extends Instruccion {
	private Expresion condicion;
	private List<Instruccion> cuerpo;
	private InsCond instElse;
	private Boolean isfirst;

    //Sin else 
    public InsCond() {}

	// Constructor del else final
	public InsCond(List<Instruccion> cuerpo) {
		this.cuerpo = cuerpo;
	}

	public InsCond(Expresion condicion, List<Instruccion> cuerpo, InsCond instElse) {
		this.condicion = condicion;
		this.cuerpo = cuerpo;
		this.instElse = instElse;
	}
	
	public InsCond(Expresion condicion, List<Instruccion> cuerpo, InsCond instElse, Boolean isfirst) {
		this.condicion = condicion;
		this.cuerpo = cuerpo;
		this.instElse = instElse;
		this.isfirst=isfirst;
		
	}
	
    @Override
	public String toString() {

    	StringBuilder sb = new StringBuilder();
    	if(cuerpo != null) {
    		if(isfirst == true )
        		sb.append("si");
        	else
        		sb.append("sino");
        	
    		if(condicion != null)
    			sb.append(" ").append(condicion);
    		
    		sb.append(" {\n");
    		
    		for (Instruccion ins : cuerpo) {		
				sb.append('\t').append(ins).append('\n');			
    		}
    		
    		sb.append("}");
    	}
    	
		return sb.toString();	
		
	}
}
