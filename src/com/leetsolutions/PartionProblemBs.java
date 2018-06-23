package com.leetsolutions;

import java.util.ArrayList;

public class PartionProblemBs {

  private final int mod = 10000003;

  public int paint(int A, int B, ArrayList<Integer> C) {
    long min = Long.MIN_VALUE;
    long max = 0;

    for (int i = 0; i < C.size(); i++) {
      min = Math.max(min, C.get(i));
      max += C.get(i);
    }
    return (int)((searchFirst(C,A,min, max, -1)*B) % mod);
  }


  public long searchFirst(ArrayList<Integer> C,int A,long low , long high, long bp){
    if(low > high){
      return bp;
    }

    long mid = (low + high)/2;
    int fmid = func(C,mid);
    if(fmid <= A){
      return searchFirst(C,A, low, mid-1, mid);
    }
    else{
      return searchFirst(C,A, mid+1, high, bp);
    }

  }

  public int func(ArrayList<Integer> arr, long x){
    int result = 1;
    long preSum = 0;
    for (int i = 0; i < arr.size(); i++) {
      preSum = (preSum + arr.get(i));
      if(preSum >x){
        result++;
        preSum = arr.get(i);
      }
    }
    return result;
  }

}
