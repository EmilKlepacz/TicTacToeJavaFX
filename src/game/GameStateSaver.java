package game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class GameStateSaver {

    public static void saveStateInXML(Board board, char lastActiveSymbol, String fileName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element root = doc.createElement("board");
            doc.appendChild(root);

            char[][] boardToSave = board.getBoard();
            for (int i = 0; i < boardToSave.length; i++) {
                for (int j = 0; j < boardToSave[0].length; j++) {
                    Element position = doc.createElement("position");
                    root.appendChild(position);

                    Element row = doc.createElement("row");
                    row.setTextContent(String.valueOf(i));
                    position.appendChild(row);

                    Element column = doc.createElement("column");
                    column.setTextContent(String.valueOf(j));
                    position.appendChild(column);

                    Element symbol = doc.createElement("symbol");
                    if (boardToSave[i][j] == ' '){
                        symbol.setTextContent("EMPTY");
                    }else{
                        symbol.setTextContent(String.valueOf(boardToSave[i][j]));
                    }

                    position.appendChild(symbol);

                }
            }

            Element lastSymbol = doc.createElement("lastSymbol");
            lastSymbol.setTextContent(String.valueOf(lastActiveSymbol));
            root.appendChild(lastSymbol);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);


        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
