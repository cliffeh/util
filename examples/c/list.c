/* simple singly-linked list implementation */
#include <stdlib.h>
#include <stdio.h>

typedef struct node_t {
  struct node_t *next;
  int value;
} node_t;

int main()
{
  node_t head, *tail = &head;
  tail->next = 0;
  char buf[1024];

  while(fgets(buf, sizeof(buf), stdin)) {
    tail->value = atoi(buf);
    tail->next = malloc(sizeof(node_t));
    tail = tail->next;
    tail->next = 0;
  }

  for(tail = &head; tail; tail = tail->next) {
    printf("value: %i\n", tail->value);
  }
}
