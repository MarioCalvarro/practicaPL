#!/bin/bash

#Analizador sintáctico
java -cp libraries/cup.jar java_cup.Main -parser AnalizadorSintacticoJaja -symbols ClaseLexica -nopositions src/main/java/sintactico/ASint.cup
mv AnalizadorSintacticoJaja.java src/main/java/sintactico/AnalizadorSintacticoJaja.java
mv ClaseLexica.java src/main/java/sintactico/ClaseLexica.java
