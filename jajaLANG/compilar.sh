#!/bin/bash

# Check if user provided an argument
if [ -z "$1" ]; then
    echo "Indique el nombre del fichero a compilar."
    exit 1
fi

# Execute the script with the argument inserted
java -jar jajaLANG-1.0.jar compilar $1

fichero=$(basename "$1" .jaja)
wat2wasm $fichero.wat -o $fichero.wasm
