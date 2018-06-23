package com.leetsolutions;

import java.util.ArrayList;

public class MaxOne {

  public ArrayList<Integer> maxone(ArrayList<Integer> A, int B) {
    int size = A.size();
    ArrayList<Integer> zeroC = new ArrayList<Integer>();
    ArrayList<Integer> zeroI = new ArrayList<Integer>();
    for(int i=0; i<size; i++){
      if(A.get(i) == 0){
        int count = (i == 0) ? 1 : zeroC.get(i-1) +1;
        zeroC.add(count);
        zeroI.add(i);
      }
      else{
        int count = (i == 0) ? 0 : zeroC.get(i-1);
        zeroC.add(count);
      }
    }
    ArrayList<Integer> result = new ArrayList<>();
    int i = 0;
    int j = 0;
    int max = Integer.MIN_VALUE;
    int left=0;
    int right=0;
    while(j < size){
      if(A.get(j) == 0){
        int zC = getZeroCount(zeroC, i, j);
        if(zC<=B){
          j++;
        }
        else{
          if(max < j-i){
            max = j-i;
            left = i;
            right = j;
          }
          if(B == 0){
            j++;
            i =j;
          }
          else{
            i = zeroI.get(getZeroCount(zeroC,0,j)-(B)-1)+1;
          }
        }
      }
      else{
        j++;
      }
    }

    if(max < j-i){
      left = i;
      right = j;
    }


    for(int x=left ; x<right;x++){
      result.add(x);
    }
    return result;
  }


  private int getZeroCount(ArrayList<Integer> zeroC, int i, int j){
    if(i == 0){
      return zeroC.get(j);
    }
    else{
      return zeroC.get(j) - zeroC.get(i-1);
    }
  }
}
