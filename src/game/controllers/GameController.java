package game.controllers;

import game.Board;
import game.GameState;
import game.GameStateLoader;
import game.GameStateSaver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class GameController {
    @FXML
    private Button button00;
    @FXML
    private Button button01;
    @FXML
    private Button button02;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;


    private char symbol = 'O';
    private Board board;

    private void changeSymbol() {
        if (symbol == 'X') {
            symbol = 'O';
        } else {
            symbol = 'X';
        }
    }

    private void makeAllButtonsVisible(boolean areVisible) {
        button00.setVisible(areVisible);
        button01.setVisible(areVisible);
        button02.setVisible(areVisible);
        button10.setVisible(areVisible);
        button11.setVisible(areVisible);
        button12.setVisible(areVisible);
        button20.setVisible(areVisible);
        button21.setVisible(areVisible);
        button22.setVisible(areVisible);
    }

    private void makeAllButtonsEmpty() {
        button00.setText("");
        button01.setText("");
        button02.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button20.setText("");
        button21.setText("");
        button22.setText("");
    }

    private void showWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Look, This FILED IS ALREADY OCCUPIED! TRY AGAIN.");
        alert.setContentText("Try again. Careful with the next step!");

        alert.showAndWait();
    }

    private void showWinDialogAndStartNewGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WINNER!");
        alert.setHeaderText("You won.");
        alert.setContentText("Do You want to start new game?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            startNewGame();
        } else {
            makeAllButtonsVisible(false);
            makeAllButtonsEmpty();
        }
    }

    public void startNewGame() {
        makeAllButtonsEmpty();
        makeAllButtonsVisible(true);
        board = new Board();
    }

    public void putSymbolInBoard(ActionEvent event) {
        Button button = (Button) event.getTarget();

        String rowColumnString = (button.getId().replaceAll("[^0-2]", ""));
        int row = Character.getNumericValue(rowColumnString.charAt(0));
        int column = Character.getNumericValue(rowColumnString.charAt(1));

        if (board.putSymbolInTable(symbol, row, column)) {

            button.setText(String.valueOf(symbol));

            if (board.checkWin()) {
                showWinDialogAndStartNewGame();
            } else {
                changeSymbol();
            }

        } else {
            showWarningDialog();
        }
    }

    public void saveStateInXML() {
        GameStateSaver.saveStateInXML(board, symbol, "save.xml");
    }

    public void loadStateInXML() {
        if (GameStateLoader.loadStateFromXMLtoBoard("save.xml").isPresent()) {
            startNewGame();

            GameState gameState = GameStateLoader.loadStateFromXMLtoBoard("save.xml").get();
            symbol = gameState.getLastSymbol();

            board.setBoard(gameState.getBoard());
            button00.setText(String.valueOf(board.getBoard()[0][0]));
            button01.setText(String.valueOf(board.getBoard()[0][1]));
            button02.setText(String.valueOf(board.getBoard()[0][2]));
            button10.setText(String.valueOf(board.getBoard()[1][0]));
            button11.setText(String.valueOf(board.getBoard()[1][1]));
            button12.setText(String.valueOf(board.getBoard()[1][2]));
            button20.setText(String.valueOf(board.getBoard()[2][0]));
            button21.setText(String.valueOf(board.getBoard()[2][1]));
            button22.setText(String.valueOf(board.getBoard()[2][2]));

        }
    }
}
