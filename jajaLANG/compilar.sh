#!/bin/bash

# Check if user provided an argument
if [ -z "$1" ]; then
    echo "Please provide an argument."
    exit 1
fi

# Execute the script with the argument inserted
mvn clean compile exec:java -Dexec.args="compilar src/test/resources/$1"
