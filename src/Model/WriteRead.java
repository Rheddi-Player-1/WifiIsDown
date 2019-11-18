package Model;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteRead
{
    private static String dataFile = "XMLs/Data.xml";

    public static boolean saveData(Player user)
    {
        try
        {
            DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = docFac.newDocumentBuilder();
            Document doc = docBuild.newDocument();

            Element playerInfoRoot = doc.createElement("player");
            doc.appendChild(playerInfoRoot);
            Element roomInfoRoot = doc.createElement("room");
            doc.appendChild(roomInfoRoot);

            Element playerName = doc.createElement("playerName");
            playerName.appendChild(doc.createTextNode(user.getPlayerName()));
            playerInfoRoot.appendChild(playerName);

            Element playerCurrentStress = doc.createElement("playerCurrentStress");
            playerCurrentStress.appendChild(doc.createTextNode(Integer.toString(user.getPlayerCurrentStress())));
            playerInfoRoot.appendChild(playerCurrentStress);

            Element playerAttack = doc.createElement("playerAttack");
            playerAttack.appendChild(doc.createTextNode(Integer.toString(user.getPlayerAttack())));
            playerInfoRoot.appendChild(playerAttack);

            Element playerEquippedItem = doc.createElement("playerEquippedItem");
            playerEquippedItem.appendChild(doc.createTe)


            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public static boolean loadData()
    {
        try
        {
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

}
