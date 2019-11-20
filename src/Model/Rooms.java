package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.File;
import java.util.*;

public class Rooms 
{
	private String roomID;
	private String roomDescription;
	private String vendingItem;
	private String roomVisited;
	private String roomPuzzleID;
	private String roomBoard;
	private boolean roomLocked;
	private String roomLockedDes;
	private ArrayList<String> roomConnections;
	private ArrayList<Item> roomItems = new ArrayList<Item>();
	public static HashMap<String, Rooms> rooms = new HashMap<String, Rooms>();
	
	
	
	public Rooms(String roomDescription, String vendingItem, String roomVisited, String roomPuzzleID,
			String roomBoard, boolean roomLocked, String roomLockedDes, ArrayList<String> roomConnections) {
		this.roomID = roomID;
		this.roomDescription = roomDescription;
		this.vendingItem = vendingItem;
		this.roomVisited = roomVisited;
		this.roomPuzzleID = roomPuzzleID;
		this.roomBoard = roomBoard;
		this.roomLocked = roomLocked;
		this.roomLockedDes = roomLockedDes;
		this.roomConnections = roomConnections;
	}
	
	public String getRoomID() {
		return roomID;
	}


	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}


	public String getRoomDescription() {
		return roomDescription;
	}


	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}


	public String getVendingItem() {
		return vendingItem;
	}


	public void setVendingItem(String vendingItem) {
		this.vendingItem = vendingItem;
	}


	public String getRoomVisited() {
		return roomVisited;
	}


	public void setRoomVisited(String roomVisited) {
		this.roomVisited = roomVisited;
	}


	public String getRoomPuzzleID() {
		return roomPuzzleID;
	}


	public void setRoomPuzzleID(String roomPuzzleID) {
		this.roomPuzzleID = roomPuzzleID;
	}


	public String getRoomBoard() {
		return roomBoard;
	}


	public void setRoomBoard(String roomBoard) {
		this.roomBoard = roomBoard;
	}


	public boolean isRoomLocked() {
		return roomLocked;
	}


	public void setRoomLocked(boolean roomLocked) {
		this.roomLocked = roomLocked;
	}


	public String getRoomLockedDes() {
		return roomLockedDes;
	}


	public void setRoomLockedDes(String roomLockedDes) {
		this.roomLockedDes = roomLockedDes;
	}


	public ArrayList<String> getRoomConnections() {
		return roomConnections;
	}


	public void setRoomConnections(ArrayList<String> roomConnections) {
		this.roomConnections = roomConnections;
	}


	public ArrayList<Item> getRoomItems() {
		return roomItems;
	}


	public void setRoomItems(ArrayList<Item> roomItems) {
		this.roomItems = roomItems;
	}


	public static HashMap<String, Rooms> getRooms() {
		return rooms;
	}


	public static void setRooms(HashMap<String, Rooms> rooms) {
		Rooms.rooms = rooms;
	}


	public void visitedRoom(String value)
	{
		if(getRoomID().equalsIgnoreCase(value))
		{
			setRoomVisited("false");
		}
	}
	
	public void addToRoom(String value, Item item)
	{	
		if(item.inventory.contains(value))
		{
			roomItems.add(item);
		}
		else
		{
			throw new NotInRoomException();
		}
	}
	
	public void removeFromRoom(Item item)
	{
		roomItems.remove(item);
	}
	
	public Rooms look() {
		return rooms.get(roomID);
	}
	
	public void changeRooms(String value)
	{
		if(roomLocked == false)
		{
			for(String a : roomConnections)
			{
				if(a.equalsIgnoreCase(value))
				{
					getRoomDescription();
				}
			}
		}
		else
		{
			throw new LockedRoomException();
		}
	}

	 //Method Reads Rooms XML file
	public static void readRoomsXML()
	{
		File xml = new File("Rooms.xml");
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		try {
			b = f.newDocumentBuilder();
			Document d = b.parse(xml);
			d.getDocumentElement();
			d.getDocumentElement().normalize();
			
			NodeList roomsNodes = d.getElementsByTagName("Room");
			for(int i = 0; i < roomsNodes.getLength(); i++)
			{
				Node node = roomsNodes.item(i);

				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element e = (Element) node;
					
					String id = e.getElementsByTagName("id").item(0).getTextContent();
					String visited = e.getElementsByTagName("visited").item(0).getTextContent();
					String description = e.getElementsByTagName("description").item(0).getTextContent();
					String vendingItem = e.getElementsByTagName("vendingItem").item(0).getTextContent();
					String puzzleID = e.getElementsByTagName("puzzleID").item(0).getTextContent();
					String roomBoard = e.getElementsByTagName("board").item(0).getTextContent();
					String locked = e.getElementsByTagName("locked").item(0).getTextContent();
					boolean roomLocked = Boolean.valueOf(locked);
					String roomLockedDes = e.getElementsByTagName("lockedDes").item(0).getTextContent();
					String connections = e.getElementsByTagName("connections").item(0).getTextContent();
					String[] connectionsArray = connections.split(":");
					ArrayList<String> connect = new ArrayList<String>(Arrays.asList(connectionsArray));
					
					rooms.put(id, new Rooms(description, vendingItem, visited, puzzleID,
						 roomBoard, roomLocked, roomLockedDes, connect));
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nWrong Input. Try Again.\n");
		}
	}
}

