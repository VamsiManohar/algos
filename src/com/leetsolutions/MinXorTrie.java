package com.leetsolutions;

import java.util.ArrayList;

public class MinXorTrie {

  public Integer minXor(ArrayList<Integer> list){
    TrieNode root = new TrieNode();
    addInteger(root, list.get(0));
    Integer min = Integer.MAX_VALUE;
    for (int i = 1; i < list.size(); i++) {
      min = Math.min(min, getMin(root, list.get(i)));
      addInteger(root, list.get(i));
    }
    return min;
  }


  public void addInteger(TrieNode root, Integer num){

    for (int i = 30; i >=0 ; i--) {
      if ((num & (1 << i)) == 0){
        if(root.leftNode == null){
          root.leftNode = new TrieNode(0);
        }
        root =  root.leftNode;
      }
      else{
        if(root.rightNode == null){
          root.rightNode = new TrieNode(0);
        }
        root =  root.rightNode;
      }
    }

  }

  public Integer getMin(TrieNode root, Integer num){
    Integer result = 0;
    for (int i = 30; i >=0 ; i--) {
      Integer cur = 1 << i;
      if((num & cur) ==0){
        if(root.leftNode == null ){
          result = result | cur;
          root = root.rightNode;
        }
        else {
          root = root.leftNode;
        }

      }
      else{
        if(root.rightNode == null){
          result = result | cur;
          root = root.leftNode;
        }
        else{
          root = root.rightNode;
        }
      }
    }
    return result;
  }


  public static class TrieNode {

    public Integer value;

    public TrieNode leftNode;

    public TrieNode rightNode;


    public TrieNode(){

    }

    public TrieNode(Integer value){
      this.value = value;
    }

  }

}
