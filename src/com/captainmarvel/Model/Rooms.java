package com.captainmarvel.Model;

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
	private String roomVisited;
	private String roomPuzzleID;
	private String[] roomConnections;
	private String vendingItem;
	private static ArrayList<String> inventory;
	private static Scanner input = new Scanner(System.in);
	private String value;
	
	//constructor with no parameters
	Rooms()
	{
		
	}
	
	public Rooms(String roomID, String roomDescription, String roomVisited, String roomPuzzleID, String[] roomConnections) 
	{
		super();
		this.roomID = roomID;
		this.roomDescription = roomDescription;
		this.roomVisited = roomVisited;
		this.roomPuzzleID = roomPuzzleID;
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

	public String getRoomVisited() {
		return roomVisited;
	}

	public void setRoomVisited(String roomVisited) {
		this.roomVisited = roomVisited;
	}

	public String getRoomPuzzleID() {
		return roomPuzzleID;
	}

	public static void setRoomPuzzleID(String roomPuzzleID) {
		roomPuzzleID = roomPuzzleID;
	}

	public String[] getRoomConnections() {
		return roomConnections;
	}

	public void setRoomConnections(String[] roomConnections) {
		this.roomConnections = roomConnections;
	}

	public String getVendingItem() {
		return vendingItem;
	}

	public static void setVendingItem(String vendingItem) {
		vendingItem = vendingItem;
	}

	public static ArrayList<String> getInventory() {
		return inventory;
	}

	public static void setInventory(ArrayList<String> inventory) {
		Rooms.inventory = inventory;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void storeItem(String value)
	{	
		if(!getVendingItem().equalsIgnoreCase("NONE"))
		{
			inventory.add(value);
			System.out.println("Item added to your inventory.");
		}
		else
		{
			throw new ItemsNotAvailableException(itemName);
		}
	}
	
	public void removeItem(String value)
	{
		if(inventory.contains(value))
		{
			inventory.remove(value);
			System.out.println("Item removed from your inventory.");
		}
	}
	public void puzzleIn()
	{
		if(!getRoomPuzzleID().equalsIgnoreCase("NONE"))
		{
			
		}
	}
	
	//Method Reads Rooms XML file
	public static void readRoomsXML(HashMap<String, Rooms> rooms)
	{
		File xml = new File("Rooms.xml");
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		try {
			b = f.newDocumentBuilder();
			Document d = b.parse(xml);
			d.getDocumentElement();
			
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
					String vendingItem = e.getElementsByTagName("connections").item(0).getTextContent();
					setVendingItem(vendingItem);
					String puzzleID = e.getElementsByTagName("puzzleID").item(0).getTextContent();
					setRoomPuzzleID(puzzleID);
					String connections = e.getElementsByTagName("connections").item(0).getTextContent();
					String[] connectionsArray = connections.split(":");
					
					rooms.put(id, new Rooms(id, description, visited, puzzleID, connectionsArray));
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nWrong Input. Try Again.\n");
		}
	}
}
