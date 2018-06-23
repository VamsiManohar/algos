

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {


    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    Integer T = in.nextInt();

    for (int t = 1 ; t <= T; t++) {

      Integer size = in.nextInt();
      ArrayList<Integer> evenList = new ArrayList<>();

      ArrayList<Integer> oddList = new ArrayList<>();

      for(int i = 0; i < size; i++) {
        if(i % 2 == 0) {
          evenList.add(in.nextInt());
        }
        else {
          oddList.add(in.nextInt());
        }
      }

      Collections.sort(evenList);
      Collections.sort(oddList);
      int i = 0;
      int j = 0;
      boolean checkEven = true;
      int total = 0;
      boolean loopRun = true;
      while((i) < evenList.size() && j < oddList.size() && loopRun) {
        if( checkEven ) {
          if(evenList.get(i) > oddList.get(j)) {
            System.out.println("Case #" + t + ": " + total);
            loopRun = false;
          }
          i++;
          checkEven = false;
        }
        else {
          if(oddList.get(j) > evenList.get(i)) {
            System.out.println("Case #" + t + ": " + total);
            loopRun = false;
          }
          j++;
          checkEven = true;
        }
        total++;
      }

      if(loopRun) {
        System.out.println("Case #" + t + ": " + "OK");
      }
    }



  }



}
