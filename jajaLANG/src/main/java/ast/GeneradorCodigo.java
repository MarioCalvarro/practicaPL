package main.java.ast;

import main.java.ast.declaraciones.DeclaracionVar;

public class GeneradorCodigo {
    public final static String SIG_FUNC = "_sig_void";
    public final static String INICIO_LOCAL = "localsStart";
    public final static int INICIO_GLOBAL = 4;
    public final static String RESERVAR_PILA = "reserveStack";
    public final static String LIBERAR_PILA = "freeStack";
    public final static String RESERVAR_HEAP = "reserveHeap";
    public final static String ZEROS = "fillZero";
    public final static String COPIAR = "copyn";
    public final static String CAMBIAR = "swap";
    public final static String MP = "MP";
    public final static String SP = "SP";
    public final static String NP = "NP";

    private final static StringBuilder sb = new StringBuilder();
    private final static int TAM_INDENTACION = 2;
    private final static int tamMemoria = 2000;
    private static int nivel_indentacion = 0;

    public static void comentario(String comentario) {
        String[] lines = comentario.split("\n");
        sb.append("\n");
        for (String line : lines) {
            escribir(";;" + line + "\n");
        }
    }

    private static void trasReservarPila() {
        escribir("""
                ;;;;;;;;INICIO POST RESERVA;;;;;;;;
                ;;Guardamos el valor anterior de MP
                local.set $temp

                ;;Guardamos en la posición de MP el antiguo MP
                global.get $MP
                local.get $temp
                i32.store       ;;MEM[MP] = MP_antiguo

                ;;localStart = MP + 4
                global.get $MP
                i32.const 4
                i32.add
                """);
        local_set(INICIO_LOCAL);
        comentario(";;;;;;;FIN POST RESERVA;;;;;;;;");
    }

    public static void reservarPila() {
        llamar(RESERVAR_PILA);
        trasReservarPila();
    }

    public static void reservarHeap() {
        llamar(RESERVAR_HEAP);
    }

    public static void llamar(String func) {
        escribir("call $" + func);
    }

    public static void escribir(String instruccion) {
        sb.append(instruccion.indent(nivel_indentacion));
    }

    //Pila de operaciones
    public static void drop() {
        escribir("drop");
    }

    public static void duplicate() {
        local_tee("temp");
        local_get("temp");
    }

    public static void local_tee(String name) {
        escribir(String.format("local.tee $%s", name));
    }

    public static void local_get(String name) {
        escribir(String.format("local.get $%s", name));
    }

    /* i32 MEMORY OPERATIONS */
    public static void i32_load() {
        escribir("i32.load");
    }

    public static void i32_load(int offset) {
        escribir("i32.load offset=" + offset);
    }

    public static void i32_store() {
        escribir("i32.store");
    }

    public static void i32_sub() {
        escribir("i32.sub");
    }

    public static void i32_mul() {
        escribir("i32.mul");
    }

    public static void i32_div_s() {
        escribir("i32.div_s");
    }

    public static void i32_rem_s() {
        escribir("i32.rem_s");
    }

    public static void i32_eq() {
        escribir("i32.eq");
    }

    public static void i32_ne() {
        escribir("i32.ne");
    }

    public static void i32_eqz() {
        escribir("i32.eqz");
    }

    public static void i32_le_s() {
        escribir("i32.le_s");
    }

    public static void i32_lt_s() {
        escribir("i32.lt_s");
    }

    public static void i32_ge_s() {
        escribir("i32.ge_s");
    }

    public static void i32_gt_s() {
        escribir("i32.gt_s");
    }

    public static void i32_and() {
        escribir("i32.and");
    }

    public static void i32_or() {
        escribir("i32.or");
    }

    public static void i32_xor() {
        escribir("i32.xor");
    }

    public static void mem_location(DeclaracionVar var) {
        if (var.esGlobal()) {
            comentario("Variable global con delta: " + var.getPosicionDelta());
            i32_const(INICIO_GLOBAL + var.getPosicionDelta());
        } else {
            comentario("Variable con delta: " + var.getPosicionDelta());
            mem_local(var.getPosicionDelta());
        }
    }

    /* i32 OPERATIONS */
    public static void i32_const(int i) {
        escribir("i32.const " + i);
    }

    /* MEMORY LOCALS */
    public static void mem_local(int offset) {
        local_get(INICIO_LOCAL);
        i32_const(offset);
        i32_add();
    }

    public static void i32_add() {
        escribir("i32.add");
    }

    /* LOCALS */
    public static void local(String name, String type) {
        escribir(String.format("(local %s %s)", name, type));
    }

    public static void local_get(int index) {
        escribir(String.format("local.get %d", index));
    }

    public static void local_set(String name) {
        escribir(String.format("local.set $%s", name));
    }

    public static void local_set(int index) {
        escribir(String.format("local.set %d", index));
    }

    public static void local_tee(int index) {
        escribir(String.format("local.tee %d", index));
    }

    /* GLOBALS */
    public static void global(String name, String type) {
        escribir(String.format("(global %s %s)", name, type));
    }

    public static void global(String name, String type, String value) {
        escribir(String.format("(global %s %s (%s.const %s))", name, type, type, value));
    }

    public static void global_mut(String name, String type) {
        escribir(String.format("(global %s (mut %s))", name, type));
    }

