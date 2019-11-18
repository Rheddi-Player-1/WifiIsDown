package Model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Exceptions.FileDoesNotExist;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class WriteRead
{
    private static String dataFile = "XMLs/Data";

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
            playerEquippedItem.appendChild(doc.createTextNode(user.getPlayerEquipedItem().getItemID()));
            playerInfoRoot.appendChild(playerEquippedItem);

            Element playerCurrentRoom = doc.createElement("playerCurrentRoom");
            playerCurrentRoom.appendChild(doc.createTextNode(user.getCurrentRooms().getRoomID()));
            playerInfoRoot.appendChild(playerCurrentRoom);

            Element playerHeldItem = doc.createElement("playerHeldItem");
            playerHeldItem.appendChild(doc.createTextNode(user.getPlayerHeldItem().getItemID()));
            playerInfoRoot.appendChild(playerHeldItem);

            ArrayList<Item> playerItems = user.getCarriedItems();
            String carriedItems = "";

            for(int i = 0; i < playerItems.size(); i++)
                carriedItems += (playerItems.get(i).getItemID() + ":");

            Element playerCarriedItems = doc.createElement("playerCarriedItems");
            playerCarriedItems.appendChild(doc.createTextNode(carriedItems));
            playerInfoRoot.appendChild(playerCarriedItems);


            for(int i = 0; i < Rooms.rooms.size(); i++)
            {
                Rooms tempRoom = null;
                if(i < 10)
                    tempRoom = Rooms.rooms.get("R0" + i);
                else
                    tempRoom = Rooms.rooms.get("R" + i);
                Attr roomId = doc.createAttribute("roomID");
                roomId.setValue(tempRoom.getRoomID());
                roomInfoRoot.setAttributeNode(roomId);

                Element roomVisited = doc.createElement("isVisited");
                roomVisited.appendChild(doc.createTextNode(tempRoom.getRoomVisited()));
                roomInfoRoot.appendChild(roomVisited);

                Element roomPuzzle = doc.createElement("roomPuzzle");
                roomPuzzle.appendChild(doc.createTextNode(tempRoom.getRoomPuzzleID()));
                roomInfoRoot.appendChild(roomPuzzle);

                Element roomLocked = doc.createElement("roomLocked");
                roomPuzzle.appendChild(doc.createTextNode(Boolean.toString(tempRoom.getRoomLocked())));
                roomInfoRoot.appendChild(roomLocked);

                //MY TIME COMPLEXITY!!!!
                String roomItemsString = "";

                for(int j = 0; j < tempRoom.getRoomItems().size(); j++)
                    roomItemsString += (tempRoom.getRoomItems().get(j).getItemID() + ":");

                Element roomItems = doc.createElement("roomItems");
                roomItems.appendChild(doc.createTextNode(roomItemsString));
                roomInfoRoot.appendChild(roomItems);
            }

            DateTimeFormatter datFor = DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss");
            LocalDateTime current = LocalDateTime.now();

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer();
            DOMSource domSc = new DOMSource(doc);
            StreamResult strRes = new StreamResult(new File(dataFile + datFor.format(current) + ".xml"));

            trans.transform(domSc, strRes);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public static Player loadData(String fileName)
    {
        Player savedPlayer = null;

        try
        {

            return savedPlayer;
        }
        catch (FileDoesNotExist e1)
        {
            return savedPlayer;
        }
        catch(Exception e2)
        {
            return savedPlayer;
        }
    }

}
