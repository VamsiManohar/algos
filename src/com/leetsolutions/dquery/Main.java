package com.leetsolutions.dquery;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;


/**
 * problem link : https://www.spoj.com/problems/DQUERY/
 *
 * Mo's Algorithm. Sort queries and process will lead to
 *
 */
public class Main {

  static long mod = 1000000007;

  public static void main(String[] args) throws Exception {
    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);

    int n = in.readInt();
    int blockSize = (int) Math.sqrt(n);
    int[] arr = new int[n];

    int[] count = new int[300000];

    for (int i = 0; i < n; i++) {
      arr[i] = in.readInt();
    }

    int q = in.readInt();

    Pair[] qrs = new Pair[q];
    Pair[] sortQrs = new Pair[q];
    for (int i = 0; i <q ; i++) {
      Pair p = new Pair(in.readInt(), in.readInt(), blockSize);
      qrs[i] = p;
      sortQrs[i] = p;
    }

    Arrays.sort(sortQrs);

    int runningLeft = 0;
    int runningRight = -1;
    int currentSum = 0;
    int i =0;
    while(i<q){
      Pair p = sortQrs[i];

      int left = p.a-1;
      int right = p.b-1;

      if(runningLeft > left ){
        for (int j = left; j< runningLeft ; j++) {
          count[arr[j]]++;
          if(count[arr[j]] == 1){
            currentSum++;
          }
        }

      }

      if(runningLeft < left){
        for (int j = runningLeft; j < left; j++) {
          count[arr[j]]--;
          if(count[arr[j]] == 0){
            currentSum--;
          }
        }
      }

      if(runningRight > right) {
        for (int j = right+1; j <=runningRight ; j++) {
          count[arr[j]]--;
          if(count[arr[j]] == 0){
            currentSum--;
          }
        }
      }

      if(runningRight < right) {
        for (int j = runningRight+1; j <=right ; j++) {
          count[arr[j]]++;
          if(count[arr[j]] == 1){
            currentSum++;
          }
        }
      }

      runningLeft  = left;
      runningRight = right;
      p.result = currentSum;
      i++;
    }

    for (int j = 0; j < qrs.length; j++) {
      pw.println(qrs[j].result);
    }


    pw.close();
  }


  static class Pair implements Comparable<Pair> {

    int a, b, blockSize, result;

    Pair(int a, int b, int blockSize) {
      this.a = a;
      this.b = b;
      this.blockSize = blockSize;
    }

    public int compareTo(Pair o) {


      if ((this.a/blockSize) != (o.a/blockSize)) {
        return Integer.compare((this.a/blockSize), (o.a/blockSize));
      } else {
        return Integer.compare(this.b, o.b);
      }
    }

    public boolean equals(Object o) {
      if (o instanceof Pair) {
        Pair p = (Pair) o;
        return p.a == a && p.b == b;
      }
      return false;
    }

    public int hashCode() {
      return new Integer(a).hashCode() * 31 + new Integer(b).hashCode();
    }
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