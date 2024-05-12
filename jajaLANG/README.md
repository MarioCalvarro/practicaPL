# Compilación

Hemos incluido un script bash `compilar.sh` que permite ejecutar el compilador.
Toma como argumento el nombre de fichero fuente. Este script ejecuta
un jar que hemos precompilado con clase `main` la encontrada en el paquete
`main.java`. Es decir, si se prefiere compilar de nuevo simplemente hay que
ejecutar esa clase que toma como argumentos el tipo de operación a realizar (la
interesante es `compilar`) y el nombre del fichero fuente. Sin embargo, con
nuestro script también generamos el fichero *wasm*.

## Archivos de ejemplo

En la carpeta `src/test/resources` se pueden encontrar multitud de ejemplos de
programas. A su vez, en su subcarpeta `input` hay ficheros que se pueden
utilizar para redirigir la entrada a la hora de realizar los `read`.

# Ejecución

También hemos incluido un fichero `js` que permite la ejecución de los `wasm`
además de un script bash `ejecutarJs` que permite redirigir directamente la salida.
Este script toma como argumentos el nombre del fichero `wasm` y el fichero `txt`
de input.

## Alternativa

Como alternativa al fichero `js` indicado, existe la posibilidad de usar el
fichero `testear.html` (cortesía de nuestro compañero de un año superior,
Fernando [este fichero nos lo envió el propio compañero y no intervino en
ningún momento con nuestro trabajo en el compilador, simplemente sirve para
ejecutar de manera un poco más visual los programas]) que permite "subir"
(realmente no se sube a ninguna parte, todo es local) y ejecutar el programa
así como ver la memoria del `wasm`.
