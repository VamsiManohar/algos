package com.leetsolutions;

import java.util.ArrayList;

public class SubSets {


  public ArrayList<ArrayList<Integer>> generate(ArrayList<Integer> list) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    ArrayList<Integer> running = new ArrayList<>();
    result.add(new ArrayList<>(running));
    subsets(result, list, running, 0);
    return result;
  }

  public  void subsets(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list,
      ArrayList<Integer> running, int index) {

    for (int i = index; i < list.size(); i++) {

        if( i > index && list.get(i).equals(list.get(i-1))){
          continue;
        }
        running.add(list.get(i));
        result.add(new ArrayList<>(running));
        subsets(result, list, running, i + 1);
        running.remove(running.size()-1);

    }

  }
}
