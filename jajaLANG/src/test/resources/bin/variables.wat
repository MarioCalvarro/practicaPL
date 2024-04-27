(module
(import "runtime" "print" (func $escribirEnt (param i32)))
(import "runtime" "print" (func $escribirBin (param i32)))
(import "runtime" "scan" (func $leerEnt (result i32)))
(import "runtime" "scan" (func $leerBin (result i32)))
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
  i32.const 4
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

;;Llamar a 'tronco'
call $tronco
drop
)

;;Declaración de la función: tronco
(func $tronco (result i32)
  (local $localsStart i32)
  (local $temp i32)

  ;;Reservar espacio de pila: 20
  i32.const 20
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

  ;;;;;INICIO DECLARACIÓN DE b;;;

  ;;Variable con delta: 0
  local.get $localsStart
  i32.const 0
  i32.add

  ;;Asignar el valor: [100, 200]

    ;;Duplicar la dirección inicial del array para el siguiente elemento.
    local.tee $temp
    local.get $temp

    ;;Dirección del siguiente elemento: 0 * 4
    i32.const 0
    i32.add

    ;;Copiar el valor de la expresión en esa posición.

    ;;Evaluar la expresión
    i32.const 100

    ;;Guardar la dirección anterior
    i32.store

    ;;Duplicar la dirección inicial del array para el siguiente elemento.
    local.tee $temp
    local.get $temp

    ;;Dirección del siguiente elemento: 1 * 4
    i32.const 4
    i32.add

    ;;Copiar el valor de la expresión en esa posición.

    ;;Evaluar la expresión
    i32.const 200

    ;;Guardar la dirección anterior
    i32.store

    ;;Eliminar la dirección inicial duplicada.
    drop

  ;;;;;FIN DECLARACIÓN DE b;;;

  ;;Evaluar una operación binaria. Izquierda:

  ;;Sacar la dirección del designador

  ;;Acceder al array b.
  i32.const 4

  ;;Calcular el valor del índice.
  i32.const 0
  i32.mul

  ;;Calcular la dirección del array.

  ;;Variable con delta: 0
  local.get $localsStart
  i32.const 0
  i32.add
  i32.add

  ;;Cargar el valor dado por esa dirección
  i32.load

  ;;Evaluar una operación binaria. Derecha:
  i32.const 21

  ;;Evaluar una operación binaria. Operador:
  i32.add
  call $escribirEnt

  ;;Si no hay 'return' (es 'void') ponemos en la posición en la que debería estar un 0

  ;;Guardar el resultado en SP - tipoRetorno
  global.get $SP
  i32.const 4
  i32.sub
  i32.const 0
  i32.store

  ;;Ponemos en la cima de la pila la dirección donde está el resultado
  global.get $SP
  i32.const 4
  i32.sub

  ;;Liberar la pila
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