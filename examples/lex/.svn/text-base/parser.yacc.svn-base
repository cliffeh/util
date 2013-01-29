%{
#include <stdio.h>
  extern char *yytext;
  %}

%token RSLASH
%token INTLIT STRLIT IDENT
%token PLUS DASH STAR FSLASH CARROT
%token LPAREN RPAREN LBRACK RBRACK
%token LT GT LE GE EQ DOT

%left PLUS DASH
%left STAR FSLASH
%left NEG
%right CARROT

%start exprlist

%%

exprlist: expr exprlist | ;

expr: 
INTLIT { printf("INTLIT: %i\n", yylval); } |
STRLIT { printf("STRLIT: \"%s\"\n", yytext); } |
IDENT  { printf("IDENT:  %s\n", yytext); } ;


%%

int main(argc, argv)
     int argc;
     char **argv;
{
  yyparse();
}

yyerror(s)
     char *s;
{
  fprintf(stderr, "%s\n",s);
}

yywrap()
{
  return(1);
}
