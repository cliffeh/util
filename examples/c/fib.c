/**
 *
 *  fib.c - find Fionacci numbers up to the argv[1]th (inclusive)
 *          (if argv[1] isn't provided, run to MAX_INT)
 *
 **/

#include <stdlib.h>
#include <limits.h>
#include <stdio.h>


int fib(int n);

int main(int argc, char *argv[])
{
  int n=0,res=0,sum=0,max=(argc > 1) ? atoi(argv[1]) : INT_MAX;

  while(n < max){
    res = fib(n++);
    sum += res;
    printf("%i: %i (%i)\n", n, res, sum);
  }
 
  return 0;
}

int fib(int n)
{
  if(n == 0) return 0;
  if(n == 1) return 1;
  return fib(n-1)+fib(n-2);
}
