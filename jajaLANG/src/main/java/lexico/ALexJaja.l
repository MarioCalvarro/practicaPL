package main.java.lexico;

import main.java.errors.GestionErroresJaja;

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
traficar = #traficar
como = como
vector = vector
nuevo = nuevo

//Ruta de un fichero. Explicación:
//1. Tenemos varios directorios opcionales (palabras [\w ó '-'] separadas por '/')
//2. Tenemos una palabra final (fichero) que acaba en ".jaja"
ruta = (([\w\-]+\/)*([\w-]+))\.jaja

//Variables
letra  = ([A-Z]|[a-z]|\_)
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = 0|({digitoPositivo}{digito}*)

identificador = ({letra})({letra}|{digito})*
numeroEntero = {parteEntera}


//Operadores
//Asignación
operadorPotenciaAsig = \^\=
operadorMultiplicacionAsig = \*\=
operadorDivisionAsig = \/\=
operadorModuloAsig = \%\=
operadorSumaAsig = \+\=
operadorRestaAsig = \-\=
operadorConjAsig = \&\=
operadorDisyAsig = \|\=

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
cuatroPuntos = \:\:
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
{ruta}                    {return ops.unidadRuta();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEntero();}

//Operadores
//Asignacion
{operadorPotenciaAsig}        {return ops.unidadPotAsig();}
{operadorMultiplicacionAsig}  {return ops.unidadMulAsig();}
{operadorDivisionAsig}        {return ops.unidadDivAsig();}
{operadorModuloAsig}          {return ops.unidadModAsig();}
{operadorSumaAsig}            {return ops.unidadSumaAsig();}
{operadorRestaAsig}           {return ops.unidadRestaAsig();}
{operadorConjAsig}            {return ops.unidadConjAsig();}
{operadorDisyAsig}            {return ops.unidadDisyAsig();}

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
{cuatroPuntos}            {return ops.unidadCuatroPuntos();}
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
