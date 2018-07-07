package com.leetsolutions.aggresive_cows;


import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.spoj.com/problems/AGGRCOW/
 *
 * Algorithm :  We have to find the maximum of "minimum largest difference between two cows" if c
 * cows are placed at n locations where each location lies between 0 and 1,000,000,000.
 *
 *  1 <= n <= 100,000
 *  0 <= loc <= 1,000,000,000
 *
 * Brute force solution : There can be n!/(c!*(n-c)!) ways to choose from the locations. In each
 * combination find the minimum difference and return the largest difference among all
 * combinations. Time complexity will be huge.
 *
 *
 * Lets define a function f(dist) which says if we can place c cows in n locations with a minimum
 * distance of dist. f(dist) can be found in O(n).
 * f(dist) is non decreasing function.
 * f(dist) is determined by using greedy algorithm by placing a cow initially at left most position
 * and add cows by maintaining a minimum distance of 'dist'.
 * Proof of Correctness for greedy : if solution doesn't exist even with our choices we couldn't
 * place c cows because we are ensuring a minimum distance of "dist"  from prev cow will give a
 * solution. If there exists a solution d1 d2 d3... dc then the above procedure will definitely
 * lead to a solution because we can always start with left most digit c1 because it only
 * increases the margin from d2, this argument continues with next numbers if we make a choice
 * before d2 it c1, c2, d3,...dc will form a solution.
 *
 *
 * Time complexity (n*log(max(dist))); 1 <= n <= 100000 0<=max(dist)<=1,000,000,000
 *
 */
public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    Integer t = sc.nextInt();

    for (int i = 0; i < t; i++) {

      Integer n = sc.nextInt();

      Integer c = sc.nextInt();

      int[] stalls = new int[n];

      for (int j = 0; j < n; j++) {
        stalls[j] = sc.nextInt();
      }

      //sort stall locations.
      Arrays.sort(stalls);

      System.out.println(
          binarySearch(0, stalls[n-1] - stalls[0], 1,-1, stalls, c));

    }

  }

  private static int binarySearch(int start, int end, int search, int bestPossible, int[] stalls,
      int cowsSize) {

    if(start > end) {
      return bestPossible;
    }
    else {
      int mid = start + (end -start)/2;
      if(isPossible(stalls, cowsSize, mid) == search) {
        return binarySearch(mid+1, end, search, mid, stalls, cowsSize);
      }
      else {
        return binarySearch(start, mid-1, search, bestPossible, stalls, cowsSize);
      }
    }
  }

  private static int  isPossible(int[] stalls, int cowsSize, int dist){
    int count =1;
    int prevPos = 0;
    for(int i =1; i<stalls.length; i++) {

      if(stalls[i] - stalls[prevPos] >= dist){
        count++;
        prevPos = i;
      }
      if(count == cowsSize ) {
        return 1;
      }
    }
    return 0;
  }


}

