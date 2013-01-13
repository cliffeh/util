#include <stdio.h>
#include <string.h>
#include "Stack.h"

#define BUFLEN 1024

int main(int argc, char *argv[]){

  typedef Stack<char*> StringStack;

  char *buf;

  StringStack stack;

  printf("push: ");
  while(fgets((buf = new char[BUFLEN]), BUFLEN, stdin)){
    buf[strlen(buf)-1] = '\0';
    stack.push(buf);
    printf("input: ");
  }

  printf("done.\n");

  while(stack.size() > 0){
    printf("pop: %s\n", stack.pop());
  }

  return 0;
}
