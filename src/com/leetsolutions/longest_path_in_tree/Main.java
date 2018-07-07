package com.leetsolutions.longest_path_in_tree;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;


/**
 * https://www.spoj.com/problems/PT07Z/
 *
 * Lesson Learnt never use scanner and system.out for JAVA.
 *
 * There are two solutions for this first using two dfs.
 * In first dfs take any vertex as source and find the farthest vertex v1. Now v1 will be at the
 * end of the longest path. Now take v1 as source and find the longest path from it using dfs.
 *
 * Proof of correctness. Let us assume s-v1, s-v2 be the distance from s to the longest path
 * start point v1 and end point v2. Without loss of generality s-v1 > s-v2, then v1 will be the
 * farthest vertex from s because if there exists another vertex y such that s-y > s-v1 the y-v1 >
 * v1-v2 which is a contradiction. (In both the cases when s lies on longest path and s doesn't
 * lie on longest path.)
 *
 *
 * Second Solution : Inspired from finding the diameter of a binary tree.
 *
 * Take any vertex as 's'.
 *
 * Let max1 be max height of child of s.
 *
 * max2 be max height of child of s.
 *
 * maxPath be the longest among all childs.
 *
 * Then longest path = Math.max(max1 + max2+ 2, maxPath);
 *
 * leaf nodes have a height of 0.
 *
 */
public class Main {

  public static void main(String[] args) {

    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);

    int n = in.readInt();

    HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();

    for (int i = 1; i < n; i++) {

      int u = in.readInt();
      int v = in.readInt();

      if(adjList.containsKey(u)) {
        adjList.get(u).add(v);
      }
      else {
        adjList.put(u, new ArrayList<Integer>());
        adjList.get(u).add(v);
      }


      if(adjList.containsKey(v)) {
        adjList.get(v).add(u);
      }
      else {
        adjList.put(v, new ArrayList<Integer>());
        adjList.get(v).add(u);
      }

    }

    boolean[] visited = new boolean[n+1];
    Result result = maxHeight(1, adjList, visited);
    pw.println(result.maxPath);
    pw.close();
  }

  public static Result maxHeight(int s, HashMap<Integer, ArrayList<Integer>> adjMap, boolean[]
      visited) {

    Result result = new Result();
    visited[s] = true;
    ArrayList<Integer> adjList = adjMap.get(s);

    Integer max1=-1, max2=-1;
    Integer maxPath = 0;
    for(Integer adj: adjList){

      if(!visited[adj]){
        Result r = maxHeight(adj, adjMap, visited);
        if(r.maxHeight >= max1 ) {
          max2 = max1;
          max1 = r.maxHeight;
        }
        else if(r.maxHeight > max2){
          max2 = r.maxHeight;
        }

        if(r.maxPath > maxPath){
          maxPath = r.maxPath;
        }
      }
    }
    result.maxHeight = max1 + 1;
    result.maxPath = Math.max(max1 + max2 + 2, maxPath);

    return result;
  }

  public static class Result{

    public int maxPath;

    public int maxHeight;

  }

  static class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
      this.stream = stream;
    }

    public int read() {
      if (numChars == -1) {
        throw new InputMismatchException();
      }
      if (curChar >= numChars) {
        curChar = 0;
        try {
          numChars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (numChars <= 0) {
          return -1;
        }
      }
      return buf[curChar++];
    }

    public int readInt() {
      int c = read();
      while (isSpaceChar(c)) {
        c = read();
      }
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      int res = 0;
      do {
        if (c < '0' || c > '9') {
          throw new InputMismatchException();
        }
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public String readString() {
      int c = read();
      while (isSpaceChar(c)) {
        c = read();
      }
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isSpaceChar(c));
      return res.toString();
    }

    public double readDouble() {
      int c = read();
      while (isSpaceChar(c)) {
        c = read();
      }
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      double res = 0;
      while (!isSpaceChar(c) && c != '.') {
        if (c == 'e' || c == 'E') {
          return res * Math.pow(10, readInt());
        }
        if (c < '0' || c > '9') {
          throw new InputMismatchException();
        }
        res *= 10;
        res += c - '0';
        c = read();
      }
      if (c == '.') {
        c = read();
        double m = 1;
        while (!isSpaceChar(c)) {
          if (c == 'e' || c == 'E') {
            return res * Math.pow(10, readInt());
          }
          if (c < '0' || c > '9') {
            throw new InputMismatchException();
          }
          m /= 10;
          res += (c - '0') * m;
          c = read();
        }
      }
      return res * sgn;
    }

    public long readLong() {
      int c = read();
      while (isSpaceChar(c)) {
        c = read();
      }
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      long res = 0;
      do {
        if (c < '0' || c > '9') {
          throw new InputMismatchException();
        }
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public boolean isSpaceChar(int c) {
      if (filter != null) {
        return filter.isSpaceChar(c);
      }
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
      return readString();
    }

    public interface SpaceCharFilter {

      public boolean isSpaceChar(int ch);
    }
  }

}
