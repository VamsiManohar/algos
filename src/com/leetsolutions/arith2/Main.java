package com.leetsolutions.arith2;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;

public class Main {

  static long mod = 1000000007;

  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(System.in);
    PrintWriter pw = new PrintWriter(System.out);
    int t = Integer.parseInt(in.nextLine());
    for (int i = 0; i < t; i++) {
      in.nextLine();
      String s = in.nextLine();
      s = s.replaceAll("=", "");
      String[] x = s.split("\\s+");
      BigInteger res = BigInteger.valueOf(0);
      char op = '+';
      for (String a : x) {
        if (a.length() == 0) {
          continue;
        }
        if ("+".equals(a)) {
          op = '+';
        } else if ("-".equals(a)) {
          op = '-';
        } else if ("*".equals(a)) {
          op = '*';
        } else if ("/".equals(a)) {
          op = '/';
        } else {
          BigInteger v = new BigInteger(a);
          switch (op) {
            case '+':
              res = res.add(v);
              break;
            case '-':
              res = res.subtract(v);
              break;
            case '*':
              res = res.multiply(v);
              break;
            case '/':
              res = res.divide(v);
              break;
          }
        }
      }
      pw.println(res);
    }
    pw.close();
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

  static class Reader {

    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
      din = new DataInputStream(System.in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
      din = new DataInputStream(new FileInputStream(file_name));
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
      byte[] buf = new byte[64]; // line length
      int cnt = 0, c;
      while ((c = read()) != -1) {
        if (c == '\n') {
          break;
        }
        buf[cnt++] = (byte) c;
      }
      return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
      int ret = 0;
      byte c = read();
      while (c <= ' ') {
        c = read();
      }
      boolean neg = (c == '-');
      if (neg) {
        c = read();
      }
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');
      if (neg) {
        return -ret;
      }
      return ret;
    }

    public long nextLong() throws IOException {
      long ret = 0;
      byte c = read();
      while (c <= ' ') {
        c = read();
      }
      boolean neg = (c == '-');
      if (neg) {
        c = read();
      }
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');
      if (neg) {
        return -ret;
      }
      return ret;
    }

    public double nextDouble() throws IOException {
      double ret = 0, div = 1;
      byte c = read();
      while (c <= ' ') {
        c = read();
      }
      boolean neg = (c == '-');
      if (neg) {
        c = read();
      }
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');
      if (c == '.') {
        while ((c = read()) >= '0' && c <= '9') {
          ret += (c - '0') / (div *= 10);
        }
      }
      if (neg) {
        return -ret;
      }
      return ret;
    }

    private void fillBuffer() throws IOException {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1) {
        buffer[0] = -1;
      }
    }

    private byte read() throws IOException {
      if (bufferPointer == bytesRead) {
        fillBuffer();
      }
      return buffer[bufferPointer++];
    }

    public void close() throws IOException {
      if (din == null) {
        return;
      }
      din.close();
    }
  }


}