#!/bin/bash

# Check if user provided an argument
if [ -z "$1" ]; then
    echo "Introduzca el nombre del fichero de la carpeta 'resources'."
    exit 1
fi

# Execute the script with the argument inserted
mvn clean compile exec:java -Dexec.args="compilar src/test/resources/$1.jaja"
wat2wasm $1.wat -o $1.wasm
