package com.leetsolutions.buglife;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;

public class Main {

  private static Integer numV;
  private static Integer[] color;
  private static boolean[] visited;
  private static ArrayList<ArrayList<Integer>> edgeMap;

  public static void main(String[] args) throws Exception {
    Reader in = new Reader();
    PrintWriter pw = new PrintWriter(System.out);

    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      numV = in.nextInt();
      int numE = in.nextInt();
      color = new Integer[numV+1];
      visited = new boolean[numV+1];
      edgeMap = new ArrayList<>();
      for (int j = 0; j <=numV; j++) {
        edgeMap.add(new ArrayList<Integer>());
      }
      for (int j = 0; j < numE; j++) {
        int u = in.nextInt();
        int v = in.nextInt();
        edgeMap.get(u).add(v);
        edgeMap.get(v).add(u);
      }
      pw.println("Scenario #"+(i+1)+":");

      boolean ok = true;
      for (int k = 1; k <= numV; k++) {
        if(!visited[k]){
          color[k] = 1;
          visited[k]= true;
          if(!color(k))
            ok=false;
        }
      }

      if(ok) {
        pw.println("No suspicious bugs found!");
      }
      else{
        pw.println("Suspicious bugs found!");
      }
    }

    pw.close();
  }

  private static boolean color(int source) {

    ArrayList<Integer> adj = edgeMap.get(source);
    if(adj == null){
      return true;
    }
    int oppColor = getOppositeColor(color[source]);
    for (Integer v: adj) {

      if(visited[v]){
        if(color[v] != oppColor){
          return false;
        }
      }
      else{
        color[v] = oppColor;
        visited[v] = true;
        if(!color(v)){
          return false;
        }
      }
    }
    return true;
  }

  private static int getOppositeColor(int color){
    if(color == 1){
      return 2;
    }
    else{
      return 1;
    }
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

  /** Faster input **/
  static class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    public Reader(){
      din=new DataInputStream(System.in);
      buffer=new byte[BUFFER_SIZE];
      bufferPointer=bytesRead=0;
    }

    public Reader(String file_name) throws IOException{
      din=new DataInputStream(new FileInputStream(file_name));
      buffer=new byte[BUFFER_SIZE];
      bufferPointer=bytesRead=0;
    }

    public String readLine() throws IOException{
      byte[] buf=new byte[64]; // line length
      int cnt=0,c;
      while((c=read())!=-1){
        if(c=='\n')break;
        buf[cnt++]=(byte)c;
      }
      return new String(buf,0,cnt);
    }

    public int nextInt() throws IOException{
      int ret=0;byte c=read();
      while(c<=' ')c=read();
      boolean neg=(c=='-');
      if(neg)c=read();
      do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
      if(neg)return -ret;
      return ret;
    }

    public long nextLong() throws IOException{
      long ret=0;byte c=read();
      while(c<=' ')c=read();
      boolean neg=(c=='-');
      if(neg)c=read();
      do{ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
      if(neg)return -ret;
      return ret;
    }

    public double nextDouble() throws IOException{
      double ret=0,div=1;byte c=read();
      while(c<=' ')c=read();
      boolean neg=(c=='-');
      if(neg)c = read();
      do {ret=ret*10+c-'0';}while((c=read())>='0'&&c<='9');
      if(c=='.')while((c=read())>='0'&&c<='9')
        ret+=(c-'0')/(div*=10);
      if(neg)return -ret;
      return ret;
    }

    private void fillBuffer() throws IOException{
      bytesRead=din.read(buffer,bufferPointer=0,BUFFER_SIZE);
      if(bytesRead==-1)buffer[0]=-1;
    }

    private byte read() throws IOException{
      if(bufferPointer==bytesRead)fillBuffer();
      return buffer[bufferPointer++];
    }

    public void close() throws IOException{
      if(din==null) return;
      din.close();
    }
  }


}