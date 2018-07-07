package com.leetsolutions.dijkstra;

public interface MinHeap<T extends Comparable<? super T>> {

  T extractMin();

  Boolean isEmpty();

  void insert(T t);



  T findMin();

  void clear();

  Integer size();


}
