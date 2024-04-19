(module
(type $_sig_i32i32i32 (func (param i32 i32 i32) ))
(type $_sig_i32ri32 (func (param i32) (result i32)))
(type $_sig_i32i32 (func (param i32 i32)))
(type $_sig_i32i32r32 (func (param i32 i32) (result i32)))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func ))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $main)
(func $main  (type $_sig_void)
    (local $temp i32)
    (local $n i32)
    (local $p i32)
   (; (local $localsStart i32) ;)
   (; (local $temp i32) ;)
   (; i32.const 200  ;; let this be the stack size needed (params+locals+2)*4 ;)
   (; call $reserveStack  ;; returns old MP (dynamic link) ;)
   (; local.set $temp ;)
   (; global.get $MP ;)
   (; local.get $temp ;)
   (; i32.store ;)
   (; global.get $MP ;)
   (; global.get $SP ;)
   (; i32.store offset=4 ;)
   (; global.get $MP ;)
   (; i32.const 8 ;)
   (; i32.add ;)
   (; local.set $localsStart ;)
   (;;)
   (; ;; instructions ;)
   (; ;; copy params from $localsStart ;)
   (; ;; call $func1 ;)
   (; ;; instructions ;)
   (;;)
   (; call $freeStack ;)
   i32.const 5      ;; n
   local.set $n
   i32.const 0      ;; p
   local.set $p

   local.get $n
   local.get $p
   call $fUno

   local.get $n
   local.get $p
   call $fDos

   local.get $n
   local.get $p
   call $fTres
   call $print
)
(; (func $func1 (type $_sig_ri32) ;)
(;    (result i32) ;)
(;    (local $temp i32) ;)
(;    (local $localsStart i32) ;)
(;    i32.const 200  ;; let this be the stack size needed (params+locals+2)*4 ;)
(;    call $reserveStack  ;; returns old MP (dynamic link) ;)
(;;)
(;    ;; Guardar en el nuevo MP el antiguo ;)
(;    local.set $temp  ;; temp := old MP ;)
(;    global.get $MP   ;; Nuevo MP ;)
(;    local.get $temp  ;; Antiguo MP ;)
(;    i32.store        ;; Almacenamos en "Nuevo MP" el "Antiguo MP" ;)
(;;)
(;    ;; Guardar la nueva cima en nuevo MP + 4 ;)
(;    global.get $MP   ;; Nuevo MP ;)
(;    global.get $SP   ;; Nueva cima ;)
(;    i32.store offset=4   ;; Guardamos en "Nuevo MP" + 4 la "Nueva Cima" ;)
(;;)
(;;)
(;    ;; LocalsStart es Nuevo MP + 8 ;)
(;    global.get $MP   ;; Nuevo MP ;)
(;    i32.const 8 ;)
(;    i32.add ;)
(;    local.set $localsStart ;)
(;;)
(;    ;; instructions ;)
(;    ;; copy params from $localsStart ;)
(;    ;; call $func1 ;)
(;    ;; instructions ;)
(;;)
(;    i32.const 0 ;; to return something ;)
(;     ;)
(;    call $freeStack ;)
(; ) ;)
(;;)
(; ;; Devuelve la antigua base del marco ;)
(; (func $reserveStack (param $size i32) ;)
(;    (result i32) ;)
(;    ;; Guardar la antigua base del marco anterior ;)
(;    ;; (Va a ser lo que se devuelva) ;)
(;    global.get $MP ;)
(;;)
(;    ;; Guardar la antigua cima de la pila en MP ;)
(;    global.get $SP ;)
(;    global.get $MP ;)
(;;)
(;    ;; La nueva cima es la antigua más el tamaño ;)
(;    global.get $SP ;)
(;    local.get $size ;)
(;    i32.add ;)
(;    global.set $SP ;)
(;;)
(;    ;; Comprobar stack overflow ;)
(;    global.get $SP ;)
(;    global.get $NP ;)
(;    i32.gt_u ;)
(;    if ;)
(;    i32.const 3 ;)
(;    call $exception ;)
(;    end ;)
(; ) ;)
(; (func $freeStack (type $_sig_void) ;)
(;    ;; Tomamos la base del marco y ponemos SP a ese valor ;)
(;    global.get $MP ;)
(;    i32.load ;)
(;    i32.load offset=4 ;)
(;    global.set $SP ;)
(;;)
(;    ;; Cargamos la antigua base del marco ;)
(;    global.get $MP ;)
(;    i32.load ;)
(;    global.set $MP    ;)
(; ) ;)
(; (func $reserveHeap (type $_sig_i32) ;)
(;    (param $size i32) ;)
(; ;; .... ;)
(; ) ;)
(; (func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest ;)
(;    (param $src i32) ;)
(;    (param $dest i32) ;)
(;    (param $n i32) ;)
(;    block ;)
(;      loop ;)
(;        local.get $n ;)
(;        i32.eqz ;)
(;        br_if 1 ;)
(;        local.get $n ;)
(;        i32.const 1 ;)
(;        i32.sub ;)
(;        local.set $n ;)
(;        local.get $dest ;)
(;        local.get $src ;)
(;        i32.load ;)
(;        i32.store ;)
(;        local.get $dest ;)
(;        i32.const 4 ;)
(;        i32.add ;)
(;        local.set $dest ;)
(;        local.get $src ;)
(;        i32.const 4 ;)
(;        i32.add ;)
(;        local.set $src ;)
(;        br 0 ;)
(;      end ;)
(;    end ;)
(; ) ;)
(func $fUno (type $_sig_i32i32)
    (param $n i32)
    (param $p i32)
    (local $temp i32)
    i32.const 0
    local.set $temp ;; temp := 0
    block
        loop
            ;; temp != n?
            local.get $temp
            local.get $n
            i32.sub
            i32.eqz     ;; temp - n = 0?
            br_if 1 

            ;; Escribir los enteros
            local.get $temp
            i32.const 4
            i32.mul
            local.get $p
            i32.add     ;; dir = p + temp * 4

                ;; Leer ent
                call $read

            i32.store

            ;; temp += 1
            local.get $temp
            i32.const 1
            i32.add
            local.set $temp
            br 0
        end
    end    
) 
(func $fDos (type $_sig_i32i32)
    (param $n i32)
    (param $p i32)
    (local $temp i32)
    i32.const 0
    local.set $temp
    block
        loop
            ;; temp != n?
            local.get $temp
            local.get $n
            i32.sub
            i32.eqz     ;; temp - n = 0?
            br_if 1 

            ;; Escribir los enteros
            local.get $temp
            i32.const 4
            i32.mul
            local.get $p
            i32.add     ;; dir = p + temp * 4

            i32.load

            call $print

            ;; temp += 1
            local.get $temp
            i32.const 1
            i32.add
            local.set $temp
            br 0
        end
    end    
) 
(func $fTres (type $_sig_i32i32r32)
    (param $n i32)
    (param $p i32)
    (result i32)
    (local $temp i32)
    (local $sum i32)
    i32.const 0
    local.set $sum
    i32.const 0
    local.set $temp
    block
        loop
            ;; temp != n?
            local.get $temp
            local.get $n
            i32.sub
            i32.eqz     ;; temp - n = 0?
            br_if 1 

            ;; Escribir los enteros
            local.get $temp
            i32.const 4
            i32.mul
            local.get $p
            i32.add     ;; dir = p + temp * 4

            i32.load

            local.get $sum
            i32.add
            local.set $sum  ;; sum += mem(dir)

            ;; temp += 1
            local.get $temp
            i32.const 1
            i32.add
            local.set $temp
            br 0
        end
    end

    local.get $sum
)
)
