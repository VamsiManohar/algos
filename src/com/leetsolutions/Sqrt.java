package com.leetsolutions;

public class Sqrt {

  public int sqrt(int a) {
    int low = 0;
    int high =a;
    int bestPossible = 0;

    while (low <= high) {

      int mid = (low+high)/2;

      if(mid  == a/mid){
        bestPossible = mid;
        break;
      }

      else if(mid  < a/mid){
        bestPossible = mid;
        low = mid+1;
      }

      else{
        high = mid -1;
      }


    }

    return bestPossible;

  }

}
