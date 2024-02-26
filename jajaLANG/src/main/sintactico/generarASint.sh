#!/bin/bash

#Analizador sintáctico
java -cp ../../../libraries/cup.jar java_cup.Main -parser AnalizadorSintacticoJaja -symbols ClaseLexica -nopositions ASint.cup
