package com.leetsolutions.sudoku;

public class ColumnNode extends DataNode {

  public int count;

  public String name;

  public ColumnNode(String name) {
    super();
    this.count = 0;
    this.name = name;
    C = this;
  }

  // It removes c from the header list and removes all rows in c’s own list from the other
  // column lists they are in.
  // key things if we cover one column, it doesn't remove connection with down nodes and even the
  // side rows doesn't loose connections.
  public void cover() {
    unlinkLR();
    DataNode tmp = this.D;
    while (tmp != this) {
      //select each row and go through all right nodes and unlink from respective columns.
      DataNode r = tmp.R;
      while (r!= tmp) {
        r.unlinkUD();
        r.C.count--;
        r = r.R;
      }

      tmp = tmp.D;
    }
  }

  //It negates the above operation using un removed connections from above operation.
  public void unCover() {
    linkLR();
    DataNode tmp = this.U;
    while (tmp != this) {
      //select each row and go through all the left nodes and link from respective columns.
      DataNode l = tmp.L;
      while (l!= tmp) {
        l.linkUD();
        l.C.count++;
        l = l.L;
      }
      tmp = tmp.U;
    }
  }

}
