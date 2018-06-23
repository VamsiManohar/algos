package com.leetsolutions;

import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTextHarness {

  public static void main(String[] args){
      Pattern pattern =
          Pattern.compile("avc");

      Matcher matcher =
          pattern.matcher("adsf");

  }

}
