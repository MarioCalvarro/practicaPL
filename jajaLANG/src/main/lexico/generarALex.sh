#!/bin/bash

#Analizador léxico
java -cp ../../../libraries/jflex.jar jflex.Main ALexJaja.l
echo Creado AnalizadorLexicoJaja
rm AnalizadorLexicoJaja.java~
echo Eliminada la anterior versión
