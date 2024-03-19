pwd

java -cp ../../cup.jar java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions TinyErrors.cup

cd ..

javac -cp "../cup.jar:." */*.java
