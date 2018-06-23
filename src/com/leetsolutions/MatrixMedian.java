package com.leetsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MatrixMedian {

  public int findMedian(ArrayList<ArrayList<Integer>> A) {
    int r = A.size();
    int c = A.get(0).size();
    int size = r*c;
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      list.add(A.get(i/c).get(i%c));
    }
    return median(list);
  }

  public int median(ArrayList<Integer> a){
    int size = a.size();
    if(size % 2 == 0){
      int f = select(a,0,size-1, (size/2)-1);
      int c = select(a,0,size-1, size/2);
      return (f+c)/2;
    }
    else{
      int m = select(a, 0, size-1,size/2);
      return m;
    }
  }

  public int select(ArrayList<Integer> a, int low, int high, int n){
    int pivot = pivot(a,low,high);
    int  k = partition(a,low, high,pivot);
    if(k == n){
      return a.get(k);
    }
    else if(k > n){
      return select(a, low, k-1, n);
    }
    else{
      return select(a,k+1, high,n);
    }
  }

  public int pivot(ArrayList<Integer> a, int low, int high){

    int size = high-low+1;
    int numM = (int)Math.ceil(size/5.0);
    int med[] = new int[numM];
    int start = low;
    for (int i = 0; i <numM ; i++) {
      int end = (start + 4) > high ? high : (start + 4);
      Collections.sort(a.subList(start,end));
      med[i] = a.get((start+end)/2);
      start = end+1;
    }
    Arrays.sort(med);
    return med[numM/2];
  }

  public int partition(ArrayList<Integer> a, int low, int high, int pivot){

    //search for pivot
    int pI =0;
    for (int i = low; i<=high ; i++) {
      if(a.get(i) == pivot){
        pI = i;
      }
    }
    swap(a,pI,high);
    int left = low-1;
    for (int i = low; i <=high-1 ; i++) {
      if(a.get(i) < pivot ){
        left++;
        swap(a,left,i);
      }
    }
    swap(a,left+1,high);
    return left+1;
  }

  public void swap(ArrayList<Integer> a, int i, int j){
    int temp = a.get(i);
    a.set(i,a.get(j));
    a.set(j,temp);
  }
}
