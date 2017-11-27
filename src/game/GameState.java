package game;


public class GameState {
    private char[][] board;
    private char lastSymbol;

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getLastSymbol() {
        return lastSymbol;
    }

    public void setLastSymbol(char lastSymbol) {
        this.lastSymbol = lastSymbol;
    }
}
