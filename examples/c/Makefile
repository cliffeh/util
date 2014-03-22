SRC=main.c gpl.c pthread.c fib.c fibE.c mutex.c curses-hello.c curses-getch.c rand.c
OBJ=main.o gpl.o fork.o pthread.o fib.o fibE.o mutex.o curses-hello.o curses-getch.o rand.o
EXE=main fork pthread fib fibE mutex curses-hello curses-getch rand

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

curses-hello.o: curses-hello.c

curses-hello: curses-hello.o
	$(CC) $(CFLAGS) -lncurses -o $@ $<

curses-getch.o: curses-getch.c

curses-getch: curses-getch.o
	$(CC) $(CFLAGS) -lncurses -o $@ $<

rand: rand.o

rand.o: rand.c

clean:
	rm -rf $(OBJ)

veryclean: clean
	rm -rf $(EXE)
