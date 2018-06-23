package com.leetsolutions.sudoku;

import java.util.ArrayList;
import java.util.List;

public class DancingLinks {

  private ColumnNode header;

  private List<ArrayList<DataNode>> solutionList;

  private ArrayList<DataNode> solution;

  public DancingLinks(int[][] cover) {
    init(cover);
  }

  /**
   * Return row numbers which solves the above cover.
   *
   * @return row numbers.
   */
  public List<List<Integer>> solve() {
    search(0);
    return convertSolutionList();
  }

  private List<List<Integer>> convertSolutionList() {
    List<List<Integer>> result = new ArrayList<>();

    for (ArrayList<DataNode>solution : solutionList) {
      ArrayList<Integer> sol = new ArrayList<>();
      for (DataNode row: solution) {
          sol.add(row.rowNumber);
      }
      result.add(sol);
    }

    return result;
  }

  /**
   * Reads cover board and constructs dancing links data structure.
   *
   * @param cover cover board.
   */
  private void init(int[][] cover) {
    createDancingLinks(cover);
    solutionList = new ArrayList<>();
    solution = new ArrayList<>();
  }

  private void createDancingLinks(int[][] cover) {

    int rowSize = cover.length;
    int colSize = cover[0].length;

    createColumnNodes(colSize);

    for (int i = 0; i < rowSize ; i++) {
      ColumnNode curColumnNode = (ColumnNode) header.R;
      DataNode prev = null;
      for (int j = 0; j < colSize; j++) {
        if(cover[i][j] == 1) {

          DataNode node = new DataNode(curColumnNode, i);
          if (prev == null )
            prev = node;

          curColumnNode.hookUp(node);
          prev.hookRight(node);
          curColumnNode.count++;

        }
        curColumnNode = (ColumnNode) curColumnNode.R;
      }
    }
  }

  private void createColumnNodes(int colSize) {
    header = new ColumnNode("H");
    header.count = colSize;
    for (int i = 0; i < colSize; i++) {
      ColumnNode columnNode = new ColumnNode(i+"");
      header.hookLeft(columnNode);
    }
  }

  /**
   * Searches for solution for exact cover in non-deterministic way.
   *
   * @param k k level.
   */
  private void search(int k) {

    if (this.header.R == this.header) {

      System.out.println("Solution found at : " + k);
      solutionList.add(new ArrayList<>(solution));

    } else {

      ColumnNode c = selectColumn();
      //cover selected column.
      c.cover();

      DataNode tmp = c.D;
      while (tmp != c) {

        //adding current selected row into the solution.
        solution.add(tmp);

        //covering all the columns which are covered by the selected row.
        DataNode rAdj = tmp.R;
        while (rAdj != tmp) {
          rAdj.C.cover();
          rAdj = rAdj.R;
        }

        //search next level.
        search(k + 1);

        //remove the row from the probable solution list.
        solution.remove(solution.size() - 1);

        //uncover all the columns which the above selected row covers.
        DataNode lAdj = tmp.L;
        while (lAdj != tmp) {
          lAdj.C.unCover();
          lAdj = lAdj.L;
        }

        //assign other row and repeat above process.
        tmp = tmp.D;
      }
      c.unCover();

    }
  }

  /**
   * Selects column with least number of count.
   *
   * @return Column node.
   */
  private ColumnNode selectColumn() {

    int min = Integer.MAX_VALUE;
    ColumnNode result = null;
    ColumnNode tmp = (ColumnNode) header.R;
    while (tmp != header) {
      if (tmp.count < min) {
        result = tmp;
        min = tmp.count;
      }
      tmp = (ColumnNode) tmp.R;
    }
    return result;
  }


}
