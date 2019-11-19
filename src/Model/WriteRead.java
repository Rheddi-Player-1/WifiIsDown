package Model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Files;

import Exceptions.FileDoesNotExist;
import org.w3c.dom.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class WriteRead
{
    public static String saveToString()
    {
        ArrayList<String> files = new ArrayList<>();
        String returnStrig = "";
        try
        {
            String saveFolder = "GameSaves/";

            Files.list(new File(saveFolder).toPath())
                    .limit(10)
                    .forEach(p ->
                    {
                        String tempFile = p.toString();
                        files.add(tempFile.replace(".xml", ""));
                    });
            for(int i = 0; i < files.size(); i++)
                returnStrig += files.get(i) + "\n";

            return returnStrig;
        }
        catch(Exception e)
        {
            return "This file does not exist!";
        }
    }
    public static String saveData(Player user)
    {
        String fullFileName = "";
        String dataFile = "GameSaves/Data";
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

            Element availableStorage = doc.createElement("availableStorage");
            availableStorage.appendChild(doc.createTextNode(Integer.toString(user.getAvailableStorage())));
            playerInfoRoot.appendChild(availableStorage);

            Element playerEquippedItem = doc.createElement("playerEquippedItem");
            if(user.getPlayerEquipedItem() != null)
                playerEquippedItem.appendChild(doc.createTextNode(user.getPlayerEquipedItem().getItemID()));
            else
                playerEquippedItem.appendChild(doc.createTextNode("DNE"));
            playerInfoRoot.appendChild(playerEquippedItem);

            Element playerStorageItem = doc.createElement("playerStorageItem");
            if(user.getPlayerStorageItem() != null)
                playerStorageItem.appendChild(doc.createTextNode(user.getPlayerStorageItem().getItemID()));
            else
                playerStorageItem.appendChild(doc.createTextNode("DNE"));
            playerInfoRoot.appendChild(playerStorageItem);

            Element playerCurrentRoom = doc.createElement("playerCurrentRoom");
            playerCurrentRoom.appendChild(doc.createTextNode(user.getCurrentRooms().getRoomID()));
            playerInfoRoot.appendChild(playerCurrentRoom);

            Element playerHeldItem = doc.createElement("playerHeldItem");
            if(user.getPlayerHeldItem() != null)
                playerHeldItem.appendChild(doc.createTextNode(user.getPlayerHeldItem().getItemID()));
            else
                playerHeldItem.appendChild(doc.createTextNode("DNE"));
            playerInfoRoot.appendChild(playerHeldItem);

            ArrayList<Item> playerItems = user.getCarriedItems();
            String carriedItems = "";

            if(playerItems.isEmpty())
                carriedItems = "DNE";
            else
            {
                for (int i = 0; i < playerItems.size(); i++)
                    carriedItems += (playerItems.get(i).getItemID() + ":");
            }

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

                if(tempRoom.getRoomItems().isEmpty())
                    roomItemsString = "DNE";
                else
                {
                    for (int j = 0; j < tempRoom.getRoomItems().size(); j++)
                        roomItemsString += (tempRoom.getRoomItems().get(j).getItemID() + ":");
                }

                Element roomItems = doc.createElement("roomItems");
                roomItems.appendChild(doc.createTextNode(roomItemsString));
                roomInfoRoot.appendChild(roomItems);
            }

            DateTimeFormatter datFor = DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss");
            LocalDateTime current = LocalDateTime.now();
            String date = datFor.format(current);

            TransformerFactory transFact = TransformerFactory.newInstance();
            Transformer trans = transFact.newTransformer();
            DOMSource domSc = new DOMSource(doc);
            StreamResult strRes = new StreamResult(new File(dataFile + date + ".xml"));
            fullFileName = "Data" + date;
            trans.transform(domSc, strRes);
            return "Game Saved! " + fullFileName;
        }
        catch(Exception e)
        {
            return "";
        }
    }

    public static Player loadData(String fileName)
    {
        Player savedPlayer = null;

        try
        {
            File itemInfo = new File("GameSaves/" + fileName + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(itemInfo);

            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            NodeList playerNode = doc.getElementsByTagName("player");
            NodeList roomNode = doc.getElementsByTagName("room");


            String playerName = "";
            int playerAttack = 0;
            int playerCurrentStress = 0;
            int availableStorage = 0;
            Item playerEquipedItem = null;
            Item playerStorageItem = null;
            Item playerHeldItem = null;
            Rooms currentRoom = null;
            ArrayList<Item> carriedItems = new ArrayList<>();
            String weaponId = "";
            String storageId = "";
            String heldId = "";
            String roomID = "";
            String carriedIDs = "";

            for(int i = 0; i < playerNode.getLength(); i++)
            {
                Node playNode = playerNode.item(i);

                if (playNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) playerNode;

                    playerName = eElement.getElementsByTagName("playerName").item(0).getTextContent();
                    playerCurrentStress = Integer.parseInt(eElement.getElementsByTagName("playerCurrentStress").item(0).getTextContent());
                    playerAttack = Integer.parseInt(eElement.getElementsByTagName("monsterAttackPhrase").item(0).getTextContent());
                    availableStorage = Integer.parseInt(eElement.getElementsByTagName("availableStorage").item(0).getTextContent());
                    weaponId = eElement.getElementsByTagName("playerEquippedItem").item(0).getTextContent();
                    storageId = eElement.getElementsByTagName("playerStorageItem").item(0).getTextContent();
                    heldId = eElement.getElementsByTagName("playerHeldItem").item(0).getTextContent();
                    roomID = eElement.getElementsByTagName("playerCurrentRoom").item(0).getTextContent();
                    carriedIDs= eElement.getElementsByTagName("playerCarriedItems").item(0).getTextContent().toUpperCase();

                }
            }

            if(!weaponId.equalsIgnoreCase("DNE"))
                playerEquipedItem = Item.allItems.get(weaponId);

            if(!storageId.equalsIgnoreCase("DNE"))
                playerStorageItem = Item.allItems.get(storageId);

            if(!heldId.equalsIgnoreCase("DNE"))
                playerHeldItem = Item.allItems.get(heldId);

            currentRoom = Rooms.rooms.get(roomID);

            if(!carriedIDs.equalsIgnoreCase("DNE"))
            {
                for(int i = 0; i < carriedIDs.length(); i++)
                {
                    String tempID = "";
                    if(carriedIDs.charAt(i) == ':')
                    {
                        carriedItems.add(Item.allItems.get(tempID));
                        tempID = "";
                    }
                    else
                    {
                        tempID += carriedIDs.charAt(i);
                    }
                }
            }

            savedPlayer = new Player(playerName, playerCurrentStress, playerAttack, playerEquipedItem, playerStorageItem, playerHeldItem, currentRoom, carriedItems, availableStorage);

            for(int i = 0; i < roomNode.getLength(); i++)
            {
                Node rNode = roomNode.item(i);

                if (rNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) playerNode;

                    String rmID = "";
                    String isVisited = "";
                    String roomPuzzle = "";
                    boolean roomLocked = false;
                    ArrayList<Item> roomItems = new ArrayList<>();

                    rmID = eElement.getAttribute("roomID");
                    isVisited = eElement.getElementsByTagName("isVisited").item(0).getTextContent();
                    roomPuzzle = eElement.getElementsByTagName("roomPuzzle").item(0).getTextContent();
                    String tempRoomLocked = eElement.getElementsByTagName("roomLocked").item(0).getTextContent();
                    String roomItemIds = eElement.getElementsByTagName("roomItems").item(0).getTextContent();

                    if(tempRoomLocked.equalsIgnoreCase("true"))
                        roomLocked = true;

                    if(!roomItemIds.equalsIgnoreCase("DNE"))
                    {
                        for(int j = 0; j < roomItemIds.length(); j++)
                        {
                            String tempID = "";
                            if(roomItemIds.charAt(j) == ':')
                            {
                                roomItems.add(Item.allItems.get(tempID));
                                tempID = "";
                            }
                            else
                            {
                                tempID += roomItemIds.charAt(j);
                            }
                        }
                    }

                    Rooms modifiedRoom = Rooms.rooms.get(roomID);
                    modifiedRoom.setRoomVisited(isVisited);
                    modifiedRoom.setRoomPuzzleID(roomPuzzle);
                    modifiedRoom.setRoomLocked(roomLocked);
                    modifiedRoom.setRoomItems(roomItems);

                }
            }


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
