package game;


import java.util.Scanner;

public class ConsoleGame {
    private Scanner scanner = new Scanner(System.in);
    private char symbol;
    private Board board = new Board();

    public void loadStateFromXMLtoBoard(String XML_PATH) {
        GameStateLoader.loadStateFromXMLtoBoard(XML_PATH);
    }

    public void saveStateInXML(String fileName) {
        GameStateSaver.saveStateInXML(board, symbol, fileName);
    }

    public void startGame() {

        int row, columns;
        boolean someoneWins = false;

        do {

            board.printTable();

            System.out.println("PLAYER " + symbol + " Give row:");
            row = scanner.nextInt();
            System.out.println("PLAYER " + symbol + " Give columns:");
            columns = scanner.nextInt();

            if (board.putSymbolInTable(symbol, row, columns)) {
                if (board.checkWin()) {
                    System.out.println("PLAYER " + symbol + " WINS!");
                    someoneWins = true;

                } else {
                    changeSymbol();
                }

            }

        } while (!someoneWins);

    }

    private void changeSymbol() {
        if (symbol == 'X') {
            symbol = 'O';
        } else {
            symbol = 'X';
        }
    }

    public ConsoleGame(char startingSymbol) {
        this.symbol = startingSymbol;
    }

    public static void main(String[] args) {
        ConsoleGame game = new ConsoleGame('O');
//        game.loadStateFromXMLtoBoard("save.xml");
        game.startGame();
    }
}

