package game;


public class Board {

    private char[][] board = new char[3][3];

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    private void fillTableWithEmptyCharacters(){
        for (int i = 0; i< board.length; i++){
            for(int j = 0; j< board.length; j++){
                board[i][j] = ' ';
            }
        }
    }

    public void printTable(){
        System.out.print(" --------------------");
        for (char[] row : board){
            System.out.println();
            System.out.print("  |  ");
            for (char item: row) {
                System.out.print(item + "  |  ");
            }
            System.out.println();
            System.out.print(" --------------------");
        }
        System.out.println();
    }

    private boolean isFieldOccupied(int row, int columns){
        if(board[row][columns]==' '){
            return false;
        }
        return true;
    }

    public boolean putSymbolInTable(char symbol, int row, int columns){
        if(isFieldOccupied(row, columns)){
            System.out.println("WARNINIG! FILED " + "["+ row +"," + columns + "] IS ALREADY OCCUPIED! TRY AGAIN.");
            return false;
        }else{
            board[row][columns] = symbol;
            return true;
        }
    }

    private boolean isEmptySymbol(char symbol){
        return (symbol==' ');
    }

    private boolean areSameNotEmptySymbols(char[] symbols){
        for (int i = 0; i < symbols.length ; i++) {
            if (symbols[0] != symbols[i] || isEmptySymbol(symbols[i])){
                return false;
            }
        }
        return true;
    }

    private boolean checkRowsForWin(){
        char[]row = new char[board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length ; j++) {
               row[j] = board[i][j];
            }

            if(areSameNotEmptySymbols(row))return true;
        }
        return false;
    }

    private boolean checkColumnsForWin(){
        char[] column = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length ; j++) {
                column[j] = board[j][i];
            }
            if(areSameNotEmptySymbols(column)) return true;
        }
        return false;
    }

    private boolean checkDiagonalsForWin(){
        char [] diagonal1 = new char[board.length];
        char [] diagonal2 = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            diagonal1[i] = board[i][i];

            diagonal2[i] = board[board.length - (i+1)][i];
        }

        return  (areSameNotEmptySymbols(diagonal1)||areSameNotEmptySymbols(diagonal2));
    }

    public boolean checkWin(){
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    public Board(){
        fillTableWithEmptyCharacters();
    }
}
