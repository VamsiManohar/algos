package com.leetsolutions.mcoins;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Main {

  public static void main(String[] args) {

    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);


    int[] steps = new int[3];
    steps[0]  = 1;
    steps[1] = in.readInt();
    steps[2] = in.readInt();
    int m = in.readInt();
    int[] queries = new int[m];

    int max = Integer.MIN_VALUE;
    for (int j = 0; j < m; j++) {
      queries[j] = in.readInt();
      max = Math.max(max, queries[j]);
    }

    int[] dp = new int[max+1];
    dp[0] = 2;
    for (int j = 1; j <= max; j++) {
      boolean isFound = false;
      for (int k = 0; k <3 ; k++) {
        if(j >= steps[k] && dp[j-steps[k]] == 2){
          isFound = true;
          break;
        }
      }

      if(isFound){
        dp[j] = 1;
      }
      else {
        dp[j] = 2;
      }
    }
    StringBuilder result = new StringBuilder();

    for (int j = 0; j < queries.length; j++) {
      if(dp[queries[j]] == 1) {
         result.append("A");
      }
      else{
        result.append("B");
      }
    }

    pw.println(result.toString());

    pw.close();
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
