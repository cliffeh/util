#ifndef NODE_H
#define NODE_H 1

template<class T>
class Node{
 public:
  T data;
  Node *prev;
  Node *next;
  Node();
};

template<class T>
Node<T>::Node(){
  prev = NULL;
  next = NULL;
}

#endif
