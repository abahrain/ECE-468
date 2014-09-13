LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Micro.g4

all: group compiler

group:
	@echo "Richard Mason Adam Bahrainwala"
compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java
clean:
	rm -rf classes build

.PHONY: all group compiler clean

test:
	clear;
	java -cp lib/antlr.jar:classes/ Micro input/test1.micro > output/test1.out
	java -cp lib/antlr.jar:classes/ Micro input/test2.micro > output/test2.out
	java -cp lib/antlr.jar:classes/ Micro input/test3.micro > output/test3.out
	java -cp lib/antlr.jar:classes/ Micro input/test4.micro > output/test4.out
	java -cp lib/antlr.jar:classes/ Micro input/test5.micro > output/test5.out
	java -cp lib/antlr.jar:classes/ Micro input/test6.micro > output/test6.out
	java -cp lib/antlr.jar:classes/ Micro input/test7.micro > output/test7.out
	java -cp lib/antlr.jar:classes/ Micro input/test8.micro > output/test8.out
	java -cp lib/antlr.jar:classes/ Micro input/test9.micro > output/test9.out
	java -cp lib/antlr.jar:classes/ Micro input/test10.micro > output/test10.out
	java -cp lib/antlr.jar:classes/ Micro input/test11.micro > output/test11.out
	java -cp lib/antlr.jar:classes/ Micro input/test12.micro > output/test12.out
	java -cp lib/antlr.jar:classes/ Micro input/test13.micro > output/test13.out
	java -cp lib/antlr.jar:classes/ Micro input/test14.micro > output/test14.out
	java -cp lib/antlr.jar:classes/ Micro input/test15.micro > output/test15.out
	java -cp lib/antlr.jar:classes/ Micro input/test16.micro > output/test16.out
	java -cp lib/antlr.jar:classes/ Micro input/test17.micro > output/test17.out
	java -cp lib/antlr.jar:classes/ Micro input/test18.micro > output/test18.out
	java -cp lib/antlr.jar:classes/ Micro input/test19.micro > output/test19.out
	java -cp lib/antlr.jar:classes/ Micro input/test20.micro > output/test20.out
	java -cp lib/antlr.jar:classes/ Micro input/test21.micro > output/test21.out
	java -cp lib/antlr.jar:classes/ Micro input/test22.micro > output/test22.out
	java -cp lib/antlr.jar:classes/ Micro input/test1.micro
	java -cp lib/antlr.jar:classes/ Micro input/test2.micro
	java -cp lib/antlr.jar:classes/ Micro input/test3.micro
	java -cp lib/antlr.jar:classes/ Micro input/test4.micro
	java -cp lib/antlr.jar:classes/ Micro input/test5.micro
	java -cp lib/antlr.jar:classes/ Micro input/test6.micro
	java -cp lib/antlr.jar:classes/ Micro input/test7.micro
	java -cp lib/antlr.jar:classes/ Micro input/test8.micro
	java -cp lib/antlr.jar:classes/ Micro input/test9.micro
	java -cp lib/antlr.jar:classes/ Micro input/test10.micro
	java -cp lib/antlr.jar:classes/ Micro input/test11.micro
	java -cp lib/antlr.jar:classes/ Micro input/test12.micro
	java -cp lib/antlr.jar:classes/ Micro input/test13.micro
	java -cp lib/antlr.jar:classes/ Micro input/test14.micro
	java -cp lib/antlr.jar:classes/ Micro input/test15.micro
	java -cp lib/antlr.jar:classes/ Micro input/test16.micro
	java -cp lib/antlr.jar:classes/ Micro input/test17.micro
	java -cp lib/antlr.jar:classes/ Micro input/test18.micro
	java -cp lib/antlr.jar:classes/ Micro input/test19.micro
	java -cp lib/antlr.jar:classes/ Micro input/test20.micro
	java -cp lib/antlr.jar:classes/ Micro input/test21.micro
	java -cp lib/antlr.jar:classes/ Micro input/test22.micro
	diff -b -B output/test1.out expected/test1.out
	diff -b -B output/test2.out expected/test2.out
	diff -b -B output/test3.out expected/test3.out
	diff -b -B output/test4.out expected/test4.out
	diff -b -B output/test5.out expected/test5.out
	diff -b -B output/test6.out expected/test6.out
	diff -b -B output/test7.out expected/test7.out
	diff -b -B output/test8.out expected/test8.out
	diff -b -B output/test9.out expected/test9.out
	diff -b -B output/test10.out expected/test10.out
	diff -b -B output/test11.out expected/test11.out
	diff -b -B output/test12.out expected/test12.out
	diff -b -B output/test13.out expected/test13.out
	diff -b -B output/test14.out expected/test14.out
	diff -b -B output/test15.out expected/test15.out
	diff -b -B output/test16.out expected/test16.out
	diff -b -B output/test17.out expected/test17.out
	diff -b -B output/test18.out expected/test18.out
	diff -b -B output/test19.out expected/test19.out
	diff -b -B output/test20.out expected/test20.out
	diff -b -B output/test21.out expected/test21.out
	diff -b -B output/test22.out expected/test22.out
