const { readFileSync } = require("fs");
const readline = require('readline');

const insrc = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

var importObjects = {
    runtime: {
        exceptionHandler : function(code) {
            let errStr;
            if (code == 1) {
                errStr= "Error 1. ";
            } else if (code == 2) {
                errStr= "Error 2. ";
            } else if (code == 3) {
                errStr= "Not enough memory. ";
            } else {
                errStr= "Unknown error\n";
            }
            throw new Error(errStr);
        },
        print: function(n) {
            console.log(n);
        },
        scan: function() {
            return new Promise(resolve => {
                insrc.question("Introduzca un número: ", answer => {
                    const val = parseInt(answer);
                    resolve(val);
                });
            });
        }
    }
};

async function start(file) {
    const code = readFileSync(file);
    wasmModule = await WebAssembly.compile(code);
    instance = await WebAssembly.instantiate(wasmModule, importObjects);
}

async function run() {
    insrc.question('Introduzca el nombre del wasm: ', (inputString) => {
        let ficWasm = inputString
        start(ficWasm)
    });
}

run();

