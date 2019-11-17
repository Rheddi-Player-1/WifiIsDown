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
	private String[] roomConnections;
	public static HashMap<String, String> roomIn;
	public static HashMap<String, String> roomItem;
	public static HashMap<String, Rooms> rooms = new HashMap<>();
	
	//constructor with no parameters
	public Rooms() 
	{
		
	}
	public Rooms(String roomDescription, String roomVisited, String roomPuzzleID, String roomBoard, String[] roomConnections) 
	{
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

	public void setRoomPuzzleID(String roomPuzzleID) {
		this.roomPuzzleID = roomPuzzleID;
	}

	public String[] getRoomConnections() {
		return roomConnections;
	}

	public void setRoomConnections(String[] roomConnections) {
		this.roomConnections = roomConnections;
	}

	public static HashMap<String, String> getRoomItem() {
		return roomItem;
	}

	public void setRoomItem(HashMap<String,String> roomItem) {
		this.roomItem = roomItem;
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
			roomItem.put(getRoomID(), value);
			System.out.println("Item successfully added to room.");
		}
		else
		{
			System.out.println("Fail to add item to room.");
		}
	}
	
	public void removeFromRoom(String value, Item item)
	{
		
		if(roomItem.containsKey(getRoomID()))
		{
			roomItem.remove(value);
			System.out.println("Item successfully removed from room.");
		}
		else
		{
			System.out.println("Fail to remove item from room.");
		}
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
					String connections = e.getElementsByTagName("connections").item(0).getTextContent();
					String[] connectionsArray = connections.split(":");
					
					rooms.put(id, new Rooms(description, visited, puzzleID, roomBoard, connectionsArray));
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
