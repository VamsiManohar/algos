package com.leetsolutions;

public class Haha {
  public ListNode reverseList(ListNode A, int B) {

    ListNode prev = null;
    ListNode start = A;
    ListNode end = A;
    ListNode result=A;
    while(start!=null){
      int i =1;
      while(i < B){
        end = end.next;
        i++;
      }

      ListNode next = end.next;
      end.next = null;
      reverse(start);
      if(prev != null){
        prev.next = end;
      }
      else{
        result = end;
      }
      prev = start;
      System.out.println(start.val);
      start = next;
      if(start != null)
        System.out.println(start.val);
    }

    return result;
  }

  public ListNode reverse(ListNode a){
    if(a == null){
      return a;
    }
    ListNode head = a;
    ListNode cur = head.next;
    head.next = null;

    while(cur != null){
      ListNode next = cur.next;
      cur.next = head;
      head = cur;
      cur = next;
    }
    return head;
  }


}
