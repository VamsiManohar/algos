package com.leetsolutions;

import java.util.ArrayList;
import java.util.Arrays;

public class HotelBooking {
  public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
    int size = arrive.size();
    Booking[] bookings = new Booking[2*size];
    for(int i=0, j =0; i+1< 2*size && j<size ; j++,i=i+2){
      bookings[i] = new Booking(arrive.get(j), true);
      bookings[i+1] = new Booking(depart.get(j), false);
    }
    Arrays.sort(bookings);
    int max = -100;
    int count = 0;
    for(int i=0; i < 2*size; i++) {
      if(bookings[i].isArrival){
        count++;
      }
      else{
        count--;
      }
      if(count > max){
        max = count;
      }

    }
    if(max > K){
      return false;
    }
    else{
      return true;
    }

  }

  class Booking implements Comparable<Booking>
  {
    int time;
    boolean isArrival;

    public Booking(int time, boolean isArrival){
      this.time = time;
      this.isArrival = isArrival;
    }

    public int compareTo(Booking a){
      if(this.time-a.time == 0){
        if(this.isArrival){
          return 1;
        }
        else{
          return -1;
        }
      }
      return this.time-a.time;
    }
  }
}

