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
	private String roomBoard;
	private String roomLocked;
	private String[] roomConnections;
	private ArrayList<Item> roomItems = new ArrayList<Item>();
	public static HashMap<String, Rooms> rooms = new HashMap<>();
	
	//constructor with no parameters
	public Rooms() 
	{
		
	}
	public Rooms(String roomDescription, String roomVisited, String roomPuzzleID, String roomBoard, String roomLocked, String[] roomConnections) 
	{
		this.roomDescription = roomDescription;
		this.roomVisited = roomVisited;
		this.roomPuzzleID = roomPuzzleID;
		this.roomBoard = roomBoard;
		this.roomLocked = roomLocked;
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

	public void setRoomPuzzleID(String roomPuzzleID) {
		this.roomPuzzleID = roomPuzzleID;
	}

	public String[] getRoomConnections() {
		return roomConnections;
	}

	public void setRoomConnections(String[] roomConnections) {
		this.roomConnections = roomConnections;
	}

	public ArrayList<Item> getRoomItems() {
		return roomItems;
	}
	
	public void setRoomItems(ArrayList<Item> roomItems) {
		this.roomItems = roomItems;
	}
	
	public String getRoomBoard() {
		return roomBoard;
	}
	public void setRoomBoard(String roomBoard) {
		this.roomBoard = roomBoard;
	}
	public String getRoomLocked() {
		return roomLocked;
	}
	public void setRoomLocked(String roomLocked) {
		this.roomLocked = roomLocked;
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
			System.out.println("Item successfully added to room.");
		}
		else
		{
			System.out.println("Fail to add item to room.");
		}
	}
	
	public void removeFromRoom(String value, Item item)
	{
		roomItems.remove(item);
		System.out.println("Item successfully removed from room.");
	}
	
	public void look() {
		getRoomDescription().toString();
	}
	
	public void changeRooms(String value)
	{
		for(String a : roomConnections)
		{
			if(a.equalsIgnoreCase(value))
			{
				System.out.println(getRoomDescription());
			}
		}
	}
	/*
	public String toString() 
	{
	     return roomDescription;
	 }
	*/
	 //Method Reads Rooms XML file
	public static HashMap<String, Rooms> readRoomsXML()
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
					String vendingItem = e.getElementsByTagName("connections").item(0).getTextContent();
					String puzzleID = e.getElementsByTagName("puzzleID").item(0).getTextContent();
					String roomBoard = e.getElementsByTagName("board").item(0).getTextContent();
					String roomLocked = e.getElementsByTagName("board").item(0).getTextContent();
					String connections = e.getElementsByTagName("connections").item(0).getTextContent();
					String[] connectionsArray = connections.split(":");
					
					rooms.put(id, new Rooms(description, visited, puzzleID, roomBoard, roomLocked, connectionsArray));
				}
				return rooms;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nWrong Input. Try Again.\n");
		}
		return rooms;
	}
}
