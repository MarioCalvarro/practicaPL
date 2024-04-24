package main.java.ast;

import main.java.ast.declaraciones.DeclaracionVar;

public class GeneradorCodigo {
    private final static StringBuilder sb = new StringBuilder();
    private final static int TAM_INDENTACION = 2;

    private final static String SIG_FUNC = "_sig_void";
    private final static String INICIO_LOCAL = "localsStart";
    private final static int INICIO_GLOBAL = 4;     //TODO: MP + SP o solo MP
    private final static String RESERVAR_PILA = "reserveStack";
    private final static String LIBERAR_PILA = "freeStack";
    private final static String RESERVAR_HEAP = "reserveHeap";
    private final static String ZEROS = "fillZero";
    private final static String COPIAR = "copyn";

    private final static int tamMemoria = 2000;
    private static int nivel_indentacion = 0;

    private static void escribir(String instruccion) {
        sb.append(instruccion.indent(nivel_indentacion));
    }

    private static void sangrar() {
        nivel_indentacion += TAM_INDENTACION;
    }

    private static void desangrar() {
        nivel_indentacion -= TAM_INDENTACION;   //TODO: Puede pasar el 0?
    }

    private void cargarFunciones(StringBuilder aux) {
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

    public static void llamar(String func) {
        escribir("call $" + func);
    }

    public static void comentario(String comentario) {
        sb.append(";;").append(comentario);
    }

    public static void reservarPila() {
        //TODO
    }    

    public static void liberarPila() {
        llamar(LIBERAR_PILA);
    }

    public static void reservarHeap() {
        llamar(RESERVAR_HEAP);
    }
    
    public static void mainFunc() {
        //TODO
    }

    //Pila de operaciones
    public static void drop() {
        escribir("drop");
    }

    public void duplicate() {
        tee_local("temp");
        get_local("temp");
    }

    /* i32 MEMORY OPERATIONS */
    public void i32_load() {
        escribir("i32.load");
    }

    public void i32_load(int offset) {
        escribir("i32.load offset=" + offset);
    }

    public void i32_store() {
        escribir("i32.store");
    }

    /* i32 OPERATIONS */
    public void i32_const(int i) {
        escribir("i32.const " + i);
    }

    public void i32_add() {
        escribir("i32.add");
    }

    public void i32_sub() {
        escribir("i32.sub");
    }

    public void i32_mul() {
        escribir("i32.mul");
    }

    public void i32_div_s() {
        escribir("i32.div_s");
    }

    public void i32_rem_s() {
        escribir("i32.rem_s");
    }

    public void i32_eq() {
        escribir("i32.eq");
    }

    public void i32_ne() {
        escribir("i32.ne");
    }

    public void i32_eqz() {
        escribir("i32.eqz");
    }

    public void i32_le_s() {
        escribir("i32.le_s");
    }

    public void i32_lt_s() {
        escribir("i32.lt_s");
    }

    public void i32_ge_s() {
        escribir("i32.ge_s");
    }

    public void i32_gt_s() {
        escribir("i32.gt_s");
    }

    public void i32_and() {
        escribir("i32.and");
    }

    public void i32_or() {
        escribir("i32.or");
    }

    public void i32_xor() {
        escribir("i32.xor");
    }

    /* MEMORY LOCALS */
    public void mem_local(int offset) {
        local_get(INICIO_LOCAL);
        i32_const(offset);
        i32_add();
    }

    public void mem_location(DeclaracionVar var) {
        if (var.esGlobal()) {
            //TODO: Cambio MP y SP o solo MP
            i32_const(INICIO_GLOBAL + var.getPosicionDelta());
        } else {
            mem_local(var.getPosicionDelta());
        }
    }

    /* LOCALS */
    public void local(String name, String type) {
        escribir("(local %s %s)", name, type);
    }

    public void local_get(String name) {
        escribir("get_local $%s", name);
    }

    public void get_local(int index) {
        escribir("get_local %d", index);
    }

    public void set_local(String name) {
        escribir("set_local $%s", name);
    }

    public void set_local(int index) {
        escribir("set_local %d", index);
    }

    public void tee_local(String name) {
        escribir("tee_local $%s", name);
    }

    public void tee_local(int index) {
        escribir("tee_local %d", index);
    }

    /* GLOBALS */
    public void global(String name, String type) {
        escribir("(global %s %s)", name, type);
    }

    public void global(String name, String type, String value) {
        escribir("(global %s %s (%s.const %s))", name, type, type, value);
    }

    public void global_mut(String name, String type) {
        escribir("(global %s (mut %s))", name, type);
    }

    public void global_mut(String name, String type, String value) {
        escribir("(global %s (mut %s) (%s.const %s))", name, type, type, value);
    }

    public void get_global(String name) {
        escribir("get_global $%s", name);
    }

    public void get_global(int index) {
        escribir("get_global %d", index);
    }

    public void set_global(String name) {
        escribir("set_global $%s", name);
    }

    public void set_global(int index) {
        escribir("set_global %d", index);
    }

    /* FUNCTIONS */
    public void call(String name) {
        escribir("call $%s", name);
    }
}