    public static void global_mut(String name, String type, String value) {
        escribir(String.format("(global %s (mut %s) (%s.const %s))", name, type, type, value));
    }

    public static void global_get(String name) {
        escribir("global.get $" + name);
    }

    public static void global_get(int index) {
        escribir("global.get " + index);
    }

    public static void global_set(String name) {
        escribir("global.set $" + name);
    }

    public static void global_set(int index) {
        escribir("global.set " + index);
    }

    /* FUNCTIONS */
    public static void hacerReturn() {
        liberarPila();
        escribir("return");
    }

    public static void liberarPila() {
        llamar(LIBERAR_PILA);
    }

    /* CONTROL FLOW */
    public static void br(int skip) {
        escribir("br " + skip);
    }

    public static void br_if(int skip) {
        escribir("br_if " + skip);
    }

    public static void sangrar() {
        nivel_indentacion += TAM_INDENTACION;
    }

    public static void desangrar() {
        nivel_indentacion -= TAM_INDENTACION;   //TODO: Puede pasar el 0?
    }

    public static void bloque() {
        escribir("block");
    }

    public static void fin() {
        desangrar();
        escribir("end");
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        aux.append("(module\n");
        aux.append("""
                (import "runtime" "print" (func $escribirEnt (param i32)))
                (import "runtime" "print" (func $escribirBin (param i32)))
                (import "runtime" "scan" (func $leerEnt (result i32)))
                (import "runtime" "scan" (func $leerBin (result i32)))
                (import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
                """);
        aux.append(String.format("(memory %d)\n", tamMemoria));
        aux.append("(start $start)\n");
        aux.append("(type $_sig_i32i32i32 (func (param i32 i32 i32) ))\n");
        aux.append("(type $_sig_i32 (func (param i32)))\n");
        aux.append("(type $_sig_ri32 (func (result i32)))\n");
        aux.append("(type $_sig_void (func))\n");
        aux.append("(global $SP (mut i32) (i32.const 0))         ;; start of stack\n");
        aux.append("(global $MP (mut i32) (i32.const 0))         ;; mark pointer\n");
        aux.append("(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\n");
        cargarFunciones(aux);
        aux.append(sb.toString());
        aux.append("""
                (export "memory" (memory 0))
                (export "init" (func $start))
                """); // Para testear
        aux.append(")");
        return aux.toString();
    }

    private static void cargarFunciones(StringBuilder aux) {
        //Función que reserva $size bytes de stack
        escribir("""
                (func $reserveStack (param $size i32)
                (result i32)
                    ;;Devolver antiguo MP
                    global.get $MP

                    ;;MP = SP_antiguo
                    global.get $SP
                    global.set $MP

                    ;;SP = SP_antiguo + size
                    global.get $SP
                    local.get $size
                    i32.add
                    global.set $SP

                    ;;Overflow
                    global.get $SP
                    global.get $NP
                    i32.gt_u
                    if
                        i32.const 3
                        call $exception
                    end
                )
                """);

        //Función que libera un marco del stack
        escribir("""
                (func $freeStack (type $_sig_void)
                    ;;SP = MP
                    global.get $MP
                    global.set $SP

                    ;;MP = MP_antiguo
                    global.get $MP
                    i32.load
                    global.set $MP   
                )
                """);

        //TODO: Reservar Heap
        escribir("""
                (func $reserveHeap (type $_sig_i32)
                (param $size i32)
                ;; ....
                )
                """);

        // Copiar $n posiciones a partir de $src en $dest
        escribir("""
                (func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
                (param $src i32)
                (param $dest i32)
                (param $n i32)
                    block
                        loop
                            ;;n == 0?
                            local.get $n
                            i32.eqz
                            br_if 1

                            ;;n -= 1
                            local.get $n
                            i32.const 1
                            i32.sub
                            local.set $n

                            ;;MEM[dest] = MEM[src]
                            local.get $dest
                            local.get $src
                            i32.load
                            i32.store

                            ;;dest += 4
                            local.get $dest
                            i32.const 4
                            i32.add
                            local.set $dest

                            ;;src += 4
                            local.get $src
                            i32.const 4
                            i32.add
                            local.set $src
                            br 0
                        end
                    end
                )
                """);

        // Poner $n ceros a partir de la dirección $src
        escribir("""
                (func $fillZero
                    (param $src i32)
                    (param $n i32)
                    block
                        loop
                            ;;n == 0?
                            local.get $n
                            i32.eqz
                            br_if 1

                            ;;n -= 1
                            local.get $n
                            i32.const 1
                            i32.sub
                            local.set $n

                            ;;MEM[src] = 0
                            local.get $src
                            i32.const 0
                            i32.store

                            ;;src += 4
                            local.get $src
                            i32.const 4
                            i32.add
                            local.set $src
                            br 0
                        end
                    end
                )
                """);

        escribir("""
                (func $swap
                    (param $a i32)
                    (param $b i32)
                    (result i32 i32)
                    local.get $b
                    local.get $a
                )
                """);

        escribir("""
                (func $liberar
                    ;;TODO? Si lo hacemos tenemos que hacer gestión de memoria 
                )
                """);
    }

    public static void generarMainVacio() {
        escribir("""
                (func $tronco (result i32)
                    ;;Main sin contenido
                    ;;Asumimos que este archivo solo se utilizará como librería
                    ;;Solo apilamos un 0 para el drop de después
                    i32.const 0
                )
                """);
    }
}
