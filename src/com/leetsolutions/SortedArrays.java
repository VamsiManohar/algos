package com.leetsolutions;

import java.util.ArrayList;

public class SortedArrays {

  public int kOrder(final ArrayList<Integer> a, final ArrayList<Integer> b, int k){
    return select(a,b,0,a.size()-1,0,b.size()-1,k);
  }

  public int select(final ArrayList<Integer> a, final ArrayList<Integer> b ,int startA, int endA,
      int startB, int endB, int k){
    int sizeA = endA-startA+1;
    int sizeB = endB-startB+1;

    if(k <= 0){
      return -1;
    }

    if(k > sizeA + sizeB){
      return -1;
    }

    if(sizeA == 0){
      return b.get(startB + k-1);
    }

    if(sizeB == 0){
      return a.get(startA + k-1);
    }

    if(k ==1){
      return Math.min(a.get(startA),b.get(startB));
    }

    int redSizeA = Math.min(k/2, sizeA);

    int redSizeB = Math.min(k/2, sizeB);

    if(a.get(startA + redSizeA -1) <= b.get(startB + redSizeB -1)){
      return select(a,b,startA + redSizeA,endA, startB,endB,k-redSizeA);
    }
    else{
      return select(a,b,startA,endA, startB + redSizeB ,endB,k-redSizeB);
    }

  }
  public double median(final ArrayList<Integer> a, final ArrayList<Integer> b){
    return (select(a,b,0, a.size()-1, 0, b.size()-1, (a.size()+b.size()-1)/2 +1) + select(a,b,
        0, a.size()-1, 0, b.size()-1, (a.size()+b.size())/2 +1))/2.0;
  }

}
