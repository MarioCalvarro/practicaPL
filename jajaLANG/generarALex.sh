#!/bin/bash

#Analizador léxico
java -cp libraries/jflex.jar jflex.Main src/main/lexico/ALexJaja.l
echo Creado AnalizadorLexicoJaja
rm src/main/lexico/AnalizadorLexicoJaja.java~
echo Eliminada la copia de seguridad
