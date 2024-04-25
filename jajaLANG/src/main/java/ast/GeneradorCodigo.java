package main.java.ast;

import main.java.ast.declaraciones.DeclaracionFun;
import main.java.ast.declaraciones.DeclaracionVar;

public class GeneradorCodigo {
    private final static StringBuilder sb = new StringBuilder();
    private final static int TAM_INDENTACION = 2;

    public final static String SIG_FUNC = "_sig_void";
    public final static String INICIO_LOCAL = "localsStart";
    public final static int INICIO_GLOBAL = 4;     //TODO: MP + SP o solo MP
    public final static String RESERVAR_PILA = "reserveStack";
    public final static String LIBERAR_PILA = "freeStack";
    public final static String RESERVAR_HEAP = "reserveHeap";
    public final static String ZEROS = "fillZero";
    public final static String COPIAR = "copyn";

    private final static int tamMemoria = 2000;
    private static int nivel_indentacion = 0;

    public static void escribir(String instruccion) {
        sb.append(instruccion.indent(nivel_indentacion));
    }

    public static void sangrar() {
        nivel_indentacion += TAM_INDENTACION;
    }

    public static void desangrar() {
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
        local_tee("temp");
        local_get("temp");
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
        escribir(String.format("(local %s %s)", name, type));
    }

    public void local_get(String name) {
        escribir(String.format("get_local $%s", name));
    }

    public void local_get(int index) {
        escribir(String.format("local.get %d", index));
    }

    public void local_set(String name) {
        escribir(String.format("local.set $%s", name));
    }

    public void local_set(int index) {
        escribir(String.format("local.set %d", index));
    }

    public void local_tee(String name) {
        escribir(String.format("local.tee $%s", name));
    }

    public void local_tee(int index) {
        escribir(String.format("local.tee %d", index));
    }

    /* GLOBALS */
    public void global(String name, String type) {
        escribir(String.format("(global %s %s)", name, type));
    }

    public void global(String name, String type, String value) {
        escribir(String.format("(global %s %s (%s.const %s))", name, type, type, value));
    }

    public void global_mut(String name, String type) {
        escribir(String.format("(global %s (mut %s))", name, type));
    }

    public void global_mut(String name, String type, String value) {
        escribir(String.format("(global %s (mut %s) (%s.const %s))", name, type, type, value));
    }

    public void global_get(String name) {
        escribir("global.get $" + name);
    }

    public void global_get(int index) {
        escribir("global.get " + index);
    }

    public void global_set(String name) {
        escribir("global.set $" + name);
    }

    public void global_set(int index) {
        escribir("global.set " + index);
    }

    /* FUNCTIONS */
    public void func(DeclaracionFun fun, Runnable runnable) {
        escribir("(func $%s" + fun.getId());
        sangrar();
            escribir(String.format("(local $%s i32)", INICIO_GLOBAL));
            escribir("(local $temp i32)");

            //TODO: Cuánto hay que sumar?
            int x = 0;
            int stackSize = fun.getTam() + x;

            i32_const(stackSize);
            reservarPila();

            //TODO: runnable.run(); // La idea es que haga algo con el ProgramOutput dentro del runnable

            liberarPila();
        desangrar();
        escribir(")");
    }

    public void hacerReturn() {
        liberarPila();
        escribir("return");
    }

    /* CONTROL FLOW */
    public void bloque(Runnable runnable) {
        escribir("block");
        //TODO: sangrado(runnable);
        escribir("end");
    }

    public void bloques(int n, Runnable runnable) {
    }

    public void bucle(Runnable runnable) {
        escribir("loop");
        //TODO: sangrado(runnable);
        escribir("end");
    }

    public void bloque_bucle(Runnable runnable) {
        bloque(() -> bucle(runnable));
    }

    public void br(int skip) {
        escribir("br " + skip);
    }

    public void br_if(int skip) {
        escribir("br_if " + skip);
    }

    public void si_sino(Runnable then, Runnable els) {
        escribir("if");
        sangrar();
        //TODO: sangrado(then);
        desangrar();
        escribir("else");
        sangrar();
        //TODO: sangrado(els);
        desangrar();
        escribir("end");
    }

    public void if_(Runnable then) {
        escribir("if");
        sangrar();
        //TODO: sangrado(then);
        desangrar();
        escribir("end");
    }

    public void bloque() {
        escribir("block");
    }

    public void fin() {
        desangrar();
        escribir("end");
    }
}
