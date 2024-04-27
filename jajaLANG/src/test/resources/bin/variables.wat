(module
(import "runtime" "print" (func $print (param i32)))
(import "runtime" "scan" (func $scan (result i32)))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(memory 2000)
(start $start)
(type $_sig_i32i32i32 (func (param i32 i32 i32) ))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func))
(global $SP (mut i32) (i32.const 0))         ;; start of stack
(global $MP (mut i32) (i32.const 0))         ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(func $start (type $_sig_void)
(local $localsStart i32)
(local $temp i32)
  i32.const 8
  call $reserveStack
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
  local.set $localsStart

  ;;;;;;;;;FIN POST RESERVA;;;;;;;;

  ;;;;;INICIO DECLARACIÓN DE var1;;;

  ;;Variable global con delta: 0
  i32.const 4

  ;;Asignando el valor: 1

  ;;Evaluamos la expresión
  i32.const 1

  ;;La guardamos en la dirección anterior
  i32.store

  ;;;;;FIN DECLARACIÓN DE var1;;;

;;Llamada a 'tronco'
call $tronco
)

;;Declaración de la función: tronco
(func $tronco
  (local $localsStart i32)
  (local $temp i32)

  ;;Reservamos espacio de pila: 16
  i32.const 16
  call $reserveStack
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
  local.set $localsStart

  ;;;;;;;;;FIN POST RESERVA;;;;;;;;

  ;;;;;INICIO DECLARACIÓN DE var2;;;

  ;;Variable con delta: 0
  local.get $localsStart
  i32.const 0
  i32.add

  ;;Asignando el valor: {
  ;;at2 = facto,
  ;;at1 = 100
  ;;}

  ;;Inicio copia valores del literal de registro.

    ;;Duplicamos la dirección inicial del registro.
    local.tee $temp
    local.get $temp

    ;;Cogemos el offset del atributo at2.
    i32.const 0
    i32.add

    ;;Copiamos el valor del atributo at2 a su posición adecuada.

    ;;Evaluamos la expresión
    i32.const 1

    ;;La guardamos en la dirección anterior
    i32.store

    ;;Duplicamos la dirección inicial del registro.
    local.tee $temp
    local.get $temp

    ;;Cogemos el offset del atributo at1.
    i32.const 0
    i32.add

    ;;Copiamos el valor del atributo at1 a su posición adecuada.

    ;;Evaluamos la expresión
    i32.const 100

    ;;La guardamos en la dirección anterior
    i32.store

    ;;Eliminamos la copia de la dirección inicial del regitro.
    drop

  ;;;;;FIN DECLARACIÓN DE var2;;;

  ;;Liberamos la pila
  call $freeStack
)
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
(func $freeStack (type $_sig_void)
    ;;SP = MP
    global.get $MP
    global.set $SP

    ;;MP = MP_antiguo
    global.get $MP
    i32.load
    global.set $MP
)
(func $reserveHeap (type $_sig_i32)
(param $size i32)
;; ....
)
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
(func $swap
    (param $a i32)
    (param $b i32)
    (result i32 i32)
    local.get $b
    local.get $a
)
(func $liberar
    ;;TODO? Si lo hacemos tenemos que hacer gestión de memoria
)
(export "memory" (memory 0))
(export "init" (func $start))
)