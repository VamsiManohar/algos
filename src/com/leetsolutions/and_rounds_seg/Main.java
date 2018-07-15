package com.leetsolutions.and_rounds_seg;

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

      int[] arr = new int[3*n];

      for (int j = 0; j < n; j++) {
        arr[j] = arr[n+j] = arr[2*n+j] = in.readInt();
      }

      SegmentTree segTree = new SegmentTree(arr);

      if(2*k+1 >= n){
        k = n;
      }
      for (int j = 0; j < n; j++) {
        if(j == n-1){
          pw.println(segTree.query(n+j-k, n+j+k));
        }
        else{
          pw.print(segTree.query(n+j-k, n+j+k) + " ");
        }
      }
      pw.println();
    }

    pw.close();
  }

  public static class SegmentTree {

    private int[] seg;
    private int len;

    public SegmentTree(int[] k) {
      int len = k.length;
      this.len = k.length;

      int maxHeight = (int)Math.ceil(Math.log(len)/Math.log(2));
      int maxLength = (int)Math.pow(2, maxHeight+1) -1;
      seg = new int[maxLength];
      build(k, 0, k.length-1, 0);
    }

    private void build(int[] arr,int l, int r , int pos){

      if(l == r) {
        seg[pos] = arr[l];
      }
      else {
        int mid = (l + r) >>> 1;
        build(arr, l, mid, 2 * pos + 1);
        build(arr, mid + 1, r, 2 * pos + 2);
        seg[pos] = seg[2 * pos + 1] & seg[2 * pos + 2];
      }
    }

    public int query(int l, int r) {
      return query(0, this.len-1, l, r, 0);
    }

    private int query(int start, int end, int l, int r, int pos){

      if(l<= start && r >= end){
        return seg[pos];
      }

      if(l > end || r < start){
        return Integer.MAX_VALUE;
      }

      int mid = (start + end) >>> 1;
      int left = query(start, mid, l, r, 2*pos+1);
      int right = query(mid+1, end, l, r, 2*pos+2);
      return left & right;
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
