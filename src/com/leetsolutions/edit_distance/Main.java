package com.leetsolutions.edit_distance;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * https://www.spoj.com/problems/EDIST/
 *
 * Both players will play optimally. for n=1 player 1 wins. For n>1 let us assume player_1 is
 * going to lose. Hence after player_1 moves, it leads to a winning state. Assume player_1 picks
 * "1" and player_2 picks x which leads to a losing state. But if player 1 picks 'x', it removes
 * both 1 and x which leads to a losing state which is a contradiction. So player_1 always wins.
 *
 */
public class Main {

  public static void main(String[] args) {

    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);

    int t = in.readInt();
    for (int i = 0; i < t; i++) {

      String a = in.readString();
      int m = a.length();
      String b = in.readString();
      int n = b.length();

      int dp[][] = new int[m+1][n+1];

      for (int j = 0; j <=m; j++) {

        for (int k = 0; k <=n ; k++) {
          if(j==0) {
            dp[j][k]= k;
          }
          else if(k ==0){
            dp[j][k] = j;
          }
          else{

            if(a.charAt(j-1) == b.charAt(k-1)){
              dp[j][k] = dp[j-1][k-1];
            }
            else{

              dp[j][k] = dp[j-1][k] + 1;

              dp[j][k] = Math.min(dp[j][k], dp[j][k-1]+1);

              dp[j][k] = Math.min(dp[j][k], dp[j-1][k-1] +1);
            }

          }
        }
      }

      pw.println(dp[m][n]);

    }

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
