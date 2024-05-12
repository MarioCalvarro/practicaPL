const { readFileSync } = require("fs");
const readline = require('readline');
const args = process.argv.slice(2);

const insrc = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

entrada = [];
i = 0; 

async function readInput(n){
    var line;
    for await (line of insrc) {
        entrada.push(parseInt(line));
        n--;
        if (n==0) return;
    }
    insrc.close();
}

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
            let val = entrada[i];
            i += 1;
            return val;
        }
    }
};

async function start() {
    const code = readFileSync(args[0]);
    wasmModule = await WebAssembly.compile(code);
    instance = await WebAssembly.instantiate(wasmModule, importObjects);
}

async function run() {
    await readInput(500);
    start();
}

run();

