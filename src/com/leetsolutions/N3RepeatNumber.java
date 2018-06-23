package com.leetsolutions;

import java.util.List;

public class N3RepeatNumber {
  // DO NOT MODIFY THE LIST
  public int repeatedNumber(final List<Integer> a) {
    Pair[] elements = new Pair[2];
    elements[0] = new Pair();
    elements[1] = new Pair();
    int size = a.size();
    for(Integer num: a){
      Boolean isInserted = false;
      for(Pair ele : elements){
        if(num.equals(ele.number)){
          isInserted = true;
          ele.count++;
        }
      }
      if(!isInserted){
        for(Pair ele : elements){
          if(ele.count.equals(0)){
            isInserted = true;
            ele.number = num;
            ele.count = 1;
            break;
          }
        }
      }
      if(!isInserted){
        for(Pair ele : elements){
          ele.count--;
        }
      }
    }

    for(Pair ele : elements ){
      int count =0;
      for(Integer num : a){
        if(ele.number.equals(num)){
          count++;
        }
      }
      if(count > (size/3)){
        return ele.number;
      }
    }

    return -1;

  }

  public static class Pair{
    public Integer number = null;
    public Integer count = 0;
  }

}
