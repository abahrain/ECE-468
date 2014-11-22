clear;
make;
clear;
java -cp lib/antlr.jar:classes/ Micro input/factorial2.micro > input/factorial2.out;
java -cp lib/antlr.jar:classes/ Micro input/fibonacci2.micro > input/fibonacci2.out;
java -cp lib/antlr.jar:classes/ Micro input/fma.micro > input/fma.out;
diff input/factorial2.out output/factorial2.out > diffs/factorial2.txt;
diff input/fibonacci2.out output/fibonacci2.out > diffs/fibonacci2.txt;
diff input/fma.out output/fma.out > diffs/fma.txt;
