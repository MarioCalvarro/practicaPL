package alex;

%%
%char
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
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
separador = [ \t\r\b\n]
palabra = {letra}+
patron = {letra}*\*{letra}*
otro = .+

%%
{separador}               {}
{palabra}                 {return ops.unidadPalabra();}
{patron}                  {return ops.unidadPatron();}
{otro}                    {return ops.unidadOtro();}

[^]                       {ops.error();}  
