package com.leetsolutions.disjoint;

import java.util.ArrayList;

public class DisjointSets {

  ArrayList<Integer> rank;

  ArrayList<Integer> parent;

  private Integer numberOfSets;

  public DisjointSets(Integer n) {
    rank = new ArrayList<>();
    parent= new ArrayList<>();
    numberOfSets = n;
    makeSet();
  }

  public void makeSet() {
    for (int i = 0; i < numberOfSets; i++) {
      rank.add(1);
      parent.add(i);
    }
  }

  private void Link(Integer i, Integer j){
    if(rank.get(i) < rank.get(j)) {
      parent.set(i, j);
    }
    else {
      if(rank.get(i).equals(rank.get(j))) {
        rank.set(i, rank.get(i)+1);
      }
      parent.set(j, i);
    }
  }

  public Integer findSet(Integer i) {
    if(!parent.get(i).equals(i)) {
      parent.set(i, findSet(parent.get(i)));
    }
    return parent.get(i);
  }

  public void Union(Integer i, Integer j) {
    Link(findSet(i), findSet(j));
  }


  public Integer getNumberOfSets() {
    return this.numberOfSets;
  }

}
