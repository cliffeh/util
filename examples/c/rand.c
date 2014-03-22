#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define RUNS 10000000
#define N 20

int main() {
  int i, r, vals[N];

  for(i = 0; i < N; i++) {
    vals[i] = 0;
  }  

  fprintf(stdout, "computing...");
  
  srand(time(NULL));
  for(i = 0; i < RUNS; i++) {
    r = rand() % N;
    vals[r]++;
  }

  fprintf(stdout, "\nexpected value: %i\n", RUNS/N);

  for(i = 0; i < N; i++) {
    fprintf(stdout, "%i: %i\n", i, vals[i]);
  }
}

