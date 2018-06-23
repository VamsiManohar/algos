package com.leetsolutions;

import java.util.ArrayList;

public class PartitionProblemDp {

  private long[] sum;

  private final int mod = 10000003;

  public int paint(int A, int B, ArrayList<Integer> C) {
    int size = C.size();
    long dp[][] = new long[size+1][A+1];
    init(C);
    for (int i = 1; i <= A; i++) {
      dp[1][i] = C.get(0);
    }
    for (int i = 1; i <=size ; i++) {
      dp[i][1] = sum[i-1];
    }
    for (int partition = 2;partition<=A;partition++){

      for (int block = 2; block <=size; block++) {
        dp[block][partition] = Long.MAX_VALUE;
        for (int i=1;i<block;i++){
          dp[block][partition] = Math.min(dp[block][partition], Math.max(dp[i][partition-1],
              getSum(i, block-1)));
        }
      }
    }
    return (int)(dp[size][A] * B)%mod;
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
