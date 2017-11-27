package game;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Optional;

public class GameStateLoader {
    public static Optional<GameState> loadStateFromXMLtoBoard(String XML_PATH) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        GameState gameState = null;
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            BoardHandler bHandler = new BoardHandler();
            saxParser.parse(XML_PATH, bHandler);

            gameState = new GameState();
            gameState.setBoard(bHandler.getBoard());
            gameState.setLastSymbol(bHandler.getLastSymbol());


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return Optional.of(gameState);
    }
}
