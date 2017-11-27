package game;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoardHandler extends DefaultHandler {
    char[][] board = new char[3][3];

    boolean bSymbol = false;
    boolean bRow = false;
    boolean bColumn = false;
    boolean bLastSymbol = false;

    int row, column;
    char symbol;
    char lastSymbol;


    public char[][] getBoard() {
        return board;
    }

    public char getLastSymbol() {
        return lastSymbol;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            bRow = true;
        } else if (qName.equalsIgnoreCase("column")) {
            bColumn = true;
        } else if (qName.equalsIgnoreCase("symbol")) {
            bSymbol = true;
        } else if(qName.equalsIgnoreCase("lastSymbol")){
            bLastSymbol = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("position")) board[row][column] = symbol;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bRow) {
            row = Integer.parseInt(new String(ch, start, length));
            bRow = false;
        } else if (bColumn) {
            column = Integer.parseInt(new String(ch, start, length));
            bColumn = false;
        } else if (bSymbol) {
            if (new String(ch, start, length).equalsIgnoreCase("EMPTY")) {
                symbol = ' ';
            } else {
                symbol = new String(ch, start, length).charAt(0);
            }
            bSymbol = false;
        } else if(bLastSymbol){
            lastSymbol = new String(ch, start, length).charAt(0);
            bLastSymbol = false;
        }
    }
}
