package com.leetsolutions;

public class DivisionBit {

  public int divide(int a, int b) {

    if(b == 0 ){
      return Integer.MAX_VALUE;
    }

    if( a == 0){
      return 0;
    }

    boolean isNegative = false;
    long dividend, divisor;
    if( (a & (1 << 31)) == (1 << 31)){
      isNegative = !isNegative;
      dividend = add(~a, 1);
    }
    else{
      dividend = (long) a;
    }
    if((b & (1 << 31)) == (1 << 31)){
      isNegative = !isNegative;
      divisor = add(~b, 1);
    }
    else {
      divisor = (long)b ;
    }
    long q = 0;
    long r;
    for (long i = 31; i >= 0 ; i = subtract(i, 1)) {
      r = divisor << i;
      if(r <= dividend){
        dividend = dividend - (divisor << i);
        q = q | (1L << i);
      }
    }
    if( isNegative && q > (Integer.MAX_VALUE + 1l)){
      return Integer.MAX_VALUE;
    }
    else if (isNegative) {
      return  (int)add( ~q , 1);
    }
    else if( q > Integer.MAX_VALUE){
      return Integer.MAX_VALUE;
    }
    else{
      return (int)q;
    }

  }

  public long add(long a, long b){
    long result, carry;
    do{
      result = a^b;
      carry = (a&b)<<1;
      a = result;
      b = carry;
    }while (carry !=0);

    return result;
  }

  public long subtract(long a, long b){
    return add(a, add(~b,1));
  }
}
