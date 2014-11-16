LIB_ANTLR := lib/antlr.jar #antlr 4.4 complete, but we renamed it to avoid confusion
ANTLR_SCRIPT := Micro.g4

all: group compiler

group:
	@echo "Richard Mason Adam Bahrainwala"
compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -visitor -o build $(ANTLR_SCRIPT) 
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java
clean:
	rm -rf classes build

.PHONY: all group compiler clean

