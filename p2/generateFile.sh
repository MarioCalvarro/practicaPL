#!/bin/bash

#Analizador léxico
java -cp jflex.jar jflex.Main AnalizadorLexicoLista.l
mv AnalizadorLexicoLista.java AnalizadorSintacticoCUP/alex/AnalizadorLexicoLista.java
echo Movido AnalizadorLexicoLista a alex

#Analizador sintáctico
java -cp cup.jar java_cup.Main -parser AnalizadorSintacticoLista -symbols ClaseLexica -nopositions Lista.cup
mv AnalizadorSintacticoLista.java AnalizadorSintacticoCUP/asint/AnalizadorSintacticoLista.java
echo Movido AnalizadorSintacticoLista a asint
mv ClaseLexica.java AnalizadorSintacticoCUP/asint/ClaseLexica.java
echo Movida ClaseLexica a asint
