package com.leetsolutions.sudoku;

import java.util.ArrayList;
import java.util.HashSet;

public class BackTrackingSudokuSolver implements SudokuSolver {

  private int boardSize;

  private int[][] board;

  public BackTrackingSudokuSolver(int[][] board) {
    this.boardSize = board[0].length;
    this.board = board;
  }

  @Override
  public void solve() {
    if(!solve(-1, boardSize, null)) {
      throw new IllegalArgumentException("Wrong Board Provided.");
    }
  }

  private boolean solve(int x, int y, ArrayList<Integer> unFilledList) {

    if(x == boardSize) {
      printSolution();
      return true;
    }
    else if(y == boardSize) {
      if(x +1 == boardSize) {
        return solve(x+1, 0, unFilledList);
      }
      HashSet<Integer> unFilledSet = getUnfilledItemsFromRow(x+1);
      unFilledList = new ArrayList<>(unFilledSet);
      //System.out.println(unFilledList);
      return solve(x+1, 0, unFilledList);
    }
    else {
      if(board[x][y] == 0) {
        boolean isSolved = true;
        for (int i = 0; i < unFilledList.size(); i++) {
          Integer val = unFilledList.get(i);
          //System.out.println("Trying val : " + val +  " for x :" + x + " and y : " + y);
          if(isValid(x, y, val)) {
            board[x][y] = val;
            unFilledList.remove(i);
            isSolved = solve(x, y+1, unFilledList );
            if(!isSolved) {
              board[x][y] = 0;
              unFilledList.add(i, val);
            }
          }
          else {
            isSolved = false;
          }
        }
        return isSolved;
      }
      else {
        return solve(x, y+1, unFilledList);
      }
    }
  }



  private HashSet<Integer> getUnfilledItemsFromRow(int row) {
    if(row == 9){
      System.out.println(row);
    }
    HashSet<Integer> unFilledSet = new HashSet<>();
    for (int j = 1; j <= boardSize; j++) {
      unFilledSet.add(j);
    }

    for (int j = 0; j < boardSize; j++) {
      if(board[row][j] != 0) {
        unFilledSet.remove(new Integer(board[row][j]));
      }
    }

    return unFilledSet;

  }

  private boolean checkColumnConstraint(int x, int y, int val) {
    for (int i = 0; i < boardSize; i++) {
      if(board[i][y] == val) {
        return false;
      }
    }
    return true;
  }

  private boolean checkSubSectionConstraint(int x, int y, int val) {

    Double subSectionSize = Math.sqrt(boardSize);
    Integer boxSize = subSectionSize.intValue();
    Integer xStart = (x/boxSize)*boxSize;
    Integer yStart = (y/boxSize)*boxSize;

    for (int i = xStart; i < xStart + boxSize; i++) {
      for (int j = yStart; j < yStart + boxSize; j++) {
        if(board[i][j] == val) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean isValid(int x, int y, int val) {
    return checkColumnConstraint(x, y, val) && checkSubSectionConstraint(x, y, val);
  }

  private void printSolution() {

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (j == boardSize - 1) {
          System.out.print(board[i][j]);
        } else {
          System.out.print(board[i][j] + " ");
        }
      }
      System.out.println();
    }

  }

}
