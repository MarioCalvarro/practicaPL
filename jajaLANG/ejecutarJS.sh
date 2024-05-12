if [ -z "$1" ]; then
    echo "Introduzca el nombre del fichero 'wasm'."
    exit 1
fi

node main.js $1 < input.txt
