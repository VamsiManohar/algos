package com.leetsolutions.mse06h;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;


/**
 *
 * Problem link <a>https://www.spoj.com/problems/MSE06H/</a>
 *
 * Find the crossings of superhighways from west to east.
 *
 * (x,y) bridge can cross (a,b) bridge only if (x > a and y < b) or (x<a and y>b)
 *
 * if we sort bridges based on x and then on y. We have to deal with only one case x > a and y <b.
 *
 *
 * Number of crossings with kth bridge(xk, yk) in sorted order will be number of bridges which
 * are greater than yK till k-1 bridges.
 *
 * Total Number of bridges on east coast M. (max 1000)
 *
 * Total Number of bridges on west coast N. (max 1000)
 *
 * we  can get cumulative sum of bridges with y index till yk then cum(M)-cum(yk)
 *
 * Time Complexity : Sort of bridges - O(klogk)
 *
 * Finding crossings will take klogM.
 *
 * Total time complexity will eb O(klogk + klogN)
 *
 *
 *
 */
public class Main {

  static long mod = 1000000007;

  public static void main(String[] args) throws Exception {
    Reader in = new Reader();
    PrintWriter pw = new PrintWriter(System.out);

    int t = in.nextInt();

    for (int i = 0; i < t; i++) {

      int m = in.nextInt();

      int n = in.nextInt();

      int k = in.nextInt();

      List<Bridge> bridges = new ArrayList<>();

      for (int j = 0; j < k; j++) {
        bridges.add(new Bridge(in.nextInt(), in.nextInt()));
      }

      Collections.sort(bridges);

      FenwickTree fenwickTree = new FenwickTree(n);
      int result = 0;
      for (int j = 0; j < k; j++) {

        Bridge bridge = bridges.get(j);
        int index = bridge.west;
        result += fenwickTree.get(n) - fenwickTree.get(index);
        fenwickTree.update(index, 1);

      }

      pw.println("Test case "+(i+1) + ": " + result);
    }


    pw.close();
  }

  static class Reader
  {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader()
    {
      din = new DataInputStream(System.in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException
    {
      din = new DataInputStream(new FileInputStream(file_name));
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException
    {
      byte[] buf = new byte[64]; // line length
      int cnt = 0, c;
      while ((c = read()) != -1)
      {
        if (c == '\n')
          break;
        buf[cnt++] = (byte) c;
      }
      return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException
    {
      int ret = 0;
      byte c = read();
      while (c <= ' ')
        c = read();
      boolean neg = (c == '-');
      if (neg)
        c = read();
      do
      {
        ret = ret * 10 + c - '0';
      }  while ((c = read()) >= '0' && c <= '9');

      if (neg)
        return -ret;
      return ret;
    }

    public long nextLong() throws IOException
    {
      long ret = 0;
      byte c = read();
      while (c <= ' ')
        c = read();
      boolean neg = (c == '-');
      if (neg)
        c = read();
      do {
        ret = ret * 10 + c - '0';
      }
      while ((c = read()) >= '0' && c <= '9');
      if (neg)
        return -ret;
      return ret;
    }

    public double nextDouble() throws IOException
    {
      double ret = 0, div = 1;
      byte c = read();
      while (c <= ' ')
        c = read();
      boolean neg = (c == '-');
      if (neg)
        c = read();

      do {
        ret = ret * 10 + c - '0';
      }
      while ((c = read()) >= '0' && c <= '9');

      if (c == '.')
      {
        while ((c = read()) >= '0' && c <= '9')
        {
          ret += (c - '0') / (div *= 10);
        }
      }

      if (neg)
        return -ret;
      return ret;
    }

    private void fillBuffer() throws IOException
    {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1)
        buffer[0] = -1;
    }

    private byte read() throws IOException
    {
      if (bufferPointer == bytesRead)
        fillBuffer();
      return buffer[bufferPointer++];
    }

    public void close() throws IOException
    {
      if (din == null)
        return;
      din.close();
    }
  }

  public static class FenwickTree {

    private int[] bit;

    public FenwickTree(int len) {
      bit = new int[len+1];
    }

    public void update(int index, int value) {

      while (index < bit.length){
        bit[index] += value;
        index += (index&-index);
      }

    }

    public int get(int x) {
      int result = 0;
      while (x > 0) {
        result += bit[x];
        x -= x&-x;
      }
      return result;
    }
  }

  public static class Bridge implements Comparable<Bridge> {

    int east,west;

    Bridge(int east, int west){
      this.east = east;
      this.west = west;
    }

    @Override
    public boolean equals(Object obj) {
      if(obj instanceof Bridge){
        Bridge b = (Bridge) obj;
        return this.east == b.east && this.west == b.west;
      }
      else{
        return false;
      }
    }


    @Override
    public int hashCode() {
      return 31 * new Integer(east).hashCode() +  new Integer(west).hashCode();
    }

    @Override
    public int compareTo(Bridge o) {

      if(this.east != o.east) {
        return Integer.compare(this.east, o.east);
      }
      else {
        return Integer.compare(this.west, o.west);
      }
    }


  }

  public static long gcd(long x, long y) {
    if (x % y == 0) {
      return y;
    } else {
      return gcd(y, x % y);
    }
  }

  public static long pow(long n, long p, long m) {
    long result = 1;
    if (p == 0) {
      return 1;
    }
    if (p == 1) {
      return n;
    }
    while (p != 0) {
      if (p % 2 == 1) {
        result *= n;
      }
      if (result >= m) {
        result %= m;
      }
      p >>= 1;
      n *= n;
      if (n >= m) {
        n %= m;
      }
    }
    return result;
  }

  static class Pair implements Comparable<Pair> {

    int a, b;

    Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }

    public int compareTo(Pair o) {
      // TODO Auto-generated method stub
      if (this.a != o.a) {
        return Integer.compare(this.a, o.a);
      } else {
        return Integer.compare(this.b, o.b);
      }
      // return 0;
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