package com.leetsolutions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestNumber {

  public String largestNumber(final List<Integer> a) {
    Integer size = a.size();
    Node[] nodes = new Node[size];
    for (int i = 0; i < size; i++) {
      nodes[i] = new Node(a.get(i));
    }

    Arrays.sort(nodes, Collections.reverseOrder());

    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < size; i++) {
      if (nodes[i].a == 0 && stringBuffer.length() != 0)
        continue;
      stringBuffer.append(nodes[i].a);
    }
    return stringBuffer.toString();
  }

  class Node implements Comparable<Node>{

    int a;

    public Node(int a){
      this.a = a;
    }


    public int compareTo(Node o){
      String first  = String.valueOf(this.a) + String.valueOf(o.a);
      String second = String.valueOf(o.a) + String.valueOf(this.a);
      return first.compareTo(second);
    }


  }
}
