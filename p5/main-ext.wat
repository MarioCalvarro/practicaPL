(module
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(type $_sig_void (func ))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_i32i32ri32ri32 (func (param i32 i32)(result i32 i32)))
(memory 2000)
(start $init)
(func $init  (type $_sig_void)
   global.get $smd
   i32.const 0
   i32.add
   i32.load 
   global.get $smd
   i32.const 4
   i32.add
   i32.load 
   call $sumaresta
   call $print
   call $print
)
(func $sumaresta  (type $_sig_i32i32ri32ri32)
   (param $op1 i32)
   (param $op2 i32)
   (result i32)
   (result i32)
   local.get $op1
   local.get $op2
   i32.sub
   local.get $op1
   local.get $op2
   i32.add
;;   return ;; optional if at the end
)
(func $sumaresta1  
   (param $op1 i32)
   (param i32)
   (result i32)
   (result i32)
   local.get $op1
   local.get 1
   i32.sub
   local.get $op1
   local.get 1
   i32.add
;;   return ;; optional if at the end
)
(func $sumaresta2  (param i32 i32) (result i32 i32)
   local.get 0
   local.get 1
   i32.sub
   local.get 0
   local.get 1
   i32.add
;;   return ;; optional if at the end
)
(func $sumaresta3  (type $_sig_i32i32ri32ri32)
   (param $op1 i32)
   (param $op2 i32)
   (result i32)
   (result i32)
   (local $temp i32)
   (local.set $temp (i32.sub (local.get $op1) (local.get $op2)))
   local.get $temp
   (i32.add (local.get $op1) (local.get $op2))
;;   return ;; optional if at the end
)
(global $smd i32 (i32.const 64)) ;; points to start of memory data
(export "init" (func $init))
(data (i32.const 64) "\22\00\00\00") ;; 34
(data (i32.const 68) "#\00\00\00")   ;; 35
)
