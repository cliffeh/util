YFLAGS=-d
SRC=lexer.c parser.c y.tab.h
OBJ=lexer.o parser.o
EXE=parser

all:    $(EXE)

lexer.c:  lexer.l

parser.c: parser.y

$(OBJ):  $(SRC)

$(EXE): $(OBJ)

emacs-clean:
	find . -name '*~' -exec rm -f {} \;

clean: emacs-clean
	rm -f $(SRC) $(OBJ) y.tab.h

veryclean: clean
	rm -f $(EXE)

#
# Yacc and lex:
#

#%.c: %.y
#	$(YACC) $(YFLAGS) $(input)
#	mv -f y.tab.c $(output)

#%.c: %.l
#	$(LEX) $(LFLAGS) -t $(input) -o $(output)
