package com.leetsolutions.lastdig2;
import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int i = 0; i < t; i++) {
      String b = sc.next();
      Long p = sc.nextLong();
      Character c = b.charAt(b.length() - 1);
      int r;
      if (p == 0) {
        r = 1;
      } else {
        r = (int)Math.pow(c-'0', 4 + (p%4))%10;
      }
      System.out.println(r);
    }
  }

}