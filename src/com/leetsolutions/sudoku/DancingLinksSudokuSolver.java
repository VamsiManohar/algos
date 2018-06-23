package com.leetsolutions.sudoku;

import java.util.List;

public class DancingLinksSudokuSolver implements SudokuSolver {

  private int[][] coverBoard;

  private int[][] board;

  private int boardSize;

  private int subSectionSize;

  private int totalCells;

  @Override
  public void solve() {
    createCoverBoard();
    DancingLinks dancingLinks = new DancingLinks(coverBoard);
    List<List<Integer>> solutions = dancingLinks.solve();
    for (List<Integer> solution : solutions) {
      constructCompleteBoard(solution);
    }
  }

  public DancingLinksSudokuSolver(int[][] board) {
    this.board = board;
    boardSize = board[0].length;
    totalCells = boardSize * boardSize;
    subSectionSize = ((Double) Math.sqrt(boardSize)).intValue();
    coverBoard = new int[totalCells * boardSize][totalCells * 4];
  }

  private void constructCompleteBoard(List<Integer> rows) {
    int[][] result = new int[boardSize][boardSize];

    for (Integer row : rows) {
      int num = (row % boardSize) + 1;
      int tmp = row / boardSize;
      int c = tmp % boardSize;
      int r = tmp / boardSize;
      result[r][c] = num;
    }

    printSolution(result);
  }

  private void createCoverBoard() {

    for (int r = 0; r < boardSize; r++) {

      for (int c = 0; c < boardSize; c++) {

        for (int p = 0; p < boardSize; p++) {

          if (board[r][c] == 0 || board[r][c] == (p+1)) {

            int rowNumber = (r * boardSize + c) * boardSize + p;

            coverBoard[rowNumber][r * boardSize + c] = 1;

            coverBoard[rowNumber][totalCells + r * boardSize + p] = 1;

            coverBoard[rowNumber][(totalCells * 2) + (c * boardSize + p)] = 1;

            coverBoard[rowNumber][(totalCells * 3)
                + ((r / subSectionSize) * subSectionSize + (c / subSectionSize)) * boardSize
                + p] = 1;
          }
        }

      }
    }

  }

  private void printSolution(int[][] board) {

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
