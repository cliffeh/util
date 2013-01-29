SRC=Node.h Stack.h main.cpp gpl.cpp
OBJ=main.o gpl.o
EXE=main

CC=g++
CPPFLAGS=-frepo -Wall -g
CXXFLAGS=-frepo -Wall -g
LDFLAGS=-frepo -Wall -g


all: $(EXE)

$(EXE): $(OBJ)

$(OBJ): $(SRC)

clean:
	rm -rf $(OBJ) *.rpo

veryclean: clean
	rm -rf $(EXE)
