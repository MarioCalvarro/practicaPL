# Check if two arguments are provided
if [ $# -ne 2 ]; then
    echo "Uso: $0 <fichero.wasm> <fichero_input.txt>"
    exit 1
fi

node main.js $1 < $2
