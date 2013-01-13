%{
#include <stdio.h>
#include "y.tab.h" // symbol table
  extern int yylval; // for storing ints for parser
  extern char yychar; // for storing characters
%}

%%

[1-9][0-9]* { 
  yylval = atoi(yytext);
  return(INTLIT);
}

[_a-zA-Z][_a-zA-Z0-9]* {
  return(IDENT);
}

[ \t\n]+ { /* ignore whitespace */ }

\"[^\"]*\" {
  yytext++;
  yytext[strlen(yytext)-1] = '\0';
  return(STRLIT);
}

"[" { return(LBRACK); }
"]" { return(RBRACK); }
"(" { return(LPAREN); }
")" { return(RPAREN); }

"+" { return(PLUS); }
"-" { return(DASH); }
"*" { return(STAR); }
"/" { return(FSLASH); }
"\\" { return(RSLASH); }
"^" { return(CARROT); }

"." { return(DOT); }
"=" { return(EQ); }
">" {return(GT); }
"<" {return(LT); }
">=" {return(GE); }
"<=" {return(LE); }

.* {
  fprintf(stderr, "lexer: unknown token '%s'\n", yytext);
}

%%
