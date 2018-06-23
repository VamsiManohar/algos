package com.leetsolutions;


import com.leetsolutions.sudoku.BackTrackingSudokuSolver;
import com.leetsolutions.sudoku.DancingLinksSudokuSolver;
import com.sun.tools.corba.se.idl.constExpr.Equal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
//    BinarySearch binarySearch = new BinarySearch();
//    int[] arr = {1, 2, 8, 10, 10, 12, 19};
//    System.out.println(binarySearch.uBinarySearch(arr, 0, arr.length-1, 67));
//
//    System.out.println(binarySearch.dFirstBinarySearch(arr, 0, arr.length-1, 67, -1));
//
//    System.out.println(binarySearch.dLastBinarySearch(arr, 0, arr.length-1, 67, -1));
//
//    System.out.println(binarySearch.floor(arr, 0, arr.length-1, 1, -1));
//
//    System.out.println(binarySearch.ceiling(arr, 0, arr.length-1, 1, -1));

//    Kmp kmp= new Kmp();
//    System.out.println(kmp.getPrefixTable("babb$bbab"));

//    Median median = new Median();
//    System.out.println(median.kOrder(new ArrayList<>(Arrays.asList(7, 10, 4, 3, 20, 15, 18)),7));

//      SortedArrays sortedArrays = new SortedArrays();
//      System.out.println(sortedArrays.median(new ArrayList<>(Arrays.asList(-49, 33, 35, 42 )),new
//          ArrayList<>(Arrays.asList( -26))));
//    System.out.println(new Main().isPalindrome("A man, a plan, a canal: Panama"));

//    MinXorTrie minXorTrie = new MinXorTrie();
//    System.out.println(minXorTrie.minXor(new ArrayList<>(Arrays.asList(0,4,7,9 ))));

//    DivisionBit divisionBit = new DivisionBit();
//    System.out.println(divisionBit.divide(-1, 1));

//    System.out.println(removeElement(new ArrayList<>(Arrays.asList(4,1,1,2,1,3)), 1));

//    MaxOne maxOne = new MaxOne();
//    System.out.println(maxOne.maxone(new ArrayList<>(Arrays.asList( 1, 0, 1, 0, 1, 1, 1, 1, 0, 0,
//        0, 0, 1, 1, 1, 1, 0 )), 4));

//    int i =1;
//    ListNode head = new ListNode(1);
//    ListNode a = head;
//    while (i <4){
//      i++;
//      head.next = new ListNode(i);
//      head = head.next;
//    }
//    Haha haha = new Haha();
//    System.out.println(haha.reverseList(a,2));
//    String[] paths = "/home//sss".split("\\/");
//    for (int i = 0; i < paths.length; i++) {
//      System.out.println(paths[i]);
//    }

//      SubSets subSets = new SubSets();
//      ArrayList<ArrayList<Integer>> subSetList = subSets.generate(new ArrayList<>(Arrays.asList
//          (3,3,3,5)));
//      System.out.println(subSetList.size() + ":" + subSetList.toString());

//    EqualAverage equalAverage = new EqualAverage();
//    ArrayList<ArrayList<Integer>> result = equalAverage.avgset(new ArrayList<>(Arrays.asList(47, 14, 30, 19, 30, 4, 32, 32, 15, 2, 6, 24)));
//    for (ArrayList<Integer> a: result) {
//      System.out.println(a);
//    }
//    Scanner scanner = new Scanner(System.in);
//    int[][] board = new int[9][9];
//    for (int i = 0; i < 9; i++) {
//      for(int j =0; j<9; j++) {
//        board[i][j] = scanner.nextInt();
//      }
//    }
//
//    BackTrackingSudokuSolver backTrackingSudokuSolver = new BackTrackingSudokuSolver(board);
//    backTrackingSudokuSolver.solve();
//
//    for (int i = 0; i < 9; i++) {
//
//      for (int j = 0; j < 9; j++) {
//
//        System.out.print(board[i][j] + " ");
//
//      }
//      System.out.println("");
//    }
    //solveSudokuUsingDancingLinks();
    solveSudokuMetrics();
  }

  public static void solveSudokuUsingDancingLinks() {
    DancingLinksSudokuSolver dancingLinksSudokuSolver = new DancingLinksSudokuSolver(
        scanSudokuBoard());
    dancingLinksSudokuSolver.solve();
  }

  public static void solveSudokuMetrics() {
    int[][] board = scanSudokuBoard();
    Long startTime = new Date().getTime();
    DancingLinksSudokuSolver dancingLinksSudokuSolver = new DancingLinksSudokuSolver(board);
    dancingLinksSudokuSolver.solve();
    Long danceTime = new Date().getTime();
    System.out.println("Dancing time : " + (danceTime - startTime) + "ms");

//    BackTrackingSudokuSolver backTrackingSudokuSolver = new BackTrackingSudokuSolver(board);
//    backTrackingSudokuSolver.solve();
//    System.out.println("Backtrack time : " + (new Date().getTime() - danceTime) + "ms");

  }

  public static int[][] scanSudokuBoard() {
    Scanner scanner = new Scanner(System.in);
    int[][] board = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for(int j =0; j<9; j++) {
        board[i][j] = scanner.nextInt();
      }
    }
    return board;
  }



  public static int removeDuplicates(ArrayList<Integer> a) {
    int j =0;
    int i =0;
    while(i < a.size()-1){

      if(!a.get(i).equals(a.get(i+1))){
        a.set(j, a.get(i));
        j++;
      }
      else{
        i++;
      }

    }
    a.set(j, a.get(a.size()-1));
    return j+1;
  }

  public static int removeElement(ArrayList<Integer> a, int b) {
    int j = a.size()-1;
    int i = 0;
    while(i < j) {

      if(a.get(i) == b) {
        swap(a, i, j);
        j--;
      }
      else{
        i++;
      }
    }
    return j++;
  }

  private static void  swap(ArrayList<Integer> a, int i, int j) {
    int temp = a.get(i);
    a.set(i, a.get(j));
    a.set(j, temp);
  }
    public int isPalindrome(String A) {
      int low = 0;
      int high = A.length()-1;
      while(low <= high){
        if(!isCharOrDigit(A.charAt(low))){
          low++;
          continue;
        }
        if(!isCharOrDigit(A.charAt(high))){
          high--;
          continue;
        }
        if(isChar(A.charAt(low)) && isChar(A.charAt(high)) ){
          if(Character.toLowerCase(A.charAt(low)) == Character.toLowerCase(A.charAt(high))){
            low++;
            high--;
          }
          else{
            break;
          }
        }
        else if(isDigit(A.charAt(low)) && isDigit(A.charAt(high)) ){
          if(A.charAt(low) == A.charAt(high)){
            low++;
            high--;
          }
          else{
            break;
          }
        }
        else{
          break;
        }

      }

      if(low > high){
        return 1;
      }
      else{
        return 0;
      }

    }


    public boolean isCharOrDigit(char c){
      if(c >= 'a' && c <= 'z' ){
        return true;
      }
      if(c >= 'A' && c <= 'Z'){
        return true;
      }
      if(c >= '0' && c <= '9'){
        return true;
      }
      return false;
    }


    public boolean isChar(char c){
      if(c >= 'a' && c <= 'z' ){
        return true;
      }
      if(c >= 'A' && c <= 'Z'){
        return true;
      }
      return false;
    }


    public boolean isDigit(char c){
      if(c >= '0' && c <= '9'){
        return true;
      }
      return false;
    }



}
