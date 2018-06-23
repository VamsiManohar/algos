package com.leetsolutions;

import java.util.*;
import java.lang.*;
import java.io.*;

class NhaySpoj {

  public static InputReader in = new InputReader();
  public static OutputWriter out = new OutputWriter();

  public static void main(String[] args)
  {
    NhaySpoj nhaySpoj = new NhaySpoj();
    nhaySpoj.solve();
    out.flush();
    out.close();
  }

  public void solve() {
    while (true) {
      int patternLength;
      try{
        patternLength = in.nextInt();
      } catch (Exception e){
        break;
      }
      String pattern = in.nextLine();
      List<Integer> pT = getPrefixTable(pattern);
      Integer c = in.read();
      int x = 0;
      int p = 0;
      while (!in.isEndOfLine(c)){
        if(c == pattern.codePointAt(p)){
          if(p == patternLength -1){
            out.println(x-patternLength+1);
            p = pT.get(p);
          }
          else{
            p = p+1;
          }
          c = in.read();
          x = x+1;
        }
        else{
          if(p==0){
            c = in.read();
            x = x+1;
          }
          else{
            p = pT.get(p-1);
          }
        }
      }
      out.println();
    }
  }

  private List<Integer> getPrefixTable(String s){
    List<Integer> result = new ArrayList<>();
    int size = s.length();
    result.add(0);
    int y = 1;
    int x = 0;
    while (y<size){
      if(s.charAt(y) == s.charAt(x)){
        result.add(result.get(y-1) + 1);
        y = y+1;
        x= x+1;
      }
      else{
        if(x == 0){
          result.add(0);
          y = y+1;
        }
        else{
          x = result.get(x-1);
        }
      }
    }
    return result;
  }

  static class InputReader
  {
    private InputStream	inputStream;
    private byte[]		buf	= new byte[1024];
    private int			curChars;
    private int			numChars;

    public InputReader()
    {
      this(System.in);
    }

    public InputReader(InputStream iStream)
    {
      inputStream = iStream;
    }

    public int read()
    {
      if (numChars == -1) throw new InputMismatchException();
      if (curChars >= numChars) {
        curChars = 0;
        try {
          numChars = inputStream.read(buf);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        if (numChars <= 0) return -1;
      }
      return buf[curChars++];
    }

    public String nextLine()
    {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      StringBuilder builder = new StringBuilder();
      do {
        builder.appendCodePoint(c);
        c = read();
      }
      while (!isEndOfLine(c));
      return builder.toString();
    }

    public String nextString()
    {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      StringBuilder builder = new StringBuilder();
      do {
        builder.appendCodePoint(c);
        c = read();
      }
      while (!isSpaceChar(c));
      return builder.toString();
    }

    public int nextInt()
    {
      int res = 0;
      int sgn = 1;
      int c = read();
      if (isEndOfLine(c))
        throw new NoSuchElementException("No more testcase");
      while (isSpaceChar(c))
        c = read();
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      do {
        if (c < '0' || c > '9') throw new InputMismatchException();
        res = res * 10 + (c - '0');
        c = read();
      }
      while (!isSpaceChar(c));
      return res * sgn;
    }

    public double nextDouble()
    {
      double ip = 0.0, dp = 0.0, res = 0.0, track = 1;
      int c = read();
      int sgn = 1;
      boolean dpDetect = false;
      while (isSpaceChar(c))
        c = read();
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      do {
        if (!((c >= '0' && c <= '9') || (c == '.' && !dpDetect)))
          throw new InputMismatchException();
        if (!dpDetect) {
          if (c == '.') dpDetect = true;
          else ip = ip * 10 + (c - '0');
        }
        else {
          dp = dp * 10 + (c - '0');
          track *= 10;
        }
        c = read();
      }
      while (!isSpaceChar(c));
      res = (dp / track) + ip;
      return res * sgn;
    }

    public Long nextLong()
    {
      long res = 0;
      int sgn = 1;
      int c = read();
      while (isSpaceChar(c))
        c = read();
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      do {
        if (c < '0' || c > '9') throw new InputMismatchException();
        res = res * 10 + (c - '0');
        c = read();
      }
      while (!isSpaceChar(c));
      return res * sgn;
    }

    public boolean isSpaceChar(int c)
    {
      return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
    }

    public boolean isEndOfLine(int c)
    {
      return c == '\n' || c == '\r' || c == -1;
    }
  }

  static class OutputWriter
  {
    private PrintWriter	printWriter;

    public OutputWriter()
    {
      this(System.out);
    }

    public OutputWriter(OutputStream outputStream)
    {
      printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer)
    {
      printWriter = new PrintWriter(writer);
    }

    public void print(Object... objects)
    {
      for (int i = 0; i < objects.length; i++) {
        if (i != 0) printWriter.print(' ');
        printWriter.print(objects[i]);
      }
    }

    public void println(Object... objects)
    {
      print(objects);
      printWriter.println();
    }

    public void close()
    {
      printWriter.close();
    }

    public void flush()
    {
      printWriter.flush();
    }

  }
}