package com.leetsolutions.sudoku;

public class DataNode {

  public DataNode L,R,U,D;

  public ColumnNode C;

  public Integer rowNumber;

  public DataNode() {
    L = R = U = D = this;
  }

  public DataNode(ColumnNode c, Integer rowNumber) {
    L = R = U = D = this;
    C = c;
    this.rowNumber = rowNumber;
  }

  public void unlinkLR() {
    L.R = R;
    R.L = L;
  }

  public void linkLR(){
    L.R = this;
    R.L = this;
  }

  public void unlinkUD() {
    U.D = D;
    D.U = U;
  }

  public void linkUD() {
    U.D = D.U = this;
  }

  public void hookDown(DataNode node) {
    node.D = this.D;
    node.D.U = node;
    node.U = this;
    this.D = node;
  }

  public void hookUp(DataNode node) {
    node.U = this.U;
    node.U.D = node;
    node.D = this;
    this.U = node;
  }

  public void hookLeft(DataNode node) {
    node.L = this.L;
    node.L.R = node;
    node.R = this;
    this.L = node;
  }

  public void hookRight(DataNode node) {
    node.R = this.R;
    node.R.L = node;
    node.L = this;
    this.R = node;
  }

}
