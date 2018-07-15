package com.leetsolutions.and_rounds;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 *
 *
 */
public class Main {

  public static void main(String[] args) {

    InputReader in = new InputReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);

    int t = in.readInt();
    for (int i = 0; i < t; i++) {

      int n = in.readInt();

      int k = in.readInt();

      int[] arr = new int[n];

      for (int j = 0; j < n; j++) {
        arr[j] = in.readInt();
      }
      int[] result = new int[n];

      for (int j = 0; j < 32; j++) {

        int [] bit = new int[n];

        bit[0] = ((arr[0] & (1 << j)) > 0 ? 1 : 0);
        for (int l = 1; l < n; l++) {
          bit[l] = bit[l-1] + ((arr[l] & (1 << j)) > 0 ? 1 : 0);
        }

        for (int l = 0; l < n; l++) {
          if(k >= n) {
            if(bit[n-1] == n){
              result[l] = result[l] | (1 << j);
            }
          }
          else {
            int nextKSum  = 0;
            if(l + k > n-1){
              int last = (l + k)%n;
              nextKSum = rangeSum(bit, l, n-1) + rangeSum(bit, 0, last);
            }
            else{
              nextKSum = rangeSum(bit, l, l+ k);
            }

            int prevkSum  = 0;

            if(l - k < 0 ){
              prevkSum = rangeSum(bit, 0, l) + rangeSum(bit, n-(k-l) , n-1);
            }
            else{
              prevkSum = rangeSum(bit, l-k, l);
            }

            if((prevkSum == k + 1) && (nextKSum == k+1)){
              result[l] = result[l] | (1 << j);
            }
          }
        }

      }

      for (int j = 0; j < n; j++) {
        if(j != n-1) {
          pw.print(result[j] + " ");
        }
        else {
          pw.print(result[j]);
        }
      }
      pw.println();
    }

    pw.close();
  }

  private static int rangeSum(int[] arr, int l, int r) {
    if(l-1 >= 0){
      return arr[r] - arr[l-1];
    }
    else{
      return arr[r];
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
