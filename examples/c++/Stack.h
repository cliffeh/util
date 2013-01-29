#ifndef STACK_H
#define STACK_H 1

#include "Node.h"
#pragma once

#ifndef NULL
#define NULL 0
#endif

template<class T>
class Stack{
 private:
  int _size;
  Node<T> *ptr;
 public:
  Stack();
  ~Stack(){ delete [] ptr; }
  void push(T t);
  T pop();
  int size();
};

template<class T>
Stack<T>::Stack(){
  // initialize the stack
  _size = 0;
  ptr = NULL;
}

template<class T>
void Stack<T>::push(T t){
  // push t onto the stack
  Node<T> *node = new Node<T>();
  node->data = t;
  node->next = ptr;
  ptr = node;
  _size++;
}

template<class T>
T Stack<T>::pop(){
  // pop an object from the stack
  Node<T> *node = ptr;
  ptr = node->next;
  _size--;
  return node->data;
}

template<class T>
int Stack<T>::size(){
  return _size;
}


#endif
