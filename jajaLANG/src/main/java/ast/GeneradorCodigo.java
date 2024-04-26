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
    private final static StringBuilder sb = new StringBuilder();
    private final static int TAM_INDENTACION = 2;
    private final static int tamMemoria = 2000;
    private static int nivel_indentacion = 0;

    public static void comentario(String comentario) {
        sb.append(";;").append(comentario);
    }

    private static void trasReservarPila() {
        sb.append("""
                ;; Guarda en $temp el valor de $MP antiguo
                set_local $temp

                ;; Guarda en MEM[$MP] el valor de $MP antiguo
                get_global $MP       ;;; Este es MP nuevo = SP antiguo
                get_local $temp
                i32.store            ;;; Guarda en MEM[MP] el valor de MP antiguo

                    get_global $MP
                    get_global $SP       ;;; Este es SP nuevo (con el espacio reservado)
                i32.store offset=4       ;;; Guarda en MEM[MP+4] el valor de SP nuevo

                ;; Calcular el inicio del stack para las variables locales (8 + MP porque usamos 2 casillas para puntero dinamico)
                        get_global $MP
                        i32.const 8
                i32.add
                """);
        local_set(INICIO_LOCAL);
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

    public static void mainFunc() {
        //TODO
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
        escribir(String.format("get_local $%s", name));
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
            //TODO: Cambio MP y SP o solo MP
            i32_const(INICIO_GLOBAL + var.getPosicionDelta());
        } else {
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
                (import "runtime" "print" (func $print (param i32)))
                (import "runtime" "scan" (func $scan (result i32)))""");
        aux.append("""
                (import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))""");
        aux.append(String.format("(memory %d)", tamMemoria));
        aux.append("(start $start)");
        aux.append("(type $_sig_i32i32i32 (func (param i32 i32 i32) ))");
        aux.append("(type $_sig_i32 (func (param i32)))");
        aux.append("(type $_sig_ri32 (func (result i32)))");
        aux.append("(type $_sig_void (func))");
        aux.append("(global $SP (mut i32) (i32.const 0))         ;; start of stack");
        aux.append("(global $MP (mut i32) (i32.const 0))         ;; mark pointer");
        aux.append("(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4");
        // Este es el tipo de todas nuestras funciones, pues nos
        // pasamos los argumentos y valores de retorno por memoria
        cargarFunciones(aux);
        aux.append(sb.toString());
        return aux.toString();
    }

    private static void cargarFunciones(StringBuilder aux) {
        //Función que reserva $size bytes de stack
        sb.append("""
                (func $reserveStack (param $size i32)
                (result i32)
                    global.get $MP
                    global.get $SP
                    global.set $MP
                    global.get $SP
                    local.get $size
                    i32.add
                    global.set $SP
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
        sb.append("""
                (func $freeStack (type $_sig_void)
                    global.get $MP
                    i32.load
                    i32.load offset=4
                    global.set $SP
                    global.get $MP
                    i32.load
                    global.set $MP   
                )
                """);

        //TODO: Reservar Heap
        sb.append("""
                (func $reserveHeap (type $_sig_i32)
                (param $size i32)
                ;; ....
                )
                """);

        // Copiar $n posiciones a partir de $src en $dest
        sb.append("""
                (func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
                (param $src i32)
                (param $dest i32)
                (param $n i32)
                    block
                        loop
                            local.get $n
                            i32.eqz
                            br_if 1

                            local.get $n
                            i32.const 1
                            i32.sub
                            local.set $n
                            local.get $dest
                            local.get $src
                            i32.load
                            i32.store
                            local.get $dest
                            i32.const 4
                            i32.add
                            local.set $dest
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
        sb.append("""
                (func $fillZero
                    (param $src i32)
                    (param $n i32)
                    block
                        loop
                            get_local $n
                            i32.eqz
                            br_if 1
                            get_local $n
                            i32.const 1
                            i32.sub
                            set_local $n
                            get_local $src
                            i32.const 0
                            i32.store
                            get_local $src
                            i32.const 4
                            i32.add
                            set_local $src
                            br 0
                        end
                    end
                )
                """);

        sb.append("""
                (func $swap
                    (param $a i32)
                    (param $b i32)
                    (result i32 i32)
                    get_local $b
                    get_local $a
                )
                """);
    }
}
