import java.util.*;

public class sudoku
{
    public static void main(String[] args)
    {
        int grid[][] = {{6,8,0,  2,0,0,  1,0,0},
                        {9,0,0,  6,8,7,  0,0,0},
                        {0,2,0,  9,0,0,  0,6,8},
                    
                        {0,0,0,  0,9,0,  4,0,3},
                        {3,0,9,  0,0,6,  0,5,1},
                        {0,0,0,  3,0,5,  9,2,0},
                        
                        {0,9,8,  1,0,0,  7,0,4},
                        {1,0,2,  4,7,0,  6,0,0},
                        {7,0,4,  5,0,8,  0,0,0}};

        boolean solveable = recurSolve(grid, 0, 0);

        if(solveable)
        {
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[i].length; j++){
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }else
        {
            System.out.println("The board u provided is not solveable");
        }
    }

    public static boolean recurSolve(int[][] grid, int row, int col){
        //board is filled, solution found
        if(row >= 9){
            return true;
        }//if the whole row is checked, move to next row and reset column to first one
        else if(col >=9){
            return recurSolve(grid, row+1, 0);
        }//index already has a value in it so skip it (most likely the preset value on the board)
        else if(grid[row][col] != 0){
            return recurSolve(grid, row, col+1);
        }//index value is 0, meaning solve it
        else{
            for(int i = 1; i <= 9; i++){
                boolean rowCheck = checkRow(grid, row, col, i);
                boolean colCheck = checkCol(grid, row, col, i);
                boolean subGridCheck = checkSubGrid(grid, row, col, i);

                if(rowCheck && colCheck && subGridCheck){
                    grid[row][col] = i;
                    if(recurSolve(grid, row, col+1)){
                        return true;
                    }else{
                        grid[row][col] = 0;
                    }
                }
            }
            return false;
        }
    }

    public static boolean checkCol(int[][] grid, int row, int col, int checkNum){
        for(int i = 0; i < grid.length; i++){
            if(grid[i][col] == checkNum){
                return false;
            }
        }
        return true;
    }

    public static boolean checkRow(int[][] grid, int row, int col, int checkNum){
        for(int i = 0; i < grid[row].length; i++){
            if(grid[row][i] == checkNum){
                return false;
            }
        }
        return true;
    }

    public static boolean checkSubGrid(int[][] grid, int row, int col, int checkNum){
        int startRow = row - (row%3);
        int startCol = col - (col%3);

        for(int i = startRow; i < startRow+3; i++){
            for(int j = startCol; j < startCol+3; j++){
                if(grid[i][j] == checkNum){
                    return false;
                }
            }
        }
        return true;
    }
}