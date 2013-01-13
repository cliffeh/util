/**
 *
 *  fibE.c - find Fionacci numbers up to the argv[1]th (inclusive)
 *           (if argv[1] isn't provided, run to MAX_INT)
 *
 *    note: this is a more efficient implementation than fib.c
 *
 **/

#include <stdlib.h>
#include <limits.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
  int n=0,n1=0,n2=0,res=0,sum=0,max=(argc > 1) ? atoi(argv[1]) : INT_MAX;

  if(max >= 0) printf("1: 0 (0)\n");
  if(max >= 1) printf("2: 1 (1)\n");

  n=2;n1=0;n2=1;

  while((n++) < max){
    res = n1+n2;
    sum += res;
    printf("%i: %i (%i)\n", n, res, sum);
    n1 = n2;
    n2 = res;
  }
 
  return 0;
}
