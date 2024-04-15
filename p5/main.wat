(module
(import "runtime" "print" (func $print (param i32)))
(import "runtime" "read" (func $read (result i32)))
(memory 2000)
(start $init)
(func $init
   (local $in1 i32)
   (local $in2 i32)
   (local $out i32)
   call $read
   local.set $in1
   call $read
   local.set $in2
   local.get $in1
   local.get $in2
   i32.add
   local.set $out
   local.get $out
   call $print
)
(export "init" (func $init))
)
