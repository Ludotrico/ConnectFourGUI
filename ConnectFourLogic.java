//This class inherits from TicTacToe base class
//Most implentation can be found there

public class ConnectFourLogic extends TicTacToe {
    //Constructor
    public ConnectFourLogic() {
        super(6);
    }

    //Current player makes move if possible
    public boolean makeMove(int y) {
        int lastEmptyRow = -1;
        for(int [] row : board) {
            if(row[y] == 0)
                lastEmptyRow++;
        }
        if(lastEmptyRow == -1) {
            return false;
        }
        board[lastEmptyRow][y] = turn;
        if (turn == 1) {
            turn = 2;
        } else {
            turn = 1;
        }
        return true;
    }
    
    //Returns the index of the last empty row in a column, -1 if column is full
    public int emptyRow(int y) {
        int lastEmptyRow = -1;
        for(int [] row : board) {
            if(row[y] == 0)
                lastEmptyRow++;
        }
        return lastEmptyRow;
    }

    //Returns -1 if the current game state is a Tie, 1 if player 1 won, 2 if player 2 won
    public int gameStatus() {
        boolean [] playerWinStatus = new boolean[] {false, false, false};

        //Check if any row is identical
        for( int[] row : board) {
            int count1 = 1;
            int count2 = 1;
            for(int p = 1; p < 3; p++) {
                for(int index = 0; index < 6; index++) {
                    if( (p == 1) && (row[index] == 1) && (index != 0) && (row[index -1] == 1) ) {
                        count1++;
                    }
                    else if ( (p== 2) && (row[index] == 2) && (index != 0) && (row[index -1] == 2)){
                        count2++;
                    }
                }
            }
            if(count1 >= 4)
                playerWinStatus[1] = true;
            if(count2 >= 4)
                playerWinStatus[2] = true;
        }

        //Check if any column is identical
        for(int column = 0; column < boardSize; column++) {
            int count1 = 1;
            int count2 = 1;
            for (int row = 0; row < boardSize - 1; row++) {
                if ((board[row][column] != 0) && (board[row][column] == board[row + 1][column])) {
                    if (board[row][column] == 1) {
                        count1++;
                    } else {
                        count2++;
                    }
                }
            }
            if (count1 >= 4)
                playerWinStatus[1] = true;
            if (count2 >= 4)
                playerWinStatus[2] = true;
        }


        //Check if an identical diagonal exists slanting right -> \
        int column = 2;
        int count = 1;
        for (int row = 0; row < 3; row ++) {
            if(( board[row][column] != 0) && (board[row][column] == board[row+1][column+1]))
                count++;
            column++;
        }
        if( (count == 4) && (board[0][2] == 1) )
            playerWinStatus[1] = true;
        if( (count == 4) && (board[0][2] == 2) )
            playerWinStatus[2] = true;


        int count1 = 1;
        int count2 = 1;
        for( int p = 1; p < 3; p++) {
            column = 1;
            for (int row = 0; row < 4; row ++) {
                if ((p == 1) && (board[row][column] == 1) && (board[row + 1][column + 1] == 1))
                    count1++;
                if ((p == 2) && (board[row][column] == 2) && (board[row + 1][column + 1] == 2))
                    count2++;
                column++;
            }
        }
        if( count1 >= 4)
            playerWinStatus[1] = true;
        if( count2>=4 )
            playerWinStatus[2] = true;

        count1 = 1;
        count2 = 1;
        for( int p = 1; p < 3; p++) {
            column = 0;
            for (int row = 0; row < 5; row ++) {
                if ((p == 1) && (board[row][column] == 1) && (board[row + 1][column + 1] == 1))
                    count1++;
                if ((p == 2) && (board[row][column] == 2) && (board[row + 1][column + 1] == 2))
                    count2++;
                column++;
            }
        }
        if( count1 >= 4)
            playerWinStatus[1] = true;
        if( count2>=4 )
            playerWinStatus[2] = true;

        count1 = 1;
        count2 = 1;
        for( int p = 1; p < 3; p++) {
            column = 0;
            for (int row = 1; row < 5; row ++) {
                if ((p == 1) && (board[row][column] == 1) && (board[row + 1][column + 1] == 1))
                    count1++;
                if ((p == 2) && (board[row][column] == 2) && (board[row + 1][column + 1] == 2))
                    count2++;
                column++;
            }
        }
        if( count1 >= 4)
            playerWinStatus[1] = true;
        if( count2>=4 )
            playerWinStatus[2] = true;

        column = 0;
        count = 1;
        for (int row = 2; row < 5; row ++) {
            if(( board[row][column] != 0) && (board[row][column] == board[row+1][column+1]))
                count++;
            column++;
        }
        if( (count >= 4) && (board[2][0] == 1) )
            playerWinStatus[1] = true;
        if( (count >= 4) && (board[2][0] == 2) )
            playerWinStatus[2] = true;

        //Check if an identical diagonal exists slanting left -> /
        int columnR = 5;
        int countR = 1;
        for (int row = 2; row < 5; row ++) {
            if( ( board[row][columnR] != 0) && (board[row][columnR] == board[row+1][columnR-1])  )
                countR++;
            columnR--;
        }
        if( (countR >= 4) && (board[2][5] == 1) )
            playerWinStatus[1] = true;
        if( (countR >= 4) && (board[2][5] == 2) )
            playerWinStatus[2] = true;

        int count1R = 1;
        int count2R = 1;
        for(int p = 1; p < 3; p++) {
            columnR = 5;
            for (int row = 1; row < 5; row ++) {
                if ((p == 1) && (board[row][columnR] == 1) && (board[row + 1][columnR - 1] == 1))
                    count1R++;
                if ((p == 2) && (board[row][columnR] == 2) && (board[row + 1][columnR - 1] == 2))
                    count2R++;
                columnR--;
            }
        }
        if( count1R >= 4)
            playerWinStatus[1] = true;
        if( count2R >= 4 )
            playerWinStatus[2] = true;

        count1R = 1;
        count2R = 1;
        for(int p = 1; p < 3; p++) {
            columnR = 5;
            for (int row = 0; row < 5; row++) {
                if ((p == 1) && (board[row][columnR] == 1) && (board[row + 1][columnR - 1] == 1))
                    count1R++;
                if ((p == 2) && (board[row][columnR] == 2) && (board[row + 1][columnR - 1] == 2))
                    count2R++;
                columnR--;
            }
        }
        if( count1R >= 4)
            playerWinStatus[1] = true;
        if( count2R >= 4 )
            playerWinStatus[2] = true;

        count1R = 1;
        count2R = 1;
        for(int p = 1; p < 3; p++) {
            columnR = 4;
            for (int row = 0; row < 4; row ++) {
                if ((p == 1) && (board[row][columnR] == 1) && (board[row + 1][columnR - 1] == 1))
                    count1R++;
                if ((p == 2) && (board[row][columnR] == 2) && (board[row + 1][columnR - 1] == 2))
                    count2R++;
                columnR--;
            }
        }
        if( count1R >= 4)
            playerWinStatus[1] = true;
        if( count2R >= 4 )
            playerWinStatus[2] = true;

        columnR = 3;
        countR = 1;
        for (int row = 0; row < 3; row ++) {
            if( ( board[row][columnR] != 0) && (board[row][columnR] == board[row+1][columnR-1])  )
                countR++;
            columnR--;
        }
        if( (countR >= 4) && (board[0][3] == 1) )
            playerWinStatus[1] = true;
        if( (countR >= 4) && (board[0][3] == 2) )
            playerWinStatus[2] = true;

        if(playerWinStatus[1] && playerWinStatus[2])
            gameStatus = -1;
        else if (playerWinStatus[1])
            gameStatus = 1;
        else if (playerWinStatus[2])
            gameStatus = 2;
        else { gameStatus = 0; }
        return gameStatus;
    }

}
