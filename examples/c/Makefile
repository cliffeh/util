SRC=main.c gpl.c pthread.c fib.c fibE.c mutex.c curses.c
OBJ=main.o gpl.o fork.o pthread.o fib.o fibE.o mutex.o curses.o
EXE=main fork pthread fib fibE mutex curses

all: $(EXE)

fork: fork.o

fork.o: fork.c

main: main.o gpl.o

gpl.o: gpl.c

main.o: main.c

pthread: pthread.o
	$(CC) $(CFLAGS) -pthread -o $@ $<

mutex: mutex.o
	$(CC) $(CFLAGS) -pthread -o $@ $<

pthread.o: pthread.c

fib.o: fib.c

fibE.o: fibE.c

curses.o: curses.c

curses: curses.o
	$(CC) $(CFLAGS) -lncurses -o $@ $<

clean:
	rm -rf $(OBJ)

veryclean: clean
	rm -rf $(EXE)
