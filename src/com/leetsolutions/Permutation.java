package com.leetsolutions;

public class Permutation {

  public void printPermutations(String s){
    printPermut(new StringBuilder(s), 0);
    return;
  }

  private void printPermut(StringBuilder s, int index){

    if(index == s.length()-1){
      System.out.println(s.toString());
    }
    else{
      for (int i = index; i < s.length(); i++) {
        char temp = s.charAt(i);
        s.deleteCharAt(i);
        s.insert(index,temp);

        printPermut(s, index+1);


        temp = s.charAt(index);
        s.deleteCharAt(index);
        s.insert(i,temp);
      }
    }
  }

  private void swap(StringBuilder s, int i ,int j){
    char temp = s.charAt(i);
    s.setCharAt(i, s.charAt(j));
    s.setCharAt(j,temp);
  }

}
