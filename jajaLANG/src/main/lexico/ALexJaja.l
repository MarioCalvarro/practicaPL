package main.lexico;

import main.errors.GestionErroresJaja;

%%
%cup
%line
%column
%class AnalizadorLexicoJaja
%unicode
%public

%{
  private ALexOperaciones ops;
  private GestionErroresJaja errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresJaja errores) {
    this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperaciones(this);
%init}

//A eliminar
comentarioUni = \/\/[^\n]* 
comentarioMulti = \/\*[\s\S]*?\*\/ 
separador = [ \t\r\b\n]

//Palabras clave
ent = ent
bin = bin
facto = facto
fake = fake
si = si
sino = sino
mientras = mientras
para = para
diver = diver
registro = registro
nulo = nulo
incognito = incognito
devuelve = devuelve
traficar = \#traficar
como = como
vector = vector
nuevo = nuevo


//Variables
letra  = ([A-Z]|[a-z]|\_)
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = 0|({digitoPositivo}{digito}*)

identificador = ({letra})({letra}|{digito})*
numeroEntero = [\+]?{parteEntera}


//Operadores
//Aritmeticos
operadorPotencia = \^
operadorMultiplicacion = \*
operadorDivision = \/
operadorModulo = \%
operadorSuma = \+
operadorResta = \-

//Relacionales
operadorIgual = \=\=
operadorDesigual = \!\=
operadorMayor = \>
operadorMenor = \<
operadorMayorIgual = \>\=
operadorMenorIgual = \<\=

//Lógicos
operadorNegacion = \!
operadorConj = \&\&
operadorDisy = \|\|

//Otros operadores
operadorDireccion = \&
operadorPuntero = \@
operadorAsignacion = \=
punto = \.
coma = ,
puntoComa = \;
dosPuntos = \:
flecha = \-\>

//Agrupaciones
corcheteApertura = \[
corcheteCierre = \]
parentesisApertura = \(
parentesisCierre = \)
llaveApertura = \{
llaveCierre = \}

%%
//A eliminar
{separador}               {}
{comentarioUni}           {}
{comentarioMulti}         {}


//Palabras clave
{ent}                     {return ops.unidadEnt();}
{bin}                     {return ops.unidadBin();}
{facto}                   {return ops.unidadFacto();}
{fake}                    {return ops.unidadFake();}
{si}                      {return ops.unidadSi();}
{sino}                    {return ops.unidadSino();}
{mientras}                {return ops.unidadMientras();}
{para}                    {return ops.unidadPara();}
{diver}                   {return ops.unidadDiver();}
{registro}                {return ops.unidadRegistro();}
{nulo}                    {return ops.unidadNulo();}
{incognito}               {return ops.unidadIncognito();}
{devuelve}                {return ops.unidadDevuelve();}
{traficar}                {return ops.unidadTraficar();}
{como}                    {return ops.unidadComo();}
{vector}                  {return ops.unidadVector();}
{nuevo}                   {return ops.unidadNuevo();}


//Variables
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEntero();}

//Operadores
//Aritmeticos
{operadorPotencia}        {return ops.unidadPot();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{operadorModulo}          {return ops.unidadMod();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}

//Relacionales
{operadorIgual}           {return ops.unidadIgual();}
{operadorDesigual}        {return ops.unidadDesigual();}
{operadorMayor}           {return ops.unidadMayor();}
{operadorMenor}           {return ops.unidadMenor();}
{operadorMayorIgual}      {return ops.unidadMayorIgual();}
{operadorMenorIgual}      {return ops.unidadMenorIgual();}

//Logicos
{operadorNegacion}        {return ops.unidadNeg();}
{operadorConj}            {return ops.unidadConj();}
{operadorDisy}            {return ops.unidadDisy();}

//Otros
{operadorDireccion}       {return ops.unidadDireccion();}
{operadorPuntero}         {return ops.unidadPuntero();}
{operadorAsignacion}      {return ops.unidadAsignacion();}
{punto}                   {return ops.unidadPunto();}
{coma}                    {return ops.unidadComa();}
{puntoComa}               {return ops.unidadPuntoComa();}
{dosPuntos}               {return ops.unidadDosPuntos();}
{flecha}                  {return ops.unidadFlecha();}


//Agrupaciones
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCierre();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{llaveApertura}           {return ops.unidadLlAp();}
{llaveCierre}             {return ops.unidadLlCierre();}


//Errores
[^]                       {errores.errorLexico(fila(),columna(),lexema());}  
