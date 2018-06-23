package com.leetsolutions;

import java.util.ArrayList;
import java.util.Collections;

public class EqualAverage {

  private final int max = Integer.MAX_VALUE-1;

  public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> A) {
    Collections.sort(A);
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

    int sum = 0;
    for(int a : A) {
      sum += a;
    }



    int w = sum/2;
    int k = A.size();
    int dp[][] = new int[w+1][k+1];
    boolean keep[][] = new boolean[w+1][k+1];

    for (int i =0; i <= w; i++) {
      dp[i][0] = max;
    }

    for(int j =0; j<=k; j++ ){
      dp[0][j] = 0;
    }

    for(int i=1; i<=w; i++) {
      for(int j=1; j<=k; j++) {

        dp[i][j] = dp[i][j-1];
        if (A.get(j-1) <= i) {
          if (( dp[i-A.get(j-1)][j-1] + 1) < dp[i][j]) {
              dp[i][j] = dp[i-A.get(j-1)][j-1] + 1;
              keep[i][j] = true;
          }
        }
      }
    }
    ArrayList<Integer> f = new ArrayList<>();
    ArrayList<Integer> s= new ArrayList<>();

    boolean isBreak = true;
    for(int i=1; i<=(k/2) && isBreak; i++){
      int req = (sum * i)/k;
      if(sum * i % k != 0){
        continue;
      }
      for (int j = k; j>= 1 ; j--){
        int ele = j;
        if(dp[req][ele] != max && dp[req][ele] == i) {
          //System.out.println(req + ":" + ele + ":" + dp[req][ele] );
          f = new ArrayList<>();
          s = new ArrayList<>();
          double fsum = 0;
          while(req!=0) {
            if(keep[req][ele]) {
              f.add(0, A.get(ele-1));
              fsum += A.get(ele-1);
              req = req-A.get(ele-1);
              ele--;
            }
            else{
              ele--;
            }
          }

          double sSum = 0;
          for(int a : A){
            if(!f.contains(a)){
              s.add(a);
              sSum += a;
            }
          }
          double faverage = fsum/f.size();
          double saverage = sSum/s.size();
          if(Double.compare(faverage, saverage) == 0) {
            isBreak = false;
          }
        }
      }

    }
    if (isBreak) {
      result.add(new ArrayList<Integer>());
      result.add(new ArrayList<Integer>());
    }
    else{
      result.add(f);
      result.add(s);
    }


    return result;
  }

}
