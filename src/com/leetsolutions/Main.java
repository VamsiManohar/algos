package com.leetsolutions;


import com.leetsolutions.sudoku.BackTrackingSudokuSolver;
import com.leetsolutions.sudoku.DancingLinksSudokuSolver;
import com.sun.tools.corba.se.idl.constExpr.Equal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
    //solveSudokuMetrics();
    DamerauLevenshteinAlgorithm d = new DamerauLevenshteinAlgorithm(1,1,1,1);

    System.out.println(d.execute("bc", "cab"));

  }


  private static  int damerauLevenshteinDistance(String s, String t) {

    int m = s.length();
    int n = t.length();
    HashMap<Character, Integer> charDictionary = new HashMap<>();

  /* For all i and j, d[i][j] holds the Damerau-Levenshtein distance
   * between the first i characters of s and the first j characters of t.
   * Note that the array has (m+1)x(n+1) values.
   */
    int d[][] = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) {
      d[i][0] = i;
    }
    for (int j = 0; j <= n; j++) {
      d[0][j] = j;
    }

    // Populate a dictionary with the alphabet of the two strings
    for (int i = 0; i < m; i++) {
      charDictionary.put(s.charAt(i),0);
    }
    for (int j = 0; j < n; j++) {
      charDictionary.put(t.charAt(j),0);
    }

    // Determine substring distances
    for (int i = 1; i <= m; i++) {
      int db = 0;
      for (int j = 1; j <= n; j++) {
        int i1 = charDictionary.get(t.charAt(j-1));
        int j1 = db;
        int cost = 0;

        if (s.charAt(i-1) == t.charAt(j-1)) { // Subtract one to start at strings' index zero instead of index one
          db = j;
        } else {
          cost = 1;
        }
        d[i][j] = Math.min(d[i][j-1] + 1,                 // insertion
            Math.min(d[i-1][j] + 1,        // deletion
                d[i-1][j-1] + cost)); // substitution
        if(i1 > 0 && j1 > 0) {
          d[i][j] = Math.min(d[i][j], d[i1-1][j1-1] + (i-i1-1) + (j-j1-1) + 1); //transposition
        }
      }
      charDictionary.put(s.charAt(i-1),i);
    }

    // Return the strings' distance
    return d[m][n];
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
