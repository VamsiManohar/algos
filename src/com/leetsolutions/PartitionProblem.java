package com.leetsolutions;

import java.util.ArrayList;

public class PartitionProblem {

  private long[] sum;

  private final int mod = 10000003;

  public int paint(int A, int B, ArrayList<Integer> C) {
    init(C);
    long result = getMax(C,C.size(),A);
    result = (result * B )% mod;
    return (int)result;
  }

  public long getMax(ArrayList<Integer> C , int b, int p){
    long min = Long.MAX_VALUE;
    if(b == 1){
      min= sum[0];
      System.out.println(b + " blocks : " + p + " partitions : " + min);
      return min;
    }

    if(p == 1){
      min = sum[b-1];
      System.out.println(b + " blocks : " + p + " partitions : " + min);
      return min;
    }

    for (int i = 1; i < b; i++) {
      min = Math.min( min, Math.max(getMax(C,i,p-1),getSum(i,b-1)));
    }
    System.out.println(b + " blocks : " + p + " partitions : " + min);
    return min;
  }

  public void init(ArrayList<Integer> arr){
    sum = new long[arr.size()];
    sum[0] = arr.get(0)*1L;
    for(int i=1;i<arr.size();i++){
      sum[i] = (sum[i-1] + arr.get(i)) % mod;
    }
  }

  public long getSum(int i, int j){
    if(i!=0){
      return sum[j] -sum[i-1];
    }
    else{
      return sum[j];
    }
  }
}
