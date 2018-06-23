//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Scanner;
//
//public class Solution {
//
//  public static void main(String[] args) {
//
//
//    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//    Integer T = in.nextInt();
//    for (int t =0 ; t< T; t++) {
//      Integer d = in.nextInt();
//      String s = in.next();
//
//      Integer fCharge = 1;
//      Integer shootValue = 0;
//
//      for (int i = 0; i <s.length() ; i++) {
//        if(s.charAt(i) == 'S') {
//          shootValue = shootValue + fCharge;
//        }
//        else {
//          fCharge = fCharge << 1;
//        }
//      }
//
//      Integer reduction = shootValue - d;
//
//      Integer th = 0;
//      Integer i = s.length()-1;
//      Integer prevScount = 0;
//      while (reduction > 0 && i >=0 ){
//
//        if(s.charAt(i) == 'S') {
//          prevScount++;
//        }
//        else{
//
//          Integer nScount = (int) Math.ceil((double) (reduction*2) / fCharge);
//          if(prevScount >= nScount) {
//            reduction = reduction - (nScount * (fCharge/2));
//            th += nScount;
//          }
//          else {
//            reduction -=  (prevScount * (fCharge/2));
//            th += prevScount;
//          }
//
//          fCharge = fCharge/2;
//        }
//
//        i--;
//      }
//
//      if(reduction > 0){
//        System.out.println("Case #" + (t+1) + ": " + "IMPOSSIBLE");
//      }
//      else{
//        System.out.println("Case #" + (t+1) + ": " + th);
//      }
//    }
//
//  }
//
//
//
//}
