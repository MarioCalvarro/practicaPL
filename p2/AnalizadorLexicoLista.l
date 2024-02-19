package alex;

import errors.GestionErroresTiny;

%%
%cup
%line
%column
%class AnalizadorLexicoLista
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digitoPositivo}{digito}*
parteDecimal = {digito}* {digitoPositivo}
separador = [ \t\r\b\n]
comentario = \/\/[^\n]* 
print = print
lfilter = lfilter
lmap = lmap
lreduce = lreduce
identificador = {letra}({letra}|{digito})*
numeroEntero = [\+\-]?{parteEntera}
numeroReal = [\+\-]?{parteEntera}\.{parteDecimal}
operadorAsignacion = \=
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = \/
operadorMenor = \<
operadorMayor = \>
operadorIgual = \=\=
operadorDesigual = \!\=
operadorConcatenacion = \#
corcheteApertura = \[
corcheteCierre = \]
parentesisApertura = \(
parentesisCierre = \)
coma = ,

%%
{separador}               {}
{comentario}              {}
{print}                   {return ops.unidadPrint();}
{lfilter}                 {return ops.unidadLfilter();}
{lmap}                    {return ops.unidadLmap();}
{lreduce}                 {return ops.unidadLreduce();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorAsignacion}      {return ops.unidadAsignacion();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{operadorMenor}           {return ops.unidadMenor();}
{operadorMayor}           {return ops.unidadMayor();}
{operadorIgual}           {return ops.unidadIgual();}
{operadorDesigual}        {return ops.unidadDesigual();}
{operadorConcatenacion}   {return ops.unidadConcatenacion();}
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCierre();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{coma}                    {return ops.unidadComa();}
[^]                       {errores.errorLexico(fila(),columna(),lexema());}  
