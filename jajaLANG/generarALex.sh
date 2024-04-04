#!/bin/bash

#Analizador léxico
java -cp libraries/jflex.jar jflex.Main src/main/java/lexico/ALexJaja.l
echo Creado AnalizadorLexicoJaja
rm src/main/java/lexico/AnalizadorLexicoJaja.java~
echo Eliminada la copia de seguridad
