import java.util.Scanner;
import java.util.Stack;

public class Spreadsheet {

  public static void main(String[] args) {


    Scanner scanner = new Scanner(System.in);
    String in = scanner.nextLine();
    String[] split = in.split(" ");
    int c = Integer.parseInt(split[0]);
    int r = Integer.parseInt(split[1]);

    boolean adjMat[][] = new boolean[r*c][r*c];
    Double[] result = new Double[r*c];
    String[] cell = new String[r*c];

    for(int i=0; i<r; i++) {
      for(int j=0; j<c; j++) {

        cell[i*c+j] = scanner.nextLine();
        split = cell[i*c+j].split(" ");

        for (int k=0; k<split.length; k++ ) {
          if(split[k].charAt(0) >= 'A' && split[k].charAt(0) <= 'Z') {
            adjMat[(split[k].charAt(0) - 'A')* c  + (split[k].charAt(1) - '1')][i*c + j] = true;
          }
        }

      }
    }

    for (int i = 0; i < r*c; i++) {

      for (int j = 0; j < r*c; j++) {
        if(adjMat[i][j]) {
          System.out.println(adjMat[i][j] + ":" + i + ":" + j);
        }


      }
    }


    //dfs
    Stack<Integer> topOrder = new Stack<>();

    int[] state = new int[r*c]; // 0 - unvisited, 1- in-stack, 2- visited.
    for (int i = 0; i < r*c; i++) {
      state[i] = 0;
    }

    for (int i = 0; i < r*c; i++) {

      if(state[i] != 0) {
        continue;
      }
      else{
        Stack<Integer> dfsStack = new Stack<>();
        dfsStack.push(i);
        while (!dfsStack.isEmpty()) {
          int top = dfsStack.peek();
          if(state[top] == 1) {
            topOrder.push(dfsStack.pop());
            state[top] = 2;
          }
          else {
            for(int j=0 ; j<r*c; j++) {

              if(adjMat[top][j] ) {
                System.out.println(top + ":" + j);
                if(state[j] == 0) {
                  dfsStack.push(j);
                }
                else if(state[j] == 1) {
                  throw new IllegalArgumentException();
                }
                else{

                }
              }
            }
            state[top] = 1;
          }
        }
      }
    }


    while (!topOrder.isEmpty()) {
      int top = topOrder.pop();
      result[top] = solveRPNExpression(cell[top], result, c);
    }

    for (int i = 0; i < result.length ; i++) {
      System.out.println(String.format("%.5f", result[i]));
    }

  }




  private static Double solveRPNExpression(String exp, Double[] result, int c) {

    Stack<Double> stack = new Stack<>();
    String[] split = exp.split(" ");

    for (int k = 0; k < split.length; k++) {
      if(split[k].charAt(0) >= 'A' && split[k].charAt(0) <= 'Z') {
        int req = (split[k].charAt(0) - 'A')* c  + (split[k].charAt(1) - '1');
        stack.push(result[req]*1.0);
      }
      else if(split[k].equals("+")){
        Double a = stack.pop();
        Double b = stack.pop();
        stack.push(b+a);
      }
      else if(split[k].equals("-")){
        Double a = stack.pop();
        Double b = stack.pop();
        stack.push(b-a);
      }
      else if(split[k].equals("*")){
        Double a = stack.pop();
        Double b = stack.pop();
        stack.push(b*a);
      }
      else if(split[k].equals("/")){
        Double a = stack.pop();
        Double b = stack.pop();
        stack.push(b/a);
      }
      else if(split[k].equals("++")){
        stack.push(stack.pop() + 1);
      }

      else if(split[k].equals("--")){
        stack.push(stack.pop() - 1);
      }
      else {
        stack.push(Double.parseDouble(split[k]));
      }
    }

    return stack.pop();
  }


}
