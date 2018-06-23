package com.leetsolutions;

import java.util.ArrayList;

public class FindPermutation {

  // DO NOT MODIFY THE LIST. IT IS READ ONLY
  public ArrayList<Integer> findPerm(final String A, int B) {
    ArrayList<Integer> result = new ArrayList(B);

    for(int i=0;i<B;i++){
      result.add(B-i);
    }
    int streak = 0;
    for(int i =0; i < A.length(); i++){
      if(A.charAt(i) == 'I'){
        streak++;
        if(i == A.length()-1){
          reverseSubArray(result, i+1-streak , i+1);
        }
      }
      else if(A.charAt(i) == 'D'){
        if(streak>0){
          reverseSubArray(result, i-streak , i);
          streak =0;
        }
      }
    }
    return result;

  }

  private void swap(ArrayList<Integer> a, int i, int j){
    Integer temp = a.get(i);
    a.set(i, a.get(j));
    a.set(j, temp);
  }

  private void reverseSubArray(ArrayList<Integer> a, int i, int j){
    int len = j-i+1;
    for(int k = 0; k < len/2; k++ ){
      swap(a, i+k, i+len -1-k);
    }
  }
}

