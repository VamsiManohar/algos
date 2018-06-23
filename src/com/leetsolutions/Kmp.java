package com.leetsolutions;

import java.util.ArrayList;
import java.util.List;

public class Kmp {

  public List<Integer> search(String pattern, String text){
    List<Integer> result = new ArrayList<>();
    int t = 0;
    int p = 0;
    int size = text.length();
    int pSize = pattern.length();
    List<Integer> pT = getPrefixTable(pattern);
    while (t < size){
      if(text.charAt(t) == pattern.charAt(p)){
        if( p == pSize-1){
          result.add(t-pSize+1);
          p = pT.get(p-1);
        }
        else{
          p = p+1;
        }
        t = t+1;
      }
      else{
        if(p == 0){
          t = t+1;
        }
        else{
          p = pT.get(p-1);
        }
      }
    }
    return result;
  }

  public List<Integer> getPrefixTable(String s){
    int size = s.length();
    List<Integer> pfTable = new ArrayList<>();
    pfTable.add(0);
    int y = 1;
    int x = 0;
    while(y<size){
      if(s.charAt(y) == s.charAt(x)){
        pfTable.add(x + 1);
        y = y + 1;
        x = x + 1;
      }
      else{
        if(x!=0){
          x = pfTable.get(x-1);
        }
        else{
          pfTable.add(0);
          y = y+1;
        }
      }
    }
    return pfTable;
  }

  public List<Integer> getPrefixTableBF(String s){
    int size = s.length();
    List<Integer> pfTable = new ArrayList<>();
    pfTable.add(0);

    for (int i = 1; i < size ; i++) {
      System.out.println("Started For index " + i);
      for(int l =i ; l >0; l--){
        System.out.println(".....Started For length " + l);
        boolean isPrefixLength = true;
        for (int j = 0; j < l; j++) {

          int prefixIndex = j;
          int suffixIndex = i-(l-1) +j;
          System.out.println("...........For length " + l + "comparing chars at " + prefixIndex +
                  ":" + s.charAt(prefixIndex) + "  and  " + suffixIndex + ":" + s.charAt(suffixIndex));
          if(s.charAt(prefixIndex) != s.charAt(suffixIndex)){
            isPrefixLength = false;
            break;
          }

        }

        if(isPrefixLength){
          System.out.println("Succeeded for index " + i);
          pfTable.add(l);
          break;
        }

      }
      if(pfTable.size()!=i+1){
        System.out.println("Not found for index " + i);
        pfTable.add(0);
      }

    }


    return pfTable;
  }

}
