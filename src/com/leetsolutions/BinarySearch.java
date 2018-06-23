package com.leetsolutions;

public class BinarySearch {

  public int uBinarySearch(int[] arr, int low, int  high, int search){
    if(low > high){
      return -1;
    }
    else{
      int mid = (low + high)/2;

      if(arr[mid] == search){
        return  mid;
      }

      else if (arr[mid] < search){
        return uBinarySearch(arr, mid+1, high, search );
      }
      else{
        return uBinarySearch(arr, low, mid-1, search);
      }
    }
  }

  public int dFirstBinarySearch(int[] arr, int low, int  high, int search, int bestPossible){
    if(low > high){
      return bestPossible;
    }
    else{
      int mid = (low + high)/2;

      if(arr[mid] == search){
        return dFirstBinarySearch(arr, low, mid-1, search, mid);
      }
      else if(arr[mid] > search){
        return dFirstBinarySearch(arr, low, mid-1, search, bestPossible);
      }
      else{
        return dFirstBinarySearch(arr, mid+1, high, search, bestPossible);
      }
    }
  }

  public int dLastBinarySearch(int[] arr, int low, int  high, int search, int bestPossible){
    if(low > high){
      return bestPossible;
    }
    else{
      int mid = (low + high)/2;

      if(arr[mid] == search){
        return dLastBinarySearch(arr, mid+1, high, search, mid);
      }
      else if(arr[mid] > search){
        return dLastBinarySearch(arr, low, mid-1, search, bestPossible);
      }
      else{
        return dLastBinarySearch(arr, mid+1, high, search, bestPossible);
      }
    }
  }

  public int ceiling(int[] arr, int low, int high, int search, int bestPossible){
    if(low > high){
      return bestPossible;
    }
    else {
      int mid = (low + high)/2;

      if(arr[mid] == search){
        return dLastBinarySearch(arr, mid, high, search, mid);
      }
      else if(arr[mid] > search){
        return ceiling(arr, low, mid-1, search, mid);
      }
      else{
        return ceiling(arr, mid+1, high, search, bestPossible);
      }
    }
  }

  public int floor(int[] arr, int low, int high, int search, int bestPossible){
    if(low > high){
      return bestPossible;
    }
    else {
      int mid = (low + high)/2;

      if(arr[mid] == search){
        return dFirstBinarySearch(arr, low, mid, search, mid);
      }
      else if(arr[mid] > search){
        return floor(arr, low, mid-1, search, bestPossible);
      }
      else{
        return floor(arr, mid+1, high, search, mid);
      }
    }
  }

}
