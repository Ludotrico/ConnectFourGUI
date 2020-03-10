import java.io.*;
import java.util.Scanner;

public class TicTacToe {
    public TicTacToe(int n) {
        board = new int[n][n];
 
        boardSize = n;
        turn = 1;
        gameStatus = 0;
    }

    public boolean makeMove(int x, int y) {
        if (board[x][y] != 0) {
            return false;
        }
        board[x][y] = turn;
        if (turn == 1) {
            turn = 2;
        } else {
            turn = 1;
        }
        return true;
    }

    public int turn() {
        return turn;
    }

    public int gameStatus() {
        boolean [] playerWinStatus = new boolean[] {false, false, false};

        //Check if any row is identical
        for( int[] row : board) {
            int count1 = 0;
            int count2 = 0;
            for(int p = 1; p < 3; p++) {
                for(int player : row) {
                    if( (p == 1) && (player == 1) ) {
                        count1++;
                    }
                    else if ( (p== 2) && (player == 2) ){
                        count2++;
                    }
                }
            }
            if(count1 == boardSize)
                playerWinStatus[1] = true;
            if(count2 == boardSize)
                playerWinStatus[2] = true;
        }

        //Check if any column is identical
        for(int column = 0; column < boardSize; column++) {
            int count1 = 1;
            int count2 = 1;
            for(int row = 0; row < boardSize - 1; row++) {
                if( (board[row][column] != 0) && (board[row][column] == board[row+1][column])   ) {
                    if(board[row][column] == 1) {
                        count1++;
                    }
                    else { count2++; }
                }
                else {
                    break;
                }
            }
            if(count1 == boardSize)
                playerWinStatus[1] = true;
            if(count2 == boardSize)
                playerWinStatus[2] = true;
        }

        //Check if Diagonal is identical
        int column = 0;
        int count = 1;
        if( board[0][0] != 0) {
            for (int row = 0; row < boardSize - 1; row ++) {
                if(board[row][column] == board[row+1][column+1])
                    count++;
                column++;
            }
            if( (count == boardSize) && (board[0][0] == 1) )
                playerWinStatus[1] = true;
            if( (count == boardSize) && (board[0][0] == 2) )
                playerWinStatus[2] = true;
        }



        if(playerWinStatus[1] && playerWinStatus[2])
            gameStatus = -1;
        else if (playerWinStatus[1])
            gameStatus = 1;
        else if (playerWinStatus[2])
            gameStatus = 2;
        else { gameStatus = 0; }
        return gameStatus;
    }

    public boolean gameOver() {
        gameStatus = gameStatus();
        if (gameStatus == 0)
            return false;
        return true;
    }

    public String toString() {
        String s = new String();
        s = "";
        for( int [] row : board) {
            for(int x : row) {
                s+=x;
            }
            s+='\n';
        }
        return s;
    }

    public void loadBoard(String fileName) {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(fileName));
            int lines = 0;
            while (lineReader.readLine() != null) lines++;
            lineReader.close();
            board = new int [lines][lines];
            boardSize = lines;

            Scanner scanner = new Scanner(new File(fileName));
            String [] lineArray = new String[lines];

            for(int row = 0; row < boardSize; row++) {
                lineArray[row] = scanner.next();
            }
            scanner.close();
            for(int row = 0; row < boardSize; row++) {
                for(int column = 0; column < boardSize; column++) {
                    board[row][column] = lineArray[row].charAt(column) - '0';
                }
            }

        }
        catch (FileNotFoundException e) {
            System.out.println(fileName + " not found");
            return;
        }
        catch(IOException e) {
            System.out.println("Something went wrong reading " + fileName);
            return;
        }
        catch (Exception e ) {
            System.out.println("Something went wrong reading " + fileName);
            return;
        }
    }

    public void saveBoard(String fileName) {
        String s = toString();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(s);
            writer.close();
        }
        catch (Exception e) {
            System.out.println("Something went wrong writing to file " + fileName);
            return;
        }
    }

    public int checkPosition(int x, int y) {
        if( (x >= boardSize) || (y >= boardSize)) {
            return -1;
        }
        return board[x][y];
    }
    
    
    

    protected int[][] board;
    protected int turn;
    protected int boardSize;
    protected int gameStatus;
}
