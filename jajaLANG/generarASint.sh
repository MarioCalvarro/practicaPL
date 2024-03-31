#!/bin/bash

#Analizador sintáctico
java -cp libraries/cup.jar java_cup.Main -parser AnalizadorSintacticoJaja -symbols ClaseLexica -nopositions src/main/sintactico/ASint.cup
mv AnalizadorSintacticoJaja.java src/main/sintactico/AnalizadorSintacticoJaja.java
mv ClaseLexica.java src/main/sintactico/ClaseLexica.java
